/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Date;
import java.sql.Time;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.*;

/**
 *
 * @author ADMIN
 */
public class ReservationDAO extends DBContext {

    public List<TableReservation> searchReservations(Date reservationDate, Time reservationTime, Integer numberOfGuests, String status, String tableName, String customerName, String phoneNumber, String email, int pageNumber, int pageSize) {
        List<TableReservation> reservations = new ArrayList<>();

        // Base query
        StringBuilder query = new StringBuilder("SELECT r.ReservationID, r.ReservationDate, r.ReservationTime, r.NumberOfGuests, r.CustomerID, r.TableID, r.Status, r.Notes, "
                + "c.CustomerName, c.PhoneNumber, c.Email, "
                + "t.TableID, t.TableName, t.Status AS TableStatus "
                + "FROM Table_Reservation r "
                + "JOIN Customer c ON r.CustomerID = c.CustomerID "
                + "JOIN [Table] t ON r.TableID = t.TableID "
                + "WHERE 1=1");

        // List to store parameter values
        List<Object> parameters = new ArrayList<>();

        // Dynamically build the query and add parameters based on non-null inputs
        if (reservationDate != null) {
            query.append(" AND r.ReservationDate = ?");
            parameters.add(reservationDate);
        }
        if (reservationTime != null) {
            query.append(" AND r.ReservationTime = ?");
            parameters.add(reservationTime);
        }
        if (numberOfGuests != null) {
            query.append(" AND r.NumberOfGuests = ?");
            parameters.add(numberOfGuests);
        }
        if (status != null && !status.isEmpty()) {
            query.append(" AND r.Status = ?");
            parameters.add(status);
        }
        if (tableName != null && !tableName.isEmpty()) {
            query.append(" AND t.TableName = ?");
            parameters.add(tableName);
        }

        // Add pagination
        int offset = (pageNumber - 1) * pageSize;
        query.append(" ORDER BY r.ReservationDate, r.ReservationTime ");
        query.append("OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

        try {
            PreparedStatement statement = connection.prepareStatement(query.toString());

            // Set parameters in the prepared statement
            for (int i = 0; i < parameters.size(); i++) {
                statement.setObject(i + 1, parameters.get(i)); // PreparedStatement is 1-indexed
            }

            // Set offset and pageSize for pagination
            statement.setInt(parameters.size() + 1, offset);     // Offset
            statement.setInt(parameters.size() + 2, pageSize);   // Fetch Next

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                TableReservation reservation = new TableReservation();
                reservation.setReservationID(rs.getInt("ReservationID"));
                reservation.setReservationDate(rs.getDate("ReservationDate"));
                reservation.setReservationTime(rs.getTime("ReservationTime"));
                reservation.setNumberOfGuests(rs.getInt("NumberOfGuests"));
                reservation.setStatus(rs.getString("Status"));
                reservation.setNotes(rs.getString("Notes"));
                reservation.setTableID(rs.getInt("TableID")); // Set TableID

                // Create Customer object and set details
                Customer customer = new Customer();
                customer.setCustomerID(rs.getInt("CustomerID"));
                customer.setCustomerName(rs.getString("CustomerName"));
                customer.setPhoneNumber(rs.getString("PhoneNumber"));
                customer.setEmail(rs.getString("Email"));
                reservation.setCustomer(customer);

                // Create Table object and set details
                Table table = new Table();
                table.setTableID(rs.getInt("TableID"));
                table.setTableName(rs.getString("TableName"));
                table.setStatus(rs.getString("TableStatus"));
                reservation.setTable(table);

                // Add the reservation to the list
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return reservations;
    }

    public List<TableReservation> searchReservationsByCustomerID(Date reservationDate, Time reservationTime, Integer numberOfGuests, String status, String tableName, Integer customerID, int pageNumber, int pageSize) {
        List<TableReservation> reservations = new ArrayList<>();

        // Base query with pagination placeholders
        StringBuilder query = new StringBuilder("SELECT r.ReservationID, r.ReservationDate, r.ReservationTime, r.NumberOfGuests, r.CustomerID, r.TableID, r.Status, r.Notes, "
                + "c.CustomerName, c.PhoneNumber, c.Email, "
                + "t.TableID, t.TableName, t.Status AS TableStatus "
                + "FROM Table_Reservation r "
                + "JOIN Customer c ON r.CustomerID = c.CustomerID "
                + "JOIN [Table] t ON r.TableID = t.TableID "
                + "WHERE 1=1"); // "1=1" allows easier appending of conditions

        // List to store parameter values
        List<Object> parameters = new ArrayList<>();

        // Dynamically build the query and add parameters based on non-null inputs
        if (reservationDate != null) {
            query.append(" AND r.ReservationDate = ?");
            parameters.add(reservationDate);
        }
        if (reservationTime != null) {
            query.append(" AND r.ReservationTime = ?");
            parameters.add(reservationTime);
        }
        if (numberOfGuests != null) {
            query.append(" AND r.NumberOfGuests = ?");
            parameters.add(numberOfGuests);
        }
        if (status != null && !status.isEmpty()) {
            query.append(" AND r.Status = ?");
            parameters.add(status);
        }
        if (tableName != null && !tableName.isEmpty()) {
            query.append(" AND t.TableName = ?");
            parameters.add(tableName);
        }
        if (customerID != null) {
            query.append(" AND r.CustomerID = ?");
            parameters.add(customerID);
        }

        // Append pagination using OFFSET and FETCH NEXT
        query.append(" ORDER BY r.ReservationDate, r.ReservationTime ");
        query.append("OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

        try {
            PreparedStatement statement = connection.prepareStatement(query.toString());

            // Set parameters in the prepared statement
            for (int i = 0; i < parameters.size(); i++) {
                statement.setObject(i + 1, parameters.get(i)); // PreparedStatement is 1-indexed
            }

            // Calculate the offset for pagination
            int offset = (pageNumber - 1) * pageSize;
            statement.setInt(parameters.size() + 1, offset);
            statement.setInt(parameters.size() + 2, pageSize);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                TableReservation reservation = new TableReservation();
                reservation.setReservationID(rs.getInt("ReservationID"));
                reservation.setReservationDate(rs.getDate("ReservationDate"));
                reservation.setReservationTime(rs.getTime("ReservationTime"));
                reservation.setNumberOfGuests(rs.getInt("NumberOfGuests"));
                reservation.setStatus(rs.getString("Status"));
                reservation.setNotes(rs.getString("Notes"));
                reservation.setTableID(rs.getInt("TableID")); // Set TableID

                // Create Customer object and set details
                Customer customer = new Customer();
                customer.setCustomerID(rs.getInt("CustomerID"));
                customer.setCustomerName(rs.getString("CustomerName"));
                customer.setPhoneNumber(rs.getString("PhoneNumber"));
                customer.setEmail(rs.getString("Email"));
                reservation.setCustomer(customer);

                // Create Table object and set details
                Table table = new Table();
                table.setTableID(rs.getInt("TableID"));
                table.setTableName(rs.getString("TableName"));
                table.setStatus(rs.getString("TableStatus"));
                reservation.setTable(table);

                // Add the reservation to the list
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }

    public int getTotalRecords() {
        int totalRecords = 0;
        String query = "SELECT COUNT(*) AS total FROM Table_Reservation"; // Replace 'Reservations' with your actual table name.

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                totalRecords = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalRecords;
    }

    public String addReservation(TableReservation reservation) {
        String conflictMessage = "There is a reservation conflict for the same table within the time range.";

        // SQL query to check for conflicting reservations (within 1 hour earlier or later)
        String conflictQuery = "SELECT COUNT(*) AS conflictCount FROM Table_Reservation "
                + "WHERE TableID = ? AND ReservationDate = ? "
                + "AND (ReservationTime BETWEEN ? AND ?)";  // Time within 1 hour

        // SQL query to insert the reservation if no conflict
        String insertQuery = "INSERT INTO Table_Reservation (ReservationDate, ReservationTime, NumberOfGuests, CustomerID, TableID, Status, Notes) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            // Check for conflicts
            PreparedStatement conflictStatement = connection.prepareStatement(conflictQuery);
            conflictStatement.setInt(1, reservation.getTableID());
            conflictStatement.setDate(2, reservation.getReservationDate());

            // Set the time range (1 hour earlier and 1 hour later)
            Time oneHourBefore = new Time(reservation.getReservationTime().getTime() - 3600000); // Subtract 1 hour (in milliseconds)
            Time oneHourAfter = new Time(reservation.getReservationTime().getTime() + 3600000);  // Add 1 hour (in milliseconds)

            conflictStatement.setTime(3, oneHourBefore);
            conflictStatement.setTime(4, oneHourAfter);

            ResultSet rs = conflictStatement.executeQuery();
            if (rs.next() && rs.getInt("conflictCount") > 0) {
                return conflictMessage;  // Return conflict message if there is a time conflict
            }

            // If no conflict, proceed with insertion
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setDate(1, reservation.getReservationDate());
            insertStatement.setTime(2, reservation.getReservationTime());
            insertStatement.setInt(3, reservation.getNumberOfGuests());
            insertStatement.setInt(4, reservation.getCustomerID());
            insertStatement.setInt(5, reservation.getTableID());
            insertStatement.setString(6, reservation.getStatus());

            // Handle nullable 'Notes'
            if (reservation.getNotes() != null) {
                insertStatement.setString(7, reservation.getNotes());
            } else {
                insertStatement.setNull(7, java.sql.Types.VARCHAR);
            }

            // Execute the insertion
            insertStatement.executeUpdate();
            return "Reservation added successfully!";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Error occurred while adding reservation.";
        } finally {
            closeConnection();
        }
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT CustomerID, CustomerName FROM Customer";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerID(rs.getInt("CustomerID"));
                customer.setCustomerName(rs.getString("CustomerName"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    // Method to fetch all tables
    public List<Table> getAllTables() {
        List<Table> tables = new ArrayList<>();
        String query = "SELECT TableID, TableName, Status FROM [Table]";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Table table = new Table();
                table.setTableID(rs.getInt("TableID"));
                table.setTableName(rs.getString("TableName"));
                table.setStatus(rs.getString("Status"));
                tables.add(table);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tables;
    }

    public static void main(String[] args) {
        ReservationDAO rd = new ReservationDAO();
        System.out.println(rd.getAllTables().get(0).getTableName());
    }

}
