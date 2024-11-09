<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Quản lý ca làm</title>
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
        <!-- Staff List Container -->
        <div class="container-fluid py-5 bg-secondary">
            <div class="row justify-content-center">
                <div class="col-12 bg-dark d-flex align-items-center">
                    <div class="p-5 w-100">
                        <h5 class="section-title ff-secondary text-start text-primary fw-normal">
                            <a href="ShiftManage?week=${weekParam}&searchStaff=${searchStaff}" class="text-primary">
                                Quay lại lịch làm việc
                            </a>
                        </h5>
                        <h1 class="text-white mb-4">Danh sách nhân viên</h1>

                        <!-- Search, Filter, Sort Form -->
                        <form action="AddStaffShift" method="get" class="mb-4">
                            <div class="row g-3">
                                <!-- Search Field -->
                                <div class="col-md-3">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" id="search" name="search" placeholder="Search by staff name" value="${param.search}">
                                        <label for="search">Tìm theo tên</label>
                                    </div>
                                </div>

                                <!-- Sort Options -->
                                <div class="col-md-3">
                                    <div class="form-floating">
                                        <select class="form-control" id="sortColumn" name="sortColumn">
                                            <option value="StaffName" ${param.sortColumn == 'StaffName' ? 'selected' : ''}>Tên</option>
                                            <option value="PhoneNumber" ${param.sortColumn == 'PhoneNumber' ? 'selected' : ''}>Số điện thoại</option>
                                            <option value="Email" ${param.sortColumn == 'Email' ? 'selected' : ''}>Email</option>
                                            <option value="Salary" ${param.sortColumn == 'Salary' ? 'selected' : ''}>Lương cứng</option>
                                        </select>
                                        <label for="sortColumn">Sắp xếp theo</label>
                                    </div>
                                </div>

                                <!-- Sort Order -->
                                <div class="col-md-3">
                                    <div class="form-floating">
                                        <select class="form-control" id="sortOrder" name="sortOrder">
                                            <option value="asc" ${param.sortOrder == 'asc' ? 'selected' : ''}>Tăng dần</option>
                                            <option value="desc" ${param.sortOrder == 'desc' ? 'selected' : ''}>Giảm dần</option>
                                        </select>
                                        <label for="sortOrder">Thứ tự</label>
                                    </div>
                                    <!-- Search Button -->
                                </div>
                                <div class="col-md-3">
                                    <button class="btn btn-primary w-100 py-3" type="submit">Tìm và sắp xếp</button>
                                </div>
                                <div></div>
                                <div class="row">
                                    <div class="col-md-3">
                                        <p class="text-white">Ngày: ${shift.weekDate}, ${shift.date}</p>
                                        <p class="text-white">
                                            <c:choose>
                                                <c:when test="${shift.dayTime == true}">
                                                    Ca : Ngày
                                                </c:when>
                                                <c:otherwise>
                                                    Ca : Đêm
                                                </c:otherwise>
                                            </c:choose>
                                        </p>
                                    </div>
                                    <div class="col-md-9">
                                        <c:forEach var="shift_staff" items="${shift.shift_staffs}">
                                            <div class="shift">
                                                <span class="text-white">${shift_staff.staff.staffName}</span>
                                                <span><a href="ShiftRemove?shiftID=${shift.shiftID}&staffID=${shift_staff.staff.staffID}&page=${i}&week=${param.week}&searchStaff=${param.searchStaff}&stafflistpage=yes" 
                                                         class="btn btn-danger" style="padding: 0.1rem 0.3rem; line-height: 1;"
                                                         onclick="return confirm('Are you sure you want to remove this staff member from the shift?');">
                                                        <i class="fas fa-times fa-2x"></i>
                                                    </a></span>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </form>
                        <c:choose>
                            <c:when test="${empty staffList}">
                                <div class="alert alert-warning text-center">No Staff Found</div>
                            </c:when>
                            <c:otherwise>
                                <div class="table-responsive">
                                    <table class="table table-dark table-striped table-bordered">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Tên </th>
                                                <th>Số điện thoại</th>
                                                <th>Email</th>
                                                <th>Lương cứng</th>
                                                <th>Đã đổi mật khẩu</th>
                                                <th>Hành động</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="staff" items="${staffList}">
                                                <tr>
                                                    <td>${staff.staffID}</td>
                                                    <td>${staff.staffName}</td>
                                                    <td>${staff.phoneNumber}</td>
                                                    <td>${staff.email}</td>
                                                    <td>${staff.salary}</td>
                                                    <td>${staff.newAccount ? 'Yes' : 'No'}</td>
                                                    <td>
                                                        <c:set var="isInShift" value="false" />
                                                        <c:forEach var="shiftStaff" items="${shift.shift_staffs}">
                                                            <c:if test="${shiftStaff.staff.staffID == staff.staffID}">
                                                                <c:set var="isInShift" value="true" />
                                                            </c:if>
                                                        </c:forEach>
                                                        <c:choose>
                                                            <c:when test="${staff.accountID == 0}">
                                                                <p class="text-danger">Nhân viên đã bị sa thải</p>
                                                            </c:when>
                                                            <c:when test="${isInShift == true}">
                                                                <p class="text-success">Đã làm việc trong ca này</p>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <form action="AddStaffShift" method="post" style="display:inline;">
                                                                    <input type="hidden" name="staffID" value="${staff.staffID}">
                                                                    <input type="hidden" name="page" value="${currentPage}">
                                                                    <input type="hidden" name="shiftID" value="${shiftID}">
                                                                    <input type="hidden" name="weekParam" value="${weekParam}">
                                                                    <input type="hidden" name="searchStaff" value="${searchStaff}">
                                                                    <input type="hidden" name="search" value="${param.search}">
                                                                    <input type="hidden" name="sortColumn" value="${param.sortColumn}">
                                                                    <input type="hidden" name="sortOrder" value="${param.sortOrder}">
                                                                    <button type="submit" class="btn btn-success btn-sm">Thêm</button>
                                                                </form>
                                                            </c:otherwise>
                                                        </c:choose>
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
                                        <a class="page-link" href="AddStaffShift?shiftID=${shiftID}&page=${currentPage - 1}&search=${param.search}&sortColumn=${param.sortColumn}&sortOrder=${param.sortOrder}" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                </c:if>
                                <c:forEach var="i" begin="1" end="${totalPages}">
                                    <li class="page-item ${currentPage == i ? 'active' : ''}">
                                        <a class="page-link" href="AddStaffShift?shiftID=${shiftID}&page=${i}&search=${param.search}&sortColumn=${param.sortColumn}&sortOrder=${param.sortOrder}">${i}</a>
                                    </li>
                                </c:forEach>
                                <c:if test="${currentPage < totalPages}">
                                    <li class="page-item">
                                        <a class="page-link" href="AddStaffShift?shiftID=${shiftID}&page=${currentPage + 1}&search=${param.search}&sortColumn=${param.sortColumn}&sortOrder=${param.sortOrder}" aria-label="Next">
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
