package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SeasonDAO {
  public static ArrayList<String> getAll(
    Connection connection
  ) {
    ArrayList<String> result = new ArrayList<>();
    try {
      PreparedStatement stmt = connection.prepareStatement("""
        SELECT t.codtemp
        FROM temporada t
      """);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        result.add(rs.getString("codtemp"));
      }
      rs.close();
      stmt.close();
    } catch (SQLException e) { e.printStackTrace(); }
    return result;
  }

  public static String getMostRecent(
    Connection connection
  ) {
    String result = null;
    try {
      PreparedStatement stmt = connection.prepareStatement("""
        SELECT MAX(t.codtemp) as mostRecent
        FROM temporada t
      """);
      ResultSet rs = stmt.executeQuery();
      rs.next();
      result = rs.getString("mostRecent");
      rs.close();
      stmt.close();
    } catch (SQLException e) { e.printStackTrace(); }
    return result;
  }
}
