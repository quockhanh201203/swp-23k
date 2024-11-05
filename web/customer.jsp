<%-- 
    Document   : Customer
    Created on : Oct 3, 2024, 3:27:07 PM
    Author     : Admin
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Restoran - Customer Management</title>
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
                    <a href="" class="navbar-brand p-0">
                        <h1 class="text-primary m-0"><i class="fa fa-utensils me-3"></i>5 ANH LỰC</h1>
                    </a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                        <span class="fa fa-bars"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarCollapse">
                        <div class="navbar-nav ms-auto py-0 pe-4">
                            <a href="index.html" class="nav-item nav-link">Trang chủ</a>
                            <a href="about.html" class="nav-item nav-link">Thông tin</a>
                    
                        </div>
                        <c:if test="${not empty sessionScope.id}">
                            <a href="Logout.jsp" class="btn btn-primary py-2 px-4">${sessionScope.username}!</a>
                        </c:if>

                        <c:if test="${empty sessionScope.id}">
                            <a href="DiscountServlet" class="btn btn-primary py-2 px-4">Giam giá </a>
                        </c:if>
                    </div>
                </nav>

                <div class="container-xxl py-5 bg-dark hero-header mb-5">
                    <div class="container text-center my-5 pt-5 pb-4">
                        <h1 class="display-3 text-white mb-3 animated slideInDown">DANH SÁCH NGƯỜI DÙNG</h1>
                        
                    </div>
                </div>
            </div>

            <div class="container-xxl py-5 bg-dark">
                <h2 class="mb-4 text-white">Danh sách người dùng</h2>


                <form action="CustomerServlet" method="get" class="mb-4">
                    <div class="input-group">

                        <input type="text" name="search" class="form-control" placeholder="Tìm kiếm" value="${param.search}">
                        <button type="submit" class="btn btn-primary">Tìm kiếm</button>
                    </div>
                </form>

                <!-- Nút "Add Customer" mở modal -->

                <!-- Bảng hiển thị danh sách khách hàng -->
                <table class="table table-bordered table-striped text-white">
                    <thead>
                        <tr>
                            <th>Mã người dùng</th>
                            <th>Tên người dùng</th>
                            <th>Số điện thoại</th>
                            <th>Email</th>
                            <th>Điểm</th>
                            <th>Mã tài khoản</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="c" items="${list}">
                            <tr class="text-white">
                                <td>${c.customerID}</td>
                                <td>${c.customerName}</td>
                                <td>${c.phoneNumber}</td>
                                <td>${c.email}</td>
                                <td>${c.point}</td>
                                <td>${c.accountID}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </div>

            <!-- Modal cập nhật điểm khách hàng -->
            <div class="modal fade" id="updatePointModal" tabindex="-1" aria-labelledby="updatePointModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="updatePointModalLabel">Update Customer Point</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="CustomerServlet" method="post">
                                <input type="hidden" name="action" value="edit">

                                <div class="mb-3">
                                    Account ID: 
                                    <input type="text" name="accountID" value="${c.accountID}" readonly class="form-control" style="border: 0;">
                                </div>

                                <div class="mb-3">
                                    <label for="point" class="form-label">Points</label>
                                    <input type="number" class="form-control" id="point" name="point">
                                </div>

                                <button type="submit" class="btn btn-primary">Update Point</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>


            
            <div class="container-fluid bg-dark text-light footer pt-5 mt-5 wow fadeIn" data-wow-delay="0.1s">
              
            </div>
            
        </div>

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
    </body>

</html>
