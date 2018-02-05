package com.adaptris.tester.runtime.messages.assertion;

import com.adaptris.util.KeyValuePairSet;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * Checks if all keys and corresponding values set in {@link #getMessageHeaders()} are not present in
 * {@link com.adaptris.tester.runtime.messages.TestMessage#getMessageHeaders()}.
 *
 * @service-test-config assert-metadata-not-equals
 */
@XStreamAlias("assert-metadata-not-equals")
public class AssertMetadataNotEquals extends MetadataAssertion {

  public AssertMetadataNotEquals(){
    super();
  }

  public AssertMetadataNotEquals(Map<String, String> metadata){
    super(new KeyValuePairSet(metadata));
  }

  @Override
  public AssertionResult execute(Map<String, String> actual) {
    String testType = "assert-metadata-not-equals";
    for(Map.Entry<String, String> entry :  actual.entrySet()){
      if((getMessageHeaders().containsKey(entry.getKey()) && getMessageHeaders().get(entry.getKey()).equals(entry.getValue()))){
        return new AssertionResult(getUniqueId(), testType, false);
      }
    }
    return new AssertionResult(getUniqueId(), "assert-metadata-not-equals", true);
  }
}
