package dao;

import models.Employee;
import util.CredentialsConection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    CredentialsConection credentialsConection = new CredentialsConection();

    private static final String INSERT_EMPLEADO_SQL = "INSERT INTO empleado (nombre, apellido, salario) VALUES (?, ?, ?)";
    private static final String SELECT_EMPLEADO_BY_ID = "SELECT id, nombre, apellido, salario FROM empleado WHERE id = ?";
    private static final String SELECT_ALL_EMPLEADOS = "SELECT * FROM empleado";
    private static final String DELETE_EMPLEADO_SQL = "DELETE FROM empleado WHERE id = ?";
    private static final String UPDATE_EMPLEADO_SQL = "UPDATE empleado SET nombre = ?, apellido = ?, salario = ? WHERE id = ?";

    public EmployeeDAO() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(credentialsConection.getJdbcURL(), credentialsConection.getJdbcUsername(), credentialsConection.getJdbcPassword());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertEmployee(Employee employee) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLEADO_SQL);
        preparedStatement.setString(1, employee.getNombre());
        preparedStatement.setString(2, employee.getApellido());
        preparedStatement.setDouble(3, employee.getSalario());
        preparedStatement.executeUpdate();

    }

    public Employee getEmployeeById(int id) {
        Employee employee = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLEADO_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                double salario = rs.getDouble("salario");
                employee = new Employee(id, nombre, apellido, salario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public List<Employee> getAllEmployee() {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLEADOS)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                double salario = rs.getDouble("salario");
                employees.add(new Employee(id, nombre, apellido, salario));
            }
            connection.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public boolean deleteEmployee(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_EMPLEADO_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateEmployee(Employee employee) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_EMPLEADO_SQL)) {
            statement.setString(1, employee.getNombre());
            statement.setString(2, employee.getApellido());
            statement.setDouble(3, employee.getSalario());
            statement.setInt(4, employee.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}
