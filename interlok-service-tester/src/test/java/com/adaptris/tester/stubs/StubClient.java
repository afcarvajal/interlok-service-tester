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

package com.adaptris.tester.stubs;

import java.io.IOException;
import com.adaptris.tester.runtime.ServiceTestConfig;
import com.adaptris.tester.runtime.ServiceTestException;
import com.adaptris.tester.runtime.clients.TestClient;
import com.adaptris.tester.runtime.messages.TestMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("stub-test-client")
public class StubClient implements TestClient {
  @Override
  public StubClient init(ServiceTestConfig config) throws ServiceTestException {
    return this;
  }

  @Override
  public TestMessage applyService(String xml, TestMessage message) throws ServiceTestException {
    return message;
  }

  @Override
  public void close() throws IOException {

  }
}
