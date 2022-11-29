package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entity.Player;

public class PlayerDAO {
  public static ArrayList<Player> getAll(
    Connection connection
  ) throws SQLException {
    ArrayList<Player> result = new ArrayList<>();
    try {
      PreparedStatement stmt = connection.prepareStatement("""
        SELECT j.codjugador,
               j.nombre,
               j.puestohabitual
        FROM jugador j
      """);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        result.add(new Player(
          rs.getInt("codjugador"),
          rs.getString("nombre"),
          rs.getInt("puestohabitual")
        ));
      }
      rs.close();
      stmt.close();
    } catch (SQLException e) { e.printStackTrace(); }
    return result;
  }
}
