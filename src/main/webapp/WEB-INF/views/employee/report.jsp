<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Report</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 800px;
            width: 90%;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #333;
            text-align: center;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            padding: 10px;
            border: 1px solid #ccc;
            text-align: left;
        }

        th {
            background-color: #007bff;
            color: #fff;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Employee Report by Department</h1>
        <table>
            <thead>
                <tr>
                    <th>Department</th>
                    <th>Employee Count</th>
                </tr>
            </thead>
            <tbody>
                <%-- Java code to dynamically generate table rows --%>
                <%@ page import="java.util.Map" %>
                <%@ page import="java.util.HashMap" %>
                <%@ page import="java.util.List" %>
                <%@ page import="java.util.ArrayList" %>
                <%@ page import="java.util.Random" %>
                <%!
                    // Método para simular datos de informe de empleados por departamento
                    public Map<String, Integer> generateReportData() {
                        Map<String, Integer> reportData = new HashMap<>();
                        Random random = new Random();
                        reportData.put("Department 1", random.nextInt(20) + 1);
                        reportData.put("Department 2", random.nextInt(20) + 1);
                        reportData.put("Department 3", random.nextInt(20) + 1);
                        return reportData;
                    }
                %>
                <%
                    // Obtener los datos del informe
                    Map<String, Integer> reportData = generateReportData();

                    // Iterar sobre los datos del informe y generar filas de tabla dinámicamente
                    for (Map.Entry<String, Integer> entry : reportData.entrySet()) {
                %>
                <tr>
                    <td><%= entry.getKey() %></td>
                    <td><%= entry.getValue() %></td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>