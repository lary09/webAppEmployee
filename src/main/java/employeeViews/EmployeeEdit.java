package employeeViews;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Employee;
import service.EmployeeService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/employee/edit")
public class EmployeeEdit extends HttpServlet {
    EmployeeService employeeService = new EmployeeService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        int id = 0;
        if (idStr != null && !idStr.isEmpty()) {
            id = Integer.parseInt(idStr);
        }
        try {
            Employee employee = employeeService.getEmployeeById(id);
            request.setAttribute("employee", employee);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("/WEB-INF/views/employee/edit.jsp").forward(request, response);
        response.sendRedirect("webAppEmployee/employee");

    }
}
