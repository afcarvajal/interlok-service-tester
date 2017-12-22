package com.adaptris.tester.runtime.messages.assertion;

import com.adaptris.util.KeyValuePairSet;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 *
 * @service-test-config assert-metadata-equals
 */
@XStreamAlias("assert-metadata-equals")
public class AssertMetadataEquals extends MetadataAssertion {

  public AssertMetadataEquals(){
    super();
  }

  public AssertMetadataEquals(Map<String, String> metadata){
    super(new KeyValuePairSet(metadata));
  }

  @Override
  public AssertionResult execute(Map<String, String> actual) {
    return new AssertionResult(getUniqueId(), "assert-metadata-equals", getMessageHeaders().equals(actual));
  }
}
