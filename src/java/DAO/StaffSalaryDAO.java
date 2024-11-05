package DAO;

import Model.Salary;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffSalaryDAO extends DBContext {

    // Lấy lương cứng của một nhân viên
    public int getFixedSalary(int staffID) {
        int salary = 0;
        try {
            String query = "SELECT Salary FROM Staff WHERE StaffID = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, staffID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                salary = resultSet.getInt("Salary");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salary;
    }

    // Lấy tổng lương thưởng của một nhân viên
    public int getTotalSalaryPlus(int staffID) {
        int totalPlus = 0;
        try {
            String query = "SELECT SUM(Salary_Plus) AS TotalPlus FROM Salary WHERE StaffID = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, staffID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                totalPlus = resultSet.getInt("TotalPlus");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalPlus;
    }

    // Lấy tổng lương trừ của một nhân viên
    public int getTotalSalaryMinus(int staffID) {
        int totalMinus = 0;
        try {
            String query = "SELECT SUM(Salary_Minus) AS TotalMinus FROM Salary WHERE StaffID = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, staffID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                totalMinus = resultSet.getInt("TotalMinus");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalMinus;
    }

    // Tính tổng lương của một nhân viên
    public int calculateTotalSalary(int staffID) {
        int totalSalary = getFixedSalary(staffID);
        totalSalary += getTotalSalaryPlus(staffID);
        totalSalary -= getTotalSalaryMinus(staffID);
        return totalSalary;
    }

    // Lấy danh sách lương của tất cả nhân viên
    public List<Salary> getAllSalaries() {
        List<Salary> salaryList = new ArrayList<>();
        try {
            String query = "SELECT StaffID, Salary_Plus, Salary_Minus, date, Note FROM Salary";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Salary salary = new Salary();
                salary.setStaffID(resultSet.getInt("StaffID"));
                salary.setSalaryPlus(resultSet.getInt("Salary_Plus"));
                salary.setSalaryMinus(resultSet.getInt("Salary_Minus"));
                salary.setDate(resultSet.getDate("date"));
                salary.setNote(resultSet.getString("Note"));
                salaryList.add(salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salaryList;
    }
}
