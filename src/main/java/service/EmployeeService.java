package service;

import dao.EmployeeDAO;
import models.Employee;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class EmployeeService {
    private EmployeeDAO employeeDAO;
    private Connection connection;
    public EmployeeService() {
        this.employeeDAO = new EmployeeDAO();
    }
    public Employee getEmployeeById(int id) throws SQLException {
        return employeeDAO.getEmployeeById( id);
    }
    public void insertEmployeesBatch(List<Employee> employees) throws  SQLException{
        employeeDAO.insertEmployeesBatch( employees);
    }
    public void insertEmployee(Employee employee) throws SQLException{
        employeeDAO.insertEmployee(employee);
    }

    public List<Employee> getAllEmployee(int pageNumber, int pageSize) throws SQLException {
        return employeeDAO.getAllEmployee(pageNumber,pageSize);
    }
    public boolean updateEmployee(Employee employee) throws SQLException {
        return employeeDAO.updateEmployee(employee);
    }

    public boolean deleteEmployee(int id) throws SQLException{
        return  employeeDAO.deleteEmployee(id);
    }
    public int getTotalEmployee() throws SQLException{
        return employeeDAO.getTotalEmployees();
    }
    public List<Employee>searchEmployee(String searchTerm) throws SQLException{
        return employeeDAO.searchEmployee(searchTerm);
    }
    public Map<String, Integer>getEmployeesByDepartmentReport() throws SQLException{
        return employeeDAO.getEmployeesByDepartmentReport();
    }
    public void startTransaction() throws SQLException {
        connection.setAutoCommit(false);
    }
    public void commit() throws SQLException {
        if (connection != null) {
            connection.commit();
        }
    }

    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

}
