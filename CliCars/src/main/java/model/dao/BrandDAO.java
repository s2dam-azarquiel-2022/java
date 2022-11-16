package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entity.Brand;

public class BrandDAO {
  public static ArrayList<Brand> getBrands(
    Connection connection
  ) {
    ArrayList<Brand> result = new ArrayList<>();
    try {
      PreparedStatement stmt = connection.prepareStatement("""
        SELECT m.nombre, m.id
        FROM marca m
      """);
      ResultSet resultSet = stmt.executeQuery();
      while (resultSet.next()) {
        result.add(new Brand(
          resultSet.getString("nombre"),
          resultSet.getInt("id")
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
