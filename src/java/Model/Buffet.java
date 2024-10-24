/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import DAO.MenuDAO;

/**
 *
 * @author Legion
 */
public class Buffet {
    private int buffetID;
    private String buffetName;
    private String image;
    private double price;
    
    public double getPrice() {
        return new MenuDAO().getBuffetPrice(buffetID);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Constructor
    public Buffet() {}

    public Buffet(int buffetID, String buffetName, String image) {
        this.buffetID = buffetID;
        this.buffetName = buffetName;
        this.image = image;
    }

    // Getters and Setters
    public int getBuffetID() {
        return buffetID;
    }

    public void setBuffetID(int buffetID) {
        this.buffetID = buffetID;
    }

    public String getBuffetName() {
        return buffetName;
    }

    public void setBuffetName(String buffetName) {
        this.buffetName = buffetName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

