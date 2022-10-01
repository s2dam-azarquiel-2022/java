package dao;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import model.Incidence;

public class Infocar extends DefaultHandler {
  private ArrayList<Incidence> list;
  private Incidence current;
  private String currentTag;

  public Infocar(ArrayList<Incidence> list) {
    this.list = list;
  }

  @Override
  public void startDocument() throws SAXException {
  }

  @Override
  public void startElement(
    String uri,
    String localName,
    String qName,
    Attributes attributes
  ) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
    if (localName.equals("item")) {
      this.current = new Incidence();
    }
    this.currentTag = localName;
  }

  @Override
  public void endElement(
    String uri,
    String localName,
    String qName
  ) throws SAXException {
    super.endElement(uri, localName, qName);
    if (localName.equals("item")) {
      list.add(current);
      this.current = null;
    }
  }

  private static String parse(char[] ch, int start, int length) {
    return String.valueOf(ch).substring(start, start+length);
  }

  @Override
  public void characters(
    char[] ch,
    int start,
    int length
  ) throws SAXException {
    if (this.current == null) return;

    if (this.currentTag.equals("title")) {
      this.current.title += parse(ch, start, length);
    } else if (this.currentTag.equals("link")) {
      this.current.link += parse(ch, start, length);
    } else if (this.currentTag.equals("pubDate")) {
      this.current.pubDate += parse(ch, start, length);
    } else if (this.currentTag.equals("description")) {
      this.current.description += parse(ch, start, length);
    }
  }

  private static XMLReader getXMLReader() {
    SAXParserFactory parser = SAXParserFactory.newInstance();
    parser.setNamespaceAware(true);

    XMLReader result = null;
    try {
      result = parser.newSAXParser().getXMLReader();
    } catch (SAXException e) {
      e.printStackTrace();
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    }

    return result;
  }

  private static InputSource getInputSource(String url) {
    InputSource result = null;
    try {
      result = new InputSource(new URL(url).openStream());
    } catch (IOException e) {
      e.printStackTrace();
    }

    return result;
  }

  public static ArrayList<Incidence> getIncidences(String region) {
    ArrayList<Incidence> result = new ArrayList<Incidence>();

    XMLReader doc = getXMLReader();
    Infocar parser = new Infocar(result);
    doc.setContentHandler(parser);
    doc.setErrorHandler(parser);

    try {
      doc.parse(getInputSource(
        "https://infocar.dgt.es/etraffic/rss_ca_" + region + ".xml"
      ));
    } catch (IOException | SAXException e) {
      e.printStackTrace();
    }

    return result;
  }
}
