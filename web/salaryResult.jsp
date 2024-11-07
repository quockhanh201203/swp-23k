<%-- 
    Document   : salaryResult.jsp
    Created on : Nov 4, 2024
    Author     : Admin
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.Salary"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Company - Salary Management</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&display=swap" rel="stylesheet">

        <!-- Icon Font Stylesheet -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="css/style.css" rel="stylesheet">
    </head>

    <body>
        <div class="container-xxl bg-white p-0">
            <!-- Spinner Start -->
            <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
                <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                    <span class="sr-only">Loading...</span>
                </div>
            </div>
            <!-- Spinner End -->

            <!-- Navbar & Hero Start -->
            <div class="container-xxl position-relative p-0">
                <%
    // Grab RoleID from the session
    Integer roleID = (Integer) session.getAttribute("RoleID");

    // Check RoleID and include the appropriate header
    if (roleID == 0) {
        // If RoleID is not in the session, include the default header.jsp
        %><%@ include file="header.jsp" %><%
    } else if (roleID == 1) {
        // RoleID 1 is customer
        %><%@ include file="customer-header.jsp" %><%
    } else if (roleID == 2) {
        // RoleID 2 is staff
        %><%@ include file="staff-header.jsp" %><%
    } else if (roleID == 3) {
        // RoleID 3 is admin
        %><%@ include file="admin-header.jsp" %><%
    }
        %>

                <div class="container-xxl py-5 bg-dark hero-header mb-5">
                    <div class="container text-center my-5 pt-5 pb-4">
                        <h1 class="display-3 text-white mb-3 animated slideInDown">QUẢN LÝ TIỀN LƯƠNG</h1>
                    </div>
                </div>
            </div>
            <!-- Navbar & Hero End -->

            <!-- Salary Management -->
            <div class="container-xxl py-5 bg-dark">
                <div class="container">

                    <h2 class="mb-4 text-white">Tìm kiếm lương bằng mã nhân viên</h2>
                    <!-- Search Form -->
                    <form action="CalculateSalary" method="get" class="mb-4">
                        <div class="input-group">
                            <input type="text" class="form-control" name="staffID" placeholder="Nhập mã nhân viên">
                            <button class="btn btn-primary" type="submit">Tìm kiếm</button>
                        </div>
                    </form>
                    <h2 class="mb-4 text-white">Tổng tiền lương</h2>
                    <!-- Salary Details Table -->
                    <%
                        String staffID = (String) request.getAttribute("staffID");
                        String fixedSalary = (String) request.getAttribute("fixedSalary");
                        if (staffID != null && !staffID.equals("-") && fixedSalary != null && !fixedSalary.equals("-")) {
                    %>
                    <table class="table table-bordered table-striped text-white">
                        <thead>
                            <tr>
                                <th>ID Nhân Viên</th>
                                <th>Lương Cứng</th>
                                <th>Lương Thưởng</th>
                                <th>Lương Trừ</th>
                                <th>Lương Tổng Sau Điều Chỉnh</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr class="text-white">
                                <td><%= staffID %></td>
                                <td><%= fixedSalary %> VND</td>
                                <td><%= request.getAttribute("salaryPlus") != null ? request.getAttribute("salaryPlus").toString() : "-" %> VND</td>
                                <td><%= request.getAttribute("salaryMinus") != null ? request.getAttribute("salaryMinus").toString() : "-" %> VND</td>
                                <td><%= request.getAttribute("totalSalary") != null ? request.getAttribute("totalSalary").toString() : "-" %> VND</td>
                            </tr>
                        </tbody>
                    </table>
                    <% } else { %>
                    <div class="text-center text-white">Vui lòng nhập mã nhân viên để tìm kiếm.</div>
                    <% } %>
                </div>
            </div>

            <!-- Footer Start -->
            <div class="container-fluid bg-dark text-light footer pt-5 mt-5">
               
            </div>
            <!-- Footer End -->

            <!-- Back to Top -->
            <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>

            <!-- JavaScript Libraries -->
            <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
            <script src="lib/wow/wow.min.js"></script>
            <script src="lib/easing/easing.min.js"></script>
            <script src="lib/waypoints/waypoints.min.js"></script>
            <script src="lib/counterup/counterup.min.js"></script>
            <script src="lib/owlcarousel/owl.carousel.min.js"></script>
            <script src="lib/tempusdominus/js/moment.min.js"></script>
            <script src="lib/tempusdominus/js/moment-timezone.min.js"></script>
            <script src="lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

            <!-- Template Javascript -->
            <script src="js/main.js"></script>
        </div>
    </body>

</html>
