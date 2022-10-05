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
  private String link;
  private String currentContent = "";

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
  }

  @Override
  public void endElement(
    String uri,
    String localName,
    String qName
  ) throws SAXException {
    super.endElement(uri, localName, qName);
    if (this.current == null) {
      if (localName.equals("link")) {
        this.link = this.currentContent;
      }
    } else {
      switch (localName) {
      case "item":
        this.list.add(this.current);
        this.current = null;
        break;
      case "title":
        this.current.title = this.currentContent;
        break;
      case "link":
        this.current.link = this.currentContent;
        break;
      case "pubDate":
        this.current.pubDate = this.currentContent;
        break;
      case "description":
        String val = this.currentContent;
        this.current.description += val.startsWith("img src") ?
          val.substring(0, 9) + this.link + val.substring(9) :
          val;
        break;
      }
    }
    this.currentContent = "";
  }

  @Override
  public void characters(
    char[] ch,
    int start,
    int length
  ) throws SAXException {
    this.currentContent += String.valueOf(ch, start, length);
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

  public static final String[] CAS = {
    "Andalucia", "Aragón", "Asturias", "Islas Baleares", "Islas Canarias",
    "Cantabria","Castilla y León", "Castilla la Mancha", "Cataluña","Valencia",
    "Extremadura", "Galicia", "Madrid", "Murcia", "Navarra", "Pais Vasco",
    "La Rioja", "Ceuta", "Melilla"
  };

  public static String getCAName(String id) {
    if (id == null) {
      return "Select a CA";
    } else {
      return CAS[Integer.valueOf(id)-1];
    }
  }
}
