package dao;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import model.Time;

public class OpenWeatherApp extends DefaultHandler {
  private ArrayList<Time> list;
  private Time current;

  public OpenWeatherApp(ArrayList<Time> list) {
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
    if (localName.equals("time")) {
      this.current = new Time();
      this.current.dateStart = attributes.getValue("from");
      this.current.dateEnd = attributes.getValue("to");

      this.current.dateStart =
        this.current.dateStart.substring(0, 10) + ' ' +
        this.current.dateStart.substring(11);

      this.current.dateEnd =
        this.current.dateEnd.substring(0, 10) + ' ' +
        this.current.dateEnd.substring(11);
    } else if (localName.equals("symbol")) {
      this.current.description = attributes.getValue("name");
      this.current.icon = attributes.getValue("var");
    } else if (localName.equals("precipitation")) {
      this.current.precipitation = 100 * Float.valueOf(
        attributes.getValue("probability")
      );
    } else if (localName.equals("temperature")) {
      this.current.temperature = toCelsius(Float.valueOf(
        attributes.getValue("value")
      ));
    }
  }

  @Override
  public void endElement(
    String uri,
    String localName,
    String qName
  ) throws SAXException {
    super.endElement(uri, localName, qName);
    if (localName.equals("time")) { list.add(current); }
  }

  @Override
  public void characters(
    char[] ch,
    int start,
    int length
  ) throws SAXException {
  }

  private static float toCelsius(float kelvin) {
    return kelvin - 273.15F;
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

  private static String getAppID(Path appidPath) {
    // Gets the app ID from the passed file
    String result = null;
    try {
      result = Files.readString(appidPath);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return result;
  }

  public static ArrayList<Time> getForecast(Path appidPath, String city, String country) {
    ArrayList<Time> result = new ArrayList<Time>();

    XMLReader doc = getXMLReader();
    OpenWeatherApp parser = new OpenWeatherApp(result);
    doc.setContentHandler(parser);
    doc.setErrorHandler(parser);

    try {
      doc.parse(getInputSource(
        "http://api.openweathermap.org/data/2.5/forecast" +
        "?mode=xml" +
        "&lang=sp" +
        "&APPID=" + getAppID(appidPath) +
        "&q=" + city + "," + country
      ));
    } catch (IOException | SAXException e) {
      e.printStackTrace();
    }

    return result;
  }
}
