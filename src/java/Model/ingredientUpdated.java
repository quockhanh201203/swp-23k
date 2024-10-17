/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


public class ingredientUpdated {
    private int FoodID;
    private String IngredientName;
  
    public ingredientUpdated(){
        
    }

    public ingredientUpdated(int FoodID, String IngredientName) {
        this.FoodID = FoodID;
        this.IngredientName = IngredientName;
        
    }

    public int getFoodID() {
        return FoodID;
    }

    public void setFoodID(int FoodID) {
        this.FoodID = FoodID;
    }

    public String getIngredientName() {
        return IngredientName;
    }

    public void setIngredientName(String IngredientName) {
        this.IngredientName = IngredientName;
    }

    @Override
    public String toString() {
        return "ingredientUpdated{" + "FoodID=" + FoodID + ", IngredientName=" + IngredientName + '}';
    }
    
}
