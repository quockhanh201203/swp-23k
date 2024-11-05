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
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ADMIN
 */
public class CustomerDAO extends DBContext {
    
    private static final Logger LOGGER = Logger.getLogger(CustomerDAO.class.getName());

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
    
    public List<Customer> selectAllCustomers() {
        List<Customer> customerList = new ArrayList<>();
        String sql = "SELECT *\n"
                + "FROM Customer\n"
                + "LEFT JOIN Account ON Customer.AccountID = Account.AccountID;";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Customer p = new Customer();

                customerList.add(new Customer(
                        rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5), rs.getInt(6)
                        
                )
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return customerList;
    }
    
    public List<Customer> searchCustomersByName(String name) {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT CustomerID, CustomerName, PhoneNumber, Email, Point, AccountID FROM Customer WHERE CustomerName LIKE ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + name + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
               while (resultSet.next()) {
                // Retrieve data from each row and create a new Customer object
                int customerID = resultSet.getInt("CustomerID");
                String customerName = resultSet.getString("CustomerName");
                String phoneNumber = resultSet.getString("PhoneNumber");
                String email = resultSet.getString("Email");
                int point = resultSet.getInt("Point");
                int accountID = resultSet.getInt("AccountID");

                Customer customer = new Customer(customerID, customerName, phoneNumber, email, point, accountID);
                customers.add(customer);
            }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    public void deleteCustomer(int accountID) {
        String query = "DELETE FROM Customer WHERE AccountID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, accountID);
            int rowsAffected = statement.executeUpdate();  // Đếm số dòng bị ảnh hưởng
            if (rowsAffected == 0) {
                System.out.println("No customer found with AccountID: " + accountID);
            } else {
                System.out.println("Customer with AccountID " + accountID + " has been deleted.");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting customer with AccountID: " + accountID, e);
        }
    }
    
    public static void main(String[] args) {
        CustomerDAO c = new CustomerDAO();
        System.out.println(c.searchCustomersByName("sa"));
    }

}
