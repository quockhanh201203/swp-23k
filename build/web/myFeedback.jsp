<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <meta charset="utf-8">
        <title>Quản lý Phản hồi</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Thư viện Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Stylesheet Bootstrap tùy chỉnh -->
        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>

        <%@ include file="header.jsp" %>

        <!-- Container Danh sách Phản hồi -->
        <div class="container-fluid py-5 bg-secondary">
            <div class="row justify-content-center">
                <div class="col-12 bg-dark d-flex align-items-center">
                    <div class="p-5 w-100">
                        <h5 class="section-title ff-secondary text-start text-primary fw-normal">Danh sách Phản hồi</h5>
                        <h1 class="text-white mb-4">Phản hồi của bạn</h1>
                        <a href="AddFeedback.jsp" class="btn btn-primary">Thêm Phản hồi Mới</a>
                        <!-- Bảng Danh sách Phản hồi -->
                        <c:choose>
                            <c:when test="${empty feedbackList}">
                                <div class="alert alert-warning text-center">Không có phản hồi nào</div>
                            </c:when>
                            <c:otherwise>
                                <div class="table-responsive">
                                    <table class="table table-dark table-striped table-bordered">
                                        <thead>
                                            <tr>
                                                <th>Ghi chú Phản hồi</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="feedback" items="${feedbackList}">
                                                <!-- Hàng đầu tiên: Tên khách hàng và Ghi chú Phản hồi -->
                                                <tr>
                                                    <td>
                                                        <span class="limited-text" data-truncated="${fn:substring(feedback.feedbackNote, 0, 100)}">
                                                            <c:choose>
                                                                <c:when test="${fn:length(feedback.feedbackNote) > 100}">
                                                                    <!-- Hiển thị văn bản rút gọn và liên kết 'Xem thêm' -->
                                                                    Phản hồi của bạn: ${fn:substring(feedback.feedbackNote, 0, 100)}...
                                                                    <a href="#" class="more-link" data-fulltext="${feedback.feedbackNote}">Xem thêm</a>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    Phản hồi của bạn: ${feedback.feedbackNote}
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </span>
                                                    </td>
                                                </tr>

                                                <!-- Hàng thứ hai: Ghi chú Phản hồi -->
                                                <tr>
                                                    <td>
                                                        <span class="limited-text" data-truncated="${fn:substring(feedback.responde.respondeNote, 0, 100)}">
                                                            <c:choose>
                                                                <c:when test="${feedback.responde != null && fn:length(feedback.responde.respondeNote) > 100}">
                                                                    <!-- Hiển thị văn bản phản hồi rút gọn và liên kết 'Xem thêm' -->
                                                                    Phản hồi: ${fn:substring(feedback.responde.respondeNote, 0, 100)}...
                                                                    <a href="#" class="more-link" data-fulltext="${feedback.responde.respondeNote}">Xem thêm</a>
                                                                </c:when>
                                                                <c:when test="${feedback.responde != null}">
                                                                    Phản hồi: ${feedback.responde.respondeNote}
                                                                </c:when>
                                                                <c:otherwise>
                                                                    Nhà hàng chưa có phản hồi
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
                    </div>
                </div>
            </div>
        </div>

        <div id="responsePopup" class="modal" style="display: none;">
            <div class="modal-content">
                <span class="close" id="closePopup">&times;</span>
                <h2>Thêm Phản hồi</h2>
                <textarea id="responseText" rows="4" cols="50" placeholder="Nhập phản hồi của bạn ở đây..."></textarea>
                <br />
                <button id="submitResponse" class="btn btn-success">Gửi</button>
            </div>
        </div>
        <!-- Thư viện JavaScript -->
        <script>
            $(document).ready(function () {
                // Xử lý khi nhấn vào liên kết 'Xem thêm'
                $(document).on('click', '.more-link', function (event) {
                    event.preventDefault();  // Ngăn hành vi mặc định của liên kết

                    var parentSpan = $(this).closest('span');  // Lấy phần tử <span> cha
                    var fullText = $(this).attr('data-fulltext');  // Văn bản đầy đủ được lưu trong thuộc tính data-fulltext
                    var truncatedText = parentSpan.attr('data-truncated');  // Văn bản rút gọn được lưu trong thuộc tính data-truncated

                    // Kiểm tra xem văn bản hiện đang bị rút gọn hay đã mở rộng
                    if ($(this).text() === "Xem thêm") {
                        // Nếu nhấn vào "Xem thêm", mở rộng văn bản
                        parentSpan.html(fullText + ' <a href="#" class="more-link" data-fulltext="' + fullText + '">Thu gọn</a>');
                    } else {
                        // Nếu nhấn vào "Thu gọn", thu gọn văn bản
                        parentSpan.html(truncatedText + '... <a href="#" class="more-link" data-fulltext="' + fullText + '">Xem thêm</a>');
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

        <!-- Javascript của Mẫu -->
        <script src="js/main.js"></script>
    </body>
</html>
