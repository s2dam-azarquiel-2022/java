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
        c.apodo as nickname
      FROM chiste c
      WHERE c.idcategoria LIKE ?
    """);
    stmt.setString(1, category == -1 ? "%" : String.valueOf(category));
    ResultSet rs = stmt.executeQuery();
    while (rs.next()) {
      result.add(new Joke(
        rs.getInt("id"),
        rs.getInt("categoryID"),
        rs.getString("title"),
        rs.getString("description"),
        rs.getString("nickname")
      ));
    }
    rs.close();
    stmt.close();
    return result;
  }
}
