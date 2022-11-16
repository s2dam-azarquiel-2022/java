package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entity.Car;

public class CarDAO {
  public final static String[] allowedOrderFields =  {
    "nombre", "modelo", "anio", "km", "cv"
  };
  public final static String[] allowedOrders = { "asc", "desc" };
  public final static String[] allowedOrdersLongNames = {
    "ascendente", "descendente"
  };

  public static ArrayList<Car> getCars(
    Connection connection,
    String brand,
    String fieldToOrder,
    String order
  ) {
    ArrayList<Car> result = new ArrayList<>();
    try {
      PreparedStatement stmt = connection.prepareStatement("""
        SELECT c.nombre,
               c.modelo,
               c.marca,
               c.id,
               c.precio,
               c.precioAntes,
               c.anio,
               c.km,
               c.cv,
               c.fav,
               c.foto
        FROM coche c
        WHERE c.marca LIKE ?
      """ + (
        ( fieldToOrder != null && order != null )
        ? String.format(" ORDER BY %s %s", fieldToOrder, order)
        : ""
      ));
      stmt.setString(1, brand);
      ResultSet resultSet = stmt.executeQuery();
      while (resultSet.next()) {
        result.add(new Car(
          resultSet.getString("nombre"),
          resultSet.getString("modelo"),
          resultSet.getInt("marca"),
          resultSet.getInt("id"),
          resultSet.getInt("precio"),
          resultSet.getInt("precioAntes"),
          resultSet.getInt("anio"),
          resultSet.getInt("km"),
          resultSet.getInt("cv"),
          resultSet.getInt("fav") == 1,
          resultSet.getString("foto")
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
