/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

/**
 *
 * @author tran tung
 */
public class orderN {
    private  int OrderID;
    private String GuestNote;
    private int GuestID;
    private int CustomerID;
    private int total;
    private int Table_OrderID;
    private int Order_FoodID;
    private int OrderIDb;
    private int FoodID;
    private int Quantity;
    private int price;
    private String FoodName;
    private String Image;
    public orderN(){
        
    }

    public orderN(int OrderID, String GuestNote, int GuestID, int CustomerID, int total, int Table_OrderID, int Order_FoodID, int OrderIDb, int FoodID, int Quantity, int price, String FoodName, String Image) {
        this.OrderID = OrderID;
        this.GuestNote = GuestNote;
        this.GuestID = GuestID;
        this.CustomerID = CustomerID;
        this.total = total;
        this.Table_OrderID = Table_OrderID;
        this.Order_FoodID = Order_FoodID;
        this.OrderIDb = OrderIDb;
        this.FoodID = FoodID;
        this.Quantity = Quantity;
        this.price = price;
        this.FoodName = FoodName;
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

    public int getOrder_FoodID() {
        return Order_FoodID;
    }

    public void setOrder_FoodID(int Order_FoodID) {
        this.Order_FoodID = Order_FoodID;
    }

    public int getOrderIDb() {
        return OrderIDb;
    }

    public void setOrderIDb(int OrderIDb) {
        this.OrderIDb = OrderIDb;
    }

    public int getFoodID() {
        return FoodID;
    }

    public void setFoodID(int FoodID) {
        this.FoodID = FoodID;
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

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String FoodName) {
        this.FoodName = FoodName;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    @Override
    public String toString() {
        return "orderN{" + "OrderID=" + OrderID + ", GuestNote=" + GuestNote + ", GuestID=" + GuestID + ", CustomerID=" + CustomerID + ", total=" + total + ", Table_OrderID=" + Table_OrderID + ", Order_FoodID=" + Order_FoodID + ", OrderIDb=" + OrderIDb + ", FoodID=" + FoodID + ", Quantity=" + Quantity + ", price=" + price + ", FoodName=" + FoodName + ", Image=" + Image + '}';
    }
    
}

