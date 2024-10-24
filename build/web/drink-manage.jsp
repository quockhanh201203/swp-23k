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
            <h2>Drink List</h2>

            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addDrinkModal">
                Add Drink
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

            <table id="drinkTable" class="table table-striped">
                <thead>
                    <tr>
                        <th>Drink ID</th>
                        <th>Drink Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Category ID</th>
                        <th>Brand ID</th>
                        <th>Status</th>
                        <th>Image</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="drink" items="${drinkList}">
                        <tr>
                            <td>${drink.drinkID}</td>
                            <td>${drink.drinkName}</td>
                            <td>${drink.price}</td>
                            <td>${drink.quantity}</td>
                            <td>${drink.drinkCategory.categoryName}</td>
                            <td>${drink.brand.brandName}</td>
                            <td>${drink.status == 1 ? 'Active' : 'Inactive'}</td>
                            <td><img src="${drink.image}" alt="Drink Image" width="100" height="100"/></td>
                            <td>
                                <button type="button" class="btn btn-info" data-toggle="modal" data-target="#editDrinkModal_${drink.drinkID}">Edit</button>
                                <form action="drinks" " method="post" style="display:inline-block;">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="status" value="${drink.status == 1 ? 0 : 1}">
                                    <input type="hidden" name="id" value="${drink.drinkID}">
                                    <c:if test="${drink.status == 1}">
                                        <button type="submit" class="btn btn-danger">Delete</button>
                                    </c:if>
                                    <c:if test="${drink.status != 1}">
                                        <button type="submit" class="btn btn-success">Active</button>
                                    </c:if>

                                </form>
                                <!-- Drink Update Modal -->
                                <div class="modal fade" id="editDrinkModal_${drink.drinkID}" tabindex="-1" aria-labelledby="updateDrinkModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <form action="drinks" method="POST">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="addBuffetModalLabel">Add Buffet</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    <!-- Drink ID (hidden) -->
                                                    <input type="hidden" name="drinkID" value="${drink.drinkID}">
                                                    <input type="hidden" name="type" value="drink">
                                                    <input type="hidden" name="action" value="update">

                                                    <!-- Drink Name -->
                                                    <div class="mb-3">
                                                        <label for="drinkName" class="form-label">Drink Name</label>
                                                        <input type="text" class="form-control" name="drinkName" value="${drink.drinkName}" required>
                                                    </div>

                                                    <div class="mb-3">
                                                        <label for="image" class="form-label">Price</label>
                                                        <input type="text" class="form-control" name="price" value="${drink.price}" required>
                                                    </div>

                                                    <!-- Quantity -->
                                                    <div class="mb-3">
                                                        <label for="quantity" class="form-label">Quantity</label>
                                                        <input type="number" class="form-control" name="quantity" value="${drink.quantity}" required>
                                                    </div>

                                                    <div class="mb-3">
                                                        <label for="categoryId">Category:</label>
                                                        <select name="categoryId" class="form-control">
                                                            <c:forEach items="${drinkCategorys}" var="dc">
                                                                <option value="${dc.categoryID}" ${dc.categoryID == drink.categoryID? 'selected' : ''}>${dc.categoryName}</option>
                                                            </c:forEach>
                                                        </select>

                                                    </div>

                                                    <div class="mb-3">
                                                        <label for="brandId">Brand:</label>
                                                        <select name="brandId" class="form-control">
                                                            <c:forEach items="${brands}" var="db">
                                                                <option value="${db.brandID}" ${db.brandID == drink.brandID? 'selected' : ''}>${db.brandName}</option>
                                                            </c:forEach>
                                                        </select>

                                                    </div>

                                                    <!-- Status -->
                                                    <div class="mb-3">
                                                        <label for="status" class="form-label">Status</label>
                                                        <select class="form-control" name="status" required>
                                                            <option value="Available" ${drink.status == 1 ? 'selected' : ''}>Active</option>
                                                            <option value="Unavailable" ${drink.status == 0 ? 'selected' : ''}>De-Active</option>
                                                        </select>
                                                    </div>

                                                    <!-- Image URL -->
                                                    <div class="mb-3">
                                                        <label for="image" class="form-label">Image URL</label>
                                                        <input type="text" class="form-control" name="image" value="${drink.image}" required>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                    <button type="submit" class="btn btn-primary">Update Drink</button>
                                                </div>
                                                <!-- Set action type -->
                                                <input type="hidden" name="action" value="update">
                                                <input type="hidden" name="type" value="drink">
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

        <div class="modal fade" id="addDrinkModal" tabindex="-1" role="dialog" aria-labelledby="addDrinkModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addDrinkModalLabel">Add Drink</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="drinks" method="post">
                            <input type="hidden" name="type" value="drink">
                            <input type="hidden" name="action" value="add">
                            <div class="form-group">
                                <label for="drinkName">Drink Name</label>
                                <input type="text" class="form-control" id="drinkName" name="drinkName" required>
                            </div>

                            <div class="form-group">
                                <label for="drinkPrice">Drink Price</label>
                                <input type="number" class="form-control" id="drinkPrice" name="price" required>
                            </div>
                            <div class="form-group">
                                <label for="drinkImage">Image URL</label>
                                <input type="text" class="form-control" id="drinkImage" name="drinkImage" required>
                            </div>

                            <div class="form-group">
                                <label for="quantity">Quantity:</label>
                                <input type="text" class="form-control" id="quantity" name="quantity" required>
                            </div>

                            <div class="form-group">
                                <label for="categoryId">Category:</label>
                                <select name="categoryId" class="form-control">
                                    <c:forEach items="${drinkCategorys}" var="dc">
                                        <option value="${dc.categoryID}">${dc.categoryName}</option>
                                    </c:forEach>
                                </select>

                            </div>

                            <div class="form-group">
                                <label for="brandId">Brand:</label>
                                <select name="brandId" class="form-control">
                                    <c:forEach items="${brands}" var="db">
                                        <option value="${db.brandID}">${db.brandName}</option>
                                    </c:forEach>
                                </select>

                            </div>

                            <button type="submit" class="btn btn-primary">Add Drink</button>
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
                $('#drinkTable').DataTable({
                    pageLength: 10,
                    lengthChange: false,
                    "sScrollY": ($(window).height() - 300)
                });
            });


        </script>

    </body>
</html>
