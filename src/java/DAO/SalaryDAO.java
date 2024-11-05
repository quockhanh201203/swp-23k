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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class SalaryDAO extends DBContext {

    public List<Salary> getAllSalariesWithStaff() {
        List<Salary> salaryList = new ArrayList<>();
        String sql = "SELECT s.SalaryID, s.Salary_Plus, s.Salary_Minus, s.date, s.Note, s.StaffID, "
                + "st.StaffName, st.PhoneNumber, st.Email "
                + "FROM Salary s "
                + "JOIN Staff st ON s.StaffID = st.StaffID";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Salary salary = new Salary();
                salary.setSalaryID(rs.getInt("SalaryID"));
                salary.setSalaryPlus(rs.getInt("Salary_Plus"));
                salary.setSalaryMinus(rs.getInt("Salary_Minus"));
                salary.setDate(rs.getDate("date"));
                salary.setNote(rs.getString("Note"));
                salary.setStaffID(rs.getInt("StaffID"));
                salaryList.add(salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return salaryList;
    }

    // Fetch a salary record by ID
    public Salary getSalaryById(int salaryID) {
        String sql = "SELECT * FROM Salary WHERE SalaryID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, salaryID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Salary salary = new Salary();
                salary.setSalaryID(rs.getInt("SalaryID"));
                salary.setSalaryPlus(rs.getInt("Salary_Plus"));
                salary.setSalaryMinus(rs.getInt("Salary_Minus"));
                salary.setDate(rs.getDate("date"));
                salary.setNote(rs.getString("Note"));
                salary.setStaffID(rs.getInt("StaffID"));
                return salary;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return null;
    }

    // Add a new salary record
    public boolean addSalary(Salary salary) {
        String sql = "INSERT INTO Salary (Salary_Plus, Salary_Minus, date, Note, StaffID) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, salary.getSalaryPlus());
            statement.setInt(2, salary.getSalaryMinus());
            statement.setDate(3, new java.sql.Date(salary.getDate().getTime()));
            statement.setString(4, salary.getNote());
            statement.setInt(5, salary.getStaffID());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return false;
    }

    // Update an existing salary record
    public boolean updateSalary(int SalaryPlus, int SalaryMinus, Date Date, String Note, int SalaryID) {
        String sql = "UPDATE Salary SET Salary_Plus = ?, Salary_Minus = ?, date = ?, Note = ? WHERE SalaryID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, SalaryPlus);
            statement.setInt(2, SalaryMinus);
            statement.setDate(3, new java.sql.Date(Date.getTime()));  // Sửa dòng date
            statement.setString(4, Note);
            statement.setInt(5, SalaryID);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return false;
    }

    // Delete a salary record by ID
    public boolean deleteSalary(int salaryID) {
        String sql = "DELETE FROM Salary WHERE SalaryID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, salaryID);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return false;
    }

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.FEBRUARY, 1);  // Thiết lập ngày
        Date date = new Date(calendar.getTimeInMillis());
        SalaryDAO d = new SalaryDAO();
        boolean isUpdated = d.updateSalary(123, 456, date, "abc", 13);

        // Thông báo kết quả cập nhật
        if (isUpdated) {
            System.out.println("Salary updated successfully.");
        } else {
            System.out.println("Failed to update salary.");
        }
    }
}
