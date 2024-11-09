<%-- 
    Document   : Login
    Created on : Sep 19, 2024, 3:17:31 PM
    Author     : tran tung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    <h1 class="text-primary m-0"><i class="fa fa-utensils me-3"></i>Đăng nhập tài khoản</h1>
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
                    <h1 class="display-3 text-white mb-3 animated slideInDown">Đăng nhập</h1>
                  
                </div>
            </div>
        </div>
        <!-- Navbar & Hero End -->


        <!-- Reservation Start -->
        <div class="container-xxl py-5 px-0 wow fadeInUp" data-wow-delay="0.1s">
            <div class="row g-0">
                <div class="col-md-6 bg-dark d-flex align-items-center">
                        <div class="p-5 wow fadeInUp" data-wow-delay="0.2s">
                        <h5 class="section-title ff-secondary text-start text-primary fw-normal"></h5>
                        <h1 class="text-white mb-4">Tiếp tục không tài khoản</h1>
                        <form action="login" method="post">
                            <input type="hidden" name="loginType" value="wol">

                            <div class="row g-3">
                                
                                <br/>
                                <div class="col-md-6">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" id="email" placeholder="Your name" name="guestname" required>
                                            <label for="email">Tên của bạn</label>
                                    </div>
                                    
                                </div>
                                 <div class="col-md-6">
                                    <div class="form-floating">
                                        <input type="tel" class="form-control" id="email" placeholder="Your phone number " name="phone" required>
                                            <label for="email">Số điện thoại</label>
                                    </div>
                                        <c:if test="${not empty message}">
        <p style="color: red;">${message}</p>
    </c:if>
                                    
                                </div>
                               
                                </div>
                                
                              
                                <div class="col-12">
                                    <button class="btn btn-primary w-100 py-3" type="submit">Start</button>
                                
                          
                            </div>
                        </form>
                    </div>
                </div>
                <div class="col-md-6 bg-dark d-flex align-items-center">
                    <div class="p-5 wow fadeInUp" data-wow-delay="0.2s">
                        <h5 class="section-title ff-secondary text-start text-primary fw-normal">Đã có tài khoản </h5>
                        <h1 class="text-white mb-4">Đăng nhập</h1>
                        <form action="login" method="post">
                <input type="hidden" name="loginType" value="wl">

                            <div class="row g-3">
                                <div class="col-md-6">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" id="name" placeholder="Username" name="username" required>
                                        <label for="name">Tài Khoản</label>
                                    </div>
                                </div>
                                <br/>
                                <div class="col-md-6">
                                    <div class="form-floating">
                                        <input type="password" class="form-control" id="email" placeholder="Password" name="password" required>
                                        <label for="email">Mật khẩu</label>
                                    </div>
                                </div>
                                 <div class="col-12 text-center mt-3">
                                    <p class="text-white">Không có tài khoản? <a href="Signup.jsp" style="color: #FFA737;">Đăng ký</a></p>
                                </div>
                                 <div class="col-12 text-center mt-3">
<c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>                                </div>
                                <div class="col-12 text-center mt-3">
                                    <a href="ForgotPassword" style="color: #FFA737;">Quên mật khẩu</a>
                                </div>
                                </div>
                                
                              
                                <div class="col-12">
                                    <button class="btn btn-primary w-100 py-3" type="submit">Đăng nhập</button>
                                
                          
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

       
        <!-- Reservation Start -->
        

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
