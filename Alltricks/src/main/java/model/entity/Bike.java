package model.entity;

public class Bike {
  private int id;
  private String photo;
  private int brand;
  private String description;
  private float price;
  private boolean favorite;

  public Bike(
    int id, String photo, int brand, String description, float price,
    boolean favorite
  ) {
    this.id = id;
    this.photo = photo;
    this.brand = brand;
    this.description = description;
    this.price = price;
    this.favorite = favorite;
  }

  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getPhoto() {
    return photo;
  }
  public void setPhoto(String photo) {
    this.photo = photo;
  }
  public int getBrand() {
    return brand;
  }
  public void setBrand(int brand) {
    this.brand = brand;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public float getPrice() {
    return price;
  }
  public void setPrice(float price) {
    this.price = price;
  }
  public boolean isFavorite() {
    return favorite;
  }
  public void setFavorite(boolean favorite) {
    this.favorite = favorite;
  }
}
