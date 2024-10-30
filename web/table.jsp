<%-- 
    Document   : table
    Created on : Oct 3, 2024, 8:40:27 AM
    Author     : Admin
--%>

<%@page import="Model.Table"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Restoran - Table Management</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicon -->
    <link href="img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&family=Pacifico&display=swap" rel="stylesheet">

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
                    <h1 class="text-primary m-0"><i class="fa fa-utensils me-3"></i>Restoran</h1>
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                    <span class="fa fa-bars"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarCollapse">
                    <div class="navbar-nav ms-auto py-0 pe-4">
                        <a href="index.html" class="nav-item nav-link">Home</a>
                        <a href="about.html" class="nav-item nav-link">About</a>
                        <a href="service.html" class="nav-item nav-link">Service</a>
                        <a href="menu.html" class="nav-item nav-link">Menu</a>
                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle active" data-bs-toggle="dropdown">Pages</a>
                            <div class="dropdown-menu m-0">
                                <a href="booking.html" class="dropdown-item active">Booking</a>
                                <a href="team.html" class="dropdown-item">Our Team</a>
                                <a href="testimonial.html" class="dropdown-item">Testimonial</a>
                            </div>
                        </div>
                        <a href="contact.html" class="nav-item nav-link">Contact</a>
                    </div>
                    <a href="" class="btn btn-primary py-2 px-4">Book A Table</a>
                </div>
            </nav>

            <div class="container-xxl py-5 bg-dark hero-header mb-5">
                <div class="container text-center my-5 pt-5 pb-4">
                    <h1 class="display-3 text-white mb-3 animated slideInDown">Table</h1>
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb justify-content-center text-uppercase">
                            <li class="breadcrumb-item"><a href="#">Home</a></li>
                            <li class="breadcrumb-item"><a href="#">Pages</a></li>
                            <li class="breadcrumb-item text-white active" aria-current="page">Table</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
        <!-- Navbar & Hero End -->

        <!-- Table Management -->
        <div class="container-xxl py-5 bg-dark">
            <h2 class="mb-4 text-white">Table Management</h2>

            <!-- Nút "Add Table" ?? m? modal -->
            <button class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#addTableModal">Add Table</button>

            <!-- Filter by Status -->
            <div class="mb-3">
                <label for="statusFilter" class="form-label text-white">Filter by Status:</label>
                <form method="get" action="TableServlet">
                    <select id="statusFilter" name="status" class="form-select" onchange="this.form.submit()">
                        <option value="all" <%= (request.getParameter("status") == null || request.getParameter("status").equals("all")) ? "selected" : "" %>>All</option>
                        <option value="available" <%= (request.getParameter("status") != null && request.getParameter("status").equals("available")) ? "selected" : "" %>>Available</option>
                        <option value="unavailable" <%= (request.getParameter("status") != null && request.getParameter("status").equals("unavailable")) ? "selected" : "" %>>Unavailable</option>
                    </select>
                </form>
            </div>

            <!-- Hi?n th? danh sách -->
            <table class="table table-bordered table-striped text-white">
                <thead>
                    <tr>
                        <th>Table ID</th>
                        <th>Table Name</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        ArrayList<Table> tables = (ArrayList<Table>) request.getAttribute("tables");
                        String filterStatus = request.getParameter("status");

                        if (tables != null) {
                            for (Table table : tables) {
                                if (filterStatus == null || filterStatus.equals("all") || filterStatus.equals(table.getStatus())) {
                    %>
                    <tr class="text-white">
                        <td><%= table.getTableID() %></td>
                        <td><%= table.getTableName() %></td>
                        <td><%= table.getStatus() %></td>
                        <td>
                            <a href="TableServlet?action=edit&tableID=<%= table.getTableID() %>" class="btn btn-warning btn-sm">Edit</a>
                            <a href="TableServlet?action=delete&tableID=<%= table.getTableID() %>" class="btn btn-danger btn-sm">Delete</a>
                        </td>
                    </tr>
                    <%
                                }
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>
        <div class="modal fade" id="addTableModal" tabindex="-1" aria-labelledby="addTableModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addTableModalLabel">Add Table</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="TableServlet" method="POST">
                            <input type="hidden" name="action" value="add">
                            <div class="mb-3">
                                <label for="tableName" class="form-label">Table Name</label>
                                <input type="text" class="form-control" id="tableName" name="tableName" required>
                            </div>
                            <div class="mb-3">
                                <label for="status" class="form-label">Status</label>
                                <select class="form-select" id="status" name="status" required>
                                    <option value="available">Available</option>
                                    <option value="unavailable">Unavailable</option>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary">Add Table</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <%
            Table editTable = (Table) request.getAttribute("editTable");
            if (editTable != null) {
        %>
        <div class="modal fade show" id="editTableModal" tabindex="-1" aria-labelledby="editTableModalLabel" aria-hidden="true" style="display:block;">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editTableModalLabel">Edit Table</h5>
                        <a href="TableServlet?action=list" class="btn-close"></a>
                    </div>
                    <div class="modal-body">
                        <form action="TableServlet" method="POST">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="tableID" value="<%= editTable.getTableID() %>">
                            <div class="mb-3">
                                <label for="tableName" class="form-label">Table Name</label>
                                <input type="text" class="form-control" id="tableName" name="tableName" value="<%= editTable.getTableName() %>" required>
                            </div>
                            <div class="mb-3">
                                <label for="status" class="form-label">Status</label>
                                <select class="form-select" id="status" name="status" required>
                                    <option value="available" <%= editTable.getStatus().equals("available") ? "selected" : "" %>>Available</option>
                                    <option value="unavailable" <%= editTable.getStatus().equals("unavailable") ? "selected" : "" %>>Unavailable</option>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary">Update Table</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <%
            }
        %>

        <!-- Back to Top -->
        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>

        <!-- Footer Start -->
        <div class="container-fluid bg-dark text-light footer pt-5 mt-5 wow fadeIn" data-wow-delay="0.1s">
            <div class="container py-5">
                <div class="row g-5">
                    <div class="col-lg-3 col-md-6">
                        <h4 class="section-title ff-secondary text-start text-primary fw-normal mb-4">Company</h4>
                        <a class="btn btn-link" href="">About Us</a>
                        <a class="btn btn-link" href="">Contact Us</a>
                        <a class="btn btn-link" href="">Reservation</a>
                        <a class="btn btn-link" href="">Privacy Policy</a>
                        <a class="btn btn-link" href="">Terms & Condition</a>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <h4 class="section-title ff-secondary text-start text-primary fw-normal mb-4">Contact</h4>
                        <p class="mb-2"><i class="fa fa-map-marker-alt me-3"></i>123 Street, New York, USA</p>
                        <p class="mb-2"><i class="fa fa-phone-alt me-3"></i>+012 345 67890</p>
                        <p class="mb-2"><i class="fa fa-envelope me-3"></i>info@example.com</p>
                        <div class="d-flex pt-2">
                            <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-twitter"></i></a>
                            <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-facebook-f"></i></a>
                            <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-youtube"></i></a>
                            <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-linkedin-in"></i></a>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <h4 class="section-title ff-secondary text-start text-primary fw-normal mb-4">Opening</h4>
                        <h5 class="text-light fw-normal">Monday - Saturday</h5>
                        <p>09AM - 09PM</p>
                        <h5 class="text-light fw-normal">Sunday</h5>
                        <p>10AM - 08PM</p>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <h4 class="section-title ff-secondary text-start text-primary fw-normal mb-4">Newsletter</h4>
                        <p>Dolor amet sit justo amet elitr clita ipsum elitr est.</p>
                        <div class="position-relative mx-auto" style="max-width: 400px;">
                            <input class="form-control border-primary w-100 py-3 ps-4 pe-5" type="text" placeholder="Your email">
                            <button type="button" class="btn btn-primary py-2 position-absolute top-0 end-0 mt-2 me-2">SignUp</button>
                        </div>
                    </div>
                </div>
            </div>
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
