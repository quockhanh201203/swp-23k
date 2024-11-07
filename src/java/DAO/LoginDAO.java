/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Account;
import Model.guest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author tran tung
 */
public class LoginDAO extends DBContext{
     public Account login(String username, String password) {
        String sql = "SELECT * FROM account WHERE username = ? AND password = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            

            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Account a = new Account();
                a.setAccountID(rs.getInt("AccountID"));
                a.setUsername(rs.getString("Username"));
                a.setPassword(rs.getString("Password"));
                a.setRoleID(rs.getInt("RoleID"));
                
                
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     
     
     
     public Account getId(String username) {
    String sql = "SELECT AccountID, Username FROM Account WHERE Username = ?";

    try (PreparedStatement st = connection.prepareStatement(sql)) {
        st.setString(1, username);
        try (ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
                Account a = new Account();
                a.setAccountID(rs.getInt("AccountID"));
                a.setUsername(rs.getString("username"));
                return a;
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}
     public guest newGuest(String guestName, String guestPhone) {
    String sql = "INSERT INTO [dbo].[Guest]\n" +
"           ([GuestName]\n" +
"           ,[GuestPhone])\n" +
"     VALUES\n" +
"           (?\n" +
"           ,?)";
    try {
        guest a = new guest();
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, guestName);
        st.setString(2, guestPhone);
        
        
        int rowsAffected = st.executeUpdate();
        if (rowsAffected > 0) {
            a.setGuestName(guestName); // Sử dụng setter hợp lệ
            a.setGuestPhone(guestPhone); // Sử dụng setter hợp lệ
    
            return a;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}
      public int getGuestId(String guestName) {
    String sql = "SELECT TOP 1 GuestID\n" +
                 "FROM guest\n" +
                 "WHERE GuestName = ?\n" +
                 "ORDER BY GuestID DESC;";

    try (PreparedStatement st = connection.prepareStatement(sql)) {
        st.setString(1, guestName);
        try (ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("GuestID"); // Trả về GuestID trực tiếp
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return -1; // Trả về -1 nếu không tìm thấy
}
  public int getCustomerId(int accountID) {
    String sql = "SELECT [CustomerID] FROM [dbo].[Customer] WHERE AccountID = ?";

    try (PreparedStatement st = connection.prepareStatement(sql)) {
        st.setInt(1, accountID);
        try (ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
                // Sửa "cusID" thành "CustomerID"
                return rs.getInt("CustomerID");
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return -1; // Trả về -1 nếu không tìm thấy hoặc có lỗi
}
  
  
   public int getCustomerIdbyName(String customerName) {
    String sql = "SELECT [CustomerID] FROM [dbo].[Customer] WHERE CustomerName = ?";

    try (PreparedStatement st = connection.prepareStatement(sql)) {
        st.setString(1, customerName);
        try (ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
                // Sửa "cusID" thành "CustomerID"
                return rs.getInt("CustomerID");
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return -1; // Trả về -1 nếu không tìm thấy hoặc có lỗi
}
  
  

     public static void main(String[] args) {
        
        LoginDAO d = new LoginDAO();
        
        int cusID = d.getCustomerId(1);
         System.out.println(cusID);
    }
}
