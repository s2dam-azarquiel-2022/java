package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entity.Joke;

public class JokeDAO {
  public static ArrayList<Joke> get(
    Connection connection
  ) throws SQLException {
    ArrayList<Joke> result = new ArrayList<>();
    PreparedStatement stmt = connection.prepareStatement("""
      SELECT
        id id,
        idcategoria categoryID,
        titulo title,
        descripcion desc,
        apodo nickname
      FROM chiste c
      WHERE c.idcategoria LIKE '%'
    """);
    ResultSet rs = stmt.executeQuery();
    while (rs.next()) {
      result.add(new Joke(
        rs.getInt("id"),
        rs.getInt("categoryID"),
        rs.getString("title"),
        rs.getString("desc"),
        rs.getString("nickname")
      ));
    }
    rs.close();
    stmt.close();
    return result;
  }
}
