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

package com.adaptris.tester.runtime;

import com.adaptris.tester.STExampleConfigCase;
import com.adaptris.tester.runtime.services.sources.Source;
import com.adaptris.util.GuidGenerator;

public abstract class TCCase extends STExampleConfigCase {

  /**
   * Key in unit-test.properties that defines where example goes unless overriden {@link #setBaseDir(String)}.
   *
   */
  public static final String BASE_DIR_KEY = "TestCase.baseDir";

  public TCCase(String name) {
    super(name);
    if (PROPERTIES.getProperty(BASE_DIR_KEY) != null) {
      setBaseDir(PROPERTIES.getProperty(BASE_DIR_KEY));
    }
  }

  protected TestCase createBaseTestCase(){
    TestCase testCase = new TestCase();
    GuidGenerator guidGenerator = new GuidGenerator();
    testCase.setUniqueId(guidGenerator.getUUID());
    return testCase;
  }

}
