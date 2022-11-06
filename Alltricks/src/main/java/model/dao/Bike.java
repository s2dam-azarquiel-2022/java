package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Bike {
  public final static String[] allowedOrderFields =  { "Precio", "Marca" };
  public final static String[] allowedOrders = { "asc", "desc" };
  public final static String[] allowedOrdersLongNames = {
    "ascendente", "descendente"
  };

  public static ArrayList<model.entity.Bike> getBikes(
    Connection connection,
    String brand,
    String fieldToOrder,
    String order
  ) {
    ArrayList<model.entity.Bike> result = new ArrayList<>();
    try {
      PreparedStatement stmt = connection.prepareStatement(
        "SELECT * FROM bici WHERE marca LIKE ?" +
        (
          ( fieldToOrder != null && order != null )
          ? String.format(" ORDER BY %s %s", fieldToOrder, order)
          : ""
        )
      );
      stmt.setString(1, brand == null ? "%" : brand);
      ResultSet resultSet = stmt.executeQuery();
      while (resultSet.next()) {
        result.add(new model.entity.Bike(
          resultSet.getInt("id"),
          resultSet.getString("foto"),
          resultSet.getInt("marca"),
          resultSet.getString("descripcion"),
          resultSet.getFloat("precio"),
          resultSet.getInt("fav") != 0
        ));
      }
      resultSet.close();
      stmt.close();
    } catch (SQLException e) {
      System.out.println("getBikes() error");
      e.printStackTrace();
    }

    return result;
  }
}
