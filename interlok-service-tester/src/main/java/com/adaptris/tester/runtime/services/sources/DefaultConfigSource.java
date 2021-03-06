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

package com.adaptris.tester.runtime.services.sources;

import com.adaptris.annotation.ComponentProfile;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Extension of {@link FileSource} that simply defaults to
 * {@code file:///${service.tester.working.directory}/src/main/interlok/config/adapter.xml}.
 * 
 * 
 * @service-test-config default-config-file-source
 */
@XStreamAlias("default-config-file-source")
@ComponentProfile(since = "3.9.3")
public class DefaultConfigSource extends FileSource {

  private static final String DEFAULT_SRC = "file:///${service.tester.working.directory}/src/main/interlok/config/adapter.xml";

  public DefaultConfigSource(){
    super();
    setFile(DEFAULT_SRC);
  }

}
