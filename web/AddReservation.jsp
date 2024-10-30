<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Add Reservation - Restaurant Management</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <link href="img/favicon.ico" rel="icon">
        <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&family=Pacifico&display=swap" rel="stylesheet">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>
        <%@ include file="header.jsp" %>

        <div class="container-xxl py-5 px-0 wow fadeInUp bg-secondary" data-wow-delay="0.1s">
            <div class="row g-0 justify-content-center">
                <div class="col-md-6 bg-dark d-flex align-items-center">
                    <div class="p-5 wow fadeInUp" data-wow-delay="0.2s">
                        <h5 class="section-title ff-secondary text-start text-primary fw-normal">Reservation Management</h5>
                        <h1 class="text-white mb-4">Add New Reservation</h1>
                        <form action="AddReservation" method="post">
                            <div class="row g-3">
                                <div class="col-md-12">
                                    <div class="form-floating">
                                        <input type="date" class="form-control" id="reservationDate" placeholder="Reservation Date" name="reservationDate" required>
                                        <label for="reservationDate">Reservation Date</label>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-floating">
                                        <input type="time" class="form-control" id="reservationTime" placeholder="Reservation Time" name="reservationTime" required>
                                        <label for="reservationTime">Reservation Time</label>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-floating">
                                        <input type="number" class="form-control" id="numberOfGuests" placeholder="Number of Guests" name="numberOfGuests" required>
                                        <label for="numberOfGuests">Number of Guests</label>
                                    </div>
                                </div>
                                <c:if test="${showCustomerSelect}">
                                    <div class="col-md-12">
                                        <label for="customerSelect" class="text-white mb-1">Customer</label>
                                        <select class="form-select select2" id="customerSelect" name="customerID" required>
                                            <option value="" disabled selected>Select Customer</option>
                                            <c:forEach var="customer" items="${customerList}">
                                                <option value="${customer.customerID}">${customer.customerName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </c:if>
                                <div class="col-md-12">
                                    <label for="tableSelect" class="text-white mb-1">Table</label>
                                    <select class="form-select select2" id="tableSelect" name="tableID" required>
                                        <option value="" disabled selected>Select Table</option>
                                        <c:forEach var="table" items="${tableList}">
                                            <option value="${table.tableID}">${table.tableName} - ${table.status}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-floating">
                                        <textarea class="form-control" placeholder="Notes" id="notes" name="notes"></textarea>
                                        <label for="notes">Notes</label>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <button class="btn btn-primary w-100 py-3" type="submit">Add Reservation</button>
                                </div>
                            </div>
                        </form>
                        <c:if test="${not empty message}">
                            <div class="message text-white">${message}</div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>

        <%@ include file="footer.jsp" %>
        
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
        <script>
            $(document).ready(function() {
                $('.select2').select2({
                    placeholder: "Select an option",
                    allowClear: true
                });
            });
        </script>
    </body>
</html>