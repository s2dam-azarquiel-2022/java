package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entity.Category;

public class CategoryDAO {
  public static ArrayList<Category> getCategories(
    Connection connection
  ) {
    ArrayList<Category> result = new ArrayList<>();
    try {
      PreparedStatement stmt = connection.prepareStatement(
        "SELECT * FROM categoria"
      );
      ResultSet resultSet = stmt.executeQuery();
      while (resultSet.next()) {
        result.add(new Category(
          resultSet.getInt("id"),
          resultSet.getString("guarnicion"),
          resultSet.getString("descripcion"),
          resultSet.getString("nombre")
        ));
      }
      resultSet.close();
      stmt.close();
    } catch (SQLException e) {
      System.out.println("getCategories() error");
      e.printStackTrace();
    }
    return result;
  }
}
