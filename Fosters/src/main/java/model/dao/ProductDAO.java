package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entity.Product;

public class ProductDAO {
  public static ArrayList<Product> getProducts(
    Connection connection,
    String category
  ) {
    ArrayList<Product> result = new ArrayList<>();
    try {
      PreparedStatement stmt = connection.prepareStatement("""
        SELECT *
        FROM producto
        WHERE categoriaid LIKE ?
        ORDER BY(
          SELECT SUM(puntos)
          FROM punto
          WHERE idproducto = producto.id
        ) DESC
      """);
      stmt.setString(1, category);
      ResultSet resultSet = stmt.executeQuery();
      while (resultSet.next()) {
        result.add(new Product(
          resultSet.getInt("id"),
          resultSet.getInt("categoriaid"),
          resultSet.getString("body"),
          resultSet.getString("titulo"),
          resultSet.getString("sumario"),
          resultSet.getString("fondo"),
          resultSet.getString("imagen")
        ));
      }
      resultSet.close();
      stmt.close();
    } catch (SQLException e) {
      System.out.println("getProducts() error");
      e.printStackTrace();
    }
    return result;
  }
}
