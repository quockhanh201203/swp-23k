/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author tran tung
 */
public class tableOrder {
    private int Table_OrderID;
    private int TotalPrice;
    private Date OrderDate;
    private int TableID;
    public tableOrder(){
        
    }
    public tableOrder(int Table_OrderID, int TotalPrice, Date OrderDate, int TableID) {
        this.Table_OrderID = Table_OrderID;
        this.TotalPrice = TotalPrice;
        this.OrderDate = OrderDate;
        this.TableID = TableID;
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

    public Date getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(Date OrderDate) {
        this.OrderDate = OrderDate;
    }

    public int getTableID() {
        return TableID;
    }

    public void setTableID(int TableID) {
        this.TableID = TableID;
    }

    @Override
    public String toString() {
        return "tableOrder{" + "Table_OrderID=" + Table_OrderID + ", TotalPrice=" + TotalPrice + ", OrderDate=" + OrderDate + ", TableID=" + TableID + '}';
    }
    
}
