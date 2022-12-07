package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entity.City;
import model.entity.CitySelectOption;

public class CityDAO {
  public static ArrayList<City> get(
    Connection connection
  ) throws SQLException {
    ArrayList<City> result = new ArrayList<>();
    PreparedStatement stmt = connection.prepareStatement("""
      SELECT
        mapa as map,
        link as link,
        descripcion as description,
        imagen as image,
        nombre as name,
        id as id
      FROM ciudad c
    """);
    ResultSet rs = stmt.executeQuery();
    while (rs.next()) {
      result.add(new City(
        rs.getString("map"),
        rs.getString("link"),
        rs.getString("description"),
        rs.getString("image"),
        rs.getString("name"),
        rs.getInt("id")
      ));
    }
    rs.close();
    stmt.close();
    return result;
  }

  public static ArrayList<CitySelectOption> getSelectOptions(
    Connection connection
  ) throws SQLException {
    ArrayList<CitySelectOption> result = new ArrayList<>();
    PreparedStatement stmt = connection.prepareStatement("""
      SELECT
        nombre as name,
        id as id
      FROM ciudad c
      WHERE (
        SELECT COUNT(id)
        FROM ruta r
        WHERE r.ciudad = c.id
      ) > 0
    """);
    ResultSet rs = stmt.executeQuery();
    while (rs.next()) {
      result.add(new CitySelectOption (
        rs.getString("name"),
        rs.getInt("id")
      ));
    }
    rs.close();
    stmt.close();
    return result;
  }
}
