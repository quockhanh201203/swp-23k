<%-- 
    Document   : AddStaff
    Created on : Sep 29, 2024, 8:54:17 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Add Staff - Restaurant Management</title>
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

        <div class="container-xxl py-5 px-0 wow fadeInUp bg-secondary" data-wow-delay="0.1s">
            <div class="row g-0 justify-content-center">
                <div class="col-md-6 bg-dark d-flex align-items-center">
                    <div class="p-5 wow fadeInUp" data-wow-delay="0.2s">
                        <h5 class="section-title ff-secondary text-start text-primary fw-normal"><a href="StaffManage">Quản lý nhân viên</a></h5>
                        <h1 class="text-white mb-4">Thêm nhân viên</h1>
                        <form action="AddStaff" method="post">
                            <div class="row g-3">
                                <!-- Staff Name -->
                                <div class="col-md-12">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" id="staffName" placeholder="Staff Name" name="staffName" required>
                                        <label for="staffName">Tên</label>
                                    </div>
                                </div>
                                <!-- Phone Number -->
                                <div class="col-md-12">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" id="phoneNumber" placeholder="Phone Number" name="phoneNumber" required>
                                        <label for="phoneNumber">Số điện thoại</label>
                                    </div>
                                </div>
                                <!-- Email -->
                                <div class="col-md-12">
                                    <div class="form-floating">
                                        <input type="email" class="form-control" id="email" placeholder="Email" name="email" required>
                                        <label for="email">Email</label>
                                    </div>
                                </div>
                                <!-- Salary -->
                                <div class="col-md-12">
                                    <div class="form-floating">
                                        <input type="number" class="form-control" id="salary" placeholder="Salary" name="salary" required>
                                        <label for="salary">Lương cứng</label>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <button class="btn btn-primary w-100 py-3" type="submit">Thêm</button>
                                </div>
                            </div>
                        </form>
                        <c:if test="${not empty Emessage}">
                            <div class="message text-danger">${Emessage}</div>
                        </c:if>
                        <c:if test="${not empty Smessage}">
                            <div class="message text-success">${Smessage}</div>
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
