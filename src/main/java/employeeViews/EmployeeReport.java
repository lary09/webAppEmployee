package employeeViews;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.EmployeeService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
@WebServlet("/report/showReport")
public class EmployeeReport extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmployeeService employeeService = new EmployeeService();
        try {
            Map<String, Integer> reportData = employeeService.getEmployeesByDepartmentReport();
            request.setAttribute("reportData", reportData);
            request.getRequestDispatcher("/WEB-INF/views/employee/report.jsp").forward(request, response);
            response.sendRedirect("report.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
