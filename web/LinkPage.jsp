<%-- 
    Document   : LinkPage.jsp
    Created on : Oct 30, 2024, 11:49:07 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div>
            <h1>Guest( không cần đăng nhập )</h1>
        </div>
        <div>
            <h1>Customer</h1>
            <div><a href="myFeedBack">myFeedBack</a></div>
            <div><a href="myReservation">myReservation</a></div>
            
            <div><a href="profile">Profile</a></div>


        </div>
        <div>
            <h1>Staff</h1>
            <div><a href="myShift">myShift</a></div>
            <div><a href="FeedbackList">FeedbackList</a></div>
            <div><a href="ReservationList">ReservationList</a></div>
            
            <div><a href="staff-profile">Profile</a></div>
            



        </div>
        <div>
            <h1>Admin</h1>
            //Toan
            <div><a href="StaffManage">StaffManage</a></div>
            <div><a href="ShiftManage">ShiftManage</a></div>
            <div><a href="FeedbackList">FeedbackList</a></div>
            <div><a href="ReservationList">ReservationList</a></div>
            <div><a href="DashboardServlet">DashBoard ( chưa xong )</a></div>
            //Khanh
            <div><a href="foods">Food</a></div>
            <div><a href="buffets">Buffet</a></div>
            <div><a href="drinks">Drink</a></div>
            <div><a href="admin-profile">Admin Profile</a></div>
            <div><a href="drink-category">Drink Category</a></div>
            <div><a href="food-category">Food Category</a></div>
            <div><a href="price-history">Price History</a></div>
            <div><a href="buffet-detail">Buffet Detail</a></div>





            //Cong
            <div><a href="CustomerServlet">Customer</a></div>
            <div><a href="DiscountServlet">Discount</a></div>
            <div><a href="TableServlet">Table</a></div>
            <div><a href="voucher">Voucher</a></div>
            <div><a href="salaryList">Salary</a></div>

        </div>
    </body>
</html>
