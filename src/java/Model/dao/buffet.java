/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.dao;

/**
 *
 * @author tran tung
 */
public class buffet {
      private int ProductID;
    private int FoodID;
    private int BuffetID;
    private int DrinkID;
    private int Price;
    private int BuffetIDb;
    private String BuffetName;
    private String Image;
    private int Buffet_FoodID;
    private int BuffetIDf;
    private int FoodIDf;
    private int Buffet_DrinkID;
    private int BuffetIDd;
    private int DrinkIDd;
    private String DrinkName;
    private String FoodName;
   public buffet(){
       
   }

    public buffet(int ProductID, int FoodID, int BuffetID, int DrinkID, int Price, int BuffetIDb, String BuffetName, String Image, int Buffet_FoodID, int BuffetIDf, int FoodIDf, int Buffet_DrinkID, int BuffetIDd, int DrinkIDd, String DrinkName, String FoodName) {
        this.ProductID = ProductID;
        this.FoodID = FoodID;
        this.BuffetID = BuffetID;
        this.DrinkID = DrinkID;
        this.Price = Price;
        this.BuffetIDb = BuffetIDb;
        this.BuffetName = BuffetName;
        this.Image = Image;
        this.Buffet_FoodID = Buffet_FoodID;
        this.BuffetIDf = BuffetIDf;
        this.FoodIDf = FoodIDf;
        this.Buffet_DrinkID = Buffet_DrinkID;
        this.BuffetIDd = BuffetIDd;
        this.DrinkIDd = DrinkIDd;
        this.DrinkName = DrinkName;
        this.FoodName = FoodName;
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

    public int getBuffetIDb() {
        return BuffetIDb;
    }

    public void setBuffetIDb(int BuffetIDb) {
        this.BuffetIDb = BuffetIDb;
    }

    public String getBuffetName() {
        return BuffetName;
    }

    public void setBuffetName(String BuffetName) {
        this.BuffetName = BuffetName;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public int getBuffet_FoodID() {
        return Buffet_FoodID;
    }

    public void setBuffet_FoodID(int Buffet_FoodID) {
        this.Buffet_FoodID = Buffet_FoodID;
    }

    public int getBuffetIDf() {
        return BuffetIDf;
    }

    public void setBuffetIDf(int BuffetIDf) {
        this.BuffetIDf = BuffetIDf;
    }

    public int getFoodIDf() {
        return FoodIDf;
    }

    public void setFoodIDf(int FoodIDf) {
        this.FoodIDf = FoodIDf;
    }

    public int getBuffet_DrinkID() {
        return Buffet_DrinkID;
    }

    public void setBuffet_DrinkID(int Buffet_DrinkID) {
        this.Buffet_DrinkID = Buffet_DrinkID;
    }

    public int getBuffetIDd() {
        return BuffetIDd;
    }

    public void setBuffetIDd(int BuffetIDd) {
        this.BuffetIDd = BuffetIDd;
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

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String FoodName) {
        this.FoodName = FoodName;
    }

    @Override
    public String toString() {
        return "buffet{" + "ProductID=" + ProductID + ", FoodID=" + FoodID + ", BuffetID=" + BuffetID + ", DrinkID=" + DrinkID + ", Price=" + Price + ", BuffetIDb=" + BuffetIDb + ", BuffetName=" + BuffetName + ", Image=" + Image + ", Buffet_FoodID=" + Buffet_FoodID + ", BuffetIDf=" + BuffetIDf + ", FoodIDf=" + FoodIDf + ", Buffet_DrinkID=" + Buffet_DrinkID + ", BuffetIDd=" + BuffetIDd + ", DrinkIDd=" + DrinkIDd + ", DrinkName=" + DrinkName + ", FoodName=" + FoodName + '}';
    }
}
