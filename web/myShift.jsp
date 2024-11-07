<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Quản Lý Ca Làm Việc Hàng Tuần</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Libraries Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>

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
        <div class="container-fluid py-5 bg-secondary">
            <div class="row justify-content-center">
                <div class="col-12 bg-dark d-flex align-items-center">
                    <div class="p-5 w-100">
                        <h5 class="section-title ff-secondary text-start text-primary fw-normal">Ca Làm Việc Hàng Tuần</h5>
                        <h1 class="text-white mb-4">Lịch Ca Làm Việc Của Bạn</h1>

                        <!-- Biểu Mẫu Tìm Kiếm và Lọc -->
                        <form action="myShift" method="get" class="mb-4">
                            <div class="row g-3">
                                <!-- Lọc Theo Tuần -->
                                <div class="col-md-3">
                                    <div class="form-floating">
                                        <input type="week" class="form-control" id="week" name="week" value="${param.week != null ? param.week : currentWeek}" onchange="this.form.submit()">
                                        <label for="week">Chọn Tuần</label>
                                    </div>
                                </div>
                            </div>
                        </form>
                        <div class="table-responsive">
                            <table class="table table-dark table-striped table-bordered">
                                <thead>
                                    <tr>
                                        <th>--</th>
                                        <th>Thứ Hai</th>
                                        <th>Thứ Ba</th>
                                        <th>Thứ Tư</th>
                                        <th>Thứ Năm</th>
                                        <th>Thứ Sáu</th>
                                        <th>Thứ Bảy</th>
                                        <th>Chủ Nhật</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                            Ban Ngày
                                        </td>
                                        <c:forEach var="date" items="${dates}">
                                            <td>
                                                <c:forEach var="shift" items="${shifts}">
                                                    <c:if test="${shift.date == date && shift.dayTime == true}">
                                                        <div class="shift">
                                                            <p>Ngày: ${shift.date}</p>
                                                            <c:forEach var="shift_staff" items="${shift.shift_staffs}">
                                                                <div class="shift">
                                                                    <span>${shift_staff.staff.staffName}</span>
                                                                    <span style="color: ${shift_staff.status == 'future' ? 'yellow' : shift_staff.status == 'absent' ? 'red' : 'green'};">
                                                                        ${shift_staff.status}
                                                                    </span>
                                                                </div>
                                                            </c:forEach>
                                                        </div>
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                        </c:forEach>
                                    </tr>

                                    <tr>
                                        <td>
                                            Ban Đêm
                                        </td>
                                        <c:forEach var="date" items="${dates}">
                                            <td>
                                                <c:forEach var="shift" items="${shifts}">
                                                    <c:if test="${shift.date == date && shift.dayTime == false}">
                                                        <div class="shift">
                                                            <p>Ngày: ${shift.date}</p>
                                                            <c:forEach var="shift_staff" items="${shift.shift_staffs}">
                                                                <div class="shift">
                                                                    <span>${shift_staff.staff.staffName}</span>
                                                                    <span style="color: ${shift_staff.status == 'future' ? 'yellow' : shift_staff.status == 'absent' ? 'red' : 'green'};">
                                                                        ${shift_staff.status}
                                                                    </span>
                                                                </div>
                                                            </c:forEach>
                                                        </div>
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                        </c:forEach>
                                    </tr>
                                </tbody>
                            </table>
                        </div>

                        <!-- Phân Trang -->
                        <nav aria-label="Điều Hướng Trang">
                            <ul class="pagination justify-content-center">
                                <c:if test="${currentPage > 1}">
                                    <li class="page-item">
                                        <a class="page-link" href="ShiftManage?page=${currentPage - 1}&week=${param.week}&searchStaff=${param.searchStaff}" aria-label="Trước">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                </c:if>
                                <c:forEach var="i" begin="1" end="${totalPages}">
                                    <li class="page-item ${currentPage == i ? 'active' : ''}">
                                        <a class="page-link" href="ShiftManage?page=${i}&week=${param.week}&searchStaff=${param.searchStaff}">${i}</a>
                                    </li>
                                </c:forEach>
                                <c:if test="${currentPage < totalPages}">
                                    <li class="page-item">
                                        <a class="page-link" href="ShiftManage?page=${currentPage + 1}&week=${param.week}&searchStaff=${param.searchStaff}" aria-label="Tiếp">
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
