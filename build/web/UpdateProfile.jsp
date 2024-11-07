<%-- 
    Document   : UpdateProfile
    Created on : Sep 19, 2024, 8:53:57 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Restoran - Mẫu Nhà Hàng Bootstrap</title>
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
        <%
    // Grab RoleID from the session
    Integer roleID = (Integer) session.getAttribute("RoleID");

    // Check RoleID and include the appropriate header
    if (roleID == null) {
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
        <div class="container-xxl py-5 px-0 wow fadeInUp bg-darker bg-secondary" data-wow-delay="0.1s">
            <div class="row g-0 justify-content-center">
                <div class="col-md-6 bg-dark d-flex align-items-center">
                    <div class="p-5 wow fadeInUp" data-wow-delay="0.2s">
                        <h5 class="section-title ff-secondary text-start text-primary fw-normal">Khách Hàng</h5>
                        <h1 class="text-white mb-4">Cập Nhật Thông Tin</h1>
                        <form action="profile" method="post">
                            <input type="hidden" name="action" value="updateProfile">
                            <input type="hidden" name="customerID" value="${customer.customerID}">
                            <div class="row mt-2">
                                <div class="col-md-12"><label class="labels">Tên Khách Hàng</label><input type="text" name="customerName" class="form-control" value="${customer.customerName}"></div>
                            </div>
                            <div class="row mt-3">
                                <div class="col-md-12"><label class="labels">Số Điện Thoại</label><input type="text" class="form-control" name="phoneNumber" value="${customer.phoneNumber}"></div>
                                <div class="col-md-12"><label class="labels">Email</label><input type="text" class="form-control"  value="${customer.email}" readonly></div>
                                <div class="col-md-12"><label class="labels">Điểm</label><input type="text" class="form-control"  value="${customer.point}" readonly></div>
                            </div>
                            <div class="mt-5 text-center"><button class="btn btn-primary profile-button" type="submit">Lưu Hồ Sơ</button></div>
                        </form>
                        <c:if test="${isSuccess ne null && isSuccess && type eq 'profile'}">
                            <div class="alert alert-success alert-dismissible fade show mt-2" role="alert" id="mess">
                                <strong>Cập nhật hồ sơ thành công!</strong> Bạn nên kiểm tra thông tin ở trên.
                            </div>
                        </c:if>
                        <c:if test="${isSuccess ne null && !isSuccess && type eq 'profile'}">
                            <div class="alert alert-danger alert-dismissible fade show mt-2" role="alert" id="mess">
                                <strong>Cập nhật hồ sơ thất bại!</strong> Bạn nên kiểm tra lại kết nối mạng.
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        </c:if>
                    </div>
                </div>
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
