/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


public class Ingredient_Food {
    private int Ingredient_FoodID;
    private int FoodID;
    private int IngredientID;
    private int Quantity;
    public Ingredient_Food(){
        
    }

    public Ingredient_Food(int Ingredient_FoodID, int FoodID, int IngredientID, int Quantity) {
        this.Ingredient_FoodID = Ingredient_FoodID;
        this.FoodID = FoodID;
        this.IngredientID = IngredientID;
        this.Quantity = Quantity;
    }

    public int getIngredient_FoodID() {
        return Ingredient_FoodID;
    }

    public void setIngredient_FoodID(int Ingredient_FoodID) {
        this.Ingredient_FoodID = Ingredient_FoodID;
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
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    @Override
    public String toString() {
        return "Ingredient_Food{" + "Ingredient_FoodID=" + Ingredient_FoodID + ", FoodID=" + FoodID + ", IngredientID=" + IngredientID + ", Quantity=" + Quantity + '}';
    }
    
}
