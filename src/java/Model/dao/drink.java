/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.dao;

/**
 *
 * @author tran tung
 */
public class drink {
       private int ProductID;
    private int FoodID;
    private int BuffetID;
    private int DrinkID; 
    private int Price;
    private int DrinkIDd;
    private String DrinkName;
    private int Quantity;
    private int CategoryID;
    private int BrandID;
    private String Status;
    private String Image; 
    private String CategoryName;
    
    public drink(){
        
    }

    public drink(int ProductID, int FoodID, int BuffetID, int DrinkID, int Price, int DrinkIDd, String DrinkName, int Quantity, int CategoryID, int BrandID, String Status, String Image, String CategoryName) {
        this.ProductID = ProductID;
        this.FoodID = FoodID;
        this.BuffetID = BuffetID;
        this.DrinkID = DrinkID;
        this.Price = Price;
        this.DrinkIDd = DrinkIDd;
        this.DrinkName = DrinkName;
        this.Quantity = Quantity;
        this.CategoryID = CategoryID;
        this.BrandID = BrandID;
        this.Status = Status;
        this.Image = Image;
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

    public int getDrinkIDd() {
        return DrinkIDd;
    }

    public void setDrinkIDd(int DrinkIDd) {
        this.DrinkIDd = DrinkIDd;
    }

    public String getDrinkName() {
        return DrinkName;
    }

    public void setDrinkName(String DrinkName) {
        this.DrinkName = DrinkName;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int CategoryID) {
        this.CategoryID = CategoryID;
    }

    public int getBrandID() {
        return BrandID;
    }

    public void setBrandID(int BrandID) {
        this.BrandID = BrandID;
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

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }

    @Override
    public String toString() {
        return "drink{" + "ProductID=" + ProductID + ", FoodID=" + FoodID + ", BuffetID=" + BuffetID + ", DrinkID=" + DrinkID + ", Price=" + Price + ", DrinkIDd=" + DrinkIDd + ", DrinkName=" + DrinkName + ", Quantity=" + Quantity + ", CategoryID=" + CategoryID + ", BrandID=" + BrandID + ", Status=" + Status + ", Image=" + Image + ", CategoryName=" + CategoryName + '}';
    }
}
