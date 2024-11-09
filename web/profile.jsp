<%-- 
    Document   : profile
    Created on : Sep 30, 2024, 9:33:20 PM
    Author     : Legion
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body {
                background: rgb(99, 39, 120)
            }

            .form-control:focus {
                box-shadow: none;
                border-color: #BA68C8
            }

            .profile-button {
                background: rgb(99, 39, 120);
                box-shadow: none;
                border: none
            }

            .profile-button:hover {
                background: #682773
            }

            .profile-button:focus {
                background: #682773;
                box-shadow: none
            }

            .profile-button:active {
                background: #682773;
                box-shadow: none
            }

            .back:hover {
                color: #682773;
                cursor: pointer
            }

            .labels {
                font-size: 11px
            }

            .add-experience:hover {
                background: #BA68C8;
                color: #fff;
                cursor: pointer;
                border: solid 1px #BA68C8
            }
        </style>

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css"/>
    </head>
    <body>
        <div class="container rounded bg-white mt-5 mb-5">
            <div class="row">
                <div class="col-md-3 border-right">
                    <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class="rounded-circle mt-5" width="150px" src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg"><span class="font-weight-bold">Edogaru</span><span class="text-black-50">edogaru@mail.com.my</span><span> </span></div>
                </div>
                <div class="col-md-5 border-right">
                    <div class="p-3 py-5">
                        <div class="d-flex justify-content-between align-items-center mb-3">
                            <h4 class="text-right">Profile Settings</h4>
                        </div>
                        <form action="profile" method="post">
                            <input type="hidden" name="action" value="updateProfile">
                            <input type="hidden" name="customerID" value="${customer.customerID}">
                            <div class="row mt-2">
                                <div class="col-md-12"><label class="labels">Customer Name</label><input type="text" name="customerName" class="form-control" value="${customer.customerName}"></div>
                            </div>
                            <div class="row mt-3">
                                <div class="col-md-12"><label class="labels">Mobile Number</label><input type="text" class="form-control" name="phoneNumber" value="${customer.phoneNumber}"></div>
                                <div class="col-md-12"><label class="labels">Email ID</label><input type="text" class="form-control"  value="${customer.email}" readonly></div>
                                <div class="col-md-12"><label class="labels">Point</label><input type="text" class="form-control"  value="${customer.point}"></div>
                            </div>
                            <div class="mt-5 d-flex justify-content-center">
                                <a class="btn btn-primary profile-button me-4" href="Login.jsp">Đăng nhập</a>
                                <button class="btn btn-primary profile-button me-4" type="submit">Lưu Hồ Sơ</button>
                                <a class="btn btn-primary profile-button me-4" href="changepass">Đổi mật khẩu</a>
                            </div>
                        </form>
                        <c:if test="${isSuccess ne null && isSuccess && type eq 'profile'}">
                            <div class="alert alert-success alert-dismissible fade show mt-2" role="alert" id="mess">
                                <strong>Update profile success!</strong> You should check information above.
                            </div>
                        </c:if>
                        <c:if test="${isSuccess ne null && !isSuccess && type eq 'profile'}">
                            <div class="alert alert-danger alert-dismissible fade show mt-2" role="alert" id="mess">
                                <strong>Update profile failed!</strong> You should check your network.
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        </c:if>

                    </div>
                </div>
               
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    </body>
</html>
