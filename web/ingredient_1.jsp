<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Quản lý Nguyên liệu</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <style>
        body {
            color: #566787;
            background: #f5f5f5;
            font-family: 'Varela Round', sans-serif;
            font-size: 13px;
        }
        .table-responsive {
            margin: 30px 0;
        }
        .table-wrapper {
            background: #fff;
            padding: 20px 25px;
            border-radius: 3px;
            box-shadow: 0 1px 1px rgba(0,0,0,.05);
        }
        .table-title {
            padding: 16px 30px;
            background: #435d7d;
            color: #fff;
            margin: -20px -25px 10px;
            border-radius: 3px 3px 0 0;
        }
        .table-title h2 {
            margin: 5px 0;
            font-size: 24px;
        }
        .table-title .btn {
            color: #fff;
            float: right;
            font-size: 13px;
            border: none;
            border-radius: 2px;
            margin-left: 10px;
        }
        table.table tr th, table.table tr td {
            border-color: #e9e9e9;
            padding: 12px 15px;
            vertical-align: middle;
        }
        table.table-striped tbody tr:nth-of-type(odd) {
            background-color: #fcfcfc;
        }
        table.table-striped.table-hover tbody tr:hover {
            background: #f5f5f5;
        }
        .modal-header, .modal-body, .modal-footer {
            padding: 20px;
        }
        .modal .modal-title {
            display: inline-block;
        }
        .modal .form-control {
            border-radius: 2px;
            border-color: #dddddd;
        }
        .modal .btn {
            border-radius: 2px;
            min-width: 100px;
        }
    </style>
</head>
<body>
    <div class="container-xl">
        <div class="table-responsive">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Quản lý <b>Nguyên liệu</b></h2>
                        </div>
                        <div class="col-sm-6 text-right">
                            <a href="#addIngredientModal" class="btn btn-success" data-toggle="modal">
                                <i class="material-icons">&#xE147;</i> <span>Công thức mới</span>
                            </a>
                            <a href="ingredientlist" class="btn btn-primary">
                                <i class="material-icons">&#xE147;</i> <span>Danh sách nguyên liệu</span>
                            </a>
                        </div>
                    </div>
                </div>

                <table class="table table-striped table-hove">
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
                                    <a href="#editIngredient" class="edit" data-toggle="modal" data-id="${list.getFoodID()}">
                                        <i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i>
                                    </a>
                                    <a href="#deleteIngredient" class="delete" data-toggle="modal" data-id="${list.getFoodID()}">
                                        <i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <!-- Add Ingredient Modal -->
    <div id="addIngredientModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="ingredient" method="post">
                    <input type="hidden" name="action" value="add">
                    <div class="modal-header">
                        <h4 class="modal-title">Tạo công thức mới</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
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
                        <button type="button" class="btn btn-default" data-dismiss="modal">Thoát</button>
                        <input type="submit" class="btn btn-success" value="Thêm">
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Edit Ingredient Modal -->
    <div id="editIngredient" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="ingredient" method="post">
                    <input type="hidden" name="action" value="edit">
                    <input type="hidden" name="foodID" value="">
                    <div class="modal-header">
                        <h4 class="modal-title">Sửa Nguyên liệu</h4>
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
                        <button type="button" class="btn btn-default" data-dismiss="modal">Hủy</button>
                        <input type="submit" class="btn btn-info" value="Lưu">
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Delete Ingredient Modal -->
    <div id="deleteIngredient" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="ingredient" method="post">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="foodID" value="">
                    <div class="modal-header">
                        <h4 class="modal-title">Xóa Nguyên liệu</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <div class="modal-body">
                        <p>Bạn có chắc muốn xóa?</p>
                        <p class="text-warning"><small>Hành động không thể hoàn tác.</small></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Hủy</button>
                        <input type="submit" class="btn btn-danger" value="Xóa">
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script>
        $('#deleteIngredient').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget);
            var foodID = button.data('id');
            var modal = $(this);
            modal.find('input[name="foodID"]').val(foodID);
        });
        $('#editIngredient').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget);
            var foodID = button.data('id');
            var modal = $(this);
            modal.find('input[name="foodID"]').val(foodID);
        });
    </script>
</body>
</html>