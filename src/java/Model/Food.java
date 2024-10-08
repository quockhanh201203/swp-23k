/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import DAO.MenuDAO;
import java.util.List;

/**
 *
 * @author Legion
 */
public class Food {
    private int foodID;
    private String foodName;
    private int categoryID;
    private int status;
    private String image;
    private FoodCategory foodCategory;
    private String statusString;
    private List<Ingredient> ingredients;

    public FoodCategory getFoodCategory() {
        return new MenuDAO().getFoodCategoryByID(categoryID);
    }

    public void setFoodCategory(FoodCategory foodCategory) {
        this.foodCategory = foodCategory;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Food(int foodID, String foodName, int categoryID, int status, String image, List<Ingredient> ingredients) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.categoryID = categoryID;
        this.status = status;
        this.image = image;
        this.ingredients = ingredients;
    }

    
    
    
    
    
    
    

    // Constructor
    public Food() {}

    public Food(int foodID, String foodName, int categoryID, int status, String image) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.categoryID = categoryID;
        this.status = status;
        this.image = image;
    }

    public Food(int foodID, String foodName, int categoryID, int status, String image, FoodCategory foodCategory) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.categoryID = categoryID;
        this.status = status;
        this.image = image;
        this.foodCategory = foodCategory;
    }

    // Getters and Setters
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

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}

