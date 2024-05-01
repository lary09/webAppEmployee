<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="models.Deparment" %>
<%@ page import="service.DepartmentService" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulario de Empleado</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
        }

        form {
            background-color: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: left;
        }

        h1 {
            margin-bottom: 20px;
            text-align: center;
        }

        label {
            display: block;
            margin-bottom: 10px;
        }

        input[type="text"],
        input[type="number"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
            font-size: 16px;
        }

        input[type="submit"],
        a.button {
            display: block;
            width: fit-content;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            text-align: center;
            text-decoration: none;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        input[type="submit"]:hover,
        a.button:hover {
            background-color: #0056b3;
        }

        a.button.secondary {
            background-color: #007bff;
        }

        a.button.secondary:hover {
            background-color: #5a6268;
        }

        .button-container {
            display: flex;
            justify-content: space-between;
            text-align: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <form action="/webAppEmployee/employee" method="post">
        <input type="hidden" name="action" value="employee">
        <h1>Edit Employee</h1>
        <label for="id">ID:</label>
        <input type="number" name="id" value="${employee.getId()}">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${employee.getNombre()}" required><br>
        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName" value="${employee.getApellido()}" required><br>
        <label for="salary">Salary:</label>
        <input type="number" id="salary" name="salary" value="${employee.getSalario()}" required><br>
        <select name='department_id' required>
                   <%
                        DepartmentService departmentService = new DepartmentService();
                       List<Deparment> departments = departmentService.getAllDepartment();
                       for (Deparment department : departments) { %>
                           <option value="<%= department.getId() %>"><%= department.getName() %></option>
                       <% } %>
                </select>
        <div class="button-container">
            <input type="submit" value="Edit">
            <a href="/webAppEmployee/employee" class="button secondary">Go to list</a>
        </div>
    </form>
</body>
</html>
