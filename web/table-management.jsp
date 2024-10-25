<%-- 
    Document   : table
    Created on : Oct 3, 2024, 7:16:26 AM
    Author     : Admin
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Table Management</title>
</head>
<body>
    <h1>Table Management</h1>

    <form action="tables" method="post">
        <input type="hidden" id="tableId" name="tableId" value="${not empty table ? table.tableId : ''}">
        <label for="tableName">Table Name:</label>
        <input type="text" id="tableName" name="tableName" value="${not empty table ? table.tableName : ''}" required>
        <br>
        <label for="status">Status:</label>
        <select id="status" name="status">
            <option value="Available" <c:if test="${not empty table && table.status == 'Available'}">selected</c:if>>Available</option>
            <option value="Occupied" <c:if test="${not empty table && table.status == 'Occupied'}">selected</c:if>>Occupied</option>
        </select>
        <br>
        <input type="submit" value="${not empty table ? 'Update' : 'Add'}">
    </form>

    <h2>Table List</h2>
    <table border="1">
        <tr>
            <th>Table ID</th>
            <th>Table Name</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="table" items="${tables}">
            <tr>
                <td>${table.tableId}</td>
                <td>${table.tableName}</td>
                <td>${table.status}</td>
                <td>
                    <a href="tables?action=edit&id=${table.tableId}">Edit</a>
                    <a href="tables?action=delete&id=${table.tableId}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
