package dao;

import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

import model.Data;

public class Detail {
  private Data data;

  public Data getData() {
    return data;
  }
  public void setData(Data data) {
    this.data = data;
  }

  public static Detail getDetail(String appID) throws IOException {
    String json = IOUtils.toString(
      new URL("https://store.steampowered.com/api/appdetails/?appids=" + appID),
      "utf-8"
    ).replaceAll("\\[\\]", "NULL");
    return new Gson().fromJson(
      json.substring(json.indexOf('{', 1), json.length()-1),
      Detail.class
    );
  }
}
