    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class DashBoardDAO extends DBContext{
    // Hàm truy vấn doanh thu theo thời gian
    public List<Double> getRevenueByDate() throws SQLException {
        String sql = "SELECT OrderDate, SUM(TotalPrice) AS Total FROM Table_Order GROUP BY OrderDate";
        statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        List<Double> revenueValues = new ArrayList<>();
        while (rs.next()) {
            revenueValues.add(rs.getDouble("Total"));
        }
        return revenueValues;
    }

    // Hàm truy vấn ngày theo doanh thu
    public List<String> getRevenueDates() throws SQLException {
        String sql = "SELECT OrderDate FROM Table_Order GROUP BY OrderDate";
        statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        List<String> revenueDates = new ArrayList<>();
        while (rs.next()) {
            revenueDates.add(rs.getString("OrderDate"));
        }
        return revenueDates;
    }

    // Hàm truy vấn doanh thu theo loại sản phẩm
    public List<Double> getProductRevenue() throws SQLException {
        String sql = "SELECT SUM(Price) AS Total FROM Product GROUP BY CASE WHEN FoodID IS NOT NULL THEN 'Food' WHEN DrinkID IS NOT NULL THEN 'Drink' ELSE 'Buffet' END";
        statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        List<Double> productTotals = new ArrayList<>();
        while (rs.next()) {
            productTotals.add(rs.getDouble("Total"));
        }
        return productTotals;
    }

    // Hàm truy vấn loại sản phẩm
    public List<String> getProductTypes() throws SQLException {
        String sql = "SELECT CASE WHEN FoodID IS NOT NULL THEN 'Food' WHEN DrinkID IS NOT NULL THEN 'Drink' ELSE 'Buffet' END AS ProductType FROM Product GROUP BY CASE WHEN FoodID IS NOT NULL THEN 'Food' WHEN DrinkID IS NOT NULL THEN 'Drink' ELSE 'Buffet' END";
        statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        List<String> productTypes = new ArrayList<>();
        while (rs.next()) {
            productTypes.add(rs.getString("ProductType"));
        }
        return productTypes;
    }

    // Hàm truy vấn số lượng đơn hàng theo trạng thái
    public List<Integer> getOrderCountsByStatus() throws SQLException {
        //String sql = "SELECT COUNT(*) AS Count FROM [Order] GROUP BY Status";
        String sql = "SELECT COUNT(*) AS Count FROM [Order]";
        statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        List<Integer> orderCounts = new ArrayList<>();
//        while (rs.next()) {
//            orderCounts.add(rs.getInt("Count"));
//        }
        return orderCounts;
    }

    // Hàm truy vấn trạng thái đơn hàng
    public List<String> getOrderStatuses() throws SQLException {
        //String sql = "SELECT Status FROM [Order] GROUP BY Status";
        String sql = "SELECT GuestNote FROM [Order]";
        statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        List<String> orderStatuses = new ArrayList<>();
//        while (rs.next()) {
//            orderStatuses.add(rs.getString("Status"));
//        }
        return orderStatuses;
    }

    // Hàm truy vấn lịch sử lương
    public List<Double> getSalaryHistory() throws SQLException {
        String sql = "SELECT SUM(Salary_Plus - Salary_Minus) AS TotalSalary FROM Salary GROUP BY [date]";
        statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        List<Double> salaryValues = new ArrayList<>();
        while (rs.next()) {
            salaryValues.add(rs.getDouble("TotalSalary"));
        }
        return salaryValues;
    }

    // Hàm truy vấn ngày lương
    public List<String> getSalaryDates() throws SQLException {
        String sql = "SELECT [date] FROM Salary GROUP BY [date]";
        statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        List<String> salaryDates = new ArrayList<>();
        while (rs.next()) {
            salaryDates.add(rs.getString("date"));
        }
        return salaryDates;
    }
}
