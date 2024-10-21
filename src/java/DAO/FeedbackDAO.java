/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class FeedbackDAO extends DBContext {

    public void addFeedback(Feedback feedback) {
        String query = "INSERT INTO Feedback (FeedbackNote, CustomerID) VALUES (?, ?)";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, feedback.getFeedbackNote());
            statement.setInt(2, feedback.getCustomerID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    // Add response (Staff response to feedback)
    public void addResponde(Responde responde) {
        String query = "INSERT INTO Responde (RespondeNote, FeedBackID, StaffID) VALUES (?, ?, ?)";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, responde.getRespondeNote());
            statement.setInt(2, responde.getFeedBackID());
            statement.setInt(3, responde.getStaffID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public List<Feedback> getAllFeedbackByCustomerID(int customerID) {
        List<Feedback> feedbackList = new ArrayList<>();
        String query = "SELECT f.FeedbackID, f.FeedbackNote, f.CustomerID, "
                + "c.CustomerID, c.CustomerName, c.PhoneNumber, c.Email, c.Point, "
                + "r.RespondeID, r.RespondeNote, r.StaffID "
                + "FROM Feedback f "
                + "JOIN Customer c ON f.CustomerID = c.CustomerID "
                + "LEFT JOIN Responde r ON f.FeedbackID = r.FeedbackID "
                + "WHERE f.CustomerID = ? "
                + "ORDER BY f.FeedbackID DESC"; // Added ORDER BY clause

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, customerID);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Feedback feedback = new Feedback();
                feedback.setFeedbackID(resultSet.getInt("FeedbackID"));
                feedback.setFeedbackNote(resultSet.getString("FeedbackNote"));
                feedback.setCustomerID(resultSet.getInt("CustomerID"));

                // Create and set Customer object
                Customer customer = new Customer();
                customer.setCustomerID(resultSet.getInt("CustomerID"));
                customer.setCustomerName(resultSet.getString("CustomerName"));
                customer.setPhoneNumber(resultSet.getString("PhoneNumber"));
                customer.setEmail(resultSet.getString("Email"));
                customer.setPoint(resultSet.getInt("Point"));
                feedback.setCustomer(customer);

                // Create and set Responde object (if exists)
                Responde responde = null;
                if (resultSet.getInt("RespondeID") != 0) {
                    responde = new Responde();
                    responde.setRespondeID(resultSet.getInt("RespondeID"));
                    responde.setRespondeNote(resultSet.getString("RespondeNote"));
                    responde.setFeedBackID(resultSet.getInt("FeedbackID")); // Correct FeedbackID
                    responde.setStaffID(resultSet.getInt("StaffID"));
                }
                feedback.setResponde(responde);

                feedbackList.add(feedback);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return feedbackList;
    }

    public Feedback getFeedbackByID(int feedbackID) {
        Feedback feedback = null;
        String query = "SELECT f.FeedbackID, f.FeedbackNote, f.CustomerID, "
                + "c.CustomerID, c.CustomerName, c.PhoneNumber, c.Email, c.Point, "
                + "r.RespondeID, r.RespondeNote, r.StaffID "
                + "FROM Feedback f "
                + "JOIN Customer c ON f.CustomerID = c.CustomerID "
                + "LEFT JOIN Responde r ON f.FeedbackID = r.FeedbackID "
                + "WHERE f.FeedbackID = ? "
                + "ORDER BY f.FeedbackID DESC"; // Optional but added for consistency

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, feedbackID);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                feedback = new Feedback();
                feedback.setFeedbackID(resultSet.getInt("FeedbackID"));
                feedback.setFeedbackNote(resultSet.getString("FeedbackNote"));
                feedback.setCustomerID(resultSet.getInt("CustomerID"));

                // Create and set Customer object
                Customer customer = new Customer();
                customer.setCustomerID(resultSet.getInt("CustomerID"));
                customer.setCustomerName(resultSet.getString("CustomerName"));
                customer.setPhoneNumber(resultSet.getString("PhoneNumber"));
                customer.setEmail(resultSet.getString("Email"));
                customer.setPoint(resultSet.getInt("Point"));
                feedback.setCustomer(customer);

                // Create and set Responde object (if exists)
                Responde responde = null;
                if (resultSet.getInt("RespondeID") != 0) {
                    responde = new Responde();
                    responde.setRespondeID(resultSet.getInt("RespondeID"));
                    responde.setRespondeNote(resultSet.getString("RespondeNote"));
                    responde.setFeedBackID(resultSet.getInt("FeedbackID")); // Correct FeedbackID
                    responde.setStaffID(resultSet.getInt("StaffID"));
                }
                feedback.setResponde(responde);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return feedback;
    }

    public List<Feedback> searchFeedback(String customerName, String feedbackNote, boolean onlyWithResponse) {
        List<Feedback> feedbackList = new ArrayList<>();
        StringBuilder query = new StringBuilder(
                "SELECT f.FeedbackID, f.FeedbackNote, f.CustomerID, "
                + "c.CustomerID, c.CustomerName, c.PhoneNumber, c.Email, c.Point, "
                + "r.RespondeID, r.RespondeNote, r.StaffID "
                + "FROM Feedback f "
                + "JOIN Customer c ON f.CustomerID = c.CustomerID "
                + "LEFT JOIN Responde r ON f.FeedbackID = r.FeedbackID "
                + "WHERE 1=1" // Always true condition to simplify appending conditions
        );

        // Dynamically add conditions if the parameters are not null
        if (customerName != null && !customerName.isEmpty()) {
            query.append(" AND c.CustomerName LIKE ?");
        }
        if (feedbackNote != null && !feedbackNote.isEmpty()) {
            query.append(" AND f.FeedbackNote LIKE ?");
        }
        if (onlyWithResponse) {
            query.append(" AND r.RespondeID IS NOT NULL"); // Filter for feedback with a response
        }

        try {
            statement = connection.prepareStatement(query.toString());
            int paramIndex = 1;

            // Set parameters only if they are included in the query
            if (customerName != null && !customerName.isEmpty()) {
                statement.setString(paramIndex++, "%" + customerName + "%");
            }
            if (feedbackNote != null && !feedbackNote.isEmpty()) {
                statement.setString(paramIndex++, "%" + feedbackNote + "%");
            }

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Feedback feedback = new Feedback();
                feedback.setFeedbackID(resultSet.getInt("FeedbackID"));
                feedback.setFeedbackNote(resultSet.getString("FeedbackNote"));
                feedback.setCustomerID(resultSet.getInt("CustomerID"));

                // Create and set Customer object
                Customer customer = new Customer();
                customer.setCustomerID(resultSet.getInt("CustomerID"));
                customer.setCustomerName(resultSet.getString("CustomerName"));
                customer.setPhoneNumber(resultSet.getString("PhoneNumber"));
                customer.setEmail(resultSet.getString("Email"));
                customer.setPoint(resultSet.getInt("Point"));
                feedback.setCustomer(customer);

                // Create and set Responde object (if exists)
                Responde responde = null;
                if (resultSet.getInt("RespondeID") != 0) {
                    responde = new Responde();
                    responde.setRespondeID(resultSet.getInt("RespondeID"));
                    responde.setRespondeNote(resultSet.getString("RespondeNote"));
                    responde.setFeedBackID(resultSet.getInt("FeedbackID"));
                    responde.setStaffID(resultSet.getInt("StaffID"));
                }
                feedback.setResponde(responde);

                feedbackList.add(feedback);
            }
            System.out.println(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return feedbackList;
    }

    // Fetch responde based on feedback ID
    public Responde getRespondeByFeedbackID(int feedbackID) {
        Responde responde = null;
        String query = "SELECT * FROM Responde WHERE FeedBackID = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, feedbackID);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                responde = new Responde();
                responde.setRespondeID(resultSet.getInt("RespondeID"));
                responde.setRespondeNote(resultSet.getString("RespondeNote"));
                responde.setFeedBackID(resultSet.getInt("FeedBackID"));
                responde.setStaffID(resultSet.getInt("StaffID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return responde;
    }
}
