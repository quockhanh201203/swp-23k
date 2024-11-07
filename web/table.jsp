<%-- 
    Document   : table
    Created on : Oct 3, 2024, 8:40:27 AM
    Author     : Admin
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.Table"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="utf-8">
    <title>Quản Lý Bàn - Nhà Hàng</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicon -->
    <link href="img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&family=Pacifico&display=swap" rel="stylesheet">

    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="css/style.css" rel="stylesheet">
</head>

<body>
    <div class="container-xxl bg-white p-0">
        <!-- Spinner Start -->
        <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
            <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                <span class="sr-only">Đang tải...</span>
            </div>
        </div>
        <!-- Spinner End -->

        <!-- Navbar & Hero Start -->
        <div class="container-xxl position-relative p-0">
            <%
    // Grab RoleID from the session
    Integer roleID = (Integer) session.getAttribute("RoleID");

    // Check RoleID and include the appropriate header
    if (roleID == 0) {
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

            <div class="container-xxl py-5 bg-dark hero-header mb-5">
                <div class="container text-center my-5 pt-5 pb-4">
                    <h1 class="display-3 text-white mb-3 animated slideInDown">DANH SÁCH BÀN</h1>
                </div>
            </div>
        </div>
        <!-- Navbar & Hero End -->

        <!-- Quản Lý Bàn -->
        <div class="container-xxl py-5 bg-dark">
            <h2 class="mb-4 text-white">Danh sách bàn</h2>

            <!-- Nút "Thêm bàn" -->
            <button class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#addTableModal">Thêm bàn</button>

            <!-- Lọc theo trạng thái -->
            <div class="mb-3">
                <label for="statusFilter" class="form-label text-white">Lọc theo trạng thái:</label>
                <form method="get" action="TableServlet">
                    <select id="statusFilter" name="status" class="form-select" onchange="this.form.submit()">
                        <option value="all" <%= (request.getParameter("status") == null || request.getParameter("status").equals("all")) ? "selected" : "" %>>Tất cả</option>
                        <option value="available" <%= (request.getParameter("status") != null && request.getParameter("status").equals("available")) ? "selected" : "" %>>Còn bàn</option>
                        <option value="unavailable" <%= (request.getParameter("status") != null && request.getParameter("status").equals("unavailable")) ? "selected" : "" %>>Hết bàn</option>
                    </select>
                </form>
            </div>

            <!-- Hiển thị danh sách -->
            <table class="table table-bordered table-striped text-white">
                <thead>
                    <tr>
                        <th>ID Bàn</th>
                        <th>Tên Bàn</th>
                        <th>Trạng Thái</th>
                        <th>Thao Tác</th>
                    </tr>
                </thead>
                <tbody>
                    <%-- Danh sách các bàn sẽ được hiển thị ở đây --%>
                    <%
                        ArrayList<Table> tables = (ArrayList<Table>) request.getAttribute("tables");
                        String filterStatus = request.getParameter("status");

                        if (tables != null) {
                            for (Table table : tables) {
                                if (filterStatus == null || filterStatus.equals("all") || filterStatus.equals(table.getStatus())) {
                    %>
                    <tr class="text-white">
                        <td><%= table.getTableID() %></td>
                        <td><%= table.getTableName() %></td>
                        <td><%= table.getStatus().equals("available") ? "Còn bàn" : "Hết bàn" %></td>
                        <td>
                            <a href="TableServlet?action=edit&tableID=<%= table.getTableID() %>" class="btn btn-warning btn-sm">Chỉnh sửa</a>
                            <a href="TableServlet?action=delete&tableID=<%= table.getTableID() %>" class="btn btn-danger btn-sm">Xóa</a>
                        </td>
                    </tr>
                    <%
                                }
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>

        <!-- Modal thêm bàn -->
        <div class="modal fade" id="addTableModal" tabindex="-1" aria-labelledby="addTableModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addTableModalLabel">Thêm Bàn</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="TableServlet" method="POST">
                            <input type="hidden" name="action" value="add">
                            <div class="mb-3">
                                <label for="tableName" class="form-label">Tên Bàn</label>
                                <input type="text" class="form-control" id="tableName" name="tableName" required>
                            </div>
                            <div class="mb-3">
                                <label for="status" class="form-label">Trạng Thái</label>
                                <select class="form-select" id="status" name="status" required>
                                    <option value="available">Còn bàn</option>
                                    <option value="unavailable">Hết bàn</option>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary">Thêm Bàn</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal chỉnh sửa bàn -->
        <%
            Table editTable = (Table) request.getAttribute("editTable");
            if (editTable != null) {
        %>
        <div class="modal fade show" id="editTableModal" tabindex="-1" aria-labelledby="editTableModalLabel" aria-hidden="true" style="display:block;">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editTableModalLabel">Chỉnh Sửa Bàn</h5>
                        <a href="TableServlet?action=list" class="btn-close"></a>
                    </div>
                    <div class="modal-body">
                        <form action="TableServlet" method="POST">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="tableID" value="<%= editTable.getTableID() %>">
                            <div class="mb-3">
                                <label for="tableName" class="form-label">Tên Bàn</label>
                                <input type="text" class="form-control" id="tableName" name="tableName" value="<%= editTable.getTableName() %>" required>
                            </div>
                            <div class="mb-3">
                                <label for="status" class="form-label">Trạng Thái</label>
                                <select class="form-select" id="status" name="status" required>
                                    <option value="available" <%= editTable.getStatus().equals("available") ? "selected" : "" %>>Còn bàn</option>
                                    <option value="unavailable" <%= editTable.getStatus().equals("unavailable") ? "selected" : "" %>>Hết bàn</option>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary">Cập Nhật Bàn</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <%
            }
        %>

        <!-- Back to Top -->
        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>

        <!-- Footer Start -->
        <div class="container-fluid bg-dark text-light footer pt-5 mt-5 wow fadeIn" data-wow-delay="0.1s">
            
        </div>
        <!-- Footer End -->
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

    <!-- Template Javascript -->
    <script src="js/main.js"></script>
</body>

</html>
