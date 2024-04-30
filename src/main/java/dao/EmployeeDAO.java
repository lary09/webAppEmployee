package dao;

import models.Employee;
import util.ConectionDB;
import util.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    ConectionDB conectionDB = new ConectionDB();
    private static final String INSERT_EMPLEADO_SQL = "INSERT INTO empleado (nombre, apellido, salario, department_id) VALUES (?, ?, ?, ?)";
    public void insertEmployee(Employee employee) throws SQLException {
        Connection connection = ConnectionPool.getDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLEADO_SQL);
        preparedStatement.setString(1, employee.getNombre());
        preparedStatement.setString(2, employee.getApellido());
        preparedStatement.setDouble(3, employee.getSalario());
        preparedStatement.setInt(4, employee.getDepartment_id());
        preparedStatement.executeUpdate();

    }

    private static final String SELECT_EMPLEADO_BY_ID = "SELECT e.id, e.nombre, e.apellido, e.salario, e.department_id, d.name AS department_name " +
            "FROM empleado e JOIN department d ON e.department_id = d.id " +
            "WHERE e.id = ?";
    public Employee getEmployeeById(int id) {
        Employee employee = null;
        try (Connection connection = conectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLEADO_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                double salario = rs.getDouble("salario");
                String departmentName = rs.getString("department_name");
                int departmentId = rs.getInt("department_id");
                employee = new Employee(id, nombre, apellido, salario, departmentId, departmentName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }
    private static final String SELECT_ALL_EMPLEADOS = "SELECT e.id, e.nombre, e.apellido, e.salario, e.department_id, d.name AS department_name " +
            "FROM empleado e JOIN department d ON e.department_id = d.id " +
            "ORDER BY e.id LIMIT ? OFFSET ?";
    public List<Employee> getAllEmployee(int pageNumber, int pageSize) {
        List<Employee> employees = new ArrayList<>();
        int offset = (pageNumber - 1) * pageSize;
        try (Connection connection = conectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLEADOS)) {
            preparedStatement.setInt(1,pageSize);
            preparedStatement.setInt(2, offset);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                double salario = rs.getDouble("salario");
                int departmentId = rs.getInt("department_id");
                String deparmentName = rs.getString("department_name");
                employees.add(new Employee(id, nombre, apellido, salario, departmentId, deparmentName));
            }
            connection.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
    private static final String DELETE_EMPLEADO_SQL = "DELETE FROM empleado WHERE id = ?";
    public boolean deleteEmployee(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = conectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_EMPLEADO_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    private static final String UPDATE_EMPLEADO_SQL = "UPDATE empleado SET nombre = ?, apellido = ?, salario = ?, department_id = ? WHERE id = ?";
    public boolean updateEmployee(Employee employee) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = conectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_EMPLEADO_SQL)) {
            statement.setString(1, employee.getNombre());
            statement.setString(2, employee.getApellido());
            statement.setDouble(3, employee.getSalario());
            statement.setInt(4,employee.getDepartment_id());
            statement.setInt(5, employee.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
    public int getTotalEmployees() throws SQLException {
        int totalEmployees = 0;
        try (Connection connection = conectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM empleado")) {
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                totalEmployees = rs.getInt(1);
            }
        }
        return totalEmployees;
    }
    private static final String SEARCH_EMPLEADO_SQL = "SELECT e.id, e.nombre, e.apellido, e.salario, e.department_id, d.name AS department_name " +
            "FROM empleado e JOIN department d ON e.department_id = d.id " +
            "WHERE e.nombre LIKE ? OR e.apellido LIKE ?";
    public List<Employee> searchEmployee(String searchTerm) {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = conectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_EMPLEADO_SQL)) {
            String searchValue = "%" + searchTerm + "%";
            preparedStatement.setString(1, searchValue);
            preparedStatement.setString(2, searchValue);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                double salario = rs.getDouble("salario");
                int departmentId = rs.getInt("department_id");
                String departmentName = rs.getString("department_name");
                employees.add(new Employee(id, nombre, apellido, salario, departmentId, departmentName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
