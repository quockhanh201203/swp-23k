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
                <nav class="navbar navbar-expand-lg navbar-dark bg-dark px-4 px-lg-5 py-3 py-lg-0">
                    <a href="index.jsp" class="navbar-brand p-0">
                        <h1 class="text-primary m-0"><i class="fa fa-dollar-sign me-3"></i>5 ANH LỰC</h1>
                    </a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                        <span class="fa fa-bars"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarCollapse">
                        <div class="navbar-nav ms-auto py-0 pe-4">
                            <a href="index.jsp" class="nav-item nav-link">Trang chủ</a>
                            <a href="about.jsp" class="nav-item nav-link">Thông tin</a>
                        </div>
                        <a href="signup" class="btn btn-primary py-2 px-4">Đăng ký</a>
                    </div>
                </nav>

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
