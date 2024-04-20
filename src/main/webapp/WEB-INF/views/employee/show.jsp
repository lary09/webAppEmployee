<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Detalles del Empleado</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
        }

        .container {
            text-align: center;
        }

        p {
            margin: 5px 0;
            font-size: 16px;
            color: #333;
        }

        a.button {
            display: inline-block;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            text-align: center;
            text-decoration: none;
            font-size: 16px;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        a.button:hover {
            background-color: #45a049;
        }

        a.button.secondary {
            background-color: #008CBA;
        }

        a.button.secondary:hover {
            background-color: #0073b8;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Employee Details</h1>
        <p>ID: ${employee.id}</p>
        <p>Name: ${employee.nombre}</p>
        <p>Last Name: ${employee.apellido}</p>
        <p>Salary: ${employee.salario}</p>
        <br>
        <a href="/webAppEmployee/employee/edit?id=${employee.id}" class="button">Edit  Employee</a>
        <a href="/webAppEmployee/employee" class="button secondary">Go back to list</a>
    </div>
</body>
</html>

