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
public class CustomerDAO extends DBContext {

    public Customer getCustomerByAccountID(int accountID) {
        String query = "SELECT CustomerID, CustomerName, PhoneNumber, Email, Point, AccountID "
                + "FROM Customer WHERE AccountID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            // Set the AccountID parameter in the query
            statement.setInt(1, accountID);

            try (ResultSet resultSet = statement.executeQuery()) {
                // If a matching customer is found, create and return a Customer object
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
            }
        } catch (SQLException e) {
            System.out.println("getCustomerByAccountID: " + e.getMessage());
        }

        // Return null if no customer is found for the provided AccountID
        return null;
    }

}
