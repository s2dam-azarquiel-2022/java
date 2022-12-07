package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entity.Reviews;
import model.entity.Route;

public class RouteDAO {
  public static ArrayList<Route> get(
    Connection connection,
    String city
  ) throws SQLException {
    ArrayList<Route> result = new ArrayList<>();
    PreparedStatement stmt = connection.prepareStatement("""
      SELECT
        r.link as link,
        r.descripcion as description,
        r.imagen as image,
        r.nombre as name,
        r.ciudad as city,
        r.id as id,
        pt.stars as stars,
        pt.nReviews as nReviews
      FROM ruta r
      LEFT JOIN (
        SELECT
          ROUND(AVG(puntos), 0) as stars,
          COUNT(puntos) as nReviews,
          ruta as id
        FROM punto
        GROUP BY ruta
      ) pt ON r.id = pt.id
      WHERE r.ciudad LIKE ?
      ORDER BY COALESCE(pt.stars, 0) DESC,
               COALESCE(pt.nReviews, 0) DESC
    """);
    stmt.setString(1, city);
    ResultSet rs = stmt.executeQuery();
    while (rs.next()) {
      result.add(new Route(
        rs.getString("link"),
        rs.getString("description"),
        rs.getString("image"),
        rs.getString("name"),
        rs.getString("city"),
        rs.getInt("id"),
        new Reviews(
          rs.getInt("stars"),
          rs.getInt("nReviews")
        )
      ));
    }
    rs.close();
    stmt.close();
    return result;
  }

  public static void addReview(
    Connection con,
    int routeID,
    int stars
  ) throws SQLException {
     PreparedStatement stmt = con.prepareStatement("""
      INSERT INTO punto
      (puntos, ruta)
      VALUES (?, ?)
    """);
    stmt.setInt(1, stars);
    stmt.setInt(2, routeID);
    stmt.execute();
    stmt.close();
  }
}
