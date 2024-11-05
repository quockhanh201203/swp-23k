/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author tran tung
 */
public class guest {
    private int guestID;
    private String guestName;
    private String guestPhone;
    public guest(){
        
    }

    public guest(int guestID, String guestName, String guestPhone) {
        this.guestID = guestID;
        this.guestName = guestName;
        this.guestPhone = guestPhone;
    }

    public int getGuestID() {
        return guestID;
    }

    public void setGuestID(int guestID) {
        this.guestID = guestID;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestPhone() {
        return guestPhone;
    }

    public void setGuestPhone(String guestPhone) {
        this.guestPhone = guestPhone;
    }

    @Override
    public String toString() {
        return "guest{" + "guestID=" + guestID + ", guestName=" + guestName + ", guestPhone=" + guestPhone + '}';
    }
    
}
