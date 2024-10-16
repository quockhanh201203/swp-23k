/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.List;
import Model.*;
import java.sql.Date;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author ADMIN
 */
public class ShiftDAO extends DBContext {

    public List<Shift> getShiftsByDateRange(Date startDate, Date endDate, String staffName) throws Exception {
        List<Shift> shifts = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT s.ShiftID, s.StaffQuantity, s.WEEKDATE, s.date, s.DayTime, "
                + "ss.StaffID, ss.Status, st.StaffName, st.PhoneNumber, st.Email, st.Salary, st.NewAccount "
                + "FROM Shift s "
                + "LEFT JOIN Shift_Staff ss ON s.ShiftID = ss.ShiftID "
                + "LEFT JOIN Staff st ON ss.StaffID = st.StaffID "
                + "WHERE s.date BETWEEN ? AND ? ");

        // Append condition for StaffName if it's not null or empty
        if (staffName != null && !staffName.trim().isEmpty()) {
            query.append("AND st.StaffName LIKE ? ");
        }
        query.append("ORDER BY s.date ASC");

        try (PreparedStatement statement = connection.prepareStatement(query.toString())) {
            statement.setDate(1, startDate);
            statement.setDate(2, endDate);

            // Set StaffName parameter if it's provided
            if (staffName != null && !staffName.trim().isEmpty()) {
                statement.setString(3, "%" + staffName + "%"); // Use LIKE for partial matching
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    // Initialize Shift object
                    Shift shift = new Shift();
                    shift.setShiftID(resultSet.getInt("ShiftID"));
                    shift.setStaffQuantity(resultSet.getInt("StaffQuantity"));
                    shift.setWeekDate(resultSet.getString("WEEKDATE"));
                    shift.setDate(resultSet.getDate("date"));
                    shift.setDayTime(resultSet.getBoolean("DayTime"));

                    // Create the shift_staffs list if it's null
                    if (shift.getShift_staffs() == null) {
                        shift.setShift_staffs(new ArrayList<>());
                    }

                    // Create ShiftStaff object
                    int staffID = resultSet.getInt("StaffID");
                    if (staffID > 0) { // Only add ShiftStaff if StaffID is valid
                        ShiftStaff shiftStaff = new ShiftStaff();
                        shiftStaff.setShiftID(shift.getShiftID());
                        shiftStaff.setStaffID(staffID);
                        shiftStaff.setStatus(resultSet.getString("Status"));

                        // Create Staff object
                        Staff staff = new Staff();
                        staff.setStaffID(staffID);
                        staff.setStaffName(resultSet.getString("StaffName"));
                        staff.setPhoneNumber(resultSet.getString("PhoneNumber"));
                        staff.setEmail(resultSet.getString("Email"));
                        staff.setSalary(resultSet.getInt("Salary"));
                        staff.setNewAccount(resultSet.getBoolean("NewAccount"));

                        // Associate Staff with ShiftStaff
                        shiftStaff.setStaff(staff);

                        // Add ShiftStaff to the shift's shift_staffs list
                        shift.getShift_staffs().add(shiftStaff);
                    }

                    shifts.add(shift);
                }
            }
        }
        System.out.println(query);
        return shifts;
    }

    public String addShiftStaff(int shiftID, int staffID, String status) {
        String message = "";

        // SQL to insert a ShiftStaff entry for the specific shift
        String sqlInsert = "INSERT INTO Shift_Staff (ShiftID, StaffID, Status) VALUES (?, ?, ?)";

        // SQL to retrieve future shifts with the same WEEKDATE and DayTime but later dates
        String sqlFutureShifts = "SELECT ShiftID FROM Shift WHERE WEEKDATE = (SELECT WEEKDATE FROM Shift WHERE ShiftID = ?) "
                + "AND DayTime = (SELECT DayTime FROM Shift WHERE ShiftID = ?) "
                + "AND date > (SELECT date FROM Shift WHERE ShiftID = ?)";

        try (PreparedStatement pstmtInsert = connection.prepareStatement(sqlInsert); PreparedStatement pstmtFutureShifts = connection.prepareStatement(sqlFutureShifts)) {

            // Insert ShiftStaff for the current shift
            pstmtInsert.setInt(1, shiftID);
            pstmtInsert.setInt(2, staffID);
            pstmtInsert.setString(3, status);
            int rowsAffected = pstmtInsert.executeUpdate();

            // Check if the current insertion was successful
            if (rowsAffected > 0) {
                message = "ShiftStaff record successfully added for ShiftID: " + shiftID;

                // Get future shifts with the same WEEKDATE and DayTime
                pstmtFutureShifts.setInt(1, shiftID);
                pstmtFutureShifts.setInt(2, shiftID);
                pstmtFutureShifts.setInt(3, shiftID);

                try (ResultSet futureShifts = pstmtFutureShifts.executeQuery()) {
                    while (futureShifts.next()) {
                        int futureShiftID = futureShifts.getInt("ShiftID");

                        // Insert the ShiftStaff for future shifts
                        pstmtInsert.setInt(1, futureShiftID); // update ShiftID for future shift
                        rowsAffected = pstmtInsert.executeUpdate();
                        if (rowsAffected > 0) {
                            message += "\nShiftStaff record successfully added for future ShiftID: " + futureShiftID;
                        }
                    }
                }
            } else {
                message = "Failed to add ShiftStaff record for ShiftID: " + shiftID;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            message = "Error adding ShiftStaff to database: " + e.getMessage();
        }

        return message;
    }

    public String deleteShiftStaff(int shiftID, int staffID) {
        String message = "";

        // SQL to delete a ShiftStaff entry for the specific shift
        String sqlDelete = "DELETE FROM Shift_Staff WHERE ShiftID = ? AND StaffID = ?";

        // SQL to retrieve future shifts with the same WEEKDATE and DayTime but later dates
        String sqlFutureShifts = "SELECT ShiftID FROM Shift WHERE WEEKDATE = (SELECT WEEKDATE FROM Shift WHERE ShiftID = ?) "
                + "AND DayTime = (SELECT DayTime FROM Shift WHERE ShiftID = ?) "
                + "AND date > (SELECT date FROM Shift WHERE ShiftID = ?)";

        try (PreparedStatement pstmtDelete = connection.prepareStatement(sqlDelete); PreparedStatement pstmtFutureShifts = connection.prepareStatement(sqlFutureShifts)) {

            // Delete ShiftStaff for the current shift
            pstmtDelete.setInt(1, shiftID);
            pstmtDelete.setInt(2, staffID);
            int rowsAffected = pstmtDelete.executeUpdate();

            // Check if the current deletion was successful
            if (rowsAffected > 0) {
                message = "ShiftStaff record successfully deleted for ShiftID: " + shiftID;

                // Get future shifts with the same WEEKDATE and DayTime
                pstmtFutureShifts.setInt(1, shiftID);
                pstmtFutureShifts.setInt(2, shiftID);
                pstmtFutureShifts.setInt(3, shiftID);

                try (ResultSet futureShifts = pstmtFutureShifts.executeQuery()) {
                    while (futureShifts.next()) {
                        int futureShiftID = futureShifts.getInt("ShiftID");

                        // Delete the ShiftStaff for future shifts
                        pstmtDelete.setInt(1, futureShiftID); // update ShiftID for future shift
                        rowsAffected = pstmtDelete.executeUpdate();
                        if (rowsAffected > 0) {
                            message += "\nShiftStaff record successfully deleted for future ShiftID: " + futureShiftID;
                        }
                    }
                }
            } else {
                message = "No ShiftStaff record found for ShiftID: " + shiftID + " and StaffID: " + staffID;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            message = "Error deleting ShiftStaff from database: " + e.getMessage();
        }

        return message;
    }

    public Shift getShiftByShiftID(int shiftID) throws Exception {
        Shift shift = null; // Initialize as null to return later if no shift is found

        // Build SQL query
        String query = "SELECT s.ShiftID, s.StaffQuantity, s.WEEKDATE, s.date, s.DayTime, "
                + "ss.StaffID, ss.Status, st.StaffName, st.PhoneNumber, st.Email, st.Salary, st.NewAccount "
                + "FROM Shift s "
                + "LEFT JOIN Shift_Staff ss ON s.ShiftID = ss.ShiftID "
                + "LEFT JOIN Staff st ON ss.StaffID = st.StaffID "
                + "WHERE s.ShiftID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Set the ShiftID parameter
            statement.setInt(1, shiftID);

            // Execute the query
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    // If shift is null, initialize it once
                    if (shift == null) {
                        shift = new Shift();
                        shift.setShiftID(resultSet.getInt("ShiftID"));
                        shift.setStaffQuantity(resultSet.getInt("StaffQuantity"));
                        shift.setWeekDate(resultSet.getString("WEEKDATE"));
                        shift.setDate(resultSet.getDate("date"));
                        shift.setDayTime(resultSet.getBoolean("DayTime"));

                        // Initialize the shift_staffs list
                        shift.setShift_staffs(new ArrayList<>());
                    }

                    // Process ShiftStaff only if StaffID is valid
                    int staffID = resultSet.getInt("StaffID");
                    if (staffID > 0) {
                        // Create ShiftStaff object
                        ShiftStaff shiftStaff = new ShiftStaff();
                        shiftStaff.setShiftID(shift.getShiftID());
                        shiftStaff.setStaffID(staffID);
                        shiftStaff.setStatus(resultSet.getString("Status"));

                        // Create Staff object and set its details
                        Staff staff = new Staff();
                        staff.setStaffID(staffID);
                        staff.setStaffName(resultSet.getString("StaffName"));
                        staff.setPhoneNumber(resultSet.getString("PhoneNumber"));
                        staff.setEmail(resultSet.getString("Email"));
                        staff.setSalary(resultSet.getInt("Salary"));
                        staff.setNewAccount(resultSet.getBoolean("NewAccount"));

                        // Associate Staff with ShiftStaff
                        shiftStaff.setStaff(staff);

                        // Add ShiftStaff to shift's list of shift_staffs
                        shift.getShift_staffs().add(shiftStaff);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log and handle the exception properly
            throw new Exception("Error retrieving Shift by ShiftID", e);
        }

        return shift; // Return the Shift object or null if no shift found
    }

    public List<Shift> getMostRecentShiftsBefore(Date beforeDate) throws Exception {
        List<Shift> shifts = new ArrayList<>();
        String query = "SELECT Top 14 s.ShiftID, s.StaffQuantity, s.WEEKDATE, s.date, s.DayTime, "
                + "ss.ShiftID , ss.StaffID, ss.Status, st.StaffName, st.PhoneNumber, st.Email, st.Salary, st.NewAccount "
                + "FROM Shift s "
                + "LEFT JOIN Shift_Staff ss ON s.ShiftID = ss.ShiftID "
                + "LEFT JOIN Staff st ON ss.StaffID = st.StaffID "
                + "WHERE s.date < ? "
                + "ORDER BY s.date DESC "; // Limit to a week's shifts

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, beforeDate);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Shift shift = new Shift();
                    shift.setShiftID(resultSet.getInt("ShiftID"));
                    shift.setStaffQuantity(resultSet.getInt("StaffQuantity"));
                    shift.setWeekDate(resultSet.getString("WEEKDATE"));
                    shift.setDate(resultSet.getDate("date"));
                    shift.setDayTime(resultSet.getBoolean("DayTime"));

                    // Create the shift_staffs list if it's null
                    if (shift.getShift_staffs() == null) {
                        shift.setShift_staffs(new ArrayList<>());
                    }

                    // Create ShiftStaff object
                    int staffID = resultSet.getInt("StaffID");
                    if (staffID > 0) { // Only add ShiftStaff if StaffID is valid
                        ShiftStaff shiftStaff = new ShiftStaff();
                        shiftStaff.setShiftID(shift.getShiftID());
                        shiftStaff.setStaffID(staffID);
                        shiftStaff.setStatus(resultSet.getString("Status"));

                        // Create Staff object
                        Staff staff = new Staff();
                        staff.setStaffID(staffID);
                        staff.setStaffName(resultSet.getString("StaffName"));
                        staff.setPhoneNumber(resultSet.getString("PhoneNumber"));
                        staff.setEmail(resultSet.getString("Email"));
                        staff.setSalary(resultSet.getInt("Salary"));
                        staff.setNewAccount(resultSet.getBoolean("NewAccount"));

                        // Associate Staff with ShiftStaff
                        shiftStaff.setStaff(staff);

                        // Add ShiftStaff to the shift's shift_staffs list
                        shift.getShift_staffs().add(shiftStaff);
                    }
                    shifts.add(shift);
                }
            }
        }
        return shifts;
    }

    public List<Shift> cloneShiftsForNewWeek(List<Shift> previousShifts, LocalDate newStartDate) {
        List<Shift> newShifts = new ArrayList<>();

        // Calculate date difference between the old and new week
        LocalDate oldStartDate = previousShifts.get(previousShifts.size() - 1).getDate().toLocalDate(); // Assuming shifts are sorted by date
        long daysDifference = ChronoUnit.DAYS.between(oldStartDate, newStartDate);

        for (Shift oldShift : previousShifts) {
            Shift newShift = new Shift();

            // Copy shift details except the date
            newShift.setStaffQuantity(oldShift.getStaffQuantity());
            newShift.setWeekDate(oldShift.getWeekDate()); // Update week date for the new week
            newShift.setDayTime(oldShift.isDayTime());

            // Adjust the date based on the new week
            LocalDate oldShiftDate = oldShift.getDate().toLocalDate();
            LocalDate newShiftDate = oldShiftDate.plusDays(daysDifference);
            newShift.setDate(Date.valueOf(newShiftDate));

            // Clone the shift staffs
            List<ShiftStaff> newShiftStaffs = new ArrayList<>();
            for (ShiftStaff oldShiftStaff : oldShift.getShift_staffs()) {
                ShiftStaff newShiftStaff = new ShiftStaff();
                newShiftStaff.setStaffID(oldShiftStaff.getStaffID());
                newShiftStaff.setStatus(oldShiftStaff.getStatus());
                newShiftStaff.setStaff(oldShiftStaff.getStaff()); // Copy Staff info

                newShiftStaffs.add(newShiftStaff);
            }
            newShift.setShift_staffs(newShiftStaffs);

            newShifts.add(newShift);
        }

        return newShifts;
    }

    public void insertShifts(List<Shift> shifts) throws Exception {
        String insertShiftQuery = "INSERT INTO Shift (StaffQuantity, WEEKDATE, date, DayTime) VALUES (?, ?, ?, ?)";
        String insertShiftStaffQuery = "INSERT INTO Shift_Staff (ShiftID, StaffID, Status) VALUES (?, ?, ?)";

        try {
            // Begin transaction
            connection.setAutoCommit(false);

            // Prepare the statement for inserting shifts
            try (PreparedStatement shiftStatement = connection.prepareStatement(insertShiftQuery, statement.RETURN_GENERATED_KEYS); PreparedStatement shiftStaffStatement = connection.prepareStatement(insertShiftStaffQuery)) {

                for (Shift shift : shifts) {
                    // Insert the Shift
                    shiftStatement.setInt(1, shift.getStaffQuantity());
                    shiftStatement.setString(2, shift.getWeekDate());
                    shiftStatement.setDate(3, shift.getDate());
                    shiftStatement.setBoolean(4, shift.isDayTime());

                    // Execute the insertion and get the generated ShiftID
                    shiftStatement.executeUpdate();

                    try (ResultSet generatedKeys = shiftStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int newShiftID = generatedKeys.getInt(1); // Get the generated ShiftID

                            // Insert the corresponding Shift_Staff entries
                            if (shift.getShift_staffs() != null) {
                                for (ShiftStaff shiftStaff : shift.getShift_staffs()) {
                                    shiftStaffStatement.setInt(1, newShiftID); // Use the newly generated ShiftID
                                    shiftStaffStatement.setInt(2, shiftStaff.getStaffID());
                                    shiftStaffStatement.setString(3, shiftStaff.getStatus());

                                    shiftStaffStatement.executeUpdate(); // Insert Shift_Staff
                                }
                            }
                        }
                    }
                }

                // Commit the transaction
                connection.commit();
            } catch (SQLException e) {
                // Rollback in case of any errors
                connection.rollback();
                throw e;
            } finally {
                // Reset auto-commit to true
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error inserting shifts", e);
        }
    }

    public boolean isShiftTableEmpty() throws Exception {
        String query = "SELECT COUNT(*) FROM Shift";

        try (PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getInt(1) == 0; // Return true if the table is empty (count == 0)
            }
        }

        return false;
    }

    public List<Shift> generateDefaultShifts(LocalDate startDate) {
        List<Shift> defaultShifts = new ArrayList<>();
        String[] week = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Satuday", "Sunday"};

        // Create default shift for each day (Monday to Sunday)
        for (int i = 0; i < 7; i++) {
            //Day
            Shift dayshift = new Shift();
            dayshift.setStaffQuantity(0); // No staff assigned initially
            dayshift.setWeekDate(week[i]);
            dayshift.setDate(Date.valueOf(startDate.plusDays(i))); // Set the actual date
            dayshift.setDayTime(true); // Assuming a default shift is daytime (could be parameterized)

            // No Shift_Staff objects added since we want shifts without staff
            defaultShifts.add(dayshift);

            //Night
            Shift nightshift = new Shift();
            nightshift.setStaffQuantity(0); // No staff assigned initially
            nightshift.setWeekDate(week[i]);
            nightshift.setDate(Date.valueOf(startDate.plusDays(i))); // Set the actual date
            nightshift.setDayTime(false); // Assuming a default shift is daytime (could be parameterized)

            // No Shift_Staff objects added since we want shifts without staff
            defaultShifts.add(nightshift);

        }

        return defaultShifts;
    }

    public String updateShiftStaffStatusBeforeToday(String oldStatus, String newStatus) {
        String message = "";

        // SQL to update the status of Shift_Staff records where the Shift date is before today and the current status matches oldStatus
        String sqlUpdate = "UPDATE Shift_Staff ss "
                + "SET ss.Status = ? "
                + "WHERE ss.Status = ? "
                + "AND ss.ShiftID IN ("
                + "  SELECT s.ShiftID FROM Shift s WHERE s.date < CURRENT_DATE"
                + ")";

        try (PreparedStatement pstmt = connection.prepareStatement(sqlUpdate)) {

            // Set the new status and the old status to be replaced
            pstmt.setString(1, newStatus);
            pstmt.setString(2, oldStatus);

            // Execute the update query
            int rowsAffected = pstmt.executeUpdate();

            // Generate the success or failure message based on rows affected
            if (rowsAffected > 0) {
                message = "Successfully replaced status '" + oldStatus + "' with '" + newStatus + "' for " + rowsAffected + " Shift_Staff records before today.";
            } else {
                message = "No Shift_Staff records found with status '" + oldStatus + "' and shifts before today.";
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Log the error or handle it accordingly
            message = "Error updating Shift_Staff status: " + e.getMessage();
        }

        return message;
    }
    
    public String updateShiftStaffStatusById(int shiftStaffID, String newStatus) {
    String message = "";
    
    // SQL query to update the status of the Shift_Staff record for a specific Shift_StaffID
    String sqlUpdate = "UPDATE Shift_Staff "
                     + "SET Status = ? "
                     + "WHERE Shift_StaffID = ?";

    try (PreparedStatement pstmt = connection.prepareStatement(sqlUpdate)) {
        
        // Set the new status and the Shift_StaffID
        pstmt.setString(1, newStatus);
        pstmt.setInt(2, shiftStaffID);

        // Execute the update query
        int rowsAffected = pstmt.executeUpdate();

        // Generate the success or failure message based on rows affected
        if (rowsAffected > 0) {
            message = "Successfully updated status to '" + newStatus + "' for Shift_StaffID: " + shiftStaffID;
        } else {
            message = "No Shift_Staff record found with Shift_StaffID: " + shiftStaffID;
        }

    } catch (SQLException e) {
        e.printStackTrace(); // Log the error or handle it accordingly
        message = "Error updating Shift_Staff status: " + e.getMessage();
    }

    return message;
}

    public static void main(String[] args) throws Exception {
        ShiftDAO sd = new ShiftDAO();
        System.out.println(sd.getShiftsByDateRange(Date.valueOf("2024-10-09"), Date.valueOf("2024-11-09"), "").get(0).getShiftID());
    }

}
