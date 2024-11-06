<%-- 
    Document   : menu
    Created on : Oct 2, 2024, 6:48:28 AM
    Author     : tran tung
--%>

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
                    <h1 class="text-primary m-0"><i class="fa fa-utensils me-3"></i>5anhluc</h1>
                    <!-- <img src="img/logo.png" alt="Logo"> -->
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                    <span class="fa fa-bars"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarCollapse">
                    <div class="navbar-nav ms-auto py-0 pe-4">
                        
                </div>
            </nav>

            <div class="container-xxl py-5 bg-dark hero-header mb-5">
                <div class="container text-center my-5 pt-5 pb-4">
                    <h1 class="display-3 text-white mb-3 animated slideInDown">ĐỒ uống theo thể loại</h1>
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
                </div>
                <div class="tab-class text-center wow fadeInUp" data-wow-delay="0.1s">
                  
                    <div class="tab-content">
                        <div id="tab-1" class="tab-pane fade show p-0 active">
                    
                       <div class="row g-6">
    <!-- Cột hiển thị các đồ uống với chỉ mục chẵn (0, 2, 4, ...) -->
    <div class="col-lg-6">
        <c:forEach items="${drinkList}" var="drink" varStatus="status">
            <c:if test="${status.index % 2 == 0}">
                <div class="d-flex align-items-center mb-4">
                    <img class="flex-shrink-0 img-fluid rounded" src="${drink.image}" alt="Drink Image" style="width: 80px;">
                    <div class="w-100 d-flex flex-column text-start ps-4">
                        <h5 class="d-flex justify-content-between border-bottom pb-2">
                            <span>${drink.drinkName}</span>
                            <span class="text-primary">${drink.price}Đ</span>
                        </h5>
                        <small class="fst-italic">${drink.categoryName}</small>
                        <a href="javascript:void(0);" class="btn btn-primary py-2 px-4" 
                           onclick="openPopUp('${drink.getDrinkID()}')">Gọi món</a>
                    </div>
                </div>
            </c:if>
        </c:forEach>
    </div>

    <!-- Cột hiển thị các đồ uống với chỉ mục lẻ (1, 3, 5, ...) -->
    <div class="col-lg-6">
        <c:forEach items="${drinkList}" var="drink" varStatus="status">
            <c:if test="${status.index % 2 == 1}">
                <div class="d-flex align-items-center mb-4">
                    <img class="flex-shrink-0 img-fluid rounded" src="${drink.image}" alt="Drink Image" style="width: 80px;">
                    <div class="w-100 d-flex flex-column text-start ps-4">
                        <h5 class="d-flex justify-content-between border-bottom pb-2">
                            <span>${drink.drinkName}</span>
                            <span class="text-primary">${drink.price}Đ</span>
                        </h5>
                        <small class="fst-italic">${drink.categoryName}</small>
                        <a href="javascript:void(0);" class="btn btn-primary py-2 px-4" 
                           onclick="openPopUp('${drink.getDrinkID()}')">Gọi món</a>
                    </div>
                </div>
            </c:if>
        </c:forEach>
    </div>
</div>

<!-- Popup Form - Chỉ khai báo một lần -->
<div id="popupDiv" style="display:none;" title="Thêm món">
    <form id="userForm" action="addtoorder" method="post">
        <input type="hidden" name="addType" value="drink">
        <input type="hidden" id="foodIdInput" name="drinkID" readonly/><br/><br/>
        Số lượng: <input type="number" name="quantity" min="0" max="10" required><br/><br/>
        Ghi chú: <input type="text" name="note"><br/><br/>
        <button type="submit" class="btn btn-primary py-2" style="margin-top: 10px;">đặt món</button>
    </form>
</div>

<!-- Script để mở popup - Chỉ khai báo một lần -->
<script>
    function openPopUp(drinkID) {
        // Đặt giá trị của drinkID vào input form
        $('#foodIdInput').val(drinkID);

        // Hiển thị popup với jQuery UI
        $('#popupDiv').dialog({
            modal: true,
            width: 400,
            buttons: {
                "Close": function () {
                    $(this).dialog("close");
                }
            }
        });
    }
</script>




                                
                                
                               
                               
                               
                              
                         
                        </div>
                        <div id="tab-2" class="tab-pane fade show p-0">
                            <div class="row g-4">
                                <div class="col-lg-6">
                                    
                                    
                                        </div>
                            </div>
                        </div>
                        
             
                <div id="tab-4" class="tab-pane fade show p-0">
                            <div class="row g-4">
                                <div class="col-lg-6">
                                    
                                    
                                        </div>
                            </div>
                        </div>          
                        
                        
                        
                        
            </div>
        </div>
        <!-- Menu End -->
        

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
