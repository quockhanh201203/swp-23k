<%-- 
    Document   : addSalary
    Created on : Oct 17, 2024, 7:53:39 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Salary</title>
</head>
<body>
    <h2>Add Salary</h2>

    <form action="salaryList" method="POST">
        <label for="salaryPlus">Salary Plus:</label>
        <input type="number" name="salaryPlus" required><br>

        <label for="salaryMinus">Salary Minus:</label>
        <input type="number" name="salaryMinus" required><br>

        <label for="date">Date:</label>
        <input type="date" name="date" required><br>

        <label for="note">Note:</label>
        <input type="text" name="note"><br>

        <label for="staffID">Staff ID:</label>
        <input type="number" name="staffID" required><br><br>

        <input type="submit" value="Add Salary">
    </form>

    <p><a href="salaryList">Back to Salary List</a></p>
</body>
</html>

