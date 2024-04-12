package util;

public class CredentialsConection {
    private  String jdbcURL = "jdbc:postgresql://localhost:5432/CRUDEmployees";
    private String jdbcUsername = "postgres";
    private String jdbcPassword = "Amary0929";
    public String getJdbcURL(){
        return jdbcURL;
    }
    public String getJdbcUsername(){
        return jdbcUsername;
    }
    public String getJdbcPassword(){
        return jdbcPassword;
    }
}
