<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Danh Sách Người Dùng</title>
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

        <div class="modal fade" id="addCategoryModal" tabindex="-1" aria-labelledby="addCategoryModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="food-category" method="post">
                        <div class="modal-header">
                            <h5 class="modal-title">Thêm Loại Đồ Ăn</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Đóng">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label>Tên Loại Đồ Ăn</label>
                                <input type="text" name="categoryName" pattern=".*\S.*" title="Không được chỉ nhập khoảng trắng" class="form-control" required>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <input type="hidden" name="action" value="add">
                            <button type="submit" class="btn btn-primary">Thêm</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>




        <%
    // Grab RoleID from the session
    Integer roleID = (Integer) session.getAttribute("RoleID");

    // Check RoleID and include the appropriate header
    if (roleID == null) {
        // If RoleID is not in the session, include the default header.jsp
        %><%@ include file="header.jsp" %><%
    } else if (roleID == 1) {
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

        <div class="container-fluid py-5 bg-secondary" >

            <div class="row justify-content-center">
                <div class="col-12 bg-dark d-flex align-items-center">
                    <div class="p-5 w-100">
                        <h5 class="section-title ff-secondary text-start text-primary fw-normal">Loại Đồ Ăn</h5>
                        <h1 class="text-white mb-4">Loại Đồ Ăn</h1>
                        <div class="table-responsive">
                            <c:if test="${param.success ne null}">
                                <div class="alert alert-success" role="alert">
                                    Thành công!
                                </div>
                            </c:if>
                            <c:if test="${param.fail ne null}">
                                <div class="alert alert-danger" role="alert">
                                    Thất bại!
                                </div>
                            </c:if>
                            <button class="btn btn-primary" data-toggle="modal" data-target="#addCategoryModal">Thêm Loại Đồ Ăn</button>    
                            <table id="category" class="table table-light table-striped table-bordered">
                                <thead>
                                    <tr>
                                        <th>ID Loại</th>
                                        <th>Tên Loại Đồ Ăn</th>
                                        <th>Hành Động</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="category" items="${categories}">
                                        <tr>
                                            <td>${category.categoryID}</td>
                                            <td>${category.categoryName}</td>
                                            <td>
                                                <button class="btn btn-info" data-toggle="modal" data-target="#updateCategoryModal" 
                                                        data-id="${category.categoryID}" data-name="${category.categoryName}">Chỉnh Sửa</button>
                                                <form action="food-category" method="post" style="display:inline-block;">
                                                    <input type="hidden" name="action" value="delete">
                                                    <input type="hidden" name="id" value="${category.categoryID}">
                                                    <button type="submit" class="btn btn-danger">Xóa</button>
                                                </form>
                                                <!-- Update Category Modal -->
                                                <div class="modal fade" id="updateCategoryModal" tabindex="-1" aria-labelledby="updateCategoryModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog">
                                                        <div class="modal-content">
                                                            <form action="food-category" method="post">
                                                                <div class="modal-header">
                                                                    <h5 class="modal-title">Cập Nhật Loại Đồ Ăn</h5>
                                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Đóng">
                                                                        <span aria-hidden="true">&times;</span>
                                                                    </button>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <div class="form-group">
                                                                        <label>Tên Loại Đồ Ăn</label>
                                                                        <input type="text" id="categoryName" name="categoryName" value="${category.categoryName}" pattern=".*\S.*" title="Không được chỉ nhập khoảng trắng" class="form-control" required>
                                                                    </div>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <input type="hidden" name="action" value="update">
                                                                    <input type="hidden" id="categoryId" name="id" value="${category.categoryID}">
                                                                    <button type="submit" class="btn btn-primary">Cập Nhật</button>
                                                                </div>
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
                $('#category').DataTable({
                    pageLength: 10,
                    "lengthChange": false,
                    "sScrollY": ($(window).height() - 300)
                });
            });
        </script>

    </body>
</html>
