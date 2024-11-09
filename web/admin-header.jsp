<%-- 
    Document   : header
    Created on : Sep 19, 2024, 9:28:37 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Restoran - Bootstrap Restaurant Template</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&family=Pacifico&display=swap" rel="stylesheet">

        <!-- Icon Font Stylesheet -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="lib/animate/animate.min.css" rel="stylesheet">
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="css/style.css" rel="stylesheet">
    </head>

    <body>
        <div class="container-xxl bg-white p-0">
            <!-- Spinner Start
            <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
                <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                    <span class="sr-only">Loading...</span>
                </div>
            </div>
            -->


            <!-- Navbar & Hero Start -->
            <div class="container-xxl position-relative p-0">
                <nav class="navbar navbar-expand-lg navbar-dark bg-dark px-4 px-lg-5 py-3 py-lg-0">
                    <a href="" class="navbar-brand p-0">
                        <h1 class="text-primary m-0"><i class="fa fa-utensils me-3"></i>5 Anh Lực</h1>
                        <!-- <img src="img/logo.png" alt="Logo"> -->
                    </a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                        <span class="fa fa-bars"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarCollapse">
                        <div class="navbar-nav ms-auto py-0 pe-4">
                            <div class="nav-item dropdown">
                                <a href="#" class="nav-link dropdown-toggle active" data-bs-toggle="dropdown">Quản lý người dùng</a>
                                <div class="dropdown-menu m-0">
                                    <a href="StaffManage" class="dropdown-item">Quản lý nhân viên</a>
                                    <a href="ShiftManage" class="dropdown-item">Quản lý ca làm</a>
                                    <a href="salaryList" class="dropdown-item">Lương</a>
                                    <a href="CustomerServlet" class="dropdown-item">Khách hàng</a>
                                    <a href="FeedbackList" class="dropdown-item">Danh sách phản hồi</a>
                                    <a href="OrderList" class="dropdown-item">Lịch sử gọi món</a>
                                    <a href="CalculateSalary" class="dropdown-item">Lương Tổng</a>
                                </div>
                            </div>
                            
                            <div class="nav-item dropdown">
                                <a href="#" class="nav-link dropdown-toggle active" data-bs-toggle="dropdown">Bàn ăn & Giảm giá</a>
                                <div class="dropdown-menu m-0">
                                    <a href="TableServlet" class="dropdown-item">Quản lý bàn</a>
                                    <a href="ReservationList" class="dropdown-item">Danh sách đặt bàn</a>
                                    <a href="DiscountServlet" class="dropdown-item">Giảm giá</a>
                                    <a href="voucher" class="dropdown-item">Phiếu giảm giá</a>
                                </div>  
                            </div>

                            <div class="nav-item dropdown">
                                <a href="#" class="nav-link dropdown-toggle active" data-bs-toggle="dropdown">Quản lý sản phẩm</a>
                                <div class="dropdown-menu m-0">
                                    <a href="foods" class="dropdown-item">Đồ ăn</a>
                                    <a href="buffets" class="dropdown-item">Buffet</a>
                                    <a href="drinks" class="dropdown-item">Nước</a>
                                    <a href="drink-category" class="dropdown-item">Phân loại nước</a>
                                    <a href="food-category" class="dropdown-item">Phân loại đồ ăn</a>
                                    <a href="price-history" class="dropdown-item">Lịch sử giá</a>
                                </div>
                            </div>
                        </div>
                        <c:if test="${not empty sessionScope.id}">
                            <a href="admin-profile" class="btn btn-primary py-2 px-4">${sessionScope.username}!</a>
                        </c:if>

                        <c:if test="${empty sessionScope.id}">
                            <a href="Login.jsp" class="btn btn-primary py-2 px-4">Đăng nhập</a>
                        </c:if>
                    </div>
                </nav>

                <div class="container-xxl py-5 bg-dark hero-header mb-5">
                </div>
            </div>
    </body>
</html>
