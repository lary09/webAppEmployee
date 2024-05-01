package util;

import java.sql.Connection;
import java.sql.SQLException;

public class ConectionDB {
    private final CredentialsConection credentialsConection;

    public ConectionDB(CredentialsConection credentialsConection) {
        this.credentialsConection = credentialsConection;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = ConnectionPool.getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
