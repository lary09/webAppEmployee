package employeeViews;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.DepartmentService;

import java.io.IOException;

@WebServlet("/employee/create")
public class EmployyeCreate extends  HttpServlet{
        DepartmentService departmentService = new DepartmentService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute("departmentService", departmentService);

        request.getRequestDispatcher("/WEB-INF/views/employee/form.jsp").forward(request, response);
        response.sendRedirect("hom.jsp");

    }
}

