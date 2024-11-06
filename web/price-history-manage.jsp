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
        <%@ include file="admin-header.jsp" %>

        <div class="container-fluid py-5 bg-secondary" >

            <div class="row justify-content-center">
                <div class="col-12 bg-dark d-flex align-items-center">
                    <div class="p-5 w-100">
                        <h5 class="section-title ff-secondary text-start text-primary fw-normal">Price History List</h5>
                        <h1 class="text-white mb-4">Price History List</h1>

                        <form action="price-history" method="get" class="form-inline mb-4">
                            <div class="form-group mr-3">
                                <label style="color: white" for="startDate" class="mr-2">Start Date:</label>
                                <input type="date" id="startDate" name="startDate" class="form-control" value="${startDate}" />
                            </div>
                            <div class="form-group mr-3">
                                <label style="color: white" for="endDate" class="mr-2">End Date:</label>
                                <input type="date" id="endDate" name="endDate" class="form-control" value="${endDate}" />
                            </div>
                            <button type="submit" class="btn btn-primary">Filter</button>
                        </form>    
                        <div class="table-responsive">

                            <table id="history" class="table table-light table-striped table-bordered">
                                <thead class="thead-dark">
                                    <tr>
                                        <th>Price_HistoryID</th>
                                        <th>ProductID</th>
                                        <th>Price</th>
                                        <th>StartDate</th>
                                        <th>EndDate</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="ph" items="${priceHistoryList}">
                                        <tr>
                                            <td>${ph.priceHistoryId}</td>
                                            <td>${ph.productId}</td>
                                            <td>${ph.price}</td>
                                            <td>${ph.startDate}</td>
                                            <td>${ph.endDate}</td>
                                            <td>
                                                <form action="price-history" method="post">
                                                    <input type="hidden" name="priceHistoryId" value="${ph.priceHistoryId}">
                                                    <button type="submit" class="btn btn-danger">Delete</button>
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
            $(document).ready(function () {
                $('#history').DataTable({
                    pageLength: 15,
                    "lengthChange": false
                });
            });
        </script>

    </body>
</html>
