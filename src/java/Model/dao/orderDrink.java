/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.dao;

/**
 *
 * @author tran tung
 */
public class orderDrink {
      private  int OrderID;
    private String GuestNote;
    private int GuestID;
    private int CustomerID;
    private int total;
    private int Table_OrderID;
    private int Order_DrinkID;
    private int OrderIDb;
    private int DrinkID;
    private int Quantity;
    private int price;
    private String DrinkName;
    private String Image;
    public orderDrink(){
        
}

    public orderDrink(int OrderID, String GuestNote, int GuestID, int CustomerID, int total, int Table_OrderID, int Order_DrinkID, int OrderIDb, int DrinkID, int Quantity, int price, String DrinkName, String Image) {
        this.OrderID = OrderID;
        this.GuestNote = GuestNote;
        this.GuestID = GuestID;
        this.CustomerID = CustomerID;
        this.total = total;
        this.Table_OrderID = Table_OrderID;
        this.Order_DrinkID = Order_DrinkID;
        this.OrderIDb = OrderIDb;
        this.DrinkID = DrinkID;
        this.Quantity = Quantity;
        this.price = price;
        this.DrinkName = DrinkName;
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

    public int getOrder_DrinkID() {
        return Order_DrinkID;
    }

    public void setOrder_DrinkID(int Order_DrinkID) {
        this.Order_DrinkID = Order_DrinkID;
    }

    public int getOrderIDb() {
        return OrderIDb;
    }

    public void setOrderIDb(int OrderIDb) {
        this.OrderIDb = OrderIDb;
    }

    public int getDrinkID() {
        return DrinkID;
    }

    public void setDrinkID(int DrinkID) {
        this.DrinkID = DrinkID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDrinkName() {
        return DrinkName;
    }

    public void setDrinkName(String DrinkName) {
        this.DrinkName = DrinkName;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    @Override
    public String toString() {
        return "orderDrink{" + "OrderID=" + OrderID + ", GuestNote=" + GuestNote + ", GuestID=" + GuestID + ", CustomerID=" + CustomerID + ", total=" + total + ", Table_OrderID=" + Table_OrderID + ", Order_DrinkID=" + Order_DrinkID + ", OrderIDb=" + OrderIDb + ", DrinkID=" + DrinkID + ", Quantity=" + Quantity + ", price=" + price + ", DrinkName=" + DrinkName + ", Image=" + Image + '}';
    }
    
}
