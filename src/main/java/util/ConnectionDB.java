package util;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionDB {

    public static Connection conn() throws SQLException{

        try{
            InitialContext cxt = new InitialContext();
            DataSource connection = (DataSource) cxt.lookup( "java:/comp/env/jdbc/postgres" );

            if ( connection == null )
                throw new Exception("Data source not found!");

            return connection.getConnection();
        } catch (Exception e) {
            System.out.println("Connection pool error:\n\t" + e.getMessage());
        return null;
        }
    }
}
