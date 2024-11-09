<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Model.Discount" %>

<%
    List<Discount> discounts = (List<Discount>) request.getAttribute("discounts");
%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Discount Management</title>

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
            <!-- Navbar & Hero Start -->
            <div class="container-xxl position-relative p-0">
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

                <div class="container-xxl py-5 bg-dark hero-header mb-5">
                    <div class="container text-center my-5 pt-5 pb-4">
                        <h1 class="display-3 text-white mb-3 animated slideInDown">QUẢN LÝ GIẢM GIÁ</h1>
                        
                    </div>
                </div>
            </div>
            <!-- Navbar & Hero End -->

            <!-- Discount Management -->
            <div class="container-xxl py-5 bg-dark">
                <h2 class="mb-4 text-white">Quản lý giảm giá</h2>
                <a href="discountForm.jsp" class="btn btn-primary py-2 px-4">Thêm giảm giá mới</a>
                
                <!-- Form tìm kiếm -->
                <div class="mb-3">
                    <form action="DiscountServlet" method="GET">
                        <input type="hidden" name="action" value="search">
                        <div class="input-group">
                            <input type="text" class="form-control" name="search" placeholder="Nhập tên discount" value="<%= request.getParameter("search") != null ? request.getParameter("search") : "" %>">
                            <button class="btn btn-primary" type="submit">Tìm kiếm</button>
                        </div>
                    </form>
                </div>

                <!-- Hiển thị danh sách discount -->
                <table border="1" class="table table-bordered table-striped text-white">
                    <tr class="text-white">
                        <th>Mã giảm giá</th>
                        <th>Tên mã giảm giá </th>
                        <th>Gía trị</th>
                        <th>Ngày tháng</th>
                        <th>Hành động</th>
                    </tr>
                    <%
                        for (Discount discount : discounts) {
                    %>
                    <tr class="text-white">
                        <td><%= discount.getDiscountID() %></td>
                        <td><%= discount.getDiscountName() %></td>
                        <td><%= discount.getValue() %></td>
                        <td><%= discount.getDate() %></td>
                        <td>
                            <a href="DiscountServlet?action=edit&id=<%= discount.getDiscountID() %>" class="btn btn-warning btn-sm">Chỉnh sửa</a> |
                            <a href="DiscountServlet?action=delete&id=<%= discount.getDiscountID() %>" class="btn btn-danger btn-sm">Xóa</a>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </div>

            <!-- Footer Start -->
            <div class="container-fluid bg-dark text-light footer pt-5 mt-5 wow fadeIn" data-wow-delay="0.1s">
               
            </div>
            <!-- Footer End -->
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
