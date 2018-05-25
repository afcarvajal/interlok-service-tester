/*
    Copyright 2018 Adaptris Ltd.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/

package com.adaptris.tester.runtime.messages.assertion;

import com.adaptris.core.fs.FsHelper;
import com.adaptris.fs.FsException;
import com.adaptris.fs.FsWorker;
import com.adaptris.fs.NioWorker;
import com.adaptris.tester.runtime.ServiceTestException;
import com.adaptris.tester.runtime.messages.TestMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Checks if {@link com.adaptris.tester.runtime.messages.TestMessage#getPayload()} equals file contents.
 *
 * <p>Assertions are used to validate the returned message is expected.</p>
 *
 * @service-test-config assert-payload-equals-file
 */
@XStreamAlias("assert-payload-equals-file")
public class AssertPayloadEqualsFile implements Assertion {

  private String uniqueId;
  private String file;

  private transient FsWorker fsWorker = new NioWorker();

  public AssertPayloadEqualsFile(){
  }

  public AssertPayloadEqualsFile(String file){
    setFile(file);
  }

  @Override
  public void setUniqueId(String uniqueId) {
    this.uniqueId = uniqueId;
  }

  @Override
  public String getUniqueId() {
    return uniqueId;
  }

  public String getFile() {
    return file;
  }

  public void setFile(String file) {
    this.file = file;
  }

  /**
   * Checks if {@link com.adaptris.tester.runtime.messages.TestMessage#getPayload()} equals contents of {@link #getFile()}.
   * @param actual Message resulting from text execution
   * @return Return result of assertion using {@link AssertionResult}
   */
  @Override
  public final AssertionResult execute(TestMessage actual) throws ServiceTestException {
    try {
      URL url = FsHelper.createUrlFromString(file, true);
      File fileToRead = FsHelper.createFileReference(url);
      final byte[] fileContents = fsWorker.get(fileToRead);
      return checkResults(actual.getPayload(), new String(fileContents));
    } catch (IOException | URISyntaxException | FsException e) {
      throw new ServiceTestException(e);
    }
  }

  public AssertionResult checkResults(String actual, String expected) throws ServiceTestException {
    return new AssertionResult(getUniqueId(), "assert-payload-equals-file", expected.equals(actual));
  }

  @Override
  public String expected() {
    return "Payload file: " + getFile();
  }

  @Override
  public boolean showReturnedMessage() {
    return true;
  }
}
