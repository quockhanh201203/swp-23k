/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Legion
 */
public class Product {
    private int productId;
    private int foodId;
    private int buffetId;
    private int drinkID;
    private double price;

    public Product() {
    }

    public Product(int productId, int foodId, int buffetId, int drinkID, double price) {
        this.productId = productId;
        this.foodId = foodId;
        this.buffetId = buffetId;
        this.drinkID = drinkID;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getBuffetId() {
        return buffetId;
    }

    public void setBuffetId(int buffetId) {
        this.buffetId = buffetId;
    }

    public int getDrinkID() {
        return drinkID;
    }

    public void setDrinkID(int drinkID) {
        this.drinkID = drinkID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
}
