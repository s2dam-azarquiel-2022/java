package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Bike {
  public static ArrayList<model.entity.Bike> getBikes(
    String brand,
    Connection connection
  ) {
    ArrayList<model.entity.Bike> result = new ArrayList<>();
    try {
      PreparedStatement stmt = connection.prepareStatement(
        "SELECT * FROM bici WHERE marca LIKE ?"
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
