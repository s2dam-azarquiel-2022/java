package view;

import dao.OpenWeatherApp;
import model.Time;

public class Test {
  public static void main(String[] args) {
    Time[] forecast = OpenWeatherApp.getForecast("Murcia", "es");
    for (Time time: forecast) {
      System.out.println(time);
    }
  }
}
