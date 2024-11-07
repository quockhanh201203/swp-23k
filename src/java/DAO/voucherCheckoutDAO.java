/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.dao.checkoutTableOrder;
import Model.dao.voucherChekout;
import Model.dao.voucherCustomer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tran tung
 */
public class voucherCheckoutDAO extends DBContext{
    public List<Model.dao.voucherCustomer> getCustomerVoucher(int CustomerID) {
        List<Model.dao.voucherCustomer> voucher = new ArrayList<>();
      String sql = "SELECT TOP (1000) \n" +
"    vc.[Voucher_CustomerID],\n" +
"    vc.[CustomerID],\n" +
"    vc.[VoucherID],\n" +
"    v.[voucherName],\n" +
"    v.[Value]\n" +
"FROM \n" +
"    [5AnhLucDB].[dbo].[Voucher_Customer] AS vc\n" +
"LEFT JOIN \n" +
"    [5AnhLucDB].[dbo].[Voucher] AS v ON vc.[VoucherID] = v.[VoucherID]\n" +
"	where customerID =?";


        try {
            PreparedStatement st = connection.prepareStatement(sql);
                        st.setInt(1, CustomerID);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                voucherCustomer p = new voucherCustomer();

                voucher.add(new voucherCustomer(
                        rs.getInt(1), rs.getInt(2), rs.getInt(3),
                        rs.getString(4), rs.getInt(5)));
                        
                
                
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return voucher;
    }
    
    
    
    
    public static void main(String[] args) {
        voucherCheckoutDAO d = new voucherCheckoutDAO();
        d.getCustomerVoucher(1);
            List<Model.dao.voucherCustomer> vouchers = d.getCustomerVoucher(1);

        
        for (Model.dao.voucherCustomer voucher : vouchers) {
        System.out.println("Voucher_CustomerID: " + voucher.getVoucher_CustomerID());
        System.out.println("CustomerID: " + voucher.getCustomerID());
        System.out.println("VoucherID: " + voucher.getVoucherID());
        System.out.println("Voucher Name: " + voucher.getVoucherName());
        System.out.println("Value: " + voucher.getValue());
        System.out.println("-------------------------");
    }
    }
    
    
    
    
}
