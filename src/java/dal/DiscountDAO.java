/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.Discount;

/**
 *
 * @author Admin
 */
public class DiscountDAO extends DBContext {
     public List<Discount> getAllDiscounts() {
        List<Discount> discounts = new ArrayList<>();
        String sql = "SELECT * FROM Discount";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Discount discount = new Discount();
                discount.setDiscountID(resultSet.getInt("DiscountID"));
                discount.setDiscountName(resultSet.getString("DiscountName"));
                discount.setValue(resultSet.getDouble("Value"));
                discount.setDate(resultSet.getDate("Date"));
                discounts.add(discount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discounts;
    }

    public List<Discount> searchDiscountsByName(String name) {
        List<Discount> discounts = new ArrayList<>();
        String sql = "SELECT * FROM Discount WHERE DiscountName LIKE ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Discount discount = new Discount();
                discount.setDiscountID(resultSet.getInt("DiscountID"));
                discount.setDiscountName(resultSet.getString("DiscountName"));
                discount.setValue(resultSet.getDouble("Value"));
                discount.setDate(resultSet.getDate("Date"));
                discounts.add(discount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discounts;
    }

    public void addDiscount(Discount discount) {
        String sql = "INSERT INTO Discount (DiscountName, Value, Date) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, discount.getDiscountName());
            statement.setDouble(2, discount.getValue());
            statement.setDate(3, new java.sql.Date(discount.getDate().getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDiscount(Discount discount) {
        String sql = "UPDATE Discount SET DiscountName = ?, Value = ?, Date = ? WHERE DiscountID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, discount.getDiscountName());
            statement.setDouble(2, discount.getValue());
            statement.setDate(3, new java.sql.Date(discount.getDate().getTime()));
            statement.setInt(4, discount.getDiscountID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDiscount(int discountID) {
        String sql = "DELETE FROM Discount WHERE DiscountID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, discountID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Discount getDiscountByID(int discountID) {
        Discount discount = null;
        String sql = "SELECT * FROM Discount WHERE DiscountID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, discountID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                discount = new Discount();
                discount.setDiscountID(resultSet.getInt("DiscountID"));
                discount.setDiscountName(resultSet.getString("DiscountName"));
                discount.setValue(resultSet.getDouble("Value"));
                discount.setDate(resultSet.getDate("Date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discount;
    }
}
