

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    // Biến session đã có sẵn trong JSP, không cần khai báo lại
    Integer tableID = (Integer) session.getAttribute("tableID");
        String message = (String) request.getAttribute("message");

%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">

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
                        <nav aria-label="breadcrumb">
                            
                        </nav>
                    </div>
                </div>
            </div>
            <!-- Navbar & Hero End -->


            <!-- Menu Start -->
            <div class="container-xxl py-5">
                <div class="container">
                    <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                        <h5 class="section-title ff-secondary text-center text-primary fw-normal">Your orders</h5>
                        <!-- Đặt giá trị total ở đây, nhưng bạn sẽ cập nhật sau khi tính toán -->
                    </div>
                    <div class="tab-class text-center wow fadeInUp" data-wow-delay="0.1s">
                        <ul class="nav nav-pills d-inline-flex justify-content-center border-bottom mb-5">
                            <li class="nav-item">
                                <a class="d-flex align-items-center text-start mx-3 ms-0 pb-3 active" data-bs-toggle="pill" href="#tab-1">
                                    <div class="ps-3"></div>
                                </a>
                            </li>
                        </ul>

                        <div class="tab-content">
                            <div id="tab-1" class="tab-pane fade show p-0 active">
                                <div class="row g-4">
                                    <c:set var="total" value="0" />

                           <c:forEach items="${foodOrderCheckoutList}" var="f">
    <div class="col-lg-6">
        <div class="d-flex align-items-center">
            <img class="flex-shrink-0 img-fluid rounded" src="${f.getImage()}" alt="" style="width: 80px;">
            <div class="w-100 d-flex flex-column text-start ps-4">
                <h5 class="d-flex justify-content-between border-bottom pb-2">
                    <span>${f.getFoodName()}</span>
                    <input type="hidden" name="orderID" value="${total}" />
                    <input type="hidden" name="foodOrderID" value="${total}" />
                    Price:  <span class="text-primary">${f.getPrice()}Đ</span>
                    Quantity:  <span class="text-primary">${f.getQuantity()}</span>
                </h5>
                <small class="fst-italic">Note: ${f.getGuestNote()}</small>
                
                <!-- Form xóa -->
                
                
               

                <!-- Nút Update -->
                

                <!-- Modal chứa form -->
                <div class="modal fade" id="infoFormModal" tabindex="-1" aria-labelledby="infoFormModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="infoFormModalLabel">Fill Your Information</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <form action="orderlist" method="post">
                                  <input type="hidden" name="orderID" value="${f.getOrderID()}" />
                    <input type="hidden" name="orderFoodID" value="${f.getOrder_FoodID()}" />
                    <input type="hidden" name="actionB" value="update" />
                                <div class="modal-body">
                                    <!-- Các trường thông tin -->
                                       
                                    <div class="mb-3">
                                        <label for="email" class="form-label"></label>
                                        Quantity: <input type="number" name="quantity" min="0" max="10" value="${f.getQuantity()}" required>
                                    </div>
                                    <div class="mb-3">
                                        <label for="message" class="form-label"></label>
                                         Note:   <input type="text" name="note" value="${f.getGuestNote()}">
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                    <button type="submit" class="btn btn-primary">Submit</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <c:set var="total" value="${total + (f.getPrice() * f.getQuantity())}" />
</c:forEach>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <c:set var="total" value="0" />
            <c:forEach items="${foodOrderList}" var="f">
            <c:set var="total" value="${total + (f.getPrice() * f.getQuantity())}" />
            </c:forEach>
            <h1 class="mb-5">Total: $${total}</h1>
        <form action="checkout" method="post"> 
        <label for="total"></label>
        <input type="hidden" id="total" name="total" step="0.01" required value="${total}">
        <button type="submit" class="btn btn-primary">Checkout</button>
    </form>
            <!-- Menu End -->


            <!-- Footer Start -->
           
            <!-- Footer End -->


            <!-- Back to Top -->
            <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
        </div>
       <script>
        const urlParams = new URLSearchParams(window.location.search);
        if (urlParams.get('success') === 'true') {
            window.alert("The employee has successfully received the information and will proceed with the payment.");
        }
    </script>
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
