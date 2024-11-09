/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import DAO.MenuDAOk;

/**
 *
 * @author Legion
 */
public class Drink {
    private int drinkID;
    private String drinkName;
    private double drinkPrice;
    private int quantity;
    private int categoryID;
    private int brandID;
    private String status;
    private String image;
    private double price;
    
    private DrinkCategory drinkCategory;
    private Brand brand;
    
    public double getPrice() {
        return new MenuDAOk().getDrinkPrice(drinkID);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    
    
    
    public Brand getBrand() {
        return new MenuDAOk().getBrandByID(brandID);
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    

    public DrinkCategory getDrinkCategory() {
        return new MenuDAOk().getDrinkCategoryByID(categoryID);
    }

    public void setDrinkCategory(DrinkCategory drinkCategory) {
        this.drinkCategory = drinkCategory;
    }

    // Constructor
    public Drink() {}

    public Drink(int drinkID, String drinkName, double drinkPrice, int quantity, int categoryID, int brandID, String status, String image) {
        this.drinkID = drinkID;
        this.drinkName = drinkName;
        this.drinkPrice = drinkPrice;
        this.quantity = quantity;
        this.categoryID = categoryID;
        this.brandID = brandID;
        this.status = status;
        this.image = image;
    }

    // Getters and Setters
    public int getDrinkID() {
        return drinkID;
    }

    public void setDrinkID(int drinkID) {
        this.drinkID = drinkID;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public double getDrinkPrice() {
        return drinkPrice;
    }

    public void setDrinkPrice(double drinkPrice) {
        this.drinkPrice = drinkPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getBrandID() {
        return brandID;
    }

    public void setBrandID(int brandID) {
        this.brandID = brandID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
