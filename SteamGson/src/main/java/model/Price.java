package model;

public class Price {
  private String initial_formatted;
  private String final_formatted;
  private int discount_percent;

  public String getInitial_formatted() {
    return initial_formatted;
  }
  public void setInitial_formatted(String initial_formatted) {
    this.initial_formatted = initial_formatted;
  }
  public String getFinal_formatted() {
    return final_formatted;
  }
  public void setFinal_formatted(String final_formatted) {
    this.final_formatted = final_formatted;
  }
  public int getDiscount_percent() {
    return discount_percent;
  }
  public void setDiscount_percent(int discount_percent) {
    this.discount_percent = discount_percent;
  }
}
