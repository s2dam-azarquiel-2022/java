package model;

import java.util.List;

public class Data {
  private String type;
  private String name;
  private int required_age;
  private boolean is_free;
  private String detailed_description;
  private String short_description;
  private String supported_languages;
  private String header_image;
  private String website;
  private Requirements pc_requirements;
  private Requirements mac_requirements;
  private Requirements linux_requirements;
  private List<String> developers;
  private List<String> publishers;
  private Price price_overview;
  private Platforms platforms;
  private List<Category> categories;
  private List<Category> genres;
  private List<Screenshot> screenshots;

  public String getType() {
    return type.toUpperCase();
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public int getRequired_age() {
    return required_age;
  }
  public void setRequired_age(int required_age) {
    this.required_age = required_age;
  }
  public boolean getIs_free() {
    return is_free;
  }
  public void setIs_free(boolean is_free) {
    this.is_free = is_free;
  }
  public String getDetailed_description() {
    return detailed_description;
  }
  public void setDetailed_description(String detailed_description) {
    this.detailed_description = detailed_description;
  }
  public String getShort_description() {
    return short_description;
  }
  public void setShort_description(String short_description) {
    this.short_description = short_description;
  }
  public String getSupported_languages() {
    return supported_languages;
  }
  public void setSupported_languages(String supported_languages) {
    this.supported_languages = supported_languages;
  }
  public String getHeader_image() {
    return header_image;
  }
  public void setHeader_image(String header_image) {
    this.header_image = header_image;
  }
  public String getWebsite() {
    return website;
  }
  public void setWebsite(String website) {
    this.website = website;
  }
  public Requirements getPc_requirements() {
    return pc_requirements;
  }
  public void setPc_requirements(Requirements pc_requirements) {
    this.pc_requirements = pc_requirements;
  }
  public Requirements getMac_requirements() {
    return mac_requirements;
  }
  public void setMac_requirements(Requirements mac_requirements) {
    this.mac_requirements = mac_requirements;
  }
  public Requirements getLinux_requirements() {
    return linux_requirements;
  }
  public void setLinux_requirements(Requirements linux_requirements) {
    this.linux_requirements = linux_requirements;
  }
  public List<String> getDevelopers() {
    return developers;
  }
  public void setDevelopers(List<String> developers) {
    this.developers = developers;
  }
  public List<String> getPublishers() {
    return publishers;
  }
  public void setPublishers(List<String> publishers) {
    this.publishers = publishers;
  }
  public Price getPrice_overview() {
    return price_overview;
  }
  public void setPrice_overview(Price price_overview) {
    this.price_overview = price_overview;
  }
  public Platforms getPlatforms() {
    return platforms;
  }
  public void setPlatforms(Platforms platforms) {
    this.platforms = platforms;
  }
  public List<Category> getCategories() {
    return categories;
  }
  public void setCategories(List<Category> categories) {
    this.categories = categories;
  }
  public List<Category> getGenres() {
    return genres;
  }
  public void setGenres(List<Category> genres) {
    this.genres = genres;
  }
  public List<Screenshot> getScreenshots() {
    return screenshots;
  }
  public void setScreenshots(List<Screenshot> screenshots) {
    this.screenshots = screenshots;
  }
}
