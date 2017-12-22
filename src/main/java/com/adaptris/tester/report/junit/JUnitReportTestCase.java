package com.adaptris.tester.report.junit;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;

/**
 * Child of {@link JUnitReportTestSuite} for storing results.
 *
 * The intention of class and classes in the hierarchy is to produce JUnit style XML.
 *
 * <p>When tests are executed via {@link com.adaptris.tester.runtime.TestCase#execute(String, com.adaptris.tester.runtime.clients.TestClient, com.adaptris.tester.runtime.services.ServiceToTest)}
 * the results are produced as this corresponding class.</p>
 *
 * @junit-config testcase
 */
@XStreamAlias("testcase")
public class JUnitReportTestCase {


  @XStreamAsAttribute
  private final String name;
  @XStreamAsAttribute
  private String classname;
  @XStreamAsAttribute
  private double time;

  @XStreamImplicit
  private List<JUnitReportTestIssue> issue;

  public JUnitReportTestCase(final String name){
    this.name = name;
  }

  /**
   * Helper method that checks if the item in the list of  {@link JUnitReportTestIssue} is instance of {@link JUnitReportFailure}
   * @return true if {@link JUnitReportTestIssue} is of type {@link JUnitReportFailure}
   */
  public boolean isFailure(){
    return issue != null && issue.get(0) instanceof JUnitReportFailure;
  }

  /**
   * Helper method that checks if the item in the list of  {@link JUnitReportTestIssue} is instance of {@link JUnitReportError}
   * @return true if {@link JUnitReportTestIssue} is of type {@link JUnitReportError}
   */
  public boolean isError(){
    return issue != null && issue.get(0) instanceof JUnitReportError;
  }

  /**
   * Helper method that checks if the item in the list of {@link JUnitReportTestIssue} is instance of {@link JUnitReportSkipped}
   * @return true if {@link JUnitReportTestIssue} is of type {@link JUnitReportSkipped}
   */
  public boolean isSkipped(){
    return issue != null && issue.get(0) instanceof JUnitReportSkipped;
  }

  /**
   * Get test case name
   * @return Test case name
   */
  public String getName() {
    return name;
  }

  /**
   * Set test case issue, this includes lazy initialisation of list of {@link JUnitReportTestIssue} due to output requirements.
   *
   * NOTE: There is only ever one item in the list of {@link JUnitReportTestIssue} and null values will be ignored.
   * @param failure Test case issue
   */
  public void setTestIssue(JUnitReportTestIssue failure) {
    if(failure != null) {
      issue = new ArrayList<>();
      this.issue.add(failure);
    }
  }

  /**
   * Get test case issue
   * @return Test case issue
   */
  public List<JUnitReportTestIssue> getIssue() {
    return issue;
  }

  /**
   * Set test case time
   * @param time Test case time
   */
  public void setTime(double time) {
    this.time = time;
  }

  /**
   * Get test case time
   * @return Test case time
   */
  public double getTime() {
    return time;
  }

  /**
   * Set test case classname
   * @param classname Test case classname
   */
  public void setClassname(String classname) {
    this.classname = classname;
  }

  /**
   * Get test case classname
   * @return Test case classname
   */
  public String getClassname() {
    return classname;
  }
}
