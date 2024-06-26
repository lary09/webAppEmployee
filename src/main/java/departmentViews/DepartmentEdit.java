package departmentViews;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Deparment;
import models.Employee;
import service.DepartmentService;
import service.EmployeeService;

import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/department/edit")
public class DepartmentEdit extends HttpServlet {
    DepartmentService departmentService = new DepartmentService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        int id = 0;
        if (idStr != null && !idStr.isEmpty()) {
            id = Integer.parseInt(idStr);
        }
        try {
            Deparment department = departmentService.getDepartmentById(id);
            request.setAttribute("department", department);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("/WEB-INF/views/deparment/departmentEdit.jsp").forward(request, response);
        response.sendRedirect("webAppEmployee/department");

    }
}
