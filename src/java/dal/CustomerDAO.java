/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Customer;

/**
 *
 * @author Admin
 */
public class CustomerDAO extends DBContext {

    private static final String INSERT_CUSTOMERS_SQL = "INSERT INTO Customer (CustomerName, PhoneNumber, Email, Point, AccountID) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_CUSTOMER_BY_ID = "SELECT * FROM Customer WHERE CustomerID = ?";
    private static final String DELETE_CUSTOMER_SQL = "DELETE FROM Customer WHERE CustomerID = ?";
    private static final String UPDATE_CUSTOMER_SQL = "UPDATE Customer SET CustomerName = ?, PhoneNumber = ?, Email = ?, Point = ?, AccountID = ? WHERE CustomerID = ?";

    
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

//    public Customer selectCustomer(int id) {
//        Customer customer = null;
//        try {
//            statement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID);
//            statement.setInt(1, id);
//            resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                String name = resultSet.getString("CustomerName");
//                String phone = resultSet.getString("PhoneNumber");
//                String email = resultSet.getString("Email");
//                int point = resultSet.getInt("Point");
//                int accountID = resultSet.getInt("AccountID");
//                 int accountIDa = resultSet.getInt("AccountID");
//                String username = resultSet.getString("Username");
//                String password = resultSet.getString("Password");
//                int roleID = resultSet.getInt("RoleID");
//                customer.add(new Customer(id, name, phone, email, point, accountID,accountIDa,username,password,roleID));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            closeConnection();
//        }
//        return customer;
//    }
    public Customer updateCustomer(int accountID, int point) {
        String sql = "UPDATE [dbo].[Customer]\n"
                + "   SET \n"
                + "      [Point] = ?\n"
                + " WHERE AccountID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, accountID);
            st.setInt(2, point);

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("xong");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public void deleteCustomer(int AccountID) {
        try {
            statement = connection.prepareStatement(DELETE_CUSTOMER_SQL);
            statement.setInt(1, AccountID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    public static void main(String[] args) {
        CustomerDAO d = new CustomerDAO();
        d.deleteCustomer(6);
    }

}
