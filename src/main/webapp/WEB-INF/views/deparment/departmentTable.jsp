<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Department List</title>
</head>
<body>
    <h1>Department List</h1>
    <table border="1">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Actions</th>
            </tr>
            ${departments}
             <a href="/webAppEmployee/employee/create" class="button">Add a new Employee</a>
    </table>
</body>
</html>
