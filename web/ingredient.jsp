<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Quản lý Nguyên liệu</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet"> <!-- Your custom style -->
</head>
<body>

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

<!-- Ingredient Management Container -->
<div class="container-fluid py-5 bg-secondary">
    <div class="row justify-content-center">
        <div class="col-12 bg-dark text-white p-5 rounded shadow">
            <h5 class="section-title ff-secondary text-start text-primary fw-normal">Quản lý <b>Công thức</b></h5>
            <h1 class="text-white mb-4">Danh sách Công thức</h1>

            <!-- Action Buttons -->
            <div class="mb-4 text-end">
                <a href="#addIngredientModal" class="btn btn-success" data-toggle="modal">Thêm Nguyên liệu</a>
                <a href="ingredientlist" class="btn btn-primary">Danh sách nguyên liệu</a>
            </div>

            <c:choose>
                <c:when test="${empty ingredientList}">
                    <div class="alert alert-warning text-center">Không tìm thấy Nguyên liệu</div>
                </c:when>
                <c:otherwise>
                    <div class="table-responsive">
                        <table class="table table-dark table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th>ID món ăn</th>
                                    <th>Món ăn</th>
                                    <th>Nguyên liệu và định lượng</th>
                                    <th>Tổng định lượng</th>
                                    <th>Hành động</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${ingredientList}" var="list">
                                    <tr>
                                        <td>${list.getFoodID()}</td>
                                        <td>${list.getFoodName()}</td>
                                        <td>${list.getIngredientQuantities()}</td>
                                        <td>${list.getTotalQuantity()}</td>
                                        <td>
                                            <a href="#editIngredient" class="btn btn-warning btn-sm" data-toggle="modal" data-id="${list.getFoodID()}">Sửa</a>
                                            <a href="#deleteIngredient" class="btn btn-danger btn-sm" data-toggle="modal" data-id="${list.getFoodID()}">Xóa</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:otherwise>
            </c:choose>

            <!-- Add Ingredient Modal -->
            <div id="addIngredientModal" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="ingredient" method="post">
                            <input type="hidden" name="action" value="add">
                            <div class="modal-header">
                                <h4 class="modal-title">Tạo Công thức Mới</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <label for="foodname">Tên món</label>
                                    <select id="foodname" name="foodname" class="form-control">
                                        <c:forEach items="${foodList}" var="food">
                                            <option value="${food.getFoodName()}">${food.getFoodName()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="ingredient">Tên nguyên liệu</label>
                                    <select id="ingredient" name="ingredient" class="form-control">
                                        <c:forEach items="${ingredientList2}" var="ingredient">
                                            <option value="${ingredient.getIngredientName()}">${ingredient.getIngredientName()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Số lượng</label>
                                    <input type="number" class="form-control" name="quantity" required>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Thoát</button>
                                <input type="submit" class="btn btn-success" value="Thêm">
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <!-- Edit Modal -->
            <div id="editIngredient" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="ingredient" method="post">
                            <input type="hidden" name="action" value="edit">
                            <input type="hidden" name="foodID" value="">
                            <div class="modal-header">
                                <h4 class="modal-title">Sửa Công thức</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <label for="ingredient">Tên nguyên liệu</label>
                                    <select id="ingredient" name="ingredient" class="form-control">
                                        <c:forEach items="${ingredientList2}" var="ingredient">
                                            <option value="${ingredient.getIngredientName()}">${ingredient.getIngredientName()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Số lượng:</label>
                                    <input type="number" class="form-control" name="quantity" required>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                                <input type="submit" class="btn btn-info" value="Lưu">
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <!-- Delete Modal -->
            <div id="deleteIngredient" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="ingredient" method="post">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="foodID" value="">
                            <div class="modal-header">
                                <h4 class="modal-title">Xóa Nguyên liệu</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <div class="modal-body">
                                <p>Bạn có chắc muốn xóa?</p>
                                <p class="text-warning"><small>Hành động không thể hoàn tác.</small></p>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                                <input type="submit" class="btn btn-danger" value="Xóa">
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
            <script>
                $('#deleteIngredient').on('show.bs.modal', function(event) {
                    var button = $(event.relatedTarget);
                    var foodID = button.data('id');
                    var modal = $(this);
                    modal.find('input[name="foodID"]').val(foodID);
                });
                $('#editIngredient').on('show.bs.modal', function(event) {
                    var button = $(event.relatedTarget);
                    var foodID = button.data('id');
                    var modal = $(this);
                    modal.find('input[name="foodID"]').val(foodID);
                });
            </script>
        </div>
    </div>
</div>

</body>
</html>