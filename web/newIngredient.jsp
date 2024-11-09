<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Quản lý Nguyên liệu</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>

<%
    // Grab RoleID from the session
    Integer roleID = (Integer) session.getAttribute("RoleID");

    // Check RoleID and include the appropriate header
    if (roleID == null) {
        %><%@ include file="header.jsp" %><%
    } else if (roleID == 1) {
        %><%@ include file="customer-header.jsp" %><%
    } else if (roleID == 2) {
        %><%@ include file="staff-header.jsp" %><%
    } else if (roleID == 3) {
        %><%@ include file="admin-header.jsp" %><%
    }
%>

<div class="container-fluid py-5 bg-secondary">
    <div class="row justify-content-center">
        <div class="col-12 bg-dark d-flex align-items-center">
            <div class="p-5 w-100">
                <h5 class="section-title ff-secondary text-start text-primary fw-normal">Quản lý <b>Nguyên liệu</b></h5>
                <h1 class="text-white mb-4">Danh sách Nguyên liệu</h1>

                <div class="mb-4 text-end">
                    <a href="#addIngredientModal" class="btn btn-success" data-toggle="modal">Thêm Nguyên liệu</a>
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
                                        <th>ID</th>
                                        <th>Tên nguyên liệu</th>
                                        <th>Số lượng trong kho</th>
                                        <th>Hoạt động</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${ingredientList}" var="list">
                                        <tr>
                                            <td>${list.getIngredientID()}</td>
                                            <td>${list.getIngredientName()}</td>
                                            <td>${list.getQuantity()}</td>
                                            <td>
                                                <a href="#editIngredientModal" class="btn btn-warning btn-sm edit" data-toggle="modal" 
                                                   data-id="${list.getIngredientID()}" 
                                                   data-name="${list.getIngredientName()}" 
                                                   data-quantity="${list.getQuantity()}">
                                                   Chỉnh sửa
                                                </a>
                                                <a href="#deleteIngredientModal" class="btn btn-danger btn-sm delete" data-toggle="modal" data-id="${list.getIngredientID()}">
                                                    Xóa
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:otherwise>
                </c:choose>

                <!-- Pagination -->
                <nav aria-label="Page navigation">
                    <ul class="pagination justify-content-center">
                        <c:if test="${currentPage > 1}">
                            <li class="page-item">
                                <a class="page-link" href="ingredientlist?page=${currentPage - 1}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>

                        <c:forEach var="i" begin="1" end="${totalPages}">
                            <li class="page-item ${currentPage == i ? 'active' : ''}">
                                <a class="page-link" href="ingredientlist?page=${i}">${i}</a>
                            </li>
                        </c:forEach>

                        <c:if test="${currentPage < totalPages}">
                            <li class="page-item">
                                <a class="page-link" href="ingredientlist?page=${currentPage + 1}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>

<!-- Add Ingredient Modal -->
<div id="addIngredientModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="ingredientlist" method="post">
                <input type="hidden" name="action" value="add">
                <div class="modal-header">						
                    <h4 class="modal-title">Tạo nguyên liệu mới</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">					
                    <div class="form-group">
                        <label for="ingredients">Tên nguyên liệu</label>
                        <input type="text" class="form-control" name="name" required>
                    </div>
                    <div class="form-group">
                        <label for="quantity">Số lượng</label>
                        <input type="number" class="form-control" name="quantity" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Hủy">
                    <input type="submit" class="btn btn-success" value="Tạo">
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Edit Ingredient Modal -->
<div id="editIngredientModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="ingredientlist" method="post">
                <input type="hidden" name="action" value="edit">
                <input type="hidden" name="ingredientID" value="">
                <div class="modal-header">
                    <h4 class="modal-title">Chỉnh sửa nguyên liệu</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="ingredientName">Tên nguyên liệu</label>
                        <input type="text" class="form-control" name="name" required>
                    </div>
                    <div class="form-group">
                        <label for="quantity">Số lượng</label>
                        <input type="number" class="form-control" name="quantity" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Hủy">
                    <input type="submit" class="btn btn-info" value="Lưu">
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Delete Ingredient Modal -->
<div id="deleteIngredientModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="ingredientlist" method="post">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="ingredientID" value=""> 
                <div class="modal-header">						
                    <h4 class="modal-title">Xóa nguyên liệu</h4>
                    <button type="button" class="btn btn-danger btn-sm" data-dismiss="modal" aria-hidden="true"> XÓA</button>
                </div>
                <div class="modal-body">					
                    <p>Bạn có chắc muốn xóa?</p>
                    <p class="text-warning"><small>Không thể hoàn tác.</small></p>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Hủy">
                    <input type="submit" class="btn btn-danger" value="Xóa">
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    $('#editIngredientModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var ingredientID = button.data('id');
        var ingredientName = button.data('name');
        var quantity = button.data('quantity');
        var modal = $(this);

        modal.find('input[name="ingredientID"]').val(ingredientID);
        modal.find('input[name="name"]').val(ingredientName);
        modal.find('input[name="quantity"]').val(quantity);
    });

    $('#deleteIngredientModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var ingredientID = button.data('id');
        var modal = $(this);
        modal.find('input[name="ingredientID"]').val(ingredientID);
    });
</script>
</body>
</html>