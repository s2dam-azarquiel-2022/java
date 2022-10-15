package dao;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

import model.Daily;
import model.Time;

public class DarkSky {
  private Time currently;
  private Daily daily;

  public Time getCurrently() {
    return currently;
  }

  public void setCurrently(Time currently) {
    this.currently = currently;
  }

  public Daily getDaily() {
    return daily;
  }

  public void setDaily(Daily daily) {
    this.daily = daily;
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

  public static DarkSky getForecasts(Path appiPath) {
    try {
      return new Gson().fromJson(
        IOUtils.toString(
          new URL(
            "https://api.darksky.net/forecast/" +
            getAppID(appiPath) +
            "/39.8710026,-4.0251675" +
            "?lang=es" +
            "&exclude=minutely,hourly,alerts,flags"
          ),
          "utf-8"
        ),
        DarkSky.class
      );
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
