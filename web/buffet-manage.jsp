<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User List</title>
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <!-- DataTable CSS -->
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.24/css/dataTables.bootstrap4.min.css">
        <!-- Font Awesome CSS for icons -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">

        <style>
            .modal-lg {
                max-width: 80%;
            }

            .table th, .table td {
                vertical-align: middle;
            }

        </style>
    </head>
    <body>
        <!-- Sidebar -->
        <%@ include file="menu-sidebar.jsp" %>

        <div class="mt-5 main-content">
            <h2>Buffet List</h2>

            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addBuffetModal">
                Add Buffet
            </button>

            <c:if test="${param.success ne null}">
                <div class="alert alert-success" role="alert">
                    Success!
                </div>
            </c:if>
            <c:if test="${param.fail ne null}">
                <div class="alert alert-danger" role="alert">
                    Failed!
                </div>
            </c:if>

            <table id="buffetTable" class="table table-striped">
                <thead>
                    <tr>
                        <th>Buffet ID</th>
                        <th>Buffet Name</th>
                        <th>Price</th>
                        <th>Image</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="buffet" items="${buffetList}">
                        <tr>
                            <td>${buffet.buffetID}</td>
                            <td>${buffet.buffetName}</td>
                            <td>${buffet.price}</td>
                            <td><img src="${buffet.image}" alt="Buffet Image" width="100" height="100"/></td>
                            <td>
                                <button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#editBuffetModal_${buffet.buffetID}">Edit</button>
                                <div class="modal fade" id="editBuffetModal_${buffet.buffetID}" tabindex="-1" aria-labelledby="updateBuffetModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <form action="buffets" method="POST">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="addBuffetModalLabel">Add Buffet</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    <!-- Buffet ID (hidden) -->
                                                    <input type="hidden" name="buffetId" value="${buffet.buffetID}">
                                                    <input type="hidden" name="type" value="buffet">
                                                    <input type="hidden" name="action" value="update">

                                                    <!-- Buffet Name -->
                                                    <div class="mb-3">
                                                        <label for="buffetName" class="form-label">Buffet Name</label>
                                                        <input type="text" class="form-control" name="buffetName" value="${buffet.buffetName}" required>
                                                    </div>
                                                    
                                                    <!-- Buffet Name -->
                                                    <div class="mb-3">
                                                        <label for="buffetName" class="form-label">Buffet Price</label>
                                                        <input type="text" class="form-control" name="price" value="${buffet.price}" required>
                                                    </div>

                                                    <!-- Buffet Image -->
                                                    <div class="mb-3">
                                                        <label for="image" class="form-label">Image URL</label>
                                                        <input type="text" class="form-control" name="image" value="${buffet.image}" required>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="submit" class="btn btn-primary">Update Buffet</button>
                                                </div>
                                                <!-- Set action type -->
                                                <input type="hidden" name="action" value="update">
                                                <input type="hidden" name="type" value="buffet">
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <!-- Buffet Update Modal -->
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="modal fade" id="addBuffetModal" tabindex="-1" role="dialog" aria-labelledby="addBuffetModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addBuffetModalLabel">Add Buffet</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="buffets" method="post">
                            <input type="hidden" name="type" value="buffet">
                            <input type="hidden" name="action" value="add">
                            <div class="form-group">
                                <label for="buffetName">Buffet Name</label>
                                <input type="text" class="form-control" id="buffetName" name="buffetName" required>
                            </div>
                            <div class="form-group">
                                <label for="buffetPrice">Buffet Price</label>
                                <input type="number" class="form-control" id="buffetPrice" name="buffetPrice" required>
                            </div>
                            <div class="form-group">
                                <label for="buffetImage">Image URL</label>
                                <input type="text" class="form-control" id="buffetImage" name="buffetImage" required>
                            </div>
                            <button type="submit" class="btn btn-primary">Add Buffet</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>


        <!-- Bootstrap JS and jQuery -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <!-- DataTable JS -->
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.24/js/dataTables.bootstrap4.min.js"></script>

        <script>
            $(window).resize(function () {
                console.log($(window).height());
                $('.dataTables_scrollBody').css('height', ($(window).height() - 200));
            });
            
            $(document).ready(function () {
                $('#buffetTable').DataTable({
                    pageLength: 10,
                    "lengthChange": false,
                    "sScrollY": ($(window).height() - 300)
                });
            });

        </script>

    </body>
</html>
