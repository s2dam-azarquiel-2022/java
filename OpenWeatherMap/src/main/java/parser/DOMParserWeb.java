package parser;

import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DOMParserWeb {
  public static Document parse(String url) {
    InputSource inputSource = null;
    try {
      inputSource = new InputSource(new URL(url).openStream());
    } catch (IOException e) {
      e.printStackTrace();
    }

    DocumentBuilder builder = null;
    try {
      builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    }

    Document result = null;
    try {
      result = builder.parse(inputSource);
    } catch (SAXException | IOException e) {
      e.printStackTrace();
    }

    return result;
  }
}
