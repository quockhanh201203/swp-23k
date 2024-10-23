<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
        <!-- Sidebar -->
        <%@ include file="menu-sidebar.jsp" %>

        <div class="mt-5 main-content">
            <h2>Food List</h2>

            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addFoodModal">
                Add Food
            </button>


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

            <table id="foodTable" class="table table-striped">
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
                    <c:forEach var="food" items="${foodList}">
                        <tr>
                            <td>${food.foodID}</td>
                            <td>${food.foodName}</td>
                            <td>${food.foodCategory.categoryName}</td>
                            <td>${food.price}</td>
                            <td>${food.status == 1 ? 'Active' : 'Inactive'}</td>
                            <td><img src="${food.image}" alt="Food Image" width="100" height="100"/></td>
                            <td>
                                <button type="button" class="btn btn-info" data-toggle="modal" data-target="#updateFoodModal_${food.foodID}">Edit</button>
                                <form action="foods" " method="post" style="display:inline-block;">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="status" value="${food.status == 1 ? 0 : 1}">
                                    <input type="hidden" name="id" value="${food.foodID}">
                                    <c:if test="${food.status == 1}">
                                        <button type="submit" class="btn btn-danger">Delete</button>
                                    </c:if>
                                        <c:if test="${food.status != 1}">
                                        <button type="submit" class="btn btn-success">Active</button>
                                    </c:if>
                                    
                                </form>
                                <!-- Food Update Modal -->
                                <div class="modal fade" id="updateFoodModal_${food.foodID}" tabindex="-1" aria-labelledby="updateFoodModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <form action="foods" method="POST">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="addDrinkModalLabel">Add Drink</h5>
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
                                                        <input type="text" class="form-control" name="foodName" value="${food.foodName}" required>
                                                    </div>

                                                    <!-- Category ID -->
                                                    <div class="form-group">
                                                        <label for="category">Category:</label>
                                                        <select name="categoryId" class="form-control">
                                                            <c:forEach items="${foodCategorys}" var="fc">
                                                                <option value="${fc.categoryID}" ${fc.categoryID == food.categoryID? 'selected' : ''}>${fc.categoryName}</option>
                                                            </c:forEach>
                                                        </select>

                                                    </div>

                                                    <!-- Status -->
                                                    <div class="mb-3">
                                                        <label for="status" class="form-label">Status</label>
                                                        <select class="form-control" name="status" required>
                                                            <option value="Available" ${food.status == 1 ? 'selected' : ''}>Active</option>
                                                            <option value="Unavailable" ${food.status == 0 ? 'selected' : ''}>De-Active</option>
                                                        </select>
                                                    </div>

                                                    <div class="mb-3">
                                                        <label for="image" class="form-label">Price</label>
                                                        <input type="text" class="form-control" name="price" value="${food.price}" required>
                                                    </div>

                                                    <!-- Image URL -->
                                                    <div class="mb-3">
                                                        <label for="image" class="form-label">Image URL</label>
                                                        <input type="text" class="form-control" name="image" value="${food.image}" required>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
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
                                <input type="text" class="form-control" id="foodName" name="foodName" required>
                            </div>
                            <div class="form-group">
                                <label for="foodPrice">Food Price</label>
                                <input type="number" class="form-control" id="foodPrice" name="foodPrice" required>
                            </div>
                            <div class="form-group">
                                <label for="foodImage">Image URL</label>
                                <input type="text" class="form-control" id="foodImage" name="foodImage" required>
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
        </script>

    </body>
</html>
