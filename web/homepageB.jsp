    

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
if (roleID == null) {
        // RoleID 1 is customer
            %><%@ include file="customer-header.jsp" %><%
    }else if (roleID == 1) {
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
                    <div class="container my-5 py-5">
                        <div class="row align-items-center g-5">
                            <div class="col-lg-6 text-center text-lg-start">
                                <h1 class="display-3 text-white animated slideInLeft">5 Anh Luc<br>Cửa hàng đồ ăn nhanh</h1>
                            </div>
                            <div class="col-lg-6 text-center text-lg-end overflow-hidden">
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
                                   
                                  
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3 col-sm-6 wow fadeInUp" data-wow-delay="0.3s">
                           
                        </div>
                        <div class="col-lg-3 col-sm-6 wow fadeInUp" data-wow-delay="0.5s">
                            
                        </div>
                        <div class="col-lg-3 col-sm-6 wow fadeInUp" data-wow-delay="0.7s">
                           
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
                            <p class="mb-4">Nhà hàng đồ ăn nhanh của chúng tôi là điểm đến lý tưởng cho những ai yêu thích hương vị nhanh, ngon và tiện lợi. Tại đây, khách hàng có thể thưởng thức đa dạng các món ăn từ gà rán giòn tan, burger nóng hổi đến khoai tây chiên vàng ruộm, được chế biến từ nguyên liệu tươi ngon và đảm bảo chất lượng.</p>
                            <p class="mb-4">Với không gian hiện đại, trẻ trung và dịch vụ nhanh chóng, chúng tôi cam kết mang đến trải nghiệm ẩm thực tiện lợi, đáp ứng nhu cầu của những người bận rộn hay gia đình muốn thưởng thức bữa ăn ngon miệng trong không gian thoải mái.</p>
                            <div class="row g-4 mb-4">
                                <div class="col-sm-6">
                                    <div class="d-flex align-items-center border-start border-5 border-primary px-3">
                                        <div class="ps-4">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="d-flex align-items-center border-start border-5 border-primary px-3">
                                        <div class="ps-4">
                                          
                                        </div>
                                    </div>
                                </div>
                            </div>
                          
                        </div>
                    </div>
                </div>
            </div>
            <!-- About End -->


            <!-- Menu Start -->
                <div class="container-xxl py-5">
                <div class="container">
                    <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                        <h5 class="section-title ff-secondary text-center text-primary fw-normal"> Menu đồ ăn</h5>
                        <h1 class="mb-5">Sản phẩm mới</h1>
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
            <h5 class="section-title ff-secondary text-center text-primary fw-normal">Bán chạy nhất</h5>
                
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
            <h5 class="section-title ff-secondary text-center text-primary fw-normal">Ăn thả ga không lo về giá</h5>
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
