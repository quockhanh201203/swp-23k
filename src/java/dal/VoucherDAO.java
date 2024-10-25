/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;


import Model.Voucher;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Admin
 */
public class VoucherDAO extends DBContext {
     // Fetch all vouchers
    public List<Voucher> getAllVouchers() {
        List<Voucher> vouchers = new ArrayList<>();
        try {
            String query = "SELECT * FROM Voucher";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Voucher voucher = new Voucher();
                voucher.setVoucherID(resultSet.getInt("VoucherID"));
                voucher.setVoucherName(resultSet.getString("VoucherName"));
                voucher.setValue(resultSet.getDouble("Value"));
                vouchers.add(voucher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return vouchers;
    }

    // Insert new voucher
    public void insertVoucher(Voucher voucher) {
        try {
            String query = "INSERT INTO Voucher (VoucherName, Value) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, voucher.getVoucherName());
            statement.setDouble(2, voucher.getValue());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    // Update voucher
    public void updateVoucher(Voucher voucher) {
        try {
            String query = "UPDATE Voucher SET VoucherName = ?, Value = ? WHERE VoucherID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, voucher.getVoucherName());
            statement.setDouble(2, voucher.getValue());
            statement.setInt(3, voucher.getVoucherID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete voucher
    public void deleteVoucher(int voucherID) {
        try {
            String query = "DELETE FROM Voucher WHERE VoucherID = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, voucherID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }
    
     // Tìm kiếm voucher theo tên
    public List<Voucher> searchVouchersByName(String name) {
        List<Voucher> vouchers = new ArrayList<>();
        try {
            String query = "SELECT * FROM Voucher WHERE VoucherName LIKE ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, "%" + name + "%");  // Tìm kiếm bằng từ khóa
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Voucher voucher = new Voucher();
                voucher.setVoucherID(resultSet.getInt("VoucherID"));
                voucher.setVoucherName(resultSet.getString("VoucherName"));
                voucher.setValue(resultSet.getDouble("Value"));
                vouchers.add(voucher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vouchers;
    }
}
