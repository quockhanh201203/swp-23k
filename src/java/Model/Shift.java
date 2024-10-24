/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class Shift {
    private int shiftID;
    private Integer staffQuantity;
    private String weekDate;
    private Date date;
    private boolean dayTime;
    private List<ShiftStaff> shift_staffs;

    public Shift() {
    }

    public Shift(int shiftID, Integer staffQuantity, String weekDate, Date date, boolean dayTime, List<ShiftStaff> shift_staffs) {
        this.shiftID = shiftID;
        this.staffQuantity = staffQuantity;
        this.weekDate = weekDate;
        this.date = date;
        this.dayTime = dayTime;
        this.shift_staffs = shift_staffs;
    }

    public int getShiftID() {
        return shiftID;
    }

    public void setShiftID(int shiftID) {
        this.shiftID = shiftID;
    }

    public Integer getStaffQuantity() {
        return staffQuantity;
    }

    public void setStaffQuantity(Integer staffQuantity) {
        this.staffQuantity = staffQuantity;
    }

    public String getWeekDate() {
        return weekDate;
    }

    public void setWeekDate(String weekDate) {
        this.weekDate = weekDate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isDayTime() {
        return dayTime;
    }

    public void setDayTime(boolean dayTime) {
        this.dayTime = dayTime;
    }

    public List<ShiftStaff> getShift_staffs() {
        return shift_staffs;
    }

    public void setShift_staffs(List<ShiftStaff> shift_staffs) {
        this.shift_staffs = shift_staffs;
    }


    
    
    
}
