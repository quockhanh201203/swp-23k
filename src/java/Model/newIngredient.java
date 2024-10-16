/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class newIngredient {
    private int FoodID;
    private int IngredientID;
    private int quantity;
    public newIngredient(){
        
    }
    public newIngredient(int FoodID, int IngredientID, int quantity) {
        this.FoodID = FoodID;
        this.IngredientID = IngredientID;
        this.quantity = quantity;
    }

    public int getFoodID() {
        return FoodID;
    }

    public void setFoodID(int FoodID) {
        this.FoodID = FoodID;
    }

    public int getIngredientID() {
        return IngredientID;
    }

    public void setIngredientID(int IngredientID) {
        this.IngredientID = IngredientID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "newIngredient{" + "FoodID=" + FoodID + ", IngredientID=" + IngredientID + ", quantity=" + quantity + '}';
    }

  
    
    
    
}
