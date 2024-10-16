/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;


public class ingredient  {
          
            
    private int foodID;
    private String foodName;
    private String ingredientQuantities; 
    private int TotalQuantity;
    public ingredient(){
        
    }

    public ingredient(int foodID, String foodName, String ingredientQuantities, int TotalQuantity) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.ingredientQuantities = ingredientQuantities;
        this.TotalQuantity = TotalQuantity;
    }
    
    public int getFoodID() {
        return foodID;
    }

    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getIngredientQuantities() {
        return ingredientQuantities;
    }

    public void setIngredientQuantities(String ingredientQuantities) {
        this.ingredientQuantities = ingredientQuantities;
    }

    public int getTotalQuantity() {
        return TotalQuantity;
    }

    public void setTotalQuantity(int TotalQuantity) {
        this.TotalQuantity = TotalQuantity;
    }

    @Override
    public String toString() {
        return "ingredient{" + "foodID=" + foodID + ", foodName=" + foodName + ", ingredientQuantities=" + ingredientQuantities + ", TotalQuantity=" + TotalQuantity + '}';
    }
    
    
   
    
}

  
        





