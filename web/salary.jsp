<%-- 
    Document   : salary.jsp
    Created on : Oct 17, 2024, 7:54:01 AM
    Author     : Admin
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.Salary"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Company - Salary Management</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&display=swap" rel="stylesheet">

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
                    <span class="sr-only">Loading...</span>
                </div>
            </div>
            <!-- Spinner End -->

            <!-- Navbar & Hero Start -->
            <div class="container-xxl position-relative p-0">
                <nav class="navbar navbar-expand-lg navbar-dark bg-dark px-4 px-lg-5 py-3 py-lg-0">
                    <a href="" class="navbar-brand p-0">
                        <h1 class="text-primary m-0"><i class="fa fa-dollar-sign me-3"></i>5 ANH LỰC</h1>
                    </a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                        <span class="fa fa-bars"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarCollapse">
                        <div class="navbar-nav ms-auto py-0 pe-4">
                            <a href="index.jsp" class="nav-item nav-link">Trang chủ</a>
                            <a href="about.jsp" class="nav-item nav-link">Thông tin</a>
                            
                        </div>
                        <a href="TableServlet" class="btn btn-primary py-2 px-4">Bàn</a>
                    </div>
                </nav>

                <div class="container-xxl py-5 bg-dark hero-header mb-5">
                    <div class="container text-center my-5 pt-5 pb-4">
                        <h1 class="display-3 text-white mb-3 animated slideInDown">QUẢN LÝ TIỀN LƯƠNG</h1>
                        <nav aria-label="breadcrumb">
                           
                        </nav>
                    </div>
                </div>
            </div>
            <!-- Navbar & Hero End -->

            <!-- Salary Management -->
            <div class="container-xxl py-5 bg-dark">
                <h2 class="mb-4 text-white">Quản lý tiền lương</h2>

                <!-- Button to Open the Add Salary Modal -->
                <button class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#addSalaryModal">Thêm lương</button>

                <!-- Filter by Staff ID -->


                <!-- Salary List -->
                <table class="table table-bordered table-striped text-white">
                    <thead>
                        <tr>
                            <th>Mã lương</th>
                            <th>Lương công thêm</th>
                            <th>Lương trừ</th>
                            <th>Ngày tháng</th>
                            <th>Ghi chú</th>
                            <th>Mã nhân viên</th>
                            <th>Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%-- List salaries --%>
                        <%
                            List<Salary> salaryList = (List<Salary>) request.getAttribute("salaryList");
                            String filterStaffID = request.getParameter("staffID");

                            if (salaryList != null) {
                                for (Salary salary : salaryList) {
                                    if (filterStaffID == null || filterStaffID.isEmpty() || filterStaffID.equals(salary.getStaffID())) {
                        %>
                        <tr class="text-white">
                            <td><%= salary.getSalaryID() %></td>
                            <td><%= salary.getSalaryPlus() %></td>
                            <td><%= salary.getSalaryMinus() %></td>
                            <td><%= salary.getDate() %></td>
                            <td><%= salary.getNote() %></td>
                            <td><%= salary.getStaffID() %></td>
                            <td>
                                <a href="editSalary.jsp?salaryID=<%= salary.getSalaryID() %>" class="btn btn-warning btn-sm">Chỉnh sửa</a> |
                                <form action="deleteSalary" method="post" style="display:inline;">
                                    <input type="hidden" name="salaryID" value="<%= salary.getSalaryID() %>">
                                    <input type="submit" class="btn btn-danger btn-sm" value="Xóa">
                                </form>
                            </td>
                        </tr>
                        <%
                                    }
                                }
                            } else {
                        %>
                        <tr>
                            <td colspan="7" class="text-center">No salary records found.</td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>

            <!-- Add Salary Modal -->
            <div class="modal fade" id="addSalaryModal" tabindex="-1" aria-labelledby="addSalaryModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="addSalaryModalLabel">Them luong</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="salaryList" method="POST">
                                <input type="hidden" name="action" value="add">
                                <div class="mb-3">
                                    <label for="salaryPlus" class="form-label">Luong cong them</label>
                                    <input type="number" class="form-control" id="salaryPlus" name="salaryPlus" required>
                                </div>
                                <div class="mb-3">
                                    <label for="salaryMinus" class="form-label">Luong tru</label>
                                    <input type="number" class="form-control" id="salaryMinus" name="salaryMinus" required>
                                </div>
                                <div class="mb-3">
                                    <label for="date" class="form-label">Ngay thang</label>
                                    <input type="date" class="form-control" id="date" name="date" required>
                                </div>
                                <div class="mb-3">
                                    <label for="note" class="form-label">Ghi chu</label>
                                    <textarea class="form-control" id="note" name="note" rows="3"></textarea>
                                </div>
                                <div class="mb-3">
                                    <label for="staffID" class="form-label">Ma nhan vien</label>
                                    <input type="text" class="form-control" id="staffID" name="staffID" required>
                                </div>
                                <button type="submit" class="btn btn-primary">Them luong</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Back to Top -->
            <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>

            <!-- Footer Start -->
            <div class="container-fluid bg-dark text-light footer pt-5 mt-5">
               
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
