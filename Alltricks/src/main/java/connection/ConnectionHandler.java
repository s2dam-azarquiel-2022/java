package connection;

import java.sql.Connection;
import java.sql.SQLException;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

public class ConnectionHandler {
  public static Connection connect() {
    OracleConnectionPoolDataSource pool = null;
    Connection connection = null;
    try {
      pool = new OracleConnectionPoolDataSource();
      pool.setURL(System.getenv("ALLTRICKS_DB_URL"));
      connection = pool.getConnection();
    } catch (SQLException e) {
      System.out.println("Connection start error");
      e.printStackTrace();
    }

    return connection;
  }

  public static void close(Connection connection) {
    try { connection.close(); }
    catch (SQLException e) {
      System.out.println("Connection close error");
      e.printStackTrace();
    }
  }
}
