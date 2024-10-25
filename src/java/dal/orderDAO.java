/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import model.Ingredient_Food;
import model.foodOrder;
import model.ingredient2;
import model.order;
import model.orderID;
import model.tableOrder;

/**
 *
 * @author tran tung
 */
public class orderDAO extends DBContext {
    
//  public order newOrder(String GuestNote, Integer GuestID, Integer CustomerID, int total, int Table_OrderID) {
//    String sql = "INSERT INTO [dbo].[Order] " +
//                 "([GuestNote], [GuestID], [CustomerID], [total], [Table_OrderID]) " +
//                 "VALUES (?, ?, ?, ?, ?); " +
//                 "SELECT SCOPE_IDENTITY() AS orderID;";  // Lấy orderID vừa tạo
//
//    try {
//        order a = new order();
//        PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // Thêm option để trả về khóa
//
//        st.setString(1, GuestNote);
//        st.setObject(2, GuestID, java.sql.Types.INTEGER);
//        st.setObject(3, CustomerID, java.sql.Types.INTEGER);
//        st.setInt(4, total);
//        st.setInt(5, Table_OrderID);
//
//        // Thực hiện câu lệnh INSERT
//        int rowsAffected = st.executeUpdate(); // Sử dụng executeUpdate cho INSERT
//
//        if (rowsAffected > 0) {
//            // Lấy orderID từ SCOPE_IDENTITY()
//            try (ResultSet rs = st.getGeneratedKeys()) { // Lấy các khóa đã sinh ra
//                if (rs.next()) {
//                    int orderID = rs.getInt(1); // Chỉ số đầu tiên
//                    a.setOrderID(orderID); // Gán orderID vào đối tượng
//                }
//            }
//            // Thiết lập các thuộc tính khác cho đối tượng order
//            a.setGuestNote(GuestNote);
//            a.setGuestID(GuestID);
//            a.setCustomerID(CustomerID);
//            a.setTotal(total);
//            a.setTable_OrderID(Table_OrderID);
//
//            return a;  // Trả về đối tượng order đã được thiết lập đầy đủ
//        }
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//
//    return null; // Trả về null nếu có lỗi xảy ra
//}

 public foodOrder newFoodOrder(int orderID, int FoodID, int Quantity) {
    String sql = "INSERT INTO [dbo].[Order_Food]\n" +
"           ([OrderID]\n" +
"           ,[FoodID]\n" +
"           ,[Quantity])\n" +
"     VALUES\n" +
"           (?\n" +
"           ,?\n" +
"           ,?)";
    try {
        foodOrder a = new foodOrder();
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1, orderID);
        st.setInt(2, FoodID);
        st.setInt(3, Quantity);
        
        int rowsAffected = st.executeUpdate();
        if (rowsAffected > 0) {
            a.setFoodID(orderID); // Sử dụng setter hợp lệ
            a.setFoodID(FoodID); // Sử dụng setter hợp lệ
            a.setQuantity(Quantity); // Sử dụng setter hợp lệ
            return a;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}
     public order createOrderWithFood(String guestNote, Integer guestID, Integer customerID, int total, int tableOrderID, int foodID, int quantity) {
    String sql = "DECLARE @NewOrderID INT; " +
                 "INSERT INTO [dbo].[Order] " +
                 "([GuestNote], [GuestID], [CustomerID], [total], [Table_OrderID]) " +
                 "VALUES (?, ?, ?, ?, ?); " +
                 "SET @NewOrderID = SCOPE_IDENTITY(); " +
                 "INSERT INTO [dbo].[Order_Food] ([OrderID], [FoodID], [Quantity]) " +
                 "VALUES (@NewOrderID, ?, ?); " +
                 "SELECT @NewOrderID AS OrderID;"; 

    try {
        // Prepare the statement for the SQL command
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, guestNote);
        st.setObject(2, guestID, java.sql.Types.INTEGER);
        st.setObject(3, customerID, java.sql.Types.INTEGER);
        st.setInt(4, total);
        st.setInt(5, tableOrderID);
        st.setInt(6, foodID);   // Set FoodID
        st.setInt(7, quantity);  // Set Quantity

        // Execute the statement
        boolean hasResultSet = st.execute(); // Use execute() to run the SQL

        if (hasResultSet) {
            // Retrieve the OrderID from the result set
            try (ResultSet rs = st.getResultSet()) {
                if (rs.next()) {
                    int newOrderID = rs.getInt("OrderID");

                    // Create and return the Order object
                    order order = new order();
                    order.setOrderID(newOrderID);
                    order.setGuestNote(guestNote);
                    order.setGuestID(guestID);
                    order.setCustomerID(customerID);
                    order.setTotal(total);
                    order.setTable_OrderID(tableOrderID);
                    return order;
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null; // Return null if there was an error
}

    public static void main(String[] args) {
        orderDAO d = new orderDAO();
        d.createOrderWithFood("o` a i' e.", 1, 1, 0, 1, 5, 1);
    }
    
    
       

}