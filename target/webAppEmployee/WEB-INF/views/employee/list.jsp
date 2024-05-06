<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Empleados</title>
    <style>
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

        .container form {
            display: flex;
            justify-content: flex-end;
            align-items: center;
            margin-bottom: 20px;
        }

        .container input[type="text"] {
            padding: 8px;
            border-radius: 5px;
            border: 1px solid #ccc;
            margin-right: 10px;
        }

        .container button[type="submit"] {
            padding: 8px 16px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .container button[type="submit"]:hover {
            background-color: #0056b3;
        }

        .pagination {
            margin-top: 20px;
            text-align: center;
        }

        .pagination a {
            display: inline-block;
            padding: 8px 16px;
            text-decoration: none;
            color: #007bff;
            border: 1px solid #007bff;
            border-radius: 5px;
            margin-right: 5px;
        }

        .pagination a.active {
            background-color: #007bff;
            color: white;
        }

        .button {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            margin-right: 10px;
        }

        .button:hover {
            background-color: #0056b3;
        }
    </style>



</head>
<body>
<div class="containerLink">
    <a href="/webAppEmployee/">Home</a>
    <a href="/webAppEmployee/department">Departments</a>
</div>
<div class="container">
    <h1>Employee List</h1>
    <form action="/webAppEmployee/employee/search" method="GET">
            <input type="text" name="search" placeholder="Search by name or last name">
            <button type="submit" >Search</button>
        </form>
        <form action="/webAppEmployee/employee/upload" method="post" enctype="multipart/form-data">
            <input type="file" name="file" accept=".csv">
            <button type="submit">Upload</button>
        </form>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Last Name</th>
            <th>Salary</th>
            <th>Department</th>
            <th>Actions</th>
        </tr>
        ${employees}
    </table>
    <div class="pagination">
            <% int currentPage = (Integer) request.getAttribute("currentPage"); %>
            <% if (request.getAttribute("currentPage") != null) { %>
                <a href="?page=<%= currentPage - 1 %>">Previous</a>
            <% } %>

            <% if (request.getAttribute("totalPages") != null) { %>
            <% int totalPages = (int) request.getAttribute("totalPages"); %>
                <% for (int i = 1; i <= totalPages; i++) { %>
                    <% if (i == currentPage) { %>
                        <a href="?page=<%= i %>" class="active"><%= i %></a>
                    <% } else { %>
                        <a href="?page=<%= i %>"><%= i %></a>
                    <% } %>
                <% } %>
            <% } %>

            <% if (request.getAttribute("currentPage") != null && request.getAttribute("totalPages") != null) { %>
                <% int totalPages = (int) request.getAttribute("totalPages"); %>
                <% if (currentPage < totalPages) { %>
                    <a href="?page=<%= currentPage + 1 %>">Next</a>
                <% } %>
            <% } %>
        </div>
    <a href="/webAppEmployee/employee/create" class="button">Add a new Employee</a>
</div>
</body>
</html>


