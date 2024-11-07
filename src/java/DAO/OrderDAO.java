/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ADMIN
 */
public class OrderDAO extends DBContext{
    public List<orderDto> searchOrders(Integer customerID, String guestNote, String customerName,
            String email, String phoneNumber,
            String foodName, String drinkName, String buffetName,
            int offset, int limit) {
        List<orderDto> orderList = new ArrayList<>();
        StringBuilder query = new StringBuilder(
                "SELECT o.OrderID, o.GuestNote, o.GuestID, o.CustomerID, o.Table_OrderID, "
                + "c.CustomerName, c.Email, c.PhoneNumber, "
                + "f.FoodID, f.FoodName, ofd.Quantity AS FoodQuantity, "
                + "d.DrinkID, d.DrinkName, od.Quantity AS DrinkQuantity, "
                + "b.BuffetID, b.BuffetName "
                + "FROM [dbo].[Order] o "
                + "LEFT JOIN Customer c ON o.CustomerID = c.CustomerID "
                + "LEFT JOIN Order_Food ofd ON o.OrderID = ofd.OrderID " // Changed alias from 'of' to 'ofd'
                + "LEFT JOIN Food f ON ofd.FoodID = f.FoodID "
                + "LEFT JOIN Order_Drink od ON o.OrderID = od.OrderID "
                + "LEFT JOIN Drink d ON od.DrinkID = d.DrinkID "
                + "LEFT JOIN Order_Buffet ob ON o.OrderID = ob.OrderID "
                + "LEFT JOIN Buffet b ON ob.BuffetID = b.BuffetID "
                + "WHERE o.CustomerID IS NOT NULL" // Always true condition to simplify appending conditions
        );

        // Dynamically add conditions if parameters are provided
        if (customerID != null) {
            query.append(" AND o.CustomerID = ?");
        }
        if (guestNote != null && !guestNote.isEmpty()) {
            query.append(" AND o.GuestNote LIKE ?");
        }
        if (customerName != null && !customerName.isEmpty()) {
            query.append(" AND c.CustomerName LIKE ?");
        }
        if (email != null && !email.isEmpty()) {
            query.append(" AND c.Email LIKE ?");
        }
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            query.append(" AND c.PhoneNumber LIKE ?");
        }
        if (foodName != null && !foodName.isEmpty()) {
            query.append(" AND f.FoodName LIKE ?");
        }
        if (drinkName != null && !drinkName.isEmpty()) {
            query.append(" AND d.DrinkName LIKE ?");
        }
        if (buffetName != null && !buffetName.isEmpty()) {
            query.append(" AND b.BuffetName LIKE ?");
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
            if (customerName != null && !customerName.isEmpty()) {
                st.setString(paramIndex++, "%" + customerName + "%");
            }
            if (email != null && !email.isEmpty()) {
                st.setString(paramIndex++, "%" + email + "%");
            }
            if (phoneNumber != null && !phoneNumber.isEmpty()) {
                st.setString(paramIndex++, "%" + phoneNumber + "%");
            }
            if (foodName != null && !foodName.isEmpty()) {
                st.setString(paramIndex++, "%" + foodName + "%");
            }
            if (drinkName != null && !drinkName.isEmpty()) {
                st.setString(paramIndex++, "%" + drinkName + "%");
            }
            if (buffetName != null && !buffetName.isEmpty()) {
                st.setString(paramIndex++, "%" + buffetName + "%");
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
                order.setTable_OrderID(rs.getInt("Table_OrderID"));

                // Set Customer information
                Customer customer = new Customer();
                customer.setCustomerID(rs.getInt("CustomerID"));
                customer.setCustomerName(rs.getString("CustomerName"));
                customer.setEmail(rs.getString("Email"));
                customer.setPhoneNumber(rs.getString("PhoneNumber"));
                order.setCustomer(customer);

                // Get product name from the first associated product (Food, Drink, or Buffet)
                String productName = null;
                int Quantity = 1;

                // Check for Food
                if (rs.getInt("FoodID") != 0) {
                    productName = rs.getString("FoodName");
                    Quantity = rs.getInt("FoodQuantity");
                } // Check for Drink
                else if (rs.getInt("DrinkID") != 0) {
                    productName = rs.getString("DrinkName");
                    Quantity = rs.getInt("DrinkQuantity");
                } // Check for Buffet
                else if (rs.getInt("BuffetID") != 0) {
                    productName = rs.getString("BuffetName");
                }
                order.setQuantity(Quantity);
                order.setProductName(productName); // Set product name
                orderList.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderList;
    }

    public int countOrders(Integer customerID, String guestNote, String customerName,
            String email, String phoneNumber,
            String productName) {
        int count = 0;
        StringBuilder query = new StringBuilder(
                "SELECT COUNT(*) FROM [dbo].[Order] o "
                + "LEFT JOIN Customer c ON o.CustomerID = c.CustomerID "
                + "LEFT JOIN Order_Food ofd ON o.OrderID = ofd.OrderID "
                + "LEFT JOIN Food f ON ofd.FoodID = f.FoodID "
                + "WHERE o.CustomerID IS NOT NULL"
        );

        // Dynamically add conditions if parameters are provided
        if (customerID != null) {
            query.append(" AND o.CustomerID = ?");
        }
        if (guestNote != null && !guestNote.isEmpty()) {
            query.append(" AND o.GuestNote LIKE ?");
        }
        if (customerName != null && !customerName.isEmpty()) {
            query.append(" AND c.CustomerName LIKE ?");
        }
        if (email != null && !email.isEmpty()) {
            query.append(" AND c.Email LIKE ?");
        }
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            query.append(" AND c.PhoneNumber LIKE ?");
        }
        if (productName != null && !productName.isEmpty()) {
            query.append(" AND f.FoodName LIKE ?");
        }
        if (productName != null && !productName.isEmpty()) {
            query.append(" AND d.DrinkName LIKE ?");
        }
        if (productName != null && !productName.isEmpty()) {
            query.append(" AND b.BuffetName LIKE ?");
        }
        try (PreparedStatement st = connection.prepareStatement(query.toString())) {
            int paramIndex = 1;

            if (customerID != null) {
                st.setInt(paramIndex++, customerID);
            }
            if (guestNote != null && !guestNote.isEmpty()) {
                st.setString(paramIndex++, "%" + guestNote + "%");
            }
            if (customerName != null && !customerName.isEmpty()) {
                st.setString(paramIndex++, "%" + customerName + "%");
            }
            if (email != null && !email.isEmpty()) {
                st.setString(paramIndex++, "%" + email + "%");
            }
            if (phoneNumber != null && !phoneNumber.isEmpty()) {
                st.setString(paramIndex++, "%" + phoneNumber + "%");
            }
            if (productName != null && !productName.isEmpty()) {
                st.setString(paramIndex++, "%" + productName + "%");
            }
            if (productName != null && !productName.isEmpty()) {
                st.setString(paramIndex++, "%" + productName + "%");
            }
            if (productName != null && !productName.isEmpty()) {
                st.setString(paramIndex++, "%" + productName + "%");
            }

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public static void main(String[] args) {
        OrderDAO o = new OrderDAO();
        System.out.println(o.searchOrders(null, null, null, null, null, null, null, null, 0, 10).get(0).getProductName());
    }
}
