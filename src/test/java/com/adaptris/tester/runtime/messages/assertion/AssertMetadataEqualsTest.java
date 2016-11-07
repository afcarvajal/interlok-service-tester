package com.adaptris.tester.runtime.messages.assertion;

import com.adaptris.tester.runtime.messages.TestMessage;
import com.adaptris.util.KeyValuePairSet;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class AssertMetadataEqualsTest extends AssertionCase{

  public AssertMetadataEqualsTest(String name) {
    super(name);
  }

  @Test
  public void testExecute() throws Exception {
    Map<String, String> expected = new HashMap<>();
    expected.put("key1", "val1");
    Map<String, String> actual = new HashMap<>();
    MetadataAssertion matcher = new AssertMetadataEquals();
    matcher.setMetadata(new KeyValuePairSet(expected));
    assertFalse(matcher.execute(new TestMessage(actual,"")).isPassed());
    actual.put("key1", "val1");
    assertTrue(matcher.execute(new TestMessage(actual,"")).isPassed());
    actual.put("key1", "valother");
    assertFalse(matcher.execute(new TestMessage(actual,"")).isPassed());
    expected.remove("key1");
    expected.put("key3", "val3");
    assertFalse(matcher.execute(new TestMessage(actual,"")).isPassed());
  }

  @Test
  public void testExpected(){
    assertEquals("Metadata: {key1=val1}", createAssertion().expected());
  }

  @Test
  public void testGetMessage(){
    AssertionResult result  = createAssertion().execute(new TestMessage());
    assertEquals("Assertion Failure: [assert-metadata-equals]", result.getMessage());
  }

  @Override
  protected Assertion createAssertion() {
    Map<String, String> expected = new HashMap<>();
    expected.put("key1", "val1");
    return new AssertMetadataEquals(expected);
  }
}