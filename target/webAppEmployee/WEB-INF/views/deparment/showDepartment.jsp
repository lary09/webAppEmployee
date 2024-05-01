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
        <h1>Department Details</h1>
        <p>ID: ${department.id}</p>
        <p>Name: ${department.name}</p>
        <p>Description: ${department.description}</p>
        <br>
        <a href="/webAppEmployee/department/edit?id=${department.id}" class="button">Edit  Department</a>
        <a href="/webAppEmployee/department" class="button secondary">Go back to list</a>
    </div>
</body>
</html>

