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

        <%@ include file="header.jsp" %>

        <div class="container-fluid py-5 bg-secondary">
            <div class="row justify-content-center">
                <div class="col-12 bg-dark d-flex align-items-center">
                    <div class="p-5 w-100">
                        <h5 class="section-title ff-secondary text-start text-primary fw-normal">Ca Làm Việc Hàng Tuần</h5>
                        <h1 class="text-white mb-4">Xem Lịch Ca Làm Việc Của Nhân Viên</h1>

                        <!-- Biểu Mẫu Tìm Kiếm và Lọc -->
                        <form action="ShiftManage" method="get" class="mb-4">
                            <div class="row g-3">
                                <!-- Lọc Theo Tuần -->
                                <div class="col-md-3">
                                    <div class="form-floating">
                                        <input type="week" class="form-control" id="week" name="week" value="${param.week != null ? param.week : currentWeek}" onchange="this.form.submit()">
                                        <label for="week">Chọn Tuần</label>
                                    </div>
                                </div>

                                <!-- Tìm Kiếm Nhân Viên -->
                                <div class="col-md-3">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" id="searchStaff" name="searchStaff" placeholder="Tìm theo tên nhân viên" value="${param.searchStaff}">
                                        <label for="searchStaff">Tìm Theo Tên Nhân Viên</label>
                                    </div>
                                </div>

                                <!-- Nút Tìm Kiếm -->
                                <div class="col-md-3">
                                    <button class="btn btn-primary w-100 py-3" type="submit">Tìm Kiếm & Lọc</button>
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
                                                                    <span>
                                                                        <a href="ShiftAttend?StaffID=${shift_staff.staff.staffID}&ShiftID=${shift.shiftID}&action=${shift_staff.status == 'attend' ? 'absent' : 'attend'}&week=${param.week}&searchStaff=${param.searchStaff}" 
                                                                           style="color: ${shift_staff.status == 'future' ? 'yellow' : shift_staff.status == 'absent' ? 'red' : 'green'};">
                                                                            ${shift_staff.status}
                                                                        </a>
                                                                    </span>
                                                                    <span><a href="ShiftRemove?shiftID=${shift.shiftID}&staffID=${shift_staff.staff.staffID}&page=${i}&week=${param.week}&searchStaff=${param.searchStaff}" 
                                                                             class="btn btn-danger" style="padding: 0.1rem 0.3rem; line-height: 1;"
                                                                             onclick="return confirm('Bạn có chắc muốn xóa nhân viên này khỏi ca làm việc không?');">
                                                                            <i class="fas fa-times fa-2x"></i>
                                                                        </a></span>
                                                                </div>
                                                            </c:forEach>
                                                        </div>
                                                        <a class="btn btn-success" href="AddStaffShift?shiftID=${shift.shiftID}&staffID=${shift_staff.staff.staffID}&page=${i}&week=${param.week}&searchStaff=${param.searchStaff}">Thêm Nhân Viên</a>
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
                                                                    <span>
                                                                        <a href="ShiftAttend?StaffID=${shift_staff.staff.staffID}&ShiftID=${shift.shiftID}&action=${shift_staff.status == 'attend' ? 'absent' : 'attend'}&week=${param.week}&searchStaff=${param.searchStaff}" 
                                                                           style="color: ${shift_staff.status == 'future' ? 'yellow' : shift_staff.status == 'absent' ? 'red' : 'green'};">
                                                                            ${shift_staff.status}
                                                                        </a>
                                                                    </span>
                                                                    <span><a href="ShiftRemove?shiftID=${shift.shiftID}&staffID=${shift_staff.staff.staffID}&page=${i}&week=${param.week}&searchStaff=${param.searchStaff}" 
                                                                             class="btn btn-danger" style="padding: 0.1rem 0.3rem; line-height: 1;"
                                                                             onclick="return confirm('Bạn có chắc muốn xóa nhân viên này khỏi ca làm việc không?');">
                                                                            <i class="fas fa-times fa-2x"></i>
                                                                        </a></span>
                                                                </div>
                                                            </c:forEach>
                                                        </div>
                                                        <a class="btn btn-success" href="AddStaffShift?shiftID=${shift.shiftID}&staffID=${shift_staff.staff.staffID}&page=${i}&week=${param.week}&searchStaff=${param.searchStaff}">Thêm Nhân Viên</a>
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                        </c:forEach>
                                    </tr>
                                </tbody>
                            </table>
                        </div>

                        <!-- Phân Trang -->
                        <nav aria-label="Điều hướng trang">
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
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>
        <script src="lib/tempusdominus/js/moment.min.js"></script>
        <script src="lib/tempusdominus/js/moment-timezone.min.js"></script>
        <script src="lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

        <!-- Template Javascript -->
        <script src="js/main.js"></script>
    </body>
</html>
