

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
        <style>
            /* Popup container */
            #popupBanner, #popupTable {
                display: none; /* Initially hidden */
                position: fixed;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                width: 50vw; /* Set width to 50% of viewport width */
                height: 50vh; /* Set height to 50% of viewport height */
                background-color: #ADD8E6; /* Light blue background */
                box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
                z-index: 1000;
            }

            /* Popup content */
            .popup-content {
                position: relative;
                padding: 20px;
                text-align: center;
            }

            /* Close button (X) */
            .close-btn {
                position: absolute;
                top: 10px;
                right: 10px;
                font-size: 18px;
                background: none;
                border: none;
                cursor: pointer;
            }

            /* Image in popup */
            .popup-img {
                width: 100%;
                height: auto;
                max-height: 200px;
            }

            /* Background overlay */
            #overlay {
                display: block;
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background-color: orange;
                z-index: 999;
            }

            /* Table selection */
            .table-options {
                margin-top: 20px;
            }
            .table-options label {
                margin-right: 10px;
            }
        </style>





    </head>

    <body>

        <div id="popupBanner">
            <div class="popup-content">
                <button class="close-btn" onclick="closePopup1()">×</button>
                <img src="https://img.freepik.com/premium-vector/fried-chicken-food-banner-store-print-promotional-business-design-template_737924-767.jpg?w=1060" alt="Banner Image" class="popup-img">
                <p>Special promotion just for you!</p>
            </div>
        </div>

        <!-- Popup 2: Select Table -->
        <div id="popupTable">
            <div class="popup-content">
                <button class="close-btn" onclick="closePopup2()">×</button>
                <p>Please select a table:</p>
                <div class="table-options">
                    <form action="homepape" method="post">
                        <label><input type="radio" name="table" value="Table 1"> Table 1</label>
                        <label><input type="radio" name="table" value="Table 2"> Table 2</label>
                        <label><input type="radio" name="table" value="Table 3"> Table 3</label>

                    </form>
                </div>
            </div>
        </div>  





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
                            <a href="index.html" class="nav-item nav-link active">Home</a>
                            <a href="about.html" class="nav-item nav-link">About</a>
                            <a href="service.html" class="nav-item nav-link">Service</a>
                            <a href="menu.html" class="nav-item nav-link">Menu</a>
                            <div class="nav-item dropdown">
                                <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Pages</a>
                                <div class="dropdown-menu m-0">
                                    <a href="booking.html" class="dropdown-item">Booking</a>
                                    <a href="team.html" class="dropdown-item">Our Team</a>
                                    <a href="testimonial.html" class="dropdown-item">Testimonial</a>
                                </div>
                            </div>
                            <a href="contact.html" class="nav-item nav-link">Contact</a>
                        </div>
                        <a href="" class="btn btn-primary py-2 px-4">Book A Table</a>
                    </div>
                </nav>

                <div class="container-xxl py-5 bg-dark hero-header mb-5">
                    <div class="container my-5 py-5">
                        <div class="row align-items-center g-5">
                            <div class="col-lg-6 text-center text-lg-start">
                                <h1 class="display-3 text-white animated slideInLeft">5 Anh Luc<br>Quick service restaurant</h1>
                                <p class="text-white animated slideInLeft mb-4 pb-2">A fast food restaurant offers quick, convenient meals featuring burgers, fries, and soft drinks, perfect for on-the-go dining.</p>
                            </div>
                            <div class="col-lg-6 text-center text-lg-end overflow-hidden">
                                <img class="img-fluid" src="img/hero.png" alt="">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Navbar & Hero End -->


            <!-- Service Start -->
            <div class="container-xxl py-5">
                <div class="container">
                    <div class="row g-4">
                        <div class="col-lg-3 col-sm-6 wow fadeInUp" data-wow-delay="0.1s">
                            <div class="service-item rounded pt-3">
                                <div class="p-4">
                                    <i class="fa fa-3x fa-user-tie text-primary mb-4"></i>
                                    <h5>Master Chefs</h5>
                                    <p>Diam elitr kasd sed at elitr sed ipsum justo dolor sed clita amet diam</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3 col-sm-6 wow fadeInUp" data-wow-delay="0.3s">
                            <div class="service-item rounded pt-3">
                                <div class="p-4">
                                    <i class="fa fa-3x fa-utensils text-primary mb-4"></i>
                                    <h5>Quality Food</h5>
                                    <p>Diam elitr kasd sed at elitr sed ipsum justo dolor sed clita amet diam</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3 col-sm-6 wow fadeInUp" data-wow-delay="0.5s">
                            <div class="service-item rounded pt-3">
                                <div class="p-4">
                                    <i class="fa fa-3x fa-cart-plus text-primary mb-4"></i>
                                    <h5>Online Order</h5>
                                    <p>Diam elitr kasd sed at elitr sed ipsum justo dolor sed clita amet diam</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3 col-sm-6 wow fadeInUp" data-wow-delay="0.7s">
                            <div class="service-item rounded pt-3">
                                <div class="p-4">
                                    <i class="fa fa-3x fa-headset text-primary mb-4"></i>
                                    <h5>24/7 Service</h5>
                                    <p>Diam elitr kasd sed at elitr sed ipsum justo dolor sed clita amet diam</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Service End -->


            <!-- About Start -->
            <div class="container-xxl py-5">
                <div class="container">
                    <div class="row g-5 align-items-center">
                        <div class="col-lg-6">
                            <div class="row g-3">
                                <div class="col-6 text-start">
                                    <img class="img-fluid rounded w-100 wow zoomIn" data-wow-delay="0.1s" src="img/burger.jpg">
                                </div>
                                <div class="col-6 text-start">
                                    <img class="img-fluid rounded w-75 wow zoomIn" data-wow-delay="0.3s" src="img/pizza.jpg" style="margin-top: 25%;">
                                </div>
                                <div class="col-6 text-end">
                                    <img class="img-fluid rounded w-75 wow zoomIn" data-wow-delay="0.5s" src="img/coca1.jpg">
                                </div>
                                <div class="col-6 text-end">
                                    <img class="img-fluid rounded w-100 wow zoomIn" data-wow-delay="0.7s" src="img/chicken1.jpg">
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <h5 class="section-title ff-secondary text-start text-primary fw-normal">About Us</h5>
                            <h1 class="mb-4">Welcome to <i class="fa fa-utensils text-primary me-2"></i>Restoran</h1>
                            <p class="mb-4">Tempor erat elitr rebum at clita. Diam dolor diam ipsum sit. Aliqu diam amet diam et eos erat ipsum et lorem et sit, sed stet lorem sit.</p>
                            <p class="mb-4">Tempor erat elitr rebum at clita. Diam dolor diam ipsum sit. Aliqu diam amet diam et eos. Clita erat ipsum et lorem et sit, sed stet lorem sit clita duo justo magna dolore erat amet</p>
                            <div class="row g-4 mb-4">
                                <div class="col-sm-6">
                                    <div class="d-flex align-items-center border-start border-5 border-primary px-3">
                                        <h1 class="flex-shrink-0 display-5 text-primary mb-0" data-toggle="counter-up">15</h1>
                                        <div class="ps-4">
                                            <p class="mb-0">Years of</p>
                                            <h6 class="text-uppercase mb-0">Experience</h6>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="d-flex align-items-center border-start border-5 border-primary px-3">
                                        <h1 class="flex-shrink-0 display-5 text-primary mb-0" data-toggle="counter-up">50</h1>
                                        <div class="ps-4">
                                            <p class="mb-0">Popular</p>
                                            <h6 class="text-uppercase mb-0">Master Chefs</h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <a class="btn btn-primary py-3 px-5 mt-2" href="">Read More</a>
                        </div>
                    </div>
                </div>
            </div>
            <!-- About End -->


            <!-- Menu Start -->
            <div class="container-xxl py-5">
                <div class="container">
                    <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                        <h5 class="section-title ff-secondary text-center text-primary fw-normal">Food Menu</h5>
                        <h1 class="mb-5">New arrival Items</h1>
                    </div>
                    <div class="tab-class text-center wow fadeInUp" data-wow-delay="0.1s">

                        <div class="tab-content">
                            <div id="tab-1" class="tab-pane fade show p-0 active">
                                <div class="row g-4">
                                    <c:forEach items="${list}" var="c">
                                        <div class="col-lg-6"> <!-- Thêm class col-lg-6 tại đây -->
                                            <div class="d-flex align-items-center">
                                                <img class="flex-shrink-0 img-fluid rounded" src="${c.getImage()}" alt="Food Image" style="width: 80px;">
                                                <div class="w-100 d-flex flex-column text-start ps-4">
                                                    <h5 class="d-flex justify-content-between border-bottom pb-2">
                                                        <span>${c.getFoodName()}</span>
                                                        <span class="text-primary">$${c.getPrice()}</span>
                                                    </h5>
                                                    <small class="fst-italic">${c.getCategoryName()}</small>
                                                    <h6>${c.getStatus()}</h6>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
            <!-- Menu End -->


            <!-- Reservation Start -->


            <div class="modal fade" id="videoModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content rounded-0">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Youtube Video</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <!-- 16:9 aspect ratio -->
                            <div class="ratio ratio-16x9">
                                <iframe class="embed-responsive-item" src="" id="video" allowfullscreen allowscriptaccess="always"
                                        allow="autoplay"></iframe>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Reservation Start -->


            <!-- Team Start -->
    <div class="container-xxl pt-5 pb-3">
    <div class="container">
        <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
            <h5 class="section-title ff-secondary text-center text-primary fw-normal">Popular in Social Media</h5>
            <h1 class="mb-5">Best Sellers</h1>
        </div>
        

        <div class="row g-4">
            <c:forEach items="${list2}" var="a"> 
                <div class="col-lg-3 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
                    <div class="team-item text-center rounded overflow-hidden">
                        <div class="rounded-circle overflow-hidden m-4">
                            <img class="img-fluid" src="${a.getImage()}" alt=""> 
                        </div>
                        <h5 class="mb-0">${a.getFoodName()}</h5>
                        <small>${a.getCategoryName()}</small> 
                        <div class="d-flex justify-content-center mt-3">
                       
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
            <h1 class="mb-5">Best Buffet</h1>
        </div>
        <div class="row g-4 mt-4"> 
            <c:forEach items="${list3}" var="buffet"> 
                <div class="col-lg-3 col-md-6 wow fadeInUp" data-wow-delay="0.7s">
                    <div class="team-item text-center rounded overflow-hidden">
                        <div class="rounded-circle overflow-hidden m-4">
                            <img class="img-fluid" src="${buffet.getImage()}" alt=""> 
                        </div>
                        <h5 class="mb-0">${buffet.getBuffetName()}</h5> 
                        <small class="fst-italic">${buffet.getFoodName()}</small>
                        <small class="fst-italic">${buffet.getDrinkName()}</small>
                        <div class="d-flex justify-content-center mt-3">
                     
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>


            <!-- Team End -->


            <!-- Testimonial Start -->

            <!-- Testimonial End -->


            <!-- Footer Start -->
            <div class="container-fluid bg-dark text-light footer pt-5 mt-5 wow fadeIn" data-wow-delay="0.1s">
                <div class="container py-5">
                    <div class="row g-5">
                        <div class="col-lg-3 col-md-6">
                            <h4 class="section-title ff-secondary text-start text-primary fw-normal mb-4">Company</h4>
                            <a class="btn btn-link" href="">About Us</a>
                            <a class="btn btn-link" href="">Contact Us</a>
                            <a class="btn btn-link" href="">Reservation</a>
                            <a class="btn btn-link" href="">Privacy Policy</a>
                            <a class="btn btn-link" href="">Terms & Condition</a>
                        </div>
                        <div class="col-lg-3 col-md-6">
                            <h4 class="section-title ff-secondary text-start text-primary fw-normal mb-4">Contact</h4>
                            <p class="mb-2"><i class="fa fa-map-marker-alt me-3"></i>123 Street, New York, USA</p>
                            <p class="mb-2"><i class="fa fa-phone-alt me-3"></i>+012 345 67890</p>
                            <p class="mb-2"><i class="fa fa-envelope me-3"></i>info@example.com</p>
                            <div class="d-flex pt-2">
                                <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-twitter"></i></a>
                                <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-facebook-f"></i></a>
                                <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-youtube"></i></a>
                                <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-linkedin-in"></i></a>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-6">
                            <h4 class="section-title ff-secondary text-start text-primary fw-normal mb-4">Opening</h4>
                            <h5 class="text-light fw-normal">Monday - Saturday</h5>
                            <p>09AM - 09PM</p>
                            <h5 class="text-light fw-normal">Sunday</h5>
                            <p>10AM - 08PM</p>
                        </div>
                        <div class="col-lg-3 col-md-6">
                            <h4 class="section-title ff-secondary text-start text-primary fw-normal mb-4">Newsletter</h4>
                            <p>Dolor amet sit justo amet elitr clita ipsum elitr est.</p>
                            <div class="position-relative mx-auto" style="max-width: 400px;">
                                <input class="form-control border-primary w-100 py-3 ps-4 pe-5" type="text" placeholder="Your email">
                                <button type="button" class="btn btn-primary py-2 position-absolute top-0 end-0 mt-2 me-2">SignUp</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="container">
                    <div class="copyright">
                        <div class="row">
                            <div class="col-md-6 text-center text-md-start mb-3 mb-md-0">
                                &copy; <a class="border-bottom" href="#">Your Site Name</a>, All Right Reserved. 

                                <!--/*** This template is free as long as you keep the footer author’s credit link/attribution link/backlink. If you'd like to use the template without the footer author’s credit link/attribution link/backlink, you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". Thank you for your support. ***/-->
                                Designed By <a class="border-bottom" href="https://htmlcodex.com">HTML Codex</a><br><br>
                                Distributed By <a class="border-bottom" href="https://themewagon.com" target="_blank">ThemeWagon</a>
                            </div>
                            <div class="col-md-6 text-center text-md-end">
                                <div class="footer-menu">
                                    <a href="">Home</a>
                                    <a href="">Cookies</a>
                                    <a href="">Help</a>
                                    <a href="">FQAs</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Footer End -->


            <!-- Back to Top -->
            <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
        </div>

        <!-- JavaScript Libraries -->
        <script>
            // Show the first popup on page load
            document.getElementById('popupBanner').style.display = 'block';

            // Function to close the first popup and open the table selection popup
            function closePopup1() {
                document.getElementById('popupBanner').style.display = 'none';
                document.getElementById('popupTable').style.display = 'block';
            }

            // Function to close the second popup, but only if a table is selected
            function closePopup2() {
                var selectedTable = document.querySelector('input[name="table"]:checked');
                if (selectedTable) {
                    alert('You selected: ' + selectedTable.value);
                    document.getElementById('popupTable').style.display = 'none';
                    document.getElementById('overlay').style.display = 'none'; // Remove the overlay after closing the second popup
                } else {
                    alert('Please select a table before closing.');
                }
            }
        </script>


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