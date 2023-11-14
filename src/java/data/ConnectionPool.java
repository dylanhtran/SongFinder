package data;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ConnectionPool {

    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;
    private static final Logger LOG = Logger.getLogger(ConnectionPool.class.getName());

    private ConnectionPool() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/musicdb");
        } catch (NamingException e) {
            LOG.log(Level.SEVERE, "*** data source lookup fail", e);
        }
    }

    public static synchronized ConnectionPool getInstance() {
        if (pool == null) {
            pool = new ConnectionPool();
        }
        return pool;
    }

    public Connection getConnection() throws SQLException {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** get connection from datasource fail", e);

            return null;
        }
    }

    public void freeConnection(Connection c) throws SQLException {
        try {
            c.close();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** freeing connection fail", e);
        }
    }
}
