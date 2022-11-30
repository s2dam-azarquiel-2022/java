package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entity.Player;
import model.entity.Position;
import model.entity.PositionPlayers;

public class PlayerDAO {
  public static ArrayList<PositionPlayers> getAll(
    Connection connection
  ) throws SQLException {
    ArrayList<PositionPlayers> result = new ArrayList<>();
    String query = """
      SELECT j.codjugador,
             j.nombre,
             j.puestohabitual
      FROM jugador j
      WHERE j.puestohabitual = ?
    """;
    ArrayList<Position> positions = PositionDAO.getAll(connection);
    for (Position position : positions) {
      PreparedStatement stmt = connection.prepareStatement(query);
      stmt.setInt(1, position.id);
      ResultSet rs = stmt.executeQuery();
      ArrayList<Player> players = new ArrayList<>();
      while (rs.next()) {
        players.add(new Player(
          rs.getInt("codjugador"),
          rs.getString("nombre"),
          rs.getInt("puestohabitual")
        ));
      }
      result.add(new PositionPlayers(
        position,
        players
      ));
      rs.close();
      stmt.close();
    }
    return result;
  }

}
