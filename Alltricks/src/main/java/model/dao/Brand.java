package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Brand {
  public static ArrayList<model.entity.Brand> getBrands(Connection connection) {
    PreparedStatement stmt = null;
    ArrayList<model.entity.Brand> result = new ArrayList<>();
    try {
      stmt = connection.prepareStatement(
        "SELECT * FROM marca"
      );
      ResultSet resultSet = stmt.executeQuery();
      while (resultSet.next()) {
        result.add(new model.entity.Brand(
          resultSet.getInt("id"),
          resultSet.getString("nombre")
        ));
      }
      resultSet.close();
      stmt.close();
    } catch (SQLException e) {
      System.out.println("getBrands() error");
      e.printStackTrace();
    }

    return result;
  }
}
