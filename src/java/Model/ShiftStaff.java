/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ADMIN
 */
public class ShiftStaff {

    private int shiftID;
    private int staffID;
    private String status;
    private Staff staff;

    public ShiftStaff(int ShiftID, int StaffID, String Status, Staff staff) {
        this.shiftID = ShiftID;
        this.staffID = StaffID;
        this.status = Status;
        this.staff = staff;
    }

    public ShiftStaff() {
    }

    public int getShiftID() {
        return shiftID;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public void setShiftID(int ShiftID) {
        this.shiftID = ShiftID;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int StaffID) {
        this.staffID = StaffID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String Status) {
        this.status = Status;
    }

}
