package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PointDAO {
  public static void addReview(
    Connection connection,
    int productID,
    int stars
  ) {
    try {
      PreparedStatement stmt = connection.prepareStatement("""
        INSERT INTO punto
        (puntos, idproducto)
        VALUES (?, ?)
      """);
      stmt.setInt(1, stars);
      stmt.setInt(2, productID);
      stmt.execute();
      stmt.close();
    } catch (SQLException e) {
      System.out.println("addReview() error");
      e.printStackTrace();
    }
  }
}
