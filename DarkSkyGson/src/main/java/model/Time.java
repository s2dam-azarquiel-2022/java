package model;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;

public class Time {
  private String summary;
  private String icon;
  private long time;
  private int precipIntesity;
  private float precipProbability;
  private float temperature;
  private float humidity;
  private float pressure;
  private float windSpeed;
  private float cloudCover;
  private float visibility;

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getIcon() {
    return String.format(
      "https://darksky.net/images/weather-icons/%s.png",
      icon
    );
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public String getTime() {
    return new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.GERMANY)
      .format(Date.from(Instant.ofEpochSecond(time)));
  }

  public void setTime(long time) {
    this.time = time;
  }

  public int getPrecipIntesity() {
    return precipIntesity;
  }

  public void setPrecipIntesity(int precipIntesity) {
    this.precipIntesity = precipIntesity;
  }

  public float getPrecipProbability() {
    return precipProbability;
  }

  public void setPrecipProbability(float precipProbability) {
    this.precipProbability = precipProbability;
  }

  public String getTemperature() {
    return String.format("%.2f", (temperature - 32) * 5 / 9);
  }

  public void setTemperature(float temperature) {
    this.temperature = temperature;
  }

  public float getHumidity() {
    return humidity;
  }

  public void setHumidity(float humidity) {
    this.humidity = humidity;
  }

  public float getPressure() {
    return pressure;
  }

  public void setPressure(float pressure) {
    this.pressure = pressure;
  }

  public float getWindSpeed() {
    return windSpeed;
  }

  public void setWindSpeed(float windSpeed) {
    this.windSpeed = windSpeed;
  }

  public float getCloudCover() {
    return cloudCover;
  }

  public void setCloudCover(float cloudCover) {
    this.cloudCover = cloudCover;
  }

  public float getVisibility() {
    return visibility;
  }

  public void setVisibility(float visibility) {
    this.visibility = visibility;
  }

}
