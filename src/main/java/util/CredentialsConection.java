package util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import javax.sql.DataSource;

public class CredentialsConection  implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/CRUDEmployees");
        config.setUsername("postgrest");
        config.setPassword("Amary0929");
        config.setMaximumPoolSize(10); // Adjust as needed
        HikariDataSource dataSource = new HikariDataSource(config);
        ConnectionPool.setDataSource(dataSource);
        }
        

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DataSource dataSource = ConnectionPool.getDataSource();
        if(dataSource instanceof HikariDataSource){
            HikariDataSource hikariDataSource = (HikariDataSource) dataSource;
            hikariDataSource.close();
        }
    }
}

