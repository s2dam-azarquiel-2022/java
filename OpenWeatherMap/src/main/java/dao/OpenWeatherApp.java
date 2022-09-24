package dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import model.Time;
import parser.DOMParserWeb;

public class OpenWeatherApp {
  private static float toCelsius(float kelvin) {
    return kelvin - 273.15F;
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

  public static Time[] getForecast(Path appidPath, String city, String country) {
    Document doc = DOMParserWeb.parse(
      "http://api.openweathermap.org/data/2.5/forecast" +
      "?mode=xml" +
      "&lang=sp" +
      "&APPID=" + getAppID(appidPath) +
      "&q=" + city + "," + country
    );

    NodeList forecastNodes =
      ((Element) doc.getElementsByTagName("forecast").item(0))
      .getElementsByTagName("time");

    Element timeNode;
    Element symbolNode;

    Time[] result = new Time[forecastNodes.getLength()];
    for (int i = 0; i < forecastNodes.getLength(); i++) {
      timeNode = (Element) forecastNodes.item(i);
      symbolNode = (Element) timeNode.getElementsByTagName("symbol").item(0);

      result[i] = new Time(
        timeNode.getAttribute("from"),
        timeNode.getAttribute("to"),
        symbolNode.getAttribute("name"),
        symbolNode.getAttribute("var"),
        100 * Float.valueOf(
          ((Element) timeNode.getElementsByTagName("precipitation").item(0))
          .getAttribute("probability")
        ),
        toCelsius(Float.valueOf(
          ((Element) timeNode.getElementsByTagName("temperature").item(0))
          .getAttribute("value")
        ))
      );

      result[i].dateStart =
        result[i].dateStart.substring(0, 10) + ' ' +
        result[i].dateStart.substring(11);

      result[i].dateEnd =
        result[i].dateEnd.substring(0, 10) + ' ' +
        result[i].dateEnd.substring(11);
    }

    return result;
  }
}
