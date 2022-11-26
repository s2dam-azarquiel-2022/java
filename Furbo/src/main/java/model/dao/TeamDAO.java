package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entity.Team;

public class TeamDAO {
  public static ArrayList<Team> getAll(
    Connection connection,
    String season
  ) {
    ArrayList<Team> result = new ArrayList<>();
    try {
      PreparedStatement stmt = connection.prepareStatement("""
        SELECT e.codequipo,
               e.nombre,
               e.presidente,
               e.entrenador,
               e.añofundacion,
               e.estadio,
               e.sitioweb
        FROM equipo e
        WHERE e.codequipo IN (
          SELECT t.equipo
          FROM equipotemporada t
          WHERE t.temporada = ?
        )
      """);
      stmt.setString(1, season);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        result.add(new Team(
          rs.getInt("codequipo"),
          rs.getString("nombre"),
          rs.getString("presidente"),
          rs.getString("entrenador"),
          rs.getInt("añofundacion"),
          rs.getString("estadio"),
          rs.getString("sitioweb")
        ));
      }
      rs.close();
      stmt.close();
    } catch (SQLException e) { e.printStackTrace(); }
    return result;
  }
}
