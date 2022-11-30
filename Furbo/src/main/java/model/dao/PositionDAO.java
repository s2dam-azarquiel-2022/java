package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entity.Position;

public class PositionDAO {
  public static ArrayList<Position> getAll(
    Connection connection
  ) throws SQLException {
    ArrayList<Position> result = new ArrayList<>();
    PreparedStatement stmt = connection.prepareStatement("""
      SELECT p.codpuesto, p.descripcion
      FROM puesto p
    """);
    ResultSet rs = stmt.executeQuery();
    while (rs.next()) {
      result.add(new Position(
        rs.getInt("codpuesto"),
        rs.getString("descripcion")
      ));
    }
    rs.close();
    stmt.close();
    return result;
  }
}
