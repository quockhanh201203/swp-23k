<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Quản lý đặt bàn</title>
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

        <!-- Reservation List Container -->
        <div class="container-fluid py-5 bg-secondary">
            <div class="row justify-content-center">
                <div class="col-12 bg-dark d-flex align-items-center">
                    <div class="p-5 w-100">
                        <h5 class="section-title ff-secondary text-start text-primary fw-normal">Quản lý đặt bàn</h5>
                        <h1 class="text-white mb-4">Danh sách đặt bàn</h1>

                        <!-- Search, Filter, Sort Form -->
                        <form action="ReservationList" method="get" class="mb-4">
                            <div class="row g-3">
                                <!-- Search Fields -->
                                <div class="col-md-3">
                                    <div class="form-floating">
                                        <input type="date" class="form-control" id="reservationDate" name="reservationDate" value="${param.reservationDate}">
                                        <label for="reservationDate">Ngày</label>
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="form-floating">
                                        <input type="number" class="form-control" id="numberOfGuests" name="numberOfGuests" placeholder="Guests" value="${param.numberOfGuests}">
                                        <label for="numberOfGuests">Lượng khách</label>
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" id="status" name="status" placeholder="Status" value="${param.status}">
                                        <label for="status">Trạng thái</label>
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" id="tableName" name="tableName" placeholder="Table Name" value="${param.tableName}">
                                        <label for="tableName">Tên bàn</label>
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" id="customerName" name="customerName" placeholder="Customer Name" value="${param.customerName}">
                                        <label for="customerName">Tên người đặt</label>
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" placeholder="Phone Number" value="${param.phoneNumber}">
                                        <label for="phoneNumber">Số điện thoại</label>
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="form-floating">
                                        <input type="email" class="form-control" id="email" name="email" placeholder="Email" value="${param.email}">
                                        <label for="email">Email</label>
                                    </div>
                                </div>

                                <!-- Search Button -->
                                <div class="col-md-3">
                                    <button class="btn btn-primary w-100 py-3" type="submit">Tìm</button>
                                </div>
                            </div>
                        </form>

                        <div class="mb-4 text-end">
                            <a href="AddReservation?myreservation=no" class="btn btn-success">Thêm đặt bàn</a>
                        </div>

                        <!-- Reservation List Table -->
                        <c:choose>
                            <c:when test="${empty reservations}">
                                <div class="alert alert-warning text-center">No Reservations Found</div>
                            </c:when>
                            <c:otherwise>
                                <div class="table-responsive">
                                    <table class="table table-dark table-striped table-bordered">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Ngày</th>
                                                <th>Thời gian</th>
                                                <th>Khách</th>
                                                <th>Trạng thái</th>
                                                <th>Tên bàn</th>
                                                <th>Tên khách</th>
                                                <th>Số điện thoại</th>
                                                <th>Email</th>
                                                <th>Ghi chú</th>
                                                <th>Hành động</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="reservation" items="${reservations}">
                                                <tr>
                                                    <td>${reservation.reservationID}</td>
                                                    <td>${reservation.reservationDate}</td>
                                                    <td>${reservation.reservationTime}</td>
                                                    <td>${reservation.numberOfGuests}</td>
                                                    <td>${reservation.status}</td>
                                                    <td>${reservation.table.tableName}</td>
                                                    <td>${reservation.customer.customerName}</td>
                                                    <td>${reservation.customer.phoneNumber}</td>
                                                    <td>${reservation.customer.email}</td>
                                                    <td>${reservation.notes}</td>
                                                    <td>
                                                        <c:if test="${reservation.status != 'Confirmed'}">
                                                            <a href="UpdateReservationStatus?reservationID=${reservation.reservationID}&totalPages=${totalPages}&newstatus=Confirmed&page=${currentPage}&reservationDate=${reservationDate}&reservationTime=${param.reservationTime}&numberOfGuests=${numberOfGuests}&status=${status}&tableName=${tableName}&customerName=${customerName}&phoneNumber=${phoneNumber}&email=${email}" class="btn btn-warning btn-sm">Xác nhận</a>
                                                        </c:if>
                                                        <c:if test="${reservation.status != 'Completed'}">
                                                            <a href="UpdateReservationStatus?reservationID=${reservation.reservationID}&totalPages=${totalPages}&newstatus=Completed&page=${currentPage}&reservationDate=${reservationDate}&reservationTime=${param.reservationTime}&numberOfGuests=${numberOfGuests}&status=${status}&tableName=${tableName}&customerName=${customerName}&phoneNumber=${phoneNumber}&email=${email}" class="btn btn-success btn-sm">Hoàn thành</a>
                                                        </c:if>
                                                        <c:if test="${reservation.status != 'Cancelled'}">
                                                            <a href="UpdateReservationStatus?reservationID=${reservation.reservationID}&totalPages=${totalPages}&newstatus=Cancelled&page=${currentPage}&reservationDate=${reservationDate}&reservationTime=${param.reservationTime}&numberOfGuests=${numberOfGuests}&status=${status}&tableName=${tableName}&customerName=${customerName}&phoneNumber=${phoneNumber}&email=${email}" class="btn btn-danger btn-sm">Hủy</a>
                                                        </c:if>                                                    
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
                                        <a class="page-link" href="ReservationList?page=${currentPage - 1}&reservationDate=${reservationDate}&reservationTime=${param.reservationTime}&numberOfGuests=${numberOfGuests}&status=${status}&tableName=${tableName}&customerName=${customerName}&phoneNumber=${phoneNumber}&email=${email}" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                </c:if>

                                <c:forEach var="i" begin="1" end="${totalPages}">
                                    <li class="page-item ${currentPage == i ? 'active' : ''}">
                                        <a class="page-link" href="ReservationList?page=${i}&reservationDate=${reservationDate}&reservationTime=${param.reservationTime}&numberOfGuests=${numberOfGuests}&status=${status}&tableName=${tableName}&customerName=${customerName}&phoneNumber=${phoneNumber}&email=${email}">${i}</a>
                                    </li>
                                </c:forEach>

                                <c:if test="${currentPage < totalPages}">
                                    <li class="page-item">
                                        <a class="page-link" href="ReservationList?page=${currentPage + 1}&reservationDate=${reservationDate}&reservationTime=${param.reservationTime}&numberOfGuests=${numberOfGuests}&status=${status}&tableName=${tableName}&customerName=${customerName}&phoneNumber=${phoneNumber}&email=${email}" aria-label="Next">
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
