/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.dao;

/**
 *
 * @author tran tung
 */
public class food {
     private int ProductID;
    private int FoodID;
    private int BuffetID;
    private int DrinkID;
    private int Price;
    private int FoodIDf;
    private String FoodName;
    private int CategoryID;
    private String Status;
    private String Image;
    private int CategoryIDf;
    private String CategoryName;
    public food(){
        
    }

    public food(int ProductID, int FoodID, int BuffetID, int DrinkID, int Price, int FoodIDf, String FoodName, int CategoryID, String Status, String Image, int CategoryIDf, String CategoryName) {
        this.ProductID = ProductID;
        this.FoodID = FoodID;
        this.BuffetID = BuffetID;
        this.DrinkID = DrinkID;
        this.Price = Price;
        this.FoodIDf = FoodIDf;
        this.FoodName = FoodName;
        this.CategoryID = CategoryID;
        this.Status = Status;
        this.Image = Image;
        this.CategoryIDf = CategoryIDf;
        this.CategoryName = CategoryName;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public int getFoodID() {
        return FoodID;
    }

    public void setFoodID(int FoodID) {
        this.FoodID = FoodID;
    }

    public int getBuffetID() {
        return BuffetID;
    }

    public void setBuffetID(int BuffetID) {
        this.BuffetID = BuffetID;
    }

    public int getDrinkID() {
        return DrinkID;
    }

    public void setDrinkID(int DrinkID) {
        this.DrinkID = DrinkID;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public int getFoodIDf() {
        return FoodIDf;
    }

    public void setFoodIDf(int FoodIDf) {
        this.FoodIDf = FoodIDf;
    }

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String FoodName) {
        this.FoodName = FoodName;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int CategoryID) {
        this.CategoryID = CategoryID;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public int getCategoryIDf() {
        return CategoryIDf;
    }

    public void setCategoryIDf(int CategoryIDf) {
        this.CategoryIDf = CategoryIDf;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }

    @Override
    public String toString() {
        return "food{" + "ProductID=" + ProductID + ", FoodID=" + FoodID + ", BuffetID=" + BuffetID + ", DrinkID=" + DrinkID + ", Price=" + Price + ", FoodIDf=" + FoodIDf + ", FoodName=" + FoodName + ", CategoryID=" + CategoryID + ", Status=" + Status + ", Image=" + Image + ", CategoryIDf=" + CategoryIDf + ", CategoryName=" + CategoryName + '}';
    }

}
