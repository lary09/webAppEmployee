package dao;


import models.Employee;
import util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDAO {
    private static final String INSERT_EMPLEADO_SQL = "INSERT INTO empleado (nombre, apellido, salario, age, department_id) VALUES (?, ?, ?, ?, ?)";
    public void insertEmployee(Employee employee) throws SQLException {
        Connection connection = ConnectionDB.conn();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLEADO_SQL);
        preparedStatement.setString(1, employee.getNombre());
        preparedStatement.setString(2, employee.getApellido());
        preparedStatement.setDouble(3, employee.getSalario());
        preparedStatement.setInt(4, employee.getAge());
        preparedStatement.setInt(5, employee.getDepartment_id());
        preparedStatement.executeUpdate();

    }
    private static final String INSERT_EMPLOYEES_SQL = "INSERT INTO empleado (nombre, apellido, salario,age, department_id) VALUES (?, ?, ?, ?,?)";

    public void insertEmployeesBatch(List<Employee> employees) throws SQLException {
        Connection connection = ConnectionDB.conn();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEES_SQL);
        for (Employee employee : employees) {
            preparedStatement.setString(1, employee.getNombre());
            preparedStatement.setString(2, employee.getApellido());
            preparedStatement.setDouble(3, employee.getSalario());
            preparedStatement.setInt(4, employee.getAge());
            preparedStatement.setInt(5, employee.getDepartment_id());
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
    }


    private static final String SELECT_EMPLEADO_BY_ID = "SELECT e.id, e.nombre, e.apellido, e.salario, e.age, e.department_id, d.name AS department_name " +
            "FROM empleado e JOIN department d ON e.department_id = d.id " +
            "WHERE e.id = ?";
    public Employee getEmployeeById(int id) {
        Employee employee = null;
        try (Connection connection = ConnectionDB.conn();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLEADO_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                double salario = rs.getDouble("salario");
                String departmentName = rs.getString("department_name");
                int age = rs.getInt("age");
                int departmentId = rs.getInt("department_id");
                employee = new Employee(id, nombre, apellido, salario,age, departmentId, departmentName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }
    private static final String SELECT_ALL_EMPLEADOS = "SELECT e.id, e.nombre, e.apellido, e.salario,e.age, e.department_id, d.name AS department_name " +
            "FROM empleado e JOIN department d ON e.department_id = d.id " +
            "ORDER BY e.id LIMIT ? OFFSET ?";
    public List<Employee> getAllEmployee(int pageNumber, int pageSize) {
        List<Employee> employees = new ArrayList<>();
        int offset = (pageNumber - 1) * pageSize;
        try (Connection connection = ConnectionDB.conn();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLEADOS)) {
            preparedStatement.setInt(1,pageSize);
            preparedStatement.setInt(2, offset);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                double salario = rs.getDouble("salario");
                int age = rs.getInt("age");
                int departmentId = rs.getInt("department_id");
                String deparmentName = rs.getString("department_name");
                employees.add(new Employee(id, nombre, apellido, salario,age, departmentId, deparmentName));
            }
            connection.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
    public boolean isEmployeeDuplicate(Employee employee) throws SQLException {
        try (Connection connection = ConnectionDB.conn();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) AS count " +
                     "FROM empleado " +
                     "WHERE nombre = ? AND apellido = ?")) {
            preparedStatement.setString(1, employee.getNombre());
            preparedStatement.setString(2, employee.getApellido());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int count = rs.getInt("count");
                return count > 0; // Si count > 0, significa que hay empleados duplicados
            }
        }
        return false;
    }

    private static final String DELETE_EMPLEADO_SQL = "DELETE FROM empleado WHERE id = ?";
    public boolean deleteEmployee(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = ConnectionDB.conn();
             PreparedStatement statement = connection.prepareStatement(DELETE_EMPLEADO_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    private static final String UPDATE_EMPLEADO_SQL = "UPDATE empleado SET nombre = ?, apellido = ?, salario = ?,age = ?, department_id = ? WHERE id = ?";
    public boolean updateEmployee(Employee employee) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = ConnectionDB.conn();
             PreparedStatement statement = connection.prepareStatement(UPDATE_EMPLEADO_SQL)) {
            statement.setString(1, employee.getNombre());
            statement.setString(2, employee.getApellido());
            statement.setDouble(3, employee.getSalario());
            statement.setInt(4,employee.getAge());
            statement.setInt(4,employee.getDepartment_id());
            statement.setInt(5, employee.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public int getTotalEmployees() throws SQLException {
        int totalEmployees = 0;
        try (Connection connection = ConnectionDB.conn();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM empleado")) {
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                totalEmployees = rs.getInt(1);
            }
        }
        return totalEmployees;
    }
    private static final String SEARCH_EMPLEADO_SQL = "SELECT e.id, e.nombre, e.apellido, e.salario,e.age, e.department_id, d.name AS department_name " +
            "FROM empleado e JOIN department d ON e.department_id = d.id " +
            "WHERE e.nombre LIKE ? OR e.apellido LIKE ?";
    public List<Employee> searchEmployee(String searchTerm) {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = ConnectionDB.conn();
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
                int age = rs.getInt("age");
                int departmentId = rs.getInt("department_id");
                String departmentName = rs.getString("department_name");
                employees.add(new Employee(id, nombre, apellido, salario,age, departmentId, departmentName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
    private static final String REPORT_EMPLOYEES_BY_DEPARTMENT_SQL = "SELECT d.name AS department_name, COUNT(e.id) AS employee_count " +
            "FROM empleado e JOIN department d ON e.department_id = d.id " +
            "GROUP BY d.name";

    public Map<String, Integer> getEmployeesByDepartmentReport() {
        Map<String, Integer> report = new HashMap<>();
        try (Connection connection = ConnectionDB.conn();
             PreparedStatement preparedStatement = connection.prepareStatement(REPORT_EMPLOYEES_BY_DEPARTMENT_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String departmentName = resultSet.getString("department_name");
                int employeeCount = resultSet.getInt("employee_count");
                report.put(departmentName, employeeCount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return report;
    }
}

