<%-- 
    Document   : customer-form
    Created on : Oct 3, 2024, 9:16:46 PM
    Author     : Admin
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String accountID = request.getParameter("accountID");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Customer Form</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h2>Update Customer point</h2>
            <form action="edit" method="post">


                <div class="mb-3">
                    Account ID: <input type="CustomerServlet" name="accountID" value="<%= accountID %>">

                    <label for="point" class="form-label">Points</label>
                    <input type="number" class="form-control" id="point" name="point"  >
                </div>


                <button type="submit" class="btn btn-primary"> Update point</button>
            </form>
        </div>
    </body>
</html>

