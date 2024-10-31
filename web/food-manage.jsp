<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Food List</title>
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <!-- DataTable CSS -->
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.24/css/dataTables.bootstrap4.min.css">
        <!-- Font Awesome CSS for icons -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
        <!-- Libraries Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/style.css" rel="stylesheet">
        <style>
            .modal-lg {
                max-width: 100%;
            }

            .table th, .table td {
                vertical-align: middle;
            }

        </style>
    </head>
    <body>
        <!-- Sidebar -->

        <%@ include file="admin-header.jsp" %>
        <div class="modal fade" id="addFoodModal" tabindex="-1" role="dialog" aria-labelledby="addFoodModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addFoodModalLabel">Add Food</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="foods" method="post">
                            <input type="hidden" name="type" value="food">
                            <input type="hidden" name="action" value="add">
                            <div class="form-group">
                                <label for="foodName">Food Name</label>
                                <input type="text" class="form-control" id="foodName" pattern=".*\S.*" title="Input cannot be only spaces" name="foodName" required>
                            </div>
                            <div class="form-group">
                                <label for="foodPrice">Food Price</label>
                                <input type="number" class="form-control" min="1" id="foodPrice" name="foodPrice" required>
                            </div>
                            <div class="form-group">
                                <label for="foodImage">Image URL</label>
                                <input type="text" class="form-control" id="foodImage" pattern=".*\S.*" title="Input cannot be only spaces" name="foodImage" required>
                            </div>
                            <div class="form-group">
                                <label for="category">Category:</label>
                                <select name="categoryId" class="form-control">
                                    <c:forEach items="${foodCategorys}" var="fc">
                                        <option value="${fc.categoryID}">${fc.categoryName}</option>
                                    </c:forEach>
                                </select>

                            </div>
                            <button type="submit" class="btn btn-primary">Add Food</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>




        <div class="container-fluid py-5 bg-secondary" >

            <div class="row justify-content-center">
                <div class="col-12 bg-dark d-flex align-items-center">
                    <div class="p-5 w-100">
                        <h5 class="section-title ff-secondary text-start text-primary fw-normal">Food List</h5>
                        <h1 class="text-white mb-4">Food List</h1>

                        <form action="price-history" method="get" class="form-inline mb-4">
                            <div class="form-group mr-3">
                                <label for="categoryFilter">Category:</label>
                                <select id="categoryFilter" class="form-control">
                                    <option value="">All</option>
                                    <c:forEach items="${foodCategorys}" var="fc">
                                        <option value="${fc.categoryName}">${fc.categoryName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group mr-3">
                                <label for="statusFilter">Status: </label>
                                <select id="statusFilter" class="form-control">
                                    <option value="">All</option>
                                    <option value="Active">Active</option>
                                    <option value="De-Active">De-Active</option>
                                </select>
                            </div>
                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addFoodModal">
                                Add Food
                            </button>
                        </form>

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



                            <table id="foodTable" class="table table-light table-striped table-bordered">
                                <thead>
                                    <tr>
                                        <th>Food ID</th>
                                        <th>Food Name</th>
                                        <th>Category Name</th>
                                        <th>Price</th>
                                        <th>Status</th>
                                        <th>Image</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="food" items="${foodList}">
                                        <tr>
                                            <td>${food.foodID}</td>
                                            <td>${food.foodName}</td>
                                            <td>${food.foodCategory.categoryName}</td>
                                            <td>${food.price}</td>
                                            <td>${food.status}</td>
                                            <td><img src="${food.image}" alt="Food Image" width="100" height="100"/></td>
                                            <td>
                                                <button type="button" class="btn btn-info" data-toggle="modal" data-target="#updateFoodModal_${food.foodID}">Edit</button>
                                                <form action="foods" " method="post" style="display:inline-block;">
                                                    <input type="hidden" name="action" value="delete">
                                                    <input type="hidden" name="status" value="${food.status == 'Active' ?  'De-Active' : 'Active'}">
                                                    <input type="hidden" name="id" value="${food.foodID}">
                                                    <c:if test="${food.status == 'Active'}">
                                                        <button type="submit" class="btn btn-danger">De Active</button>
                                                    </c:if>
                                                    <c:if test="${food.status == 'De-Active'}">
                                                        <button type="submit" class="btn btn-success">Active</button>
                                                    </c:if>

                                                </form>
                                                <!-- Food Update Modal -->
                                                <div class="modal fade" id="updateFoodModal_${food.foodID}" tabindex="-1" aria-labelledby="updateFoodModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog">
                                                        <div class="modal-content">
                                                            <form action="foods" method="POST">
                                                                <div class="modal-header">
                                                                    <h5 class="modal-title" id="addDrinkModalLabel">Edit Food</h5>
                                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                        <span aria-hidden="true">&times;</span>
                                                                    </button>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <!-- Food ID (hidden) -->
                                                                    <input type="hidden" name="foodID" value="${food.foodID}">

                                                                    <!-- Food Name -->
                                                                    <div class="mb-3">
                                                                        <label for="foodName" class="form-label">Food Name</label>
                                                                        <input type="text" class="form-control" name="foodName" pattern=".*\S.*" title="Input cannot be only spaces" value="${food.foodName}"  required>
                                                                    </div>

                                                                    <!-- Category ID -->
                                                                    <div class="form-group">
                                                                        <label for="category">Category:</label>
                                                                        <select name="categoryId" class="form-control">
                                                                            <c:forEach items="${foodCategorys}" var="fc">
                                                                                <option value="${fc.categoryID}" ${fc.categoryID == food.categoryID ? 'selected' : ''}>${fc.categoryName}</option>
                                                                            </c:forEach>
                                                                        </select>
                                                                    </div>

                                                                    <!-- Status -->
                                                                    <div class="mb-3">
                                                                        <label for="status" class="form-label">Status</label>
                                                                        <select class="form-control" name="status" required>
                                                                            <option value="Active" ${food.status == 'Active' ? 'selected' : ''}>Active</option>
                                                                            <option value="Deactive" ${food.status == 'Deactive' ? 'selected' : ''}>De-Active</option>
                                                                        </select>
                                                                    </div>

                                                                    <!-- Price -->
                                                                    <div class="mb-3">
                                                                        <label for="price" class="form-label">Price</label>
                                                                        <input type="text" class="form-control" name="price" value="${food.price}" min="1" required>
                                                                    </div>

                                                                    <!-- Image URL -->                          
                                                                    <div class="mb-3">
                                                                        <label for="image" class="form-label">Image URL</label>
                                                                        <input type="text" class="form-control" name="image" value="${food.image}" pattern=".*\S.*" title="Input cannot be only spaces" required>
                                                                    </div>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                                    <button type="submit" class="btn btn-primary">Update Food</button>
                                                                </div>
                                                                <!-- Set action type -->
                                                                <input type="hidden" name="action" value="update">
                                                                <input type="hidden" name="type" value="food">
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
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
                var table = $('#foodTable').DataTable({
                    pageLength: 5,
                    "lengthChange": false,
                    "sScrollY": ($(window).height() - 300)
                });

                $('#categoryFilter').on('change', function () {
                    var categoryValue = $(this).val();
                    table.column(2).search(categoryValue).draw(); // Adjust column index accordingly
                });

                // Event listener for status filter
                $('#statusFilter').on('change', function () {
                    
                    var statusValue = $(this).val();

                    // If "All" is selected, reset the search to show all statuses
                    if (statusValue === "") {
                        table.column(4).search('').draw(); // Clear search
                    } else {
                        // Custom search for exact match
                        table.column(4).search('^' + statusValue + '$', true, false).draw();
                    }
                    
                });

            });
        </script>
    </body>
</html>
