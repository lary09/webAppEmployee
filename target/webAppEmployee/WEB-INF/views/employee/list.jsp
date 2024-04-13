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
    <h1>Lista de Empleados</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Salario</th>
            <th>Acciones</th>
        </tr>
        <c:forEach var="employee" items="${employees}">
            <tr>
                <td>${employee.id}</td>
                <td>${employee.name}</td>
                <td>${employee.lastName}</td>
                <td>${employee.salary}</td>
                <td>
                    <a href="employee?action=show&id=${employee.id}" class="button action-link">Mostrar</a>
                    <a href="employee?action=edit&id=${employee.id}" class="button action-link">Editar</a>
                    <a href="employee?action=delete&id=${employee.id}" class="button action-link">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="employee?action=save" class="button">Agregar Nuevo Empleado</a>
</body>
</html>

