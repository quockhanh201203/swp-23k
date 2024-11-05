/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAO.DBContext;
import Model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ADMIN
 */
public class UserDAO extends DBContext{
    private Connection connection;

    public UserDAO() {
        try {
            this.connection = getConnection();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Customer getFirstCustomer() {
        String query = "SELECT TOP 1 CustomerID, CustomerName, PhoneNumber, Email, Point, AccountID "
                + "FROM Customer ORDER BY CustomerID ASC";

        try (PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                Customer customer = new Customer();
                customer.setCustomerID(resultSet.getInt("CustomerID"));
                customer.setCustomerName(resultSet.getString("CustomerName"));
                customer.setPhoneNumber(resultSet.getString("PhoneNumber"));
                customer.setEmail(resultSet.getString("Email"));
                customer.setPoint(resultSet.getInt("Point"));
                customer.setAccountID(resultSet.getInt("AccountID"));

                return customer;
            }
        } catch (SQLException e) {
            System.out.println("getFirstCustomer: " + e.getMessage());
        }
        return null; // No customer found
    }

    public Account getAccountByID(int accountID) {
        String query = "SELECT AccountID, Username, Password, RoleID "
                + "FROM Account WHERE AccountID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, accountID);  // Set the AccountID parameter

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Account account = new Account();
                    account.setAccountID(resultSet.getInt("AccountID"));
                    account.setUsername(resultSet.getString("Username"));
                    account.setPassword(resultSet.getString("Password"));
                    account.setRoleID(resultSet.getInt("RoleID"));

                    return account;
                }
            }
        } catch (SQLException e) {
            System.out.println("getAccountByID: " + e.getMessage());
        }
        return null; // No account found
    }

    public boolean updateProfile(String customerID, String customerName, String phoneNumber) {
        String query = "UPDATE Customer SET customerName=?,  PhoneNumber = ? WHERE [CustomerID] = ?";

        try (
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, customerName);
            statement.setString(2, phoneNumber);
            statement.setString(3, customerID);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("updateProfile: " + e.getMessage());
        }
        return false;
    }
    
    public boolean updateStaffProfile(String staffID, String staffName, String phoneNumber) {
        String query = "UPDATE Staff SET [StaffName]=?,  [PhoneNumber] = ? WHERE [StaffID] = ?";

        try (
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, staffName);
            statement.setString(2, phoneNumber);
            statement.setString(3, staffID);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("updateStaffProfile: " + e.getMessage());
        }
        return false;
    }
    
    public boolean updateAdminProfile(String adminID, String adminName, String phoneNumber) {
        String query = "UPDATE Admin SET [Name]=?,  [PhoneNumber] = ? WHERE [AdminID] = ?";

        try (
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, adminName);
            statement.setString(2, phoneNumber);
            statement.setString(3, adminID);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("updateStaffProfile: " + e.getMessage());
        }
        return false;
    }

    public boolean changePassword(String username, String newPassword, String currentPassword) {
        String query = "UPDATE Account SET Password = ? WHERE [Username] = ? and Password = ?";

        try (
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newPassword);
            statement.setString(2, username);
            statement.setString(3, currentPassword);
            int rowsUpdated = statement.executeUpdate();

            return rowsUpdated > 0; // Return true if password was updated
        } catch (SQLException e) {
            System.out.println("getAccountByID: " + e.getMessage());
        }
        return false;
    }

    public Staff getFirstStaff() {
        String query = "SELECT TOP 1 [StaffID], [StaffName], [PhoneNumber], [Email], [Salary], [NewAccount], [AccountID] FROM [5AnhLucDB].[dbo].[Staff]";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int staffID = rs.getInt("StaffID");
                String staffName = rs.getString("StaffName");
                String phoneNumber = rs.getString("PhoneNumber");
                String email = rs.getString("Email");
                double salary = rs.getDouble("Salary");
                boolean newAccount = rs.getBoolean("NewAccount");
                int accountID = rs.getInt("AccountID");
                return new Staff(staffID, staffName, phoneNumber, email, salary, newAccount, accountID);
            }
        } catch (SQLException e) {
            System.out.println("getFirstStaff: " + e.getMessage());
        }
        return null;
    }
    
    public Admin getFirstAdmin() {
        String query = "SELECT TOP 1 [AdminID], [Name], [PhoneNumber], [Email], [AccountID] FROM [5AnhLucDB].[dbo].[Admin]";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int adminID = rs.getInt("AdminID");
                String name = rs.getString("Name");
                String phoneNumber = rs.getString("PhoneNumber");
                String email = rs.getString("Email");
                int accountID = rs.getInt("AccountID");

                return new Admin(adminID, name, phoneNumber, email, accountID);
            }
        } catch (SQLException e) {
            System.out.println("getFirstAdmin: " + e.getMessage());
        }
        return null;
    }
}
