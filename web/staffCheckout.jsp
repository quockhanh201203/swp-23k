

    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
                <nav class="navbar navbar-expand-lg navbar-dark bg-dark px-4 px-lg-5 py-3 py-lg-0">
                    <a href="" class="navbar-brand p-0">
                        <h1 class="text-primary m-0"><i class="fa fa-utensils me-3"></i>Restoran</h1>
                        <!-- <img src="img/logo.png" alt="Logo"> -->
                    </a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                        <span class="fa fa-bars"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarCollapse">
                        <div class="navbar-nav ms-auto py-0 pe-4">

                        </div>
                    </div>
                </nav>

                <div class="container-xxl py-5 bg-dark hero-header mb-5">
                    <div class="container text-center my-5 pt-5 pb-4">
                        <h1 class="display-3 text-white mb-3 animated slideInDown">Các lần đặt món của bàn</h1>
                        <nav aria-label="breadcrumb">

                        </nav>
                    </div>
                </div>
            </div>
            <!-- Navbar & Hero End -->


            <!-- Service Start -->
            <div class="container-xxl py-5">
                <div class="container">
                    <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                        <h5 class="section-title ff-secondary text-center text-primary fw-normal">Danh sách</h5>
                    </div>
                    <div class="row g-4">
                  <c:set var="totalSum" value="0" /> <!-- Khởi tạo biến totalSum với giá trị 0 -->

    <c:forEach items="${tableOrderList}" var="tb">
        <div class="col-lg-3 col-sm-6 wow fadeInUp" data-wow-delay="0.1s">
         <a href="orderdetail?tableOrderID=${tb.getTable_OrderID()}">Chi tiết</a>

            <div class="p-4">
                <h5>Tên khách hàng: ${tb.getCustomerName()}</h5>
                <h5>Tên khách: ${tb.getGuestName()}</h5>
                <h5>Ngày: ${tb.getOrderdate()}</h5>
                <h5>Tổng: ${tb.getTotalPrice()}Đ</h5>
            </div>
        </div>

        <!-- Cộng dồn totalPrice vào totalSum -->
        <c:set var="totalSum" value="${totalSum + tb.getTotalPrice()}" />
    </c:forEach>

    <div>
        <h5>Tổng: ${totalSum}Đ</h5>
        <div>
          <c:forEach items="${voucherList}" var="voucher">
    <p>${voucher.getVoucherName()}</p>
</c:forEach>

        </div>
           <form action="staffcheckout" method="post"> 
                   <input type="hidden" name="action" value="total">

                    <label for="total"></label>
                    <input type="hidden" id="total" name="total" step="0.01" required value="${totalSum}">
                    <button type="submit" class="btn btn-primary">Checkout</button>
                </form>


                    <script>
                const urlParams = new URLSearchParams(window.location.search);
                if (urlParams.get('success') === 'true') {
                    window.alert("The employee has successfully received the information and will proceed with the payment.");
                }
            </script>
    </div>

                    </div>
                </div>
            </div>
            <!-- Service End -->


            <!-- Footer Start -->

            <!-- Footer End -->


            <!-- Back to Top -->
            <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
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
