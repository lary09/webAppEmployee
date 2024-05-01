<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
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
        .containerLink {
            display: flex;
            justify-content: flex-end;
            align-items: center;
            margin-bottom: 20px;
            margin-right: 200px;
        }

        .containerLink a {
           margin-left: 10px;
           text-decoration: none;
           color: #007bff;
        }

        h1 {
            color: #333;
            text-align: center;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
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

        a.button {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
        }

        a.button:hover {
            background-color: #0056b3;
        }

        a.action-link {
            margin-right: 10px;
        }

    </style>
</head>
<body>
<div class="containerLink">
    <a href="/webAppEmployee/">Home</a>
    <a href="/webAppEmployee/employee">Employee list</a>
</div>
<div class="container">
    <h1>Employee List</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Acctions</th>
        </tr>
        ${departments}
    </table>
    <a href="/webAppEmployee/department/create" class="button">Add a new Department</a>
    </div>
</body>
</html>

