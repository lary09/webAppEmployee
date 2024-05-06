package employeeViews;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import models.Employee;
import service.EmployeeService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/upload")
@MultipartConfig
public class FileUploadController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Part filePart = request.getPart("file");
        if (filePart != null && filePart.getSubmittedFileName().endsWith(".csv")) {
            try {
                processCSV(filePart.getInputStream());
                response.sendRedirect("/webAppEmployee/employee");
            } catch (SQLException e) {
                throw new ServletException("Error al procesar el archivo CSV", e);
            }
        } else {
            response.getWriter().println("Por favor, sube un archivo CSV.");
        }
    }

    private void processCSV(InputStream inputStream) throws IOException, SQLException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            int batchSize = 20000; // Tamaño del lote
            int count = 0;
            EmployeeService employeeService = new EmployeeService();
            //employeeService.startTransaction(); // Comienza la transacción
            List<Employee> batchEmployees = new ArrayList<>(); // Lista para almacenar los empleados del lote
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 5) {
                    Employee newEmployee = getEmployee(fields);
                    batchEmployees.add(newEmployee);
                    count++;
                    if (count % batchSize == 0) {
                        // Insertar el lote de empleados
                        employeeService.insertEmployeesBatch(batchEmployees);
                        batchEmployees.clear(); // Limpiar la lista para el próximo lote
                    }
                }
            }
            // Insertar cualquier empleado restante en el lote
            if (!batchEmployees.isEmpty()) {
                employeeService.insertEmployeesBatch(batchEmployees);
            }
            employeeService.commit(); // Confirmar la transacción al final
        }
    }



    private static Employee getEmployee(String[] fields) {
        int id = Integer.parseInt(fields[0].trim());
        String name = fields[1].trim();
        String lastName = fields[2].trim();
        double salary = Double.parseDouble(fields[3].trim().replace("$", ""));
        int departmentId = Integer.parseInt(fields[4].trim());
        return new Employee( id,name, lastName, salary, departmentId);
    }

}
