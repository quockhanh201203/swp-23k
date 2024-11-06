/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author tran tung
 */
public class foodOrder {
    private int Order_FoodID;
    private int OrderID;
    private int FoodID;
    private int quantity;
    public foodOrder(){
        
    }
    public foodOrder(int Order_FoodID, int OrderID, int FoodID, int quantity) {
        this.Order_FoodID = Order_FoodID;
        this.OrderID = OrderID;
        this.FoodID = FoodID;
        this.quantity = quantity;
    }

    public int getOrder_FoodID() {
        return Order_FoodID;
    }

    public void setOrder_FoodID(int Order_FoodID) {
        this.Order_FoodID = Order_FoodID;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public int getFoodID() {
        return FoodID;
    }

    public void setFoodID(int FoodID) {
        this.FoodID = FoodID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "foodOrder{" + "Order_FoodID=" + Order_FoodID + ", OrderID=" + OrderID + ", FoodID=" + FoodID + ", quantity=" + quantity + '}';
    }
    
}
