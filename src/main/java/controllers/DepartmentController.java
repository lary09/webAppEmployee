package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Deparment;
import models.Employee;
import service.DepartmentService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/department")
public class DepartmentController extends HttpServlet {
    DepartmentService departmentService = new DepartmentService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deparmentRepresentation = "";
        List<Deparment> deparments = null;
        try {
            deparments = departmentService.getAllDepartment();
            for ( Deparment deparment : deparments) {
                deparmentRepresentation += "<tr>";
                deparmentRepresentation += "<td>" + deparment.getId() + "</td>";
                deparmentRepresentation += "<td>" + deparment.getName() + "</td>";
                deparmentRepresentation += "<td>" + deparment.getDescription() + "</td>";
                //employeeRepresentation += "<td>" + employee.getSalario() + "</td>";
                deparmentRepresentation += "<td>";
                deparmentRepresentation += "<a href='/webAppEmployee/employee/edit?id=" + deparment.getId() + "' class=\"button action-link\">Edit</a>";
                deparmentRepresentation += "<a href='/webAppEmployee/employee/show?id=" + deparment.getId() + "' class=\"button action-link\">Show</a>";
                deparmentRepresentation += "<a href='/webAppEmployee/employee/delete?id=" + deparment.getId() + "' class=\"button action-link\">Delete</a>";
                deparmentRepresentation += "</td>";
                deparmentRepresentation += "</tr>";
            }
        } catch (SQLException e) {
            deparmentRepresentation = "Nothing to show";
        }
        request.setAttribute("departments", deparmentRepresentation);
        request.getRequestDispatcher("/WEB-INF/views/deparment/departmentTable.jsp").forward(request, response);
    }

}
