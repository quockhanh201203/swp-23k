/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import java.util.Date;

/**
 *
 * @author tran tung
 */
public class checkoutTableOrder {
    private int Table_OrderID;
    private int TotalPrice;
    private Date Orderdate;
    private int TableID;
    private int CustomerID;
    private int guestID;
    private String tableName;
    private String customerName;
    private String guestName;
    public checkoutTableOrder(){
        
    }

    public checkoutTableOrder(int Table_OrderID, int TotalPrice, Date Orderdate, int TableID, int CustomerID, int guestID, String tableName, String customerName, String guestName) {
        this.Table_OrderID = Table_OrderID;
        this.TotalPrice = TotalPrice;
        this.Orderdate = Orderdate;
        this.TableID = TableID;
        this.CustomerID = CustomerID;
        this.guestID = guestID;
        this.tableName = tableName;
        this.customerName = customerName;
        this.guestName = guestName;
    }

    public int getTable_OrderID() {
        return Table_OrderID;
    }

    public void setTable_OrderID(int Table_OrderID) {
        this.Table_OrderID = Table_OrderID;
    }

    public int getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(int TotalPrice) {
        this.TotalPrice = TotalPrice;
    }

    public Date getOrderdate() {
        return Orderdate;
    }

    public void setOrderdate(Date Orderdate) {
        this.Orderdate = Orderdate;
    }

    public int getTableID() {
        return TableID;
    }

    public void setTableID(int TableID) {
        this.TableID = TableID;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    public int getGuestID() {
        return guestID;
    }

    public void setGuestID(int guestID) {
        this.guestID = guestID;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    @Override
    public String toString() {
        return "checkoutTableOrder{" + "Table_OrderID=" + Table_OrderID + ", TotalPrice=" + TotalPrice + ", Orderdate=" + Orderdate + ", TableID=" + TableID + ", CustomerID=" + CustomerID + ", guestID=" + guestID + ", tableName=" + tableName + ", customerName=" + customerName + ", guestName=" + guestName + '}';
    }
    
}
