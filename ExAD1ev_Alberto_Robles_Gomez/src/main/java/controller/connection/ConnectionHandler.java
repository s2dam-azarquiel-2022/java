package controller.connection;

import java.sql.Connection;
import java.sql.SQLException;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;
import view.PageUtils;

public class ConnectionHandler {
  public static Connection connect() throws SQLException {
    OracleConnectionPoolDataSource pool = null;
    Connection connection = null;
    pool = new OracleConnectionPoolDataSource();
    // TODO change db url
    pool.setURL(System.getenv(String.format("%s_DB_URL", PageUtils.pageName)));
    connection = pool.getConnection();

    return connection;
  }

  public static void close(Connection connection) throws SQLException {
    connection.close();
  }
}
