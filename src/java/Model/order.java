/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author tran tung
 */
public class order {
    private int OrderID;
    private String GuestNote;
    private int GuestID;
    private int CustomerID;
    private int total;
    private int Table_OrderID;
    public order(){
        
    }

    public order(int OrderID, String GuestNote, int GuestID, int CustomerID, int total, int Table_OrderID) {
        this.OrderID = OrderID;
        this.GuestNote = GuestNote;
        this.GuestID = GuestID;
        this.CustomerID = CustomerID;
        this.total = total;
        this.Table_OrderID = Table_OrderID;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public String getGuestNote() {
        return GuestNote;
    }

    public void setGuestNote(String GuestNote) {
        this.GuestNote = GuestNote;
    }

    public int getGuestID() {
        return GuestID;
    }

    public void setGuestID(int GuestID) {
        this.GuestID = GuestID;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTable_OrderID() {
        return Table_OrderID;
    }

    public void setTable_OrderID(int Table_OrderID) {
        this.Table_OrderID = Table_OrderID;
    }

    @Override
    public String toString() {
        return "order{" + "OrderID=" + OrderID + ", GuestNote=" + GuestNote + ", GuestID=" + GuestID + ", CustomerID=" + CustomerID + ", total=" + total + ", Table_OrderID=" + Table_OrderID + '}';
    }
    
}
