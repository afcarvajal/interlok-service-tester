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

package com.adaptris.tester.runtime.messages;

import com.adaptris.tester.STExampleConfigCase;
import org.junit.Before;

import java.util.HashMap;
import java.util.Map;

public abstract class MessagesCase extends STExampleConfigCase {

  public static final String BASE_DIR_KEY = "MessagesCase.baseDir";
  protected static final String METADATA_KEY = "key";
  protected static final String METADATA_VALUE = "value";
  protected static final String PAYLOAD = "payload";
  protected Map<String, String> metadata;

  public MessagesCase() {
    if (PROPERTIES.getProperty(BASE_DIR_KEY) != null) {
      setBaseDir(PROPERTIES.getProperty(BASE_DIR_KEY));
    }

  }

  @Before
  public  void setUp() throws Exception {
    metadata = new HashMap<>();
    metadata.put(METADATA_KEY, METADATA_VALUE);
  }
}
