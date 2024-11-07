/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.dao;

/**
 *
 * @author tran tung
 */
public class voucherCustomer {
    private int voucher_CustomerID;
    private int CustomerID;
    private int VoucherID;
    private String VoucherName;
    private int value; 

    public voucherCustomer() {
    }
    
    public voucherCustomer(int voucher_CustomerID, int CustomerID, int VoucherID, String VoucherName, int value) {
        this.voucher_CustomerID = voucher_CustomerID;
        this.CustomerID = CustomerID;
        this.VoucherID = VoucherID;
        this.VoucherName = VoucherName;
        this.value = value;
    }

    public int getVoucher_CustomerID() {
        return voucher_CustomerID;
    }

    public void setVoucher_CustomerID(int voucher_CustomerID) {
        this.voucher_CustomerID = voucher_CustomerID;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    public int getVoucherID() {
        return VoucherID;
    }

    public void setVoucherID(int VoucherID) {
        this.VoucherID = VoucherID;
    }

    public String getVoucherName() {
        return VoucherName;
    }

    public void setVoucherName(String VoucherName) {
        this.VoucherName = VoucherName;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "voucherCustomer{" + "voucher_CustomerID=" + voucher_CustomerID + ", CustomerID=" + CustomerID + ", VoucherID=" + VoucherID + ", VoucherName=" + VoucherName + ", value=" + value + '}';
    }
    
    
}
