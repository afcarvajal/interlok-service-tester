package com.adaptris.tester.runtime;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.adaptris.annotation.MarshallingCDATA;
import com.adaptris.core.util.DocumentBuilderFactoryBuilder;
import com.adaptris.core.util.XmlHelper;
import com.adaptris.util.KeyValuePairSet;
import com.adaptris.util.text.xml.SimpleNamespaceContext;
import com.adaptris.util.text.xml.XPath;

/**
 * Abstract class providing common functionality and configuration to its extenders.
 */
public abstract class XpathCommon {

  @MarshallingCDATA
  private String xpath;
  private KeyValuePairSet namespaceContext;

  /**
   *
   * @param document
   * @return
   * @throws XpathCommonException
   */
  protected final Node selectSingleNode(Node document) throws XpathCommonException {
    try {
      NamespaceContext ctx = SimpleNamespaceContext.create(getNamespaceContext());
      XPath xpathUtils = new XPath(ctx);
      Node node = xpathUtils.selectSingleNode(document, getXpath());
      if (node == null){
        throw new XpathCommonException(String.format("xpath [%s] didn't return a match", getXpath()));
      }
      return node;
    } catch (XPathExpressionException e) {
      throw new XpathCommonException("Failed to make xpath query", e);
    }
  }

  /**
   *
   * @param input
   * @return
   * @throws XpathCommonException
   */
  protected final Node selectSingleNode(String input) throws XpathCommonException {
    try {
      return selectSingleNode(XmlHelper.createDocument(input, new DocumentBuilderFactoryBuilder()));
    } catch (ParserConfigurationException | IOException | SAXException e) {
      throw new XpathCommonException("Failed to make xpath query", e);
    }
  }

  /**
   * Utility class that returns a String of a Node
   * @param node node to transform
   * @return string of node
   * @throws XpathCommonException wraps thrown exceptions
   */
  protected final String nodeToString(Node node) throws XpathCommonException {
    StringWriter sw = new StringWriter();
    try {
      Transformer t = TransformerFactory.newInstance().newTransformer();
      t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
      t.transform(new DOMSource(node), new StreamResult(sw));
    } catch (TransformerException e) {
      throw new XpathCommonException("Failed to convert node to string", e);
    }
    return sw.toString();
  }

  protected final boolean selectSingleBoolean(String input) throws XpathCommonException {
    javax.xml.xpath.XPath xpath = com.adaptris.util.text.xml.XPath.newXPathFactory().newXPath();
    NamespaceContext ctx = SimpleNamespaceContext.create(getNamespaceContext());
    if(ctx != null) {
      xpath.setNamespaceContext(ctx);
    }
    try {
      XPathExpression xpr = xpath.compile(getXpath());
      return (Boolean)xpr.evaluate(XmlHelper.createDocument(input, new DocumentBuilderFactoryBuilder()), XPathConstants.BOOLEAN);
    } catch (XPathExpressionException | SAXException | ParserConfigurationException | IOException e) {
      throw new XpathCommonException("Failed to execute xpath", e);
    }
  }

  /**
   * Returns the xpath to use.
   * @return the xpath
   */
  public String getXpath() {
    return xpath;
  }

  /**
   * Sets the xpath to use.
   * @param xpath the xpath
   */
  public void setXpath(String xpath) {
    this.xpath = xpath;
  }

  /**
   * @return the namespaceContext
   */
  public KeyValuePairSet getNamespaceContext() {
    return namespaceContext;
  }

  /**
   * Set the namespace context for resolving namespaces.
   * <ul>
   * <li>The key is the namespace prefix</li>
   * <li>The value is the namespace uri</li>
   * </ul>
   *
   * @param kvps the namespace context
   * @see SimpleNamespaceContext#create(KeyValuePairSet)
   */
  public void setNamespaceContext(KeyValuePairSet kvps) {
    this.namespaceContext = kvps;
  }
}
