package dao;

import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

import model.AppList;

public class Main {
  private AppList applist;

  public AppList getApplist() {
    return applist;
  }
  public void setApplist(AppList applist) {
    this.applist = applist;
  }

  public static Main getMain() {
    try {
      return new Gson().fromJson(
        IOUtils.toString(
          new URL("http://www.ies-azarquiel.es/paco/apisteam/game"),
          "utf-8"
        ),
        Main.class
      );
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
