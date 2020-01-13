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

package com.adaptris.tester.runtime.services.preprocessor;

import com.adaptris.core.util.DocumentBuilderFactoryBuilder;
import com.adaptris.core.util.XmlHelper;
import com.adaptris.tester.runtime.ServiceTestConfig;
import org.junit.Test;
import org.w3c.dom.Document;

import static org.junit.Assert.assertEquals;

public class WrapInServiceCollectionPreprocessorTest extends PreprocessorCase {

  private static final String XML = "<root><xml><id>blah</id></xml></root>";


  @Test
  public void testExecute() throws Exception {
    String result  = createPreprocessor().execute(XML, new ServiceTestConfig());
    Document document = XmlHelper.createDocument(result, new DocumentBuilderFactoryBuilder());
    assertEquals("service-collection", document.getDocumentElement().getNodeName());
  }

  @Override
  protected Preprocessor createPreprocessor() {
    return new WrapInServiceCollectionPreprocessor();
  }

  @Override
  public boolean isAnnotatedForJunit4() {
    return true;
  }
}
