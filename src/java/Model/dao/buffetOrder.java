/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.dao;

/**
 *
 * @author tran tung
 */
public class buffetOrder {
      private  int OrderID;
    private String GuestNote;
    private int GuestID;
    private int CustomerID;
    private int total;
    private int Table_OrderID;
    private int Order_BuffetID;
    private int OrderIDb;
   
     private int BuffetID;
    private int price;
    private String buffetName;
        private String Image;

    public buffetOrder() {
    }

    public buffetOrder(int OrderID, String GuestNote, int GuestID, int CustomerID, int total, int Table_OrderID, int Order_BuffetID, int OrderIDb, int BuffetID, int price, String buffetName, String Image) {
        this.OrderID = OrderID;
        this.GuestNote = GuestNote;
        this.GuestID = GuestID;
        this.CustomerID = CustomerID;
        this.total = total;
        this.Table_OrderID = Table_OrderID;
        this.Order_BuffetID = Order_BuffetID;
        this.OrderIDb = OrderIDb;
        this.BuffetID = BuffetID;
        this.price = price;
        this.buffetName = buffetName;
        this.Image = Image;
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

    public int getOrder_BuffetID() {
        return Order_BuffetID;
    }

    public void setOrder_BuffetID(int Order_BuffetID) {
        this.Order_BuffetID = Order_BuffetID;
    }

    public int getOrderIDb() {
        return OrderIDb;
    }

    public void setOrderIDb(int OrderIDb) {
        this.OrderIDb = OrderIDb;
    }

    public int getBuffetID() {
        return BuffetID;
    }

    public void setBuffetID(int BuffetID) {
        this.BuffetID = BuffetID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBuffetName() {
        return buffetName;
    }

    public void setBuffetName(String buffetName) {
        this.buffetName = buffetName;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    @Override
    public String toString() {
        return "buffetorder{" + "OrderID=" + OrderID + ", GuestNote=" + GuestNote + ", GuestID=" + GuestID + ", CustomerID=" + CustomerID + ", total=" + total + ", Table_OrderID=" + Table_OrderID + ", Order_BuffetID=" + Order_BuffetID + ", OrderIDb=" + OrderIDb + ", BuffetID=" + BuffetID + ", price=" + price + ", buffetName=" + buffetName + ", Image=" + Image + '}';
    }

   
    
}
