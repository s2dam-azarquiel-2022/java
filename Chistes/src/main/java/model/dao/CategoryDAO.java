package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entity.Category;

public class CategoryDAO {
  public static ArrayList<Category> get(
    Connection connection
  ) throws SQLException {
    ArrayList<Category> result = new ArrayList<>();
    PreparedStatement stmt = connection.prepareStatement("""
      SELECT
        c.id as id,
        c.nombre as name
      FROM categoria c
    """);
    ResultSet rs = stmt.executeQuery();
    while (rs.next()) {
      result.add(new Category(
        rs.getInt("id"),
        rs.getString("name")
      ));
    }
    rs.close();
    stmt.close();
    return result;
  }
}
