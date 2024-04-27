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

import static java.lang.Integer.parseInt;

@WebServlet("/employee")
public class EmployeeController extends HttpServlet {
    private EmployeeService employeeService;

    public void init() {
        employeeService = new EmployeeService();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchTerm = request.getParameter("search");
        String pageParam = request.getParameter("page");
        int currentPage = 1;
        if (pageParam != null && !pageParam.isEmpty()) {
            currentPage = Integer.parseInt(pageParam);
        }
        if (searchTerm != null && !searchTerm.isEmpty()) {

            searchEmployees(request, response, searchTerm, currentPage);
        } else {
            listEmployees(request, response);
        }

    }

    private void listEmployees(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int currentPage = 1;

        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            currentPage = Integer.parseInt(pageParam);
        }
        int totalEmployees = 0;
        try {
            totalEmployees = employeeService.getTotalEmployee();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int pageSize = 10;
        int totalPages = (int) Math.ceil((double) totalEmployees / pageSize);

        List<Employee> employees = null;
        String employeeRepresentation = "";
        try {
            employees = employeeService.getAllEmployee(currentPage,pageSize);
            for (Employee employee : employees) {
                employeeRepresentation += "<tr>";
                employeeRepresentation += "<td>" + employee.getId() + "</td>";
                employeeRepresentation += "<td>" + employee.getNombre() + "</td>";
                employeeRepresentation += "<td>" + employee.getApellido() + "</td>";
                employeeRepresentation += "<td>" + employee.getSalario() + "</td>";
                employeeRepresentation += "<td>" + employee.getDepartmentName() + "</td>";
                employeeRepresentation += "<td>";
                employeeRepresentation += "<a href='/webAppEmployee/employee/edit?id=" + employee.getId() + "' class=\"button action-link\">Edit</a>";
                employeeRepresentation += "<a href='/webAppEmployee/employee/show?id=" + employee.getId() + "' class=\"button action-link\">Show</a>";
                employeeRepresentation += "<a href='/webAppEmployee/employee/delete?id=" + employee.getId() + "' class=\"button action-link\">Delete</a>";
                employeeRepresentation += "</td>";
                employeeRepresentation += "</tr>";
            }
        } catch (SQLException e) {
            employeeRepresentation = "Nothing to show";
        }


        request.setAttribute("employees", employeeRepresentation);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.getRequestDispatcher("/WEB-INF/views/employee/list.jsp").forward(request, response);
    }

    private void searchEmployees(HttpServletRequest request, HttpServletResponse response, String searchTerm, int currentPage)
            throws ServletException, IOException {


        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            currentPage = Integer.parseInt(pageParam);
        }
        int totalEmployees = 0;
        try {
            totalEmployees = employeeService.getTotalEmployee();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int pageSize = 10;
        int totalPages = (int) Math.ceil((double) totalEmployees / pageSize);
        List<Employee> searchResults = null;
        String searchRepresentation = "";
        try {
            searchResults = employeeService.searchEmployee(searchTerm);
            for (Employee employee : searchResults) {
                searchRepresentation += "<tr>";
                searchRepresentation += "<td>" + employee.getId() + "</td>";
                searchRepresentation += "<td>" + employee.getNombre() + "</td>";
                searchRepresentation += "<td>" + employee.getApellido() + "</td>";
                searchRepresentation += "<td>" + employee.getSalario() + "</td>";
                searchRepresentation += "<td>" + employee.getDepartmentName() + "</td>";
                searchRepresentation += "<td>";
                searchRepresentation += "<a href='/webAppEmployee/employee/edit?id=" + employee.getId() + "' class=\"button action-link\">Edit</a>";
                searchRepresentation += "<a href='/webAppEmployee/employee/show?id=" + employee.getId() + "' class=\"button action-link\">Show</a>";
                searchRepresentation += "<a href='/webAppEmployee/employee/delete?id=" + employee.getId() + "' class=\"button action-link\">Delete</a>";
                searchRepresentation += "</td>";
                searchRepresentation += "</tr>";
            }
        } catch (SQLException e) {
            searchRepresentation = "Search failed";
        }

        request.setAttribute("currentPage", currentPage);
        request.setAttribute("employees", searchRepresentation);
        request.setAttribute("totalPages", totalPages);
        request.getRequestDispatcher("/WEB-INF/views/employee/list.jsp").forward(request, response);
}

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        int id = 0;
        if (idStr != null && !idStr.isEmpty()) {
            id = parseInt(idStr);
        }
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        Integer departmentId = parseInt(request.getParameter("department_id"));
        String departmentName = getInitParameter("department_name");
        String salaryStr = request.getParameter("salary");
        double salary;
        if (salaryStr != null && !salaryStr.isEmpty()) {
            salary = Double.parseDouble(salaryStr);
        } else {
            salary = 0.0;
        }
        Employee newEmployee = new Employee(id, name, lastName, salary, departmentId, departmentName);
        try {
            if (newEmployee.getId()!= 0) {
               boolean employee = employeeService.updateEmployee(newEmployee);
                request.setAttribute("employee", employee);
            } else {
                employeeService.insertEmployee(newEmployee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //request.getRequestDispatcher("/WEB-INF/views/employee/form.jsp").forward(request, response);
        response.sendRedirect( "/webAppEmployee/employee");
    }


}

