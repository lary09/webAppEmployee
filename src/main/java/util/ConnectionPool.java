package util;
import javax.sql.DataSource;

public class ConnectionPool {
    private static DataSource dataSource;

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static void setDataSource(DataSource dataSource) {
        ConnectionPool.dataSource = dataSource;
    }
}
