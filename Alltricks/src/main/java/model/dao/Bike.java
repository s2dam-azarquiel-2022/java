package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.Utils;

public class Bike {
  private final static String[] allowedOrderFields =  { "precio", "marca" };
  private final static String[] allowedOrders = { "asc", "desc" };

  public static ArrayList<model.entity.Bike> getBikes(
    String brand,
    Connection connection,
    String fieldToOrder,
    String order
  ) {
    ArrayList<model.entity.Bike> result = new ArrayList<>();
    try {
      PreparedStatement stmt = connection.prepareStatement(
        "SELECT * FROM bici WHERE marca LIKE ?" +
        (
          ( Utils.includes(allowedOrderFields, fieldToOrder)
            && Utils.includes(allowedOrders, order) )
          ? String.format(" ORDER BY %s %s", fieldToOrder, order)
          : ""
        )
      );
      stmt.setString(1, brand);
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
