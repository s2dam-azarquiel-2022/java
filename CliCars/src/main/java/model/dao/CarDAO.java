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
  public final static String[] allowedOrderFieldsLongName =  {
    "Nombre", "Modelo", "Año", "Kilometros", "Caballos"
  };
  public final static String[] allowedOrders = { "asc", "desc" };
  public final static String[] allowedOrdersLongNames = {
    "ascendente", "descendente"
  };

  public static ArrayList<Car> getCars(
    Connection connection,
    String brand,
    String fieldToOrder,
    String order,
    String search
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
          AND (
               c.nombre LIKE ?
            OR c.modelo LIKE ?
          )
      """ + (
        ( fieldToOrder != null && order != null )
        ? String.format(" ORDER BY %s %s", fieldToOrder, order)
        : ""
      ));
      stmt.setString(1, brand);
      String searchVal = search == null ? "%" : "%" + search + "%";
      stmt.setString(2, searchVal);
      stmt.setString(3, searchVal);
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
      System.out.println("getCars() error");
      e.printStackTrace();
    }
    return result;
  }

  public static void toggleFavorite(Connection connection, int carID) {
    try {
      PreparedStatement stmt = connection.prepareStatement("""
        UPDATE coche c
        SET    fav = (
          SELECT
            CASE WHEN fav > 0
            THEN 0
            ELSE 1
            END AS toggledFav
          FROM coche
          WHERE id = ?
        )
        WHERE  c.id = ?
      """);
      stmt.setInt(1, carID);
      stmt.setInt(2, carID);
      stmt.execute();
    } catch (SQLException e) {
      System.out.println("toggleFavorite() error");
      e.printStackTrace();
    }
  }
}
