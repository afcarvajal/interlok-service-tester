package com.adaptris.tester.runtime.services.sources;

import com.adaptris.annotation.MarshallingCDATA;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 * @service-test-config inline-source
 */
@XStreamAlias("inline-source")
public class InlineSource implements Source {

  @MarshallingCDATA
  public String xml;

  @Override
  public String getSource() {
    return getXml();
  }

  public void setXml(String xml) {
    this.xml = xml;
  }

  public String getXml() {
    return xml;
  }


}
