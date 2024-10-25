/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

/**
 *
 * @author tran tung
 */
public class product {
    private int ProductID;
    private int FoodID;
    private int BuffetID;
    private int DrinkID;
    private int Price;
    
    private int foodIDf;
    private String foodName;
    
    private int foodCategoryID;
    private String foodStatus;
    private String imagef;
     // Thuộc tính của bảng Drink
    private int drinkIDd;
    private String drinkName;
   
    private int drinkQuantity;
    private int drinkCategoryID;
    private int drinkBrandID;
    private String drinkStatus;
    private String imaged;
    // Thuộc tính của bảng Drink_Category
    private int drinkCategoryID2; 
    private String drinkCategoryName;

    // Thuộc tính của bảng Food_Category
    private int foodCategoryID2; 
    private String foodCategoryName;

    // Thuộc tính của bảng Buffet
    private int buffetIDb;
    private String buffetName;
    private int buffetPrice; 

    // Thuộc tính của bảng Buffet_Food
    private int buffetFoodID;
    private int buffetID2; 
    private int foodID2; 

    // Thuộc tính của bảng Buffet_Drink
    private int buffetDrinkID;
    private int buffetID3; 
    private int drinkID2;
    public product(){
        
    }

    public product(
            int ProductID, int FoodID, int BuffetID, 
            int DrinkID, int Price, int foodIDf,
            String foodName,int foodCategoryID, String foodStatus,
            String imagef, int drinkIDd, String drinkName,
            int drinkQuantity,int drinkCategoryID, int drinkBrandID,
            String drinkStatus, String imaged, int drinkCategoryID2,
            String drinkCategoryName,int foodCategoryID2, String foodCategoryName,
            int buffetIDb, String buffetName, int buffetPrice,
            int buffetFoodID, int buffetID2, int foodID2,
            int buffetDrinkID, int buffetID3, int drinkID2) {
        this.ProductID = ProductID;
        this.FoodID = FoodID;
        this.BuffetID = BuffetID;
        this.DrinkID = DrinkID;
        this.Price = Price;
        this.foodIDf = foodIDf;
        this.foodName = foodName;
        this.foodCategoryID = foodCategoryID;
        this.foodStatus = foodStatus;
        this.imagef = imagef;
        this.drinkIDd = drinkIDd;
        this.drinkName = drinkName;
        this.drinkQuantity = drinkQuantity;
        this.drinkCategoryID = drinkCategoryID;
        this.drinkBrandID = drinkBrandID;
        this.drinkStatus = drinkStatus;
        this.imaged = imaged;
        this.drinkCategoryID2 = drinkCategoryID2;
        this.drinkCategoryName = drinkCategoryName;
        this.foodCategoryID2 = foodCategoryID2;
        this.foodCategoryName = foodCategoryName;
        this.buffetIDb = buffetIDb;
        this.buffetName = buffetName;
        this.buffetPrice = buffetPrice;
        this.buffetFoodID = buffetFoodID;
        this.buffetID2 = buffetID2;
        this.foodID2 = foodID2;
        this.buffetDrinkID = buffetDrinkID;
        this.buffetID3 = buffetID3;
        this.drinkID2 = drinkID2;
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
        return foodIDf;
    }

    public void setFoodIDf(int foodIDf) {
        this.foodIDf = foodIDf;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getFoodCategoryID() {
        return foodCategoryID;
    }

    public void setFoodCategoryID(int foodCategoryID) {
        this.foodCategoryID = foodCategoryID;
    }

    public String getFoodStatus() {
        return foodStatus;
    }

    public void setFoodStatus(String foodStatus) {
        this.foodStatus = foodStatus;
    }

    public String getImagef() {
        return imagef;
    }

    public void setImagef(String imagef) {
        this.imagef = imagef;
    }

    public int getDrinkIDd() {
        return drinkIDd;
    }

    public void setDrinkIDd(int drinkIDd) {
        this.drinkIDd = drinkIDd;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public int getDrinkQuantity() {
        return drinkQuantity;
    }

    public void setDrinkQuantity(int drinkQuantity) {
        this.drinkQuantity = drinkQuantity;
    }

    public int getDrinkCategoryID() {
        return drinkCategoryID;
    }

    public void setDrinkCategoryID(int drinkCategoryID) {
        this.drinkCategoryID = drinkCategoryID;
    }

    public int getDrinkBrandID() {
        return drinkBrandID;
    }

    public void setDrinkBrandID(int drinkBrandID) {
        this.drinkBrandID = drinkBrandID;
    }

    public String getDrinkStatus() {
        return drinkStatus;
    }

    public void setDrinkStatus(String drinkStatus) {
        this.drinkStatus = drinkStatus;
    }

    public String getImaged() {
        return imaged;
    }

    public void setImaged(String imaged) {
        this.imaged = imaged;
    }

    public int getDrinkCategoryID2() {
        return drinkCategoryID2;
    }

    public void setDrinkCategoryID2(int drinkCategoryID2) {
        this.drinkCategoryID2 = drinkCategoryID2;
    }

    public String getDrinkCategoryName() {
        return drinkCategoryName;
    }

    public void setDrinkCategoryName(String drinkCategoryName) {
        this.drinkCategoryName = drinkCategoryName;
    }

    public int getFoodCategoryID2() {
        return foodCategoryID2;
    }

    public void setFoodCategoryID2(int foodCategoryID2) {
        this.foodCategoryID2 = foodCategoryID2;
    }

    public String getFoodCategoryName() {
        return foodCategoryName;
    }

    public void setFoodCategoryName(String foodCategoryName) {
        this.foodCategoryName = foodCategoryName;
    }

    public int getBuffetIDb() {
        return buffetIDb;
    }

    public void setBuffetIDb(int buffetIDb) {
        this.buffetIDb = buffetIDb;
    }

    public String getBuffetName() {
        return buffetName;
    }

    public void setBuffetName(String buffetName) {
        this.buffetName = buffetName;
    }

    public int getBuffetPrice() {
        return buffetPrice;
    }

    public void setBuffetPrice(int buffetPrice) {
        this.buffetPrice = buffetPrice;
    }

    public int getBuffetFoodID() {
        return buffetFoodID;
    }

    public void setBuffetFoodID(int buffetFoodID) {
        this.buffetFoodID = buffetFoodID;
    }

    public int getBuffetID2() {
        return buffetID2;
    }

    public void setBuffetID2(int buffetID2) {
        this.buffetID2 = buffetID2;
    }

    public int getFoodID2() {
        return foodID2;
    }

    public void setFoodID2(int foodID2) {
        this.foodID2 = foodID2;
    }

    public int getBuffetDrinkID() {
        return buffetDrinkID;
    }

    public void setBuffetDrinkID(int buffetDrinkID) {
        this.buffetDrinkID = buffetDrinkID;
    }

    public int getBuffetID3() {
        return buffetID3;
    }

    public void setBuffetID3(int buffetID3) {
        this.buffetID3 = buffetID3;
    }

    public int getDrinkID2() {
        return drinkID2;
    }

    public void setDrinkID2(int drinkID2) {
        this.drinkID2 = drinkID2;
    }

    @Override
    public String toString() {
        return "product{" + "ProductID=" + ProductID + ", FoodID=" + FoodID + ", BuffetID=" + BuffetID + ", DrinkID=" + DrinkID + ", Price=" + Price + ", foodIDf=" + foodIDf + ", foodName=" + foodName + ", foodCategoryID=" + foodCategoryID + ", foodStatus=" + foodStatus + ", imagef=" + imagef + ", drinkIDd=" + drinkIDd + ", drinkName=" + drinkName + ", drinkQuantity=" + drinkQuantity + ", drinkCategoryID=" + drinkCategoryID + ", drinkBrandID=" + drinkBrandID + ", drinkStatus=" + drinkStatus + ", imaged=" + imaged + ", drinkCategoryID2=" + drinkCategoryID2 + ", drinkCategoryName=" + drinkCategoryName + ", foodCategoryID2=" + foodCategoryID2 + ", foodCategoryName=" + foodCategoryName + ", buffetIDb=" + buffetIDb + ", buffetName=" + buffetName + ", buffetPrice=" + buffetPrice + ", buffetFoodID=" + buffetFoodID + ", buffetID2=" + buffetID2 + ", foodID2=" + foodID2 + ", buffetDrinkID=" + buffetDrinkID + ", buffetID3=" + buffetID3 + ", drinkID2=" + drinkID2 + '}';
    }

    
    
}
