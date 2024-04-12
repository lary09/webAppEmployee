package controllers;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Employee;
import service.EmployeeService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/employee")
public class EmployeeController extends HttpServlet {
    private EmployeeService employeeService;

    public void init() {
        employeeService = new EmployeeService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                try {
                    listEmployees(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "save":
                try {
                    saveEmployee(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "show":
                try {
                    showEmployee(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "edit":
                try {
                    editEmployee(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "delete":
                try {
                    deleteEmployee(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                try {
                    listEmployees(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    private void listEmployees(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        List<Employee> employees = employeeService.getAllEmployee();
        request.setAttribute("employees", employees);
        request.getRequestDispatcher("/WEB-INF/views/employee/list.jsp").forward(request, response);

    }
    private void saveEmployee(HttpServletRequest request, HttpServletResponse response)
            throws  ServletException, IOException, SQLException{
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String salaryStr = request.getParameter("salary");
        double salary;
        if(salaryStr != null && !salaryStr.isEmpty()){
             salary = Double.parseDouble(salaryStr);
        }else{
            salary = 0.0;
        }
        Employee newEmployee = new Employee(0, name, lastName, salary);
        employeeService.insertEmployee(newEmployee);
        request.getRequestDispatcher("/WEB-INF/views/employee/form.jsp").forward(request, response);
        response.sendRedirect(request.getContextPath() + "/employee?action=list");
    }

    private void showEmployee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int employeeId = Integer.parseInt(request.getParameter("id"));
        Employee employee = employeeService.getEmployeeById(employeeId);
        request.setAttribute("employee", employee);
        request.getRequestDispatcher("/WEB-INF/views/employee/show.jsp").forward(request, response);
    }

    private void editEmployee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        double salary = Double.parseDouble(request.getParameter("salary"));
        int id = Integer.parseInt(request.getParameter("id"));

        Employee employeeEdited = new Employee(id,name,lastName,salary);
        employeeService.updateEmployee(employeeEdited);
        request.setAttribute("employee", employeeEdited);
        request.getRequestDispatcher("/WEB-INF/views/employee/list.jsp").forward(request,response);
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        employeeService.deleteEmployee(id);
    }
}

