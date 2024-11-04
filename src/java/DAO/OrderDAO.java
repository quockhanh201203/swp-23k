/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.*;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class OrderDAO extends DBContext{
    public List<orderDto> searchOrders(Integer customerID, String guestNote, int offset, int limit) {
    List<orderDto> orderList = new ArrayList<>();
    StringBuilder query = new StringBuilder(
            "SELECT o.OrderID, o.GuestNote, o.GuestID, o.CustomerID, o.total, o.Table_OrderID, "
            + "c.CustomerName, c.Email, c.PhoneNumber, "
            + "f.FoodID, f.FoodName, of.Quantity "
            + "FROM [dbo].[Order] o "
            + "LEFT JOIN Customer c ON o.CustomerID = c.CustomerID "
            + "LEFT JOIN Order_Food of ON o.OrderID = of.OrderID "
            + "LEFT JOIN Food f ON of.FoodID = f.FoodID "
            + "WHERE 1=1" // Always true condition to simplify appending conditions
    );

    // Dynamically add conditions if parameters are provided
    if (customerID != null) {
        query.append(" AND o.CustomerID = ?");
    }
    if (guestNote != null && !guestNote.isEmpty()) {
        query.append(" AND o.GuestNote LIKE ?");
    }

    // Add pagination support for SQL Server
    query.append(" ORDER BY o.OrderID DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

    try (PreparedStatement st = connection.prepareStatement(query.toString())) {
        int paramIndex = 1;

        if (customerID != null) {
            st.setInt(paramIndex++, customerID);
        }
        if (guestNote != null && !guestNote.isEmpty()) {
            st.setString(paramIndex++, "%" + guestNote + "%");
        }

        // Set pagination parameters
        st.setInt(paramIndex++, offset);
        st.setInt(paramIndex++, limit);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            orderDto order = new orderDto();
            order.setOrderID(rs.getInt("OrderID"));
            order.setGuestNote(rs.getString("GuestNote"));
            order.setGuestID(rs.getInt("GuestID"));
            order.setCustomerID(rs.getInt("CustomerID"));
            order.setTotal(rs.getInt("total"));
            order.setTable_OrderID(rs.getInt("Table_OrderID"));

            // Set Customer information
            Customer customer = new Customer();
            customer.setCustomerID(rs.getInt("CustomerID"));
            customer.setCustomerName(rs.getString("CustomerName"));
            customer.setEmail(rs.getString("Email"));
            customer.setPhoneNumber(rs.getString("PhoneNumber"));
            order.setCustomer(customer);

            // Set Food information if exists
            if (rs.getInt("FoodID") != 0) {
                Food food = new Food();
                food.setFoodID(rs.getInt("FoodID"));
                food.setFoodName(rs.getString("FoodName"));
                order.setFood(food);
            }

            orderList.add(order);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return orderList;
}
}
