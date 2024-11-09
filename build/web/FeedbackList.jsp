<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <meta charset="utf-8">
        <title>Danh sách nhận xét</title>
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
        <!-- Feedback List Container -->
        <div class="container-fluid py-5 bg-secondary">
            <div class="row justify-content-center">
                <div class="col-12 bg-dark d-flex align-items-center">
                    <div class="p-5 w-100">
                        <h5 class="section-title ff-secondary text-start text-primary fw-normal">Feedback List</h5>
                        <h1 class="text-white mb-4">Nhận xét</h1>

                        <!-- Search Form -->
                        <form action="FeedbackList" method="get" class="mb-4">
                            <div class="row g-3">
                                <!-- Search by Customer Name -->
                                <div class="col-md-4">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" id="customerName" name="customerName" placeholder="Search by Customer Name" value="${param.customerName}">
                                        <label for="customerName">Tìm theo tên khách</label>
                                    </div>
                                </div>

                                <!-- Search by Feedback Note -->
                                <div class="col-md-4">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" id="feedbackNote" name="feedbackNote" placeholder="Search by Feedback Note" value="${param.feedbackNote}">
                                        <label for="feedbackNote">Tìm theo nội dung</label>
                                    </div>
                                </div>

                                <!-- Filter by Response Checkbox -->
                                <div class="col-md-4 d-flex align-items-center">
                                    <div class="form-check form-switch">
                                        <input class="form-check-input" type="checkbox" id="hasResponse" name="hasResponse" ${param.hasResponse == 'on' ? 'checked' : ''}>
                                        <label class="form-check-label text-white" for="hasResponse">Đã phản hồi</label>
                                    </div>
                                </div>

                                <!-- Search Button -->
                                <div class="col-md-12">
                                    <button class="btn btn-primary w-100 py-3" type="submit">Tìm</button>
                                </div>
                            </div>
                        </form>

                        <!-- Feedback List Table -->
                        <c:choose>
                            <c:when test="${empty feedbackList}">
                                <div class="alert alert-warning text-center">No Feedback Available</div>
                            </c:when>
                            <c:otherwise>
                                <div class="table-responsive">
                                    <table class="table table-dark table-striped table-bordered">
                                        <thead>
                                            <tr>
                                                <th>Tên khách</th>
                                                <th>Nội dung nhận xét</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="feedback" items="${feedbackList}">
                                                <!-- First Row: Customer Name and Feedback Note -->
                                                <tr>
                                                    <td>${feedback.customer.customerName}</td>
                                                    <td>
                                                        <span class="limited-text" data-truncated="${fn:substring(feedback.feedbackNote, 0, 100)}">
                                                            <c:choose>
                                                                <c:when test="${fn:length(feedback.feedbackNote) > 100}">
                                                                    <!-- Display truncated text and the 'More' link -->
                                                                    ${fn:substring(feedback.feedbackNote, 0, 100)}...
                                                                    <a href="#" class="more-link" data-fulltext="${feedback.feedbackNote}">Xem thêm</a>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    ${feedback.feedbackNote}
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </span>
                                                    </td>
                                                </tr>

                                                <!-- Second Row: Response Note -->
                                                <tr>
                                                    <td colspan="2">
                                                        <span class="limited-text" data-truncated="${fn:substring(feedback.responde.respondeNote, 0, 100)}">
                                                            <c:choose>
                                                                <c:when test="${feedback.responde != null && fn:length(feedback.responde.respondeNote) > 100}">
                                                                    <!-- Display truncated response and 'More' link -->
                                                                    Phản hồi : ${fn:substring(feedback.responde.respondeNote, 0, 100)}...
                                                                    <a href="#" class="more-link" data-fulltext="${feedback.responde.respondeNote}">Xem thêm</a>
                                                                </c:when>
                                                                <c:when test="${feedback.responde != null}">
                                                                    Nội dung phản hồi : ${feedback.responde.respondeNote}
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <a href="AddResponde.jsp?feedbackId=${feedback.feedbackID}" class="btn btn-primary">Phản hồi</a>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </span>
                                                    </td>
                                                </tr>
                                            </c:forEach>

                                        </tbody>
                                    </table>
                                </div>
                            </c:otherwise>
                        </c:choose>
                        <nav aria-label="Page navigation">
                            <ul class="pagination justify-content-center">
                                <c:if test="${currentPage > 1}">
                                    <li class="page-item">
                                        <a class="page-link" href="?page=${currentPage - 1}&customerName=${param.customerName}&feedbackNote=${param.feedbackNote}&hasResponse=${param.hasResponse}" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                </c:if>

                                <c:forEach var="i" begin="1" end="${totalPages}">
                                    <li class="page-item ${currentPage == i ? 'active' : ''}">
                                        <a class="page-link" href="?page=${i}&customerName=${param.customerName}&feedbackNote=${param.feedbackNote}&hasResponse=${param.hasResponse}">${i}</a>
                                    </li>
                                </c:forEach>

                                <c:if test="${currentPage < totalPages}">
                                    <li class="page-item">
                                        <a class="page-link" href="?page=${currentPage + 1}&customerName=${param.customerName}&feedbackNote=${param.feedbackNote}&hasResponse=${param.hasResponse}" aria-label="Next">
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

        <div id="responsePopup" class="modal" style="display: none;">
            <div class="modal-content">
                <span class="close" id="closePopup">&times;</span>
                <h2>Add Response</h2>
                <textarea id="responseText" rows="4" cols="50" placeholder="Type your response here..."></textarea>
                <br />
                <button id="submitResponse" class="btn btn-success">Submit</button>
            </div>
        </div>

        <%@ include file="footer.jsp" %>
        <!-- JavaScript Libraries -->
        <script>
            $(document).ready(function () {
                // Handle 'More' link clicks
                $(document).on('click', '.more-link', function (event) {
                    event.preventDefault();  // Prevent default link behavior

                    var parentSpan = $(this).closest('span');  // Get the parent <span> element
                    var fullText = $(this).attr('data-fulltext');  // Full text stored in the data-fulltext attribute
                    var truncatedText = parentSpan.attr('data-truncated');  // Truncated text stored in the data-truncated attribute

                    // Check if the text is currently truncated or expanded
                    if ($(this).text() === "More") {
                        // If "More" is clicked, expand the text
                        parentSpan.html(fullText + ' <a href="#" class="more-link" data-fulltext="' + fullText + '">Less</a>');
                    } else {
                        // If "Less" is clicked, collapse the text
                        parentSpan.html(truncatedText + '... <a href="#" class="more-link" data-fulltext="' + fullText + '">More</a>');
                    }
                });
            });

        </script>


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
