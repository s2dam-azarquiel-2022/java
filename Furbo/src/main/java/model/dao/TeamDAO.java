package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entity.DivisionTeams;
import model.entity.Team;

public class TeamDAO {
  public static ArrayList<DivisionTeams> getAll(
    Connection connection,
    String season
  ) throws SQLException {
    ArrayList<DivisionTeams> result = new ArrayList<>();
    String query = """
      SELECT *
      FROM (
        SELECT
          e.codequipo,
          e.nombre,
          e.presidente,
          e.entrenador,
          e.añofundacion,
          e.estadio,
          e.sitioweb,
          ( SELECT SUM(
              CASE WHEN visitante = e.codequipo
              THEN (
                CASE WHEN golesVisitante > golesLocal
                THEN 3
                ELSE (CASE WHEN golesVisitante = golesLocal THEN 1 ELSE 0 END)
                END
              ) ELSE (
                CASE WHEN local = e.codequipo
                THEN (
                  CASE WHEN golesLocal > golesVisitante
                  THEN 3
                  ELSE (CASE WHEN golesLocal = golesVisitante THEN 1 ELSE 0 END)
                  END
                )
                ELSE 0
                END
              )
              END
            )
            FROM partido p
            WHERE temporada = ?
        ) as points
        FROM equipo e
        WHERE e.codequipo IN (
          SELECT t.equipo
          FROM equipotemporada t
          WHERE t.temporada = ?
            AND t.division = %s
        )
      ) e
      ORDER BY e.points DESC
    """;
    for (int i = 1; i <= 3; i++) {
      PreparedStatement stmt = connection.prepareStatement(
        String.format(query, i)
      );
      stmt.setString(1, season);
      stmt.setString(2, season);
      ResultSet rs = stmt.executeQuery();
      ArrayList<Team> teams = new ArrayList<>();
      while (rs.next()) {
        teams.add(new Team(
          rs.getInt("codequipo"),
          rs.getString("nombre"),
          rs.getString("presidente"),
          rs.getString("entrenador"),
          rs.getInt("añofundacion"),
          rs.getString("estadio"),
          rs.getString("sitioweb"),
          rs.getInt("points")
        ));
      }
      result.add(new DivisionTeams(i, teams));
      rs.close();
      stmt.close();
    }
    return result;
  }
}
