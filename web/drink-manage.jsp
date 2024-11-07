<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Danh Sách Đồ Uống</title>
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

        <div class="modal fade" id="addDrinkModal" tabindex="-1" role="dialog" aria-labelledby="addDrinkModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addDrinkModalLabel">Thêm Đồ Uống</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Đóng">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="drinks" method="post">
                            <input type="hidden" name="type" value="drink">
                            <input type="hidden" name="action" value="add">
                            <div class="form-group">
                                <label for="drinkName">Tên Đồ Uống</label>
                                <input type="text" class="form-control" id="drinkName" pattern=".*\S.*" title="Không được để trống" name="drinkName" required>
                            </div>

                            <div class="form-group">
                                <label for="drinkPrice">Giá Đồ Uống</label>
                                <input type="number" class="form-control" id="drinkPrice" min="1" name="price" required>
                            </div>
                            <div class="form-group">
                                <label for="drinkImage">URL Hình Ảnh</label>
                                <input type="text" class="form-control" id="drinkImage" pattern=".*\S.*" title="Không được để trống" name="drinkImage" required>
                            </div>

                            <div class="form-group">
                                <label for="quantity">Số Lượng:</label>
                                <input type="text" class="form-control" id="quantity" min="1" name="quantity" required>
                            </div>

                            <div class="form-group">
                                <label for="categoryId">Danh Mục:</label>
                                <select name="categoryId" class="form-control">
                                    <c:forEach items="${drinkCategorys}" var="dc">
                                        <option value="${dc.categoryID}">${dc.categoryName}</option>
                                    </c:forEach>
                                </select>

                            </div>

                            <div class="form-group">
                                <label for="brandId">Thương Hiệu:</label>
                                <select name="brandId" class="form-control">
                                    <c:forEach items="${brands}" var="db">
                                        <option value="${db.brandID}">${db.brandName}</option>
                                    </c:forEach>
                                </select>

                            </div>

                            <button type="submit" class="btn btn-primary">Thêm Đồ Uống</button>
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
                        <h5 class="section-title ff-secondary text-start text-primary fw-normal">Danh Sách Đồ Uống</h5>
                        <h1 class="text-white mb-4">Danh Sách Đồ Uống</h1>


                        <form action="price-history" method="get" class="form-inline mb-4">
                            <div class="form-group mr-3">
                                <label for="categoryFilter">Danh Mục:</label>
                                <select id="categoryFilter" class="form-control">
                                    <option value="">Tất Cả</option>
                                    <c:forEach items="${drinkCategorys}" var="fc">
                                        <option value="${fc.categoryName}">${fc.categoryName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            
                            <div class="form-group mr-3">
                                <label for="brandFilter">Thương Hiệu: </label>
                                <select id="brandFilter" class="form-control">
                                    <option value="">Tất Cả</option>
                                    <c:forEach items="${brands}" var="fc">
                                        <option value="${fc.brandName}">${fc.brandName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            
                            <div class="form-group mr-3">
                                <label for="statusFilter">Trạng Thái: </label>
                                <select id="statusFilter" class="form-control">
                                    <option value="">Tất Cả</option>
                                    <option value="Active">Hoạt Động</option>
                                    <option value="De-Active">Ngừng Hoạt Động</option>
                                </select>
                            </div>
                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addDrinkModal">
                                Thêm Đồ Uống
                            </button>
                        </form>


                        <div class="table-responsive">
                            <c:if test="${param.success ne null}">
                                <div class="alert alert-success" role="alert">
                                    Thành Công!
                                </div>
                            </c:if>
                            <c:if test="${param.fail ne null}">
                                <div class="alert alert-danger" role="alert">
                                    Thất Bại!
                                </div>
                            </c:if>

                            <table id="drinkTable" class="table table-light table-striped table-bordered">
                                <thead>
                                    <tr>
                                        <th>Mã Đồ Uống</th>
                                        <th>Tên Đồ Uống</th>
                                        <th>Giá</th>
                                        <th>Số Lượng</th>
                                        <th>Tên Danh Mục</th>
                                        <th>Tên Thương Hiệu</th>
                                        <th>Trạng Thái</th>
                                        <th>Hình Ảnh</th>
                                        <th>Hành Động</th>
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
                                            <td>${drink.status}</td>
                                            <td><img src="${drink.image}" alt="Hình Ảnh Đồ Uống" width="100" height="100"/></td>
                                            <td>
                                                <button type="button" class="btn btn-info" data-toggle="modal" data-target="#editDrinkModal_${drink.drinkID}">Chỉnh Sửa</button>
                                                <form action="drinks" " method="post" style="display:inline-block;">
                                                    <input type="hidden" name="action" value="delete">
                                                    <input type="hidden" name="status" value="${drink.status == 'Active' ? 'De-Active' : 'Active'}">
                                                    <input type="hidden" name="id" value="${drink.drinkID}">
                                                    <c:if test="${drink.status == 'Active'}">
                                                        <button type="submit" class="btn btn-danger">Xóa</button>
                                                    </c:if>
                                                    <c:if test="${drink.status == 'De-Active'}">
                                                        <button type="submit" class="btn btn-success">Hoạt Động</button>
                                                    </c:if>

                                                </form>
                                                <!-- Drink Update Modal -->
                                                <div class="modal fade" id="editDrinkModal_${drink.drinkID}" tabindex="-1" aria-labelledby="updateDrinkModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog">
                                                        <div class="modal-content">
                                                            <form action="drinks" method="POST">
                                                                <div class="modal-header">
                                                                    <h5 class="modal-title" id="addBuffetModalLabel">Chỉnh Sửa Đồ Uống</h5>
                                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Đóng">
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
                                                                        <label for="drinkName" class="form-label">Tên Đồ Uống</label>
                                                                        <input type="text" class="form-control" name="drinkName" pattern=".*\S.*" title="Không được để trống" value="${drink.drinkName}" required>
                                                                    </div>

                                                                    <div class="mb-3">
                                                                        <label for="image" class="form-label">Giá</label>
                                                                        <input type="text" class="form-control" name="price" min="1" value="${drink.price}" required>
                                                                    </div>

                                                                    <!-- Quantity -->
                                                                    <div class="mb-3">
                                                                        <label for="quantity" class="form-label">Số Lượng</label>
                                                                        <input type="number" class="form-control" name="quantity" min="1" value="${drink.quantity}" required>
                                                                    </div>

                                                                    <div class="mb-3">
                                                                        <label for="categoryId">Danh Mục:</label>
                                                                        <select name="categoryId" class="form-control">
                                                                            <c:forEach items="${drinkCategorys}" var="dc">
                                                                                <option value="${dc.categoryID}" ${dc.categoryID == drink.categoryID? 'selected' : ''}>${dc.categoryName}</option>
                                                                            </c:forEach>
                                                                        </select>

                                                                    </div>

                                                                    <div class="mb-3">
                                                                        <label for="brandId">Thương Hiệu:</label>
                                                                        <select name="brandId" class="form-control">
                                                                            <c:forEach items="${brands}" var="db">
                                                                                <option value="${db.brandID}" ${db.brandID == drink.brandID? 'selected' : ''}>${db.brandName}</option>
                                                                            </c:forEach>
                                                                        </select>

                                                                    </div>

                                                                    <!-- Status -->
                                                                    <div class="mb-3">
                                                                        <label for="status" class="form-label">Trạng Thái</label>
                                                                        <select class="form-control" name="status" required>
                                                                            <option value="Active" ${drink.status == 'Active' ? 'selected' : ''}>Hoạt Động</option>
                                                                            <option value="Deactive" ${drink.status == 'Deactive' ? 'selected' : ''}>Ngừng Hoạt Động</option>
                                                                        </select>
                                                                    </div>

                                                                    <!-- Image URL -->
                                                                    <div class="mb-3">
                                                                        <label for="image" class="form-label">URL Hình Ảnh</label>
                                                                        <input type="text" class="form-control" name="image" pattern=".*\S.*" title="Không được để trống" value="${drink.image}" required>
                                                                    </div>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                                                                    <button type="submit" class="btn btn-primary">Cập Nhật Đồ Uống</button>
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
                    </div>
                </div>
            </div>
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
                var table = $('#drinkTable').DataTable({
                    pageLength: 5,
                    lengthChange: false,
                    "sScrollY": ($(window).height() - 300)
                });
                $('#categoryFilter').on('change', function () {
                    var categoryValue = $(this).val();
                    table.column(4).search(categoryValue).draw(); // Adjust column index accordingly
                });

                // Event listener for status filter
                $('#statusFilter').on('change', function () {
                    var statusValue = $(this).val();

                    // If "All" is selected, reset the search to show all statuses
                    if (statusValue === "") {
                        table.column(6).search('').draw(); // Clear search
                    } else {
                        // Custom search for exact match
                        table.column(6).search('^' + statusValue + '$', true, false).draw();
                    }
                });
                
                // Event listener for status filter
                $('#brandFilter').on('change', function () {
                    var statusValue = $(this).val();

                    // If "All" is selected, reset the search to show all statuses
                    if (statusValue === "") {
                        table.column(5).search('').draw(); // Clear search
                    } else {
                        // Custom search for exact match
                        table.column(5).search('^' + statusValue + '$', true, false).draw();
                    }
                });
            });
        </script>

    </body>
</html>
