<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Weekly Staff Shift Management</title>
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
                        <h5 class="section-title ff-secondary text-start text-primary fw-normal">Weekly Staff Shifts</h5>
                        <h1 class="text-white mb-4">View Staff Shift Schedule</h1>

                        <!-- Search and Filter Form -->
                        <form action="ShiftManage" method="get" class="mb-4">
                            <div class="row g-3">
                                <!-- Week Filter -->
                                <div class="col-md-3">
                                    <div class="form-floating">
                                        <input type="week" class="form-control" id="week" name="week" value="${param.week != null ? param.week : currentWeek}">
                                        <label for="week">Select Week</label>
                                    </div>
                                </div>

                                <!-- Staff Search -->
                                <div class="col-md-3">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" id="searchStaff" name="searchStaff" placeholder="Search by staff name" value="${param.searchStaff}">
                                        <label for="searchStaff">Search by Staff Name</label>
                                    </div>
                                </div>

                                <!-- Search Button -->
                                <div class="col-md-3">
                                    <button class="btn btn-primary w-100 py-3" type="submit">Search & Filter</button>
                                </div>
                            </div>
                        </form>
                        <div class="table-responsive">
                            <table class="table table-dark table-striped table-bordered">
                                <thead>
                                    <tr>
                                        <th>--</th>
                                        <th>Monday</th>
                                        <th>Tuesday</th>
                                        <th>Wednesday</th>
                                        <th>Thursday</th>
                                        <th>Friday</th>
                                        <th>Saturday</th>
                                        <th>Sunday</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                            Day
                                        </td>
                                        <c:forEach var="date" items="${dates}">
                                            <td>
                                                <c:forEach var="shift" items="${shifts}">
                                                    <c:if test="${shift.date == date && shift.dayTime == true}">
                                                        <div class="shift">
                                                            <p>Shift ID: ${shift.shiftID}</p>
                                                            <c:forEach var="shift_staff" items="${shift.shift_staffs}">
                                                                <div class="shift">
                                                                    <span>${shift_staff.staff.staffName}</span>
                                                                    <span style="color: ${shift_staff.status == 'absent' ? 'red' : 'green'};">
                                                                        ${shift_staff.status}
                                                                    </span>
                                                                    <span><a href="ShiftRemove?shiftID=${shift.shiftID}&staffID=${shift_staff.staff.staffID}&page=${i}&week=${param.week}&searchStaff=${param.searchStaff}" 
                                                                             class="btn btn-danger" style="padding: 0.1rem 0.3rem; line-height: 1;"
                                                                             onclick="return confirm('Are you sure you want to remove this staff member from the shift?');">
                                                                            <i class="fas fa-times fa-2x"></i>
                                                                        </a></span>
                                                                </div>
                                                            </c:forEach>
                                                        </div>
                                                            <a class="btn btn-success" href="AddStaffShift?shiftID=${shift.shiftID}&staffID=${shift_staff.staff.staffID}&page=${i}&week=${param.week}&searchStaff=${param.searchStaff}" >Add Staff</a>
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                        </c:forEach>
                                    </tr>

                                    <tr>
                                        <td>
                                            Night
                                        </td>
                                        <c:forEach var="date" items="${dates}">
                                            <td>
                                                <c:forEach var="shift" items="${shifts}">
                                                    <c:if test="${shift.date == date && shift.dayTime == false}">
                                                        <div class="shift">
                                                            <p>Shift ID: ${shift.shiftID}</p>
                                                            <c:forEach var="shift_staff" items="${shift.shift_staffs}">
                                                                <div class="shift">
                                                                    <span>${shift_staff.staff.staffName}</span>
                                                                    <span style="color: ${shift_staff.status == 'absent' ? 'red' : 'green'};">
                                                                        ${shift_staff.status}
                                                                    </span>
                                                                    <span><a href="ShiftRemove?shiftID=${shift.shiftID}&staffID=${shift_staff.staff.staffID}&page=${i}&week=${param.week}&searchStaff=${param.searchStaff}" 
                                                                             class="btn btn-danger" style="padding: 0.1rem 0.3rem; line-height: 1;"
                                                                             onclick="return confirm('Are you sure you want to remove this staff member from the shift?');">
                                                                            <i class="fas fa-times fa-2x"></i>
                                                                        </a></span>
                                                                </div>
                                                            </c:forEach>
                                                        </div>
                                                        <a class="btn btn-success">Add Staff</a>
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                        </c:forEach>
                                    </tr>
                                </tbody>
                            </table>
                        </div>

                        <!-- Pagination -->
                        <nav aria-label="Page navigation">
                            <ul class="pagination justify-content-center">
                                <c:if test="${currentPage > 1}">
                                    <li class="page-item">
                                        <a class="page-link" href="ShiftManage?page=${currentPage - 1}&week=${param.week}&searchStaff=${param.searchStaff}" aria-label="Previous">
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
                                        <a class="page-link" href="ShiftManage?page=${currentPage + 1}&week=${param.week}&searchStaff=${param.searchStaff}" aria-label="Next">
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

        <!-- Template Javascript -->
        <script src="js/main.js"></script>
    </body>
</html>
