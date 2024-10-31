<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User List</title>
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <!-- DataTable CSS -->
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.24/css/dataTables.bootstrap4.min.css">
        <!-- Font Awesome CSS for icons -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">

        <style>
            .modal-lg {
                max-width: 80%;
            }

            .table th, .table td {
                vertical-align: middle;
            }

        </style>
    </head>
    <body>



        <div class="modal fade" id="addFoodModal" tabindex="-1" role="dialog" aria-labelledby="addFoodModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document" style="max-width: 90vw;">
                <div class="modal-content" style="display: inline-block;">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addFoodModalLabel">Add Food</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="buffet-detail" method="post" class="mt-3">
                            <!-- Pass buffetId as a hidden field -->
                            <input type="hidden" name="action" value="add">
                            <input type="hidden" name="buffetId" value="${buffetId}"/>

                            <table id="selectFood" class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>Select</th>
                                        <th>Food ID</th>
                                        <th>Food Name</th>
                                        <th>Category ID</th>
                                        <th>Status</th>
                                        <th>Image</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="food" items="${foodsNotInBuffet}">
                                        <tr>
                                            <td><input type="checkbox" name="foodIds" value="${food.foodID}"/></td>
                                            <td>${food.foodID}</td>
                                            <td>${food.foodName}</td>
                                            <td>${food.categoryID}</td>
                                            <td>${food.status}</td>
                                            <td><img src="${food.image}" alt="${food.foodName}" style="width: 50px; height: 50px;"></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <button type="submit" class="btn btn-primary">Add Selected Foods</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>



        <%@ include file="admin-header.jsp" %>

        <div class="container-fluid py-5 bg-secondary" >

            <div class="row justify-content-center">
                <div class="col-12 bg-dark d-flex align-items-center">
                    <div class="p-5 w-100">
                        <h5 class="section-title ff-secondary text-start text-primary fw-normal">Buffet Food</h5>
                        <h1 class="text-white mb-4">Buffet Food</h1>
                        <div class="table-responsive">
                            <c:if test="${param.success ne null}">
                                <div class="alert alert-success" role="alert">
                                    Success!
                                </div>
                            </c:if>
                            <c:if test="${param.fail ne null}">
                                <div class="alert alert-danger" role="alert">
                                    Failed!
                                </div>
                            </c:if>
                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addFoodModal">
                                Add Food to Buffet
                            </button>  
                            <table id="foodTable" class="table table-light table-striped table-bordered">
                                <thead>
                                    <tr>
                                        <th>Food ID</th>
                                        <th>Food Name</th>
                                        <th>Category ID</th>
                                        <th>Price</th>
                                        <th>Status</th>
                                        <th>Image</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="food" items="${foods}">
                                        <tr>
                                            <td>${food.foodID}</td>
                                            <td>${food.foodName}</td>
                                            <td>${food.foodCategory.categoryName}</td>
                                            <td>${food.price}</td>
                                            <td>${food.status}</td>
                                            <td><img src="${food.image}" alt="Food Image" width="100" height="100"/></td>
                                            <td>
                                                <form action="buffet-detail" method="post" class="mt-3">
                                                    <input type="hidden" name="action" value="delete">
                                                    <input type="hidden" name="foodId" value="${food.foodID}">
                                                    <input type="hidden" name="buffetId" value="${buffetId}">
                                                    <button type="submit" class="btn btn-danger">Remove</button>

                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>




        <%@ include file="footer.jsp" %>
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
        <!-- Bootstrap JS and jQuery -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <!-- DataTable JS -->
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.24/js/dataTables.bootstrap4.min.js"></script>

        <script>
            $(window).resize(function () {
                console.log($(window).height());
                $('.dataTables_scrollBody').css('height', ($(window).height() - 200));
            });
            $(document).ready(function () {
                $('#foodTable').DataTable({
                    pageLength: 5,
                    "lengthChange": false,
                    "sScrollY": ($(window).height() - 300)
                });
            });

            $(document).ready(function () {
                $('#selectFood').DataTable({
                    pageLength: 5,
                    "lengthChange": false
                });
            });
        </script>
    </body>
</html>
