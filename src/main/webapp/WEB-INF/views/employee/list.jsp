<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Empleados</title>
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
            align-items: center;
            margin-bottom: 20px;
        }

        .container form input[type="text"] {
            padding: 8px;
            border-radius: 5px;
            border: 1px solid #ccc;
            width: 100%;
        }

        .container form button[type="submit"] {
            padding: 8px 16px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-left: 10px;
        }

        .container form button[type="submit"]:hover {
            background-color: #0056b3;
        }
        .search-upload-container{
            display: flex;
            justify-content: space-between;
        }
        .search-container, .upload-container {
            width: 30%;
            margin-right: 10px;
        }

        .pagination-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-top: 20px;
            text-align: center;
        }

        .pagination {
            margin-bottom: 20px;
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

        .button-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
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

        .containerLink {
            display: flex;
            justify-content: flex-end;
            gap: 15px;
            padding-right: 50px

        }

        .containerLink a {
            text-decoration: none;
            color: #007bff;
            padding: 5px 10px;
            border: 1px solid #007bff;
            border-radius: 5px;
        }

        .containerLink a:hover {
            background-color: #007bff;
            color: #fff;
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
    <div class="search-upload-container">
        <div class="search-container">
            <form action="/webAppEmployee/employee/search" method="GET">
                <input type="text" name="search" placeholder="Search by name or last name">
                <button type="submit">Search</button>
            </form>
        </div>
        <div class="upload-container">
            <form action="/webAppEmployee/upload" method="post" enctype="multipart/form-data">
                <input type="file" name="file" accept=".csv">
                <button type="submit">Upload</button>
            </form>
        </div>
    </div>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Last Name</th>
            <th>Salary</th>
            <th>Age</th>
            <th>Department</th>
            <th>Actions</th>
        </tr>
        ${employees}
    </table>
    <div class="pagination-container">
        <div class="pagination">
            <% int currentPage = (Integer) request.getAttribute("currentPage"); %>
            <% if (request.getAttribute("currentPage") != null) { %>
                <% int totalPages = (int) request.getAttribute("totalPages"); %>
                <% if (currentPage > 1) { %>
                    <a href="?page=<%= currentPage - 1 %>">Previous</a>
                <% } %>
                <% if (currentPage > 1) { %>
                    <a href="?page=1">1</a>
                    <% if (currentPage > 2) { %>
                        <span>...</span>
                    <% } %>
                <% } %>
                <span><%= currentPage %></span>
                <% if (currentPage < totalPages) { %>
                    <% if (currentPage < totalPages - 1) { %>
                        <span>...</span>
                    <% } %>
                    <a href="?page=<%= totalPages %>"><%= totalPages %></a>
                <% } %>
                <% if (currentPage < totalPages) { %>
                    <a href="?page=<%= currentPage + 1 %>">Next</a>
                <% } %>
            <% } %>
        </div>
        <div class="button-container">
            <a href="/webAppEmployee/employee/create" class="button">Create Employee</a>
            <a href="/webAppEmployee/report" class="button">Report</a>
        </div>
    </div>
</div>
</body>
</html>
