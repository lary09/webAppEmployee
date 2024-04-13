<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Detalles del Empleado</title>
</head>
<body>
    <h1>Detalles del Empleado</h1>
    <p>ID: ${employee.id}</p>
    <p>Nombre: ${employee.nombre}</p>
    <p>Apellido: ${employee.apellido}</p>
    <p>Salario: ${employee.salario}</p>
    <br>
    <a href="employee?action=edit&id=${employee.id}">Editar Empleado</a>
    <a href="employee">Volver a la Lista</a>
</body>
</html>
