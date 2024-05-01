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
        try {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:postgresql://localhost:5432/CRUDEmployees");
            config.setUsername("postgrest");
            config.setPassword("Amary0929");
            config.setMaximumPoolSize(10);
            HikariDataSource dataSource = new HikariDataSource(config);
            ConnectionPool.setDataSource(dataSource);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
        

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            DataSource dataSource = ConnectionPool.getDataSource();
            if(dataSource instanceof HikariDataSource){
                HikariDataSource hikariDataSource = (HikariDataSource) dataSource;
                hikariDataSource.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

