package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entity.Joke;

public class JokeDAO {
  public static ArrayList<Joke> get(
    Connection connection,
    int category
  ) throws SQLException {
    ArrayList<Joke> result = new ArrayList<>();
    PreparedStatement stmt = connection.prepareStatement("""
      SELECT
        c.id as id,
        c.idcategoria as categoryID,
        c.titulo as title,
        c.descripcion as description,
        c.apodo as nickname,
        pt.stars as stars,
        pt.reviews as reviews
      FROM chiste c
      LEFT JOIN (
        SELECT
          ROUND(AVG(puntos), 0) as stars,
          COUNT(puntos) as reviews,
          idchiste
        FROM punto
        GROUP BY idchiste
      ) pt ON c.id = pt.idchiste
      WHERE c.idcategoria LIKE ?
      ORDER BY COALESCE(pt.stars, 0) DESC,
               COALESCE(pt.reviews, 0) DESC
    """);
    stmt.setString(1, category == -1 ? "%" : String.valueOf(category));
    ResultSet rs = stmt.executeQuery();
    while (rs.next()) {
      result.add(new Joke(
        rs.getInt("id"),
        rs.getInt("categoryID"),
        rs.getString("title"),
        rs.getString("description"),
        rs.getString("nickname"),
        rs.getInt("stars"),
        rs.getInt("reviews")
      ));
    }
    rs.close();
    stmt.close();
    return result;
  }
}
