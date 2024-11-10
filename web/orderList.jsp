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
                          
                            <a href="menu" class="nav-item nav-link active">Gọi thêm món</a>
                            <div class="nav-item dropdown">
                               
                            </div>
                        </div>
                    </div>
                </nav>

                <div class="container-xxl py-5 bg-dark hero-header mb-5">
                    <div class="container text-center my-5 pt-5 pb-4">
                        <h1 class="display-3 text-white mb-3 animated slideInDown">Các món ăn đã gọi</h1>
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb justify-content-center text-uppercase">
                                
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
            <!-- Navbar & Hero End -->


            <!-- Menu Start -->
            <div class="container-xxl py-5">
                <div class="container">
                    <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                        <h5 class="section-title ff-secondary text-center text-primary fw-normal">Món ăn của bạn</h5>
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
                                    <c:set var="fTotal" value="0" />
                                    <c:set var="dTotal" value="0" />
                                    <c:set var="bTotal" value="0" />
                                   

                                 <c:forEach items="${goc}" var="f">
    <div class="col-lg-6">
        <div class="d-flex align-items-center">
            <img class="flex-shrink-0 img-fluid rounded" src="${f.getImage()}" alt="" style="width: 80px;">
            <div class="w-100 d-flex flex-column text-start ps-4">
                <h5 class="d-flex justify-content-between border-bottom pb-2">
                    <span>${f.getFoodName()}</span>
                    Price: <span class="text-primary">${f.getPrice()}Đ</span>
                    Quantity: <span class="text-primary">${f.getQuantity()}</span>
                </h5>
                <small class="fst-italic">Note: ${f.getGuestNote()}</small>

                <!-- Form xóa -->
                <form id="deleteForm${f.getOrderID()}" action="orderlist" method="post" style="display: inline;">
                    <input type="hidden" name="product" value="food" />
                    <input type="hidden" name="orderID" value="${f.getOrderID()}" />
                    <input type="hidden" name="orderFoodID" value="${f.getOrder_FoodID()}" />
                    <input type="hidden" name="actionB" value="delete" />
                    <button type="button" class="btn btn-danger btn-sm me-2" onclick="confirmDelete(${f.getOrderID()})">Bỏ chọn món</button>
                </form>

                <script>
                    function confirmDelete(orderID) {
                        if (confirm("Are you sure you want to delete this order?")) {
                            document.getElementById("deleteForm" + orderID).submit();
                        }
                    }
                </script>

                <!-- Nút Update -->
                <button type="button" class="btn btn-success btn-sm" data-bs-toggle="modal" data-bs-target="#infoFormModal${f.getOrderID()}">
                    Chỉnh sửa
                </button>

                <!-- Modal chứa form -->
                <div class="modal fade" id="infoFormModal${f.getOrderID()}" tabindex="-1" aria-labelledby="infoFormModalLabel${f.getOrderID()}" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="infoFormModalLabel${f.getOrderID()}">Fill Your Information</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <form action="orderlist" method="post">
                                <input type="hidden" name="product" value="food" />
                                <input type="hidden" name="orderID" value="${f.getOrderID()}" />
                                <input type="hidden" name="orderFoodID" value="${f.getOrder_FoodID()}" />
                                <input type="hidden" name="actionB" value="update" />
                                <div class="modal-body">
                                    <div class="mb-3">
                                        <label for="quantity${f.getOrderID()}" class="form-label">Quantity:</label>
                                        <input type="number" id="quantity${f.getOrderID()}" name="quantity" min="0" max="10" value="${f.getQuantity()}" required>
                                    </div>
                                    <div class="mb-3">
                                        <label for="note${f.getOrderID()}" class="form-label">Note:</label>
                                        <input type="text" id="note${f.getOrderID()}" name="note" value="${f.getGuestNote()}">
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
    <c:set var="fTotal" value="${fTotal + (f.getPrice() * f.getQuantity())}" />
</c:forEach>

<c:forEach items="${drinkOrderList}" var="d">
    <div class="col-lg-6">
        <div class="d-flex align-items-center">
            <img class="flex-shrink-0 img-fluid rounded" src="${d.getImage()}" alt="" style="width: 80px;">
            <div class="w-100 d-flex flex-column text-start ps-4">
                <h5 class="d-flex justify-content-between border-bottom pb-2">
                    <span>${d.getDrinkName()}</span>
                    Price: <span class="text-primary">${d.getPrice()}Đ</span>
                    Quantity: <span class="text-primary">${d.getQuantity()}</span>
                </h5>
                <small class="fst-italic">Note: ${d.getGuestNote()}</small>

                <!-- Form xóa -->
                <form id="deleteForm${d.getOrderID()}" action="orderlist" method="post" style="display: inline;">
                    <input type="hidden" name="orderID" value="${d.getOrderID()}" />
                    <input type="hidden" name="orderDrinkID" value="${d.getOrder_DrinkID()}" />
                    <input type="hidden" name="actionB" value="delete" />
                    <input type="hidden" name="product" value="drink" />
                    <button type="button" class="btn btn-danger btn-sm me-2" onclick="confirmDelete(${d.getOrderID()})">Bỏ chọn món</button>
                </form>

                <script>
                    function confirmDelete(orderID) {
                        if (confirm("Are you sure you want to delete this order?")) {
                            document.getElementById("deleteForm" + orderID).submit();
                        }
                    }
                </script>

                <!-- Nút Update -->
                <button type="button" class="btn btn-success btn-sm" data-bs-toggle="modal" data-bs-target="#infoFormModal${d.getOrderID()}">
                    Chỉnh sửa
                </button>

                <!-- Modal chứa form -->
                <div class="modal fade" id="infoFormModal${d.getOrderID()}" tabindex="-1" aria-labelledby="infoFormModalLabel${d.getOrderID()}" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="infoFormModalLabel${d.getOrderID()}"></h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <form action="orderlist" method="post">
                                <input type="hidden" name="product" value="drink" />
                                <input type="hidden" name="orderID" value="${d.getOrderID()}" />
                                <input type="hidden" name="orderDrinkID" value="${d.getOrder_DrinkID()}" />
                                <input type="hidden" name="actionB" value="update" />
                                <div class="modal-body">
                                    <div class="mb-3">
                                        <label for="quantity${d.getOrderID()}" class="form-label">Số lượng</label>
                                        <input type="number" id="quantity${d.getOrderID()}" name="quantity" min="0" max="10" value="${f.getQuantity()}" required>
                                    </div>
                                    <div class="mb-3">
                                        <label for="note${d.getOrderID()}" class="form-label">ghi chú</label>
                                        <input type="text" id="note${d.getOrderID()}" name="note" value="${d.getGuestNote()}">
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                    <button type="submit" class="btn btn-primary">Xác nhận</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
    <c:set var="dTotal" value="${dTotal + (d.getPrice() * d.getQuantity())}" />
</c:forEach>
                                    
                                    
                                    
                                <c:forEach items="${buffetOrderList}" var="b">
    <div class="col-lg-6">
        <div class="d-flex align-items-center">
            <img class="flex-shrink-0 img-fluid rounded" src="${b.getImage()}" alt="" style="width: 80px;">
            <div class="w-100 d-flex flex-column text-start ps-4">
                <h5 class="d-flex justify-content-between border-bottom pb-2">
                    <span>${b.getBuffetName()}</span>
                    Price: <span class="text-primary">${b.getPrice()}Đ</span>
                </h5>
                <small class="fst-italic">Note: ${b.getGuestNote()}</small>

                <!-- Form xóa -->
                <form id="deleteForm${b.getOrderID()}" action="orderlist" method="post" style="display: inline;">
                    <input type="hidden" name="product" value="buffet" />
                    <input type="hidden" name="orderID" value="${b.getOrderID()}" />
                    <input type="hidden" name="orderBuffetID" value="${b.getOrder_BuffetID()}" />
                    <input type="hidden" name="actionB" value="delete" />
                    <button type="button" class="btn btn-danger btn-sm me-2" onclick="confirmDelete(${b.getOrderID()})">Bỏ chọn món</button>
                </form>

                <script>
                    function confirmDelete(orderID) {
                        if (confirm("Are you sure you want to delete this order?")) {
                            document.getElementById("deleteForm" + orderID).submit();
                        }
                    }
                </script>

                <!-- Nút Update -->
                <button type="button" class="btn btn-success btn-sm" data-bs-toggle="modal" data-bs-target="#infoFormModal${b.getOrderID()}">
                    Chỉnh sửa
                </button>

                <!-- Modal chứa form -->
                <div class="modal fade" id="infoFormModal${b.getOrderID()}" tabindex="-1" aria-labelledby="infoFormModalLabel${b.getOrderID()}" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="infoFormModalLabel${b.getOrderID()}">Đặt món</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <form action="orderlist" method="post">
                                <input type="hidden" name="product" value="buffet" />
                                <input type="hidden" name="orderID" value="${b.getOrderID()}" />
                                <input type="hidden" name="orderBuffetID" value="${b.getOrder_BuffetID()}" />
                                <input type="hidden" name="actionB" value="update" />
                                <div class="modal-body">
                                    <div class="mb-3">
                                        <label for="note${b.getOrderID()}" class="form-label">Note:</label>
                                        <input type="text" id="note${b.getOrderID()}" name="note" value="${b.getGuestNote()}">
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">hủy</button>
                                    <button type="submit" class="btn btn-primary">Đặt món</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <c:set var="bTotal" value="${bTotal + b.getPrice()}" />
</c:forEach>

<c:set var="grandTotal" value="${fTotal + dTotal + bTotal}" />
                                    <div class="col-12 text-center">
    <h1 class="text-primary">Total Amount: ${grandTotal}Đ</h1>
                                    </div>
                                    
                                 


                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

          
            <form action="checkout" method="post"> 
                <label for="total"></label>
                <input type="hidden" id="total" name="total" step="0.01" required value="${grandTotal}">
                <button type="submit" class="btn btn-primary">Hoàn tất gọi món</button>
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