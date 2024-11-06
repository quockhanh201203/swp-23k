package DAO;

import Model.dao.checkoutTableOrder;
import Model.tableOrder;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class tableOrderDAO extends DBContext {
    public tableOrder newOrderTable(int totalPrice, Date orderDate, int TableID, Integer CustomerID, Integer GuestID) {
        String sql = "INSERT INTO [dbo].[Table_Order] " +
                     "([TotalPrice], [OrderDate], [TableID], [CustomerID], [GuestID]) " +
                     "VALUES (?, ?, ?, ?, ?)";
        try {
            tableOrder a = new tableOrder();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, totalPrice);
            if (orderDate != null) {
                st.setDate(2, new java.sql.Date(orderDate.getTime()));
            } else {
                st.setNull(2, java.sql.Types.DATE);
            }
            st.setInt(3, TableID);  

            // Kiểm tra CustomerID có phải null không
            if (CustomerID != null) {
                st.setInt(4, CustomerID);
            } else {
                st.setNull(4, java.sql.Types.INTEGER); // Nếu CustomerID là null
            }

            // Kiểm tra GuestID có phải null không
            if (GuestID != null) {
                st.setInt(5, GuestID);
            } else {
                st.setNull(5, java.sql.Types.INTEGER); // Nếu GuestID là null
            }

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                a.setTotalPrice(totalPrice);
                a.setOrderDate(orderDate);
                a.setTableID(TableID);
                a.setCustomerID(CustomerID);
                a.setGuestID(GuestID);
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace(); // Kiểm tra ngoại lệ
        }
        return null;
    }

     public int getTableOrderIDc(int customerID) {
    String sql = "SELECT TOP 1 [Table_OrderID]\n" +
"FROM [dbo].[Table_Order]\n" +
"WHERE customerID = ?\n" +
"ORDER BY Table_OrderID DESC;" ;


    try (PreparedStatement st = connection.prepareStatement(sql)) {
        st.setInt(1, customerID);
        try (ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
                // Sửa "cusID" thành "CustomerID"
                return rs.getInt("Table_OrderID");
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return -1; // Trả về -1 nếu không tìm thấy hoặc có lỗi
}
     public int getTableOrderIDg(int guestID) {
    String sql = "SELECT TOP 1 [Table_OrderID]\n" +
"FROM [dbo].[Table_Order]\n" +
"WHERE guestID = ?\n" +
"ORDER BY Table_OrderID DESC;" ;


    try (PreparedStatement st = connection.prepareStatement(sql)) {
        st.setInt(1, guestID);
        try (ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
                // Sửa "cusID" thành "CustomerID"
                return rs.getInt("Table_OrderID");
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return -1; // Trả về -1 nếu không tìm thấy hoặc có lỗi
}
     public void totalUpdate(int total, int customerID,int customerIDcondition) throws Exception {
    String sql = "UPDATE [dbo].[Table_Order]\n" +
"SET [TotalPrice] = ?\n" +
"WHERE CustomerID = ?\n" +
"AND Table_OrderID = (\n" +
"    SELECT MAX(Table_OrderID) \n" +
"    FROM [dbo].[Table_Order] \n" +
"    WHERE CustomerID = ?);";

    try {
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1, total);
        st.setInt(2, customerID);
        st.setInt(3, customerIDcondition);
        int rowsUpdated = st.executeUpdate();
        if (rowsUpdated == 0) {
            throw new Exception("No rows updated, check FoodID and IngredientID.");
        }
    } catch (Exception e) {
        throw new Exception("Error updating quantity: " + e.getMessage());
    }
    
}
     public List<Model.dao.checkoutTableOrder> getstaffTableOrder(int tableID) {
        List<Model.dao.checkoutTableOrder> staffTableOrder = new ArrayList<>();
      String sql = "SELECT \n" +
    "    o.*, \n" +
    "    t.tableName,  \n" +
    "    c.customerName, \n" +
    "    g.guestName  \n" +
    "FROM \n" +
    "    [5AnhLucDB].[dbo].[Table_Order] o\n" +
    "LEFT JOIN \n" +
    "    [5AnhLucDB].[dbo].[Table] t ON o.tableID = t.tableID \n" +
    "LEFT JOIN \n" +
    "    [5AnhLucDB].[dbo].[Customer] c ON o.customerID = c.customerID \n" +
    "LEFT JOIN \n" +
    "    [5AnhLucDB].[dbo].[Guest] g ON o.guestID = g.guestID \n" +
    "WHERE \n" +
    "    o.tableID = ?; ";


        try {
            PreparedStatement st = connection.prepareStatement(sql);
                        st.setInt(1, tableID);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                checkoutTableOrder p = new checkoutTableOrder();

                staffTableOrder.add(new checkoutTableOrder(
                        rs.getInt(1), rs.getInt(2), rs.getDate(3),
                        rs.getInt(4), rs.getInt(5), rs.getInt(6),
                        rs.getString(7), rs.getString(8), rs.getString(9)
                        
                )
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return staffTableOrder;
    }

   public static void main(String[] args) {
        tableOrderDAO d = new tableOrderDAO();
        Date orderDate = new Date(); // Ngày hiện tại
        d.newOrderTable(0, orderDate, 1, 1, null);
    } 
}
