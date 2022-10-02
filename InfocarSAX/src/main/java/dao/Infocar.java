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
  private String link;

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

  @Override
  public void characters(
    char[] ch,
    int start,
    int length
  ) throws SAXException {
    if (this.current == null) {
      if (this.currentTag.equals("link")) {
        this.link = String.valueOf(ch, start, length);
      }
      return;
    }

    if (this.currentTag.equals("title")) {
      this.current.title += String.valueOf(ch, start, length);
    } else if (this.currentTag.equals("link")) {
      this.current.link += String.valueOf(ch, start, length);
    } else if (this.currentTag.equals("pubDate")) {
      this.current.pubDate += String.valueOf(ch, start, length);
    } else if (this.currentTag.equals("description")) {
      String val = String.valueOf(ch, start, length);
      this.current.description += val.startsWith("img src") ?
        val.substring(0, 9) + this.link + val.substring(9) :
        val;
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
