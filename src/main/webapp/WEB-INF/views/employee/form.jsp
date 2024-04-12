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
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${employee.name}" required><br>
        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName" value="${employee.lastName}" required><br>
        <label for="salary">Salary:</label>
        <input type="number" id="salary" name="salary" value="${employee.salary}" required><br>
        <input type="submit" value="Save">
    </form>
    <br>
    <a href="employee">Cancel</a>
</body>
</html>
