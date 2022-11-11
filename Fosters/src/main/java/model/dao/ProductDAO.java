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
        SELECT p.id,
               p.titulo,
               p.imagen,
               p.body,
               pt.stars,
               pt.reviews
        FROM producto p
        LEFT JOIN (
          SELECT ROUND(AVG(puntos), 0) as stars,
                 COUNT(puntos) as reviews,
                 idproducto
          FROM punto
          GROUP BY idproducto
        ) pt ON p.id = pt.idproducto
        WHERE categoriaid LIKE ?
        ORDER BY COALESCE(pt.stars, 0) DESC,
                 COALESCE(pt.reviews, 0) DESC
      """);
      stmt.setString(1, category);
      ResultSet resultSet = stmt.executeQuery();
      while (resultSet.next()) {
        result.add(new Product(
          resultSet.getInt("id"),
          resultSet.getString("titulo"),
          resultSet.getString("imagen"),
          resultSet.getString("body"),
          resultSet.getInt("stars"),
          resultSet.getInt("reviews")
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
