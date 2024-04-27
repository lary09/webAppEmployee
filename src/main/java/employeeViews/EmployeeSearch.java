package employeeViews;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.EmployeeService;

import java.io.IOException;

@WebServlet("/employee/search")
public class EmployeeSearch  extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchTerm = request.getParameter("search");
        if (searchTerm != null && !searchTerm.isEmpty()) {
            response.sendRedirect("/webAppEmployee/employee?search=" + searchTerm);

        } else {
            response.sendRedirect("/webAppEmployee/employee");
        }

    }
}
