/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author tran tung
 */
public class ingredient2 {
        private int IngredientID;
    private String IngredientName;
    private int Quantity;
    public ingredient2(){
        
    }

    public ingredient2(int IngredientID, String IngredientName, int Quantity) {
        this.IngredientID = IngredientID;
        this.IngredientName = IngredientName;
        this.Quantity = Quantity;
    }

    public int getIngredientID() {
        return IngredientID;
    }

    public void setIngredientID(int IngredientID) {
        this.IngredientID = IngredientID;
    }

    public String getIngredientName() {
        return IngredientName;
    }

    public void setIngredientName(String IngredientName) {
        this.IngredientName = IngredientName;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    @Override
    public String toString() {
        return "ingredient{" + "IngredientID=" + IngredientID + ", IngredientName=" + IngredientName + ", Quantity=" + Quantity + '}';
    }
    
}
