package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SeasonDAO {
  public static ArrayList<String> getAll(
    Connection connection
  ) throws SQLException {
    ArrayList<String> result = new ArrayList<>();
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
    return result;
  }

  public static String getMostRecent(
    Connection connection
  ) throws SQLException {
    String result = null;
    PreparedStatement stmt = connection.prepareStatement("""
      SELECT MAX(t.codtemp) as mostRecent
      FROM temporada t
    """);
    ResultSet rs = stmt.executeQuery();
    rs.next();
    result = rs.getString("mostRecent");
    rs.close();
    stmt.close();
    return result;
  }
}
