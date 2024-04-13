<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulario de Empleado</title>
</head>
<body>
    <h1>Formulario de Empleado</h1>
    <form action="employee" method="post">
        <input type="hidden" name="action" value="save">
        <input type="hidden" name="id" value="${employee.id}">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" value="${employee.nombre}" required><br>
        <label for="apellido">Apellido:</label>
        <input type="text" id="apellido" name="apellido" value="${employee.apellido}" required><br>
        <label for="salario">Salario:</label>
        <input type="number" id="salario" name="salario" value="${employee.salario}" required><br>
        <input type="submit" value="Guardar">
    </form>
    <br>
    <a href="employee">Cancelar</a>
</body>
</html>
