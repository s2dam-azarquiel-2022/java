package model;

import java.util.List;

public class Daily {
  private String summary;
  private String icon;
  private List<Time> data;
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
  public List<Time> getData() {
    return data;
  }
  public void setData(List<Time> data) {
    this.data = data;
  }
}
