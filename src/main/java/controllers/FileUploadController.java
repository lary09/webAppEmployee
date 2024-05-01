package controllers;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Employee;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.EmployeeService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/upload")
public class FileUploadController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException{
        if (ServletFileUpload.isMultipartContent((javax.servlet.http.HttpServletRequest) request)){
            try {
                List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest((javax.servlet.http.HttpServletRequest) request);
                for(FileItem item : items){
                    if (!item.isFormField() && item.getName().endsWith(".csv")){
                        processCSV(item.getInputStream());
                        response.sendRedirect("/list.jsp");
                        return;
                    }
                }
            } catch (FileUploadException | SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }
    private void processCSV(InputStream inputStream) throws IOException, SQLException{
        try (BufferedReader reader= new BufferedReader(new InputStreamReader(inputStream,StandardCharsets.UTF_8))){
            String line;
            while ((line = reader.readLine()) != null){
                String[] fields = line.split(",");
                if (fields.length == 5){
                    int id = Integer.parseInt(fields[0]);
                    String name = fields[1];
                    String lastName = fields[2];
                    double salary = Double.parseDouble(fields[3]);
                    int departmentId = Integer.parseInt(fields[4]);
                    Employee newEmployee = new Employee(id, name, lastName, salary, departmentId);
                    EmployeeService employeeService = new EmployeeService();
                    employeeService.insertEmployee(newEmployee);
                }
            }
        }
    }
}
