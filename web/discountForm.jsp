<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Model.Discount" %>

<%
    Discount discount = (Discount) request.getAttribute("discount");
    String formAction = (discount != null) ? "update" : "add";
    String buttonText = (discount != null) ? "Cập nhật" : "Thêm";
%>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title><%= buttonText %> Giảm Giá</title>

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
                        <h1 class="display-3 text-white mb-3 animated slideInDown"><%= buttonText %> giảm giá</h1>
                        
                    </div>
                </div>
            </div>
            <!-- Navbar & Hero End -->

            <!-- Form Start -->
            <div class="container-xxl py-5 bg-dark">
                <div class="row justify-content-center">
                    <div class="col-lg-6">
                        <div class="bg-light p-5 rounded shadow">
                            <h3 class="mb-4 text-center"><%= buttonText %> Giảm Giá</h3>
                            <form action="DiscountServlet" method="post">
                                <input type="hidden" name="action" value="<%= formAction %>">
                                <% if (discount != null) { %>
                                    <input type="hidden" name="discountID" value="<%= discount.getDiscountID() %>">
                                <% } %>

                                <!-- Tên Giảm Giá -->
                                <div class="mb-3">
                                    <label for="discountName" class="form-label">Tên Giảm Giá:</label>
                                    <input type="text" class="form-control" name="discountName" value="<%= discount != null ? discount.getDiscountName() : "" %>" required>
                                </div>

                                <!-- Giá Trị Giảm Giá -->
                                <div class="mb-3">
                                    <label for="value" class="form-label">Giá Trị:</label>
                                    <input type="number" class="form-control" name="value" value="<%= discount != null ? discount.getValue() : "" %>" step="0.01" required>
                                </div>

                                <!-- Ngày Giảm Giá -->
                                <div class="mb-3">
                                    <label for="date" class="form-label">Ngày:</label>
                                    <input type="date" class="form-control" name="date" value="<%= discount != null ? new java.text.SimpleDateFormat("yyyy-MM-dd").format(discount.getDate()) : "" %>" required>
                                </div>

                                <button type="submit" class="btn btn-primary w-100 py-2"><%= buttonText %> giảm giá</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Form End -->

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
