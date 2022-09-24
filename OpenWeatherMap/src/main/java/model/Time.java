package model;

import java.text.DecimalFormat;

public class Time {
  public String dateStart;
  public String dateEnd;
  public String description;
  public String icon;
  public float precipitation;
  public float temperature;

  public Time(
    String dateStart,
    String dateEnd,
    String description,
    String icon,
    float precipitation,
    float temperature
  ) {
    this.dateStart = dateStart;
    this.dateEnd = dateEnd;
    this.description = description;
    this.icon = icon;
    this.precipitation = precipitation;
    this.temperature = temperature;
  }

  @Override
  public String toString() {
    return
      String.format(
        "From: %s to: %s\n" +
        "Description: %s\n" +
        "Icon: %s\n" +
        "Percentage: %s%%\n" +
        "Temperature: %s°C\n",
        dateStart,
        dateEnd,
        description,
        icon,
        precipitation,
        (new DecimalFormat("0.0")).format(temperature)
      );
  }
}
