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
            background-color: #f0f0f0;
        }

        .container {
            text-align: left;
            background-color: #fff;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 90%;
        }

        h1 {
            color: #333;
            margin-bottom: 20px;
            text-align: center;
        }

        .detail {
            margin-bottom: 15px;
            padding-bottom: 15px;
            border-bottom: 1px solid #ddd;
        }

        .detail:last-child {
            border-bottom: none;
            margin-bottom: 0;
            padding-bottom: 0;
        }

        .detail strong {
            display: inline-block;
            width: 120px;
            font-weight: bold;
            margin-right: 10px;
        }

        .detail p {
            display: inline-block;
            margin: 0;
            vertical-align: top;
        }

        a.button {
            display: block;
            width: 100%;
            padding: 10px 0;
            background-color: #4CAF50;
            color: white;
            text-align: center;
            text-decoration: none;
            font-size: 16px;
            border-radius: 5px;
            transition: background-color 0.3s;
            margin-top: 20px;
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
        <div class="detail">
            <strong>ID:</strong>
            <p>${employee.id}</p>
        </div>
        <div class="detail">
            <strong>Name:</strong>
            <p>${employee.nombre}</p>
        </div>
        <div class="detail">
            <strong>Last Name:</strong>
            <p>${employee.apellido}</p>
        </div>
        <div class="detail">
            <strong>Salary:</strong>
            <p>${employee.salario}</p>
        </div>
        <div class="detail">
            <strong>Department:</strong>
            <p>${employee.departmentName}</p>
        </div>
        <a href="/webAppEmployee/employee/edit?id=${employee.id}" class="button">Editar Empleado</a>
        <a href="/webAppEmployee/employee" class="button secondary">Volver a la lista</a>
    </div>
</body>
</html>
