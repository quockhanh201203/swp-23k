/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Salary {
    private int salaryID;
    private int salaryPlus;
    private int salaryMinus;
    private Date date;
    private String note;
    private int staffID;

    // Getters and Setters
    public int getSalaryID() {
        return salaryID;
    }

    public void setSalaryID(int salaryID) {
        this.salaryID = salaryID;
    }

    public int getSalaryPlus() {
        return salaryPlus;
    }

    public void setSalaryPlus(int salaryPlus) {
        this.salaryPlus = salaryPlus;
    }

    public int getSalaryMinus() {
        return salaryMinus;
    }

    public void setSalaryMinus(int salaryMinus) {
        this.salaryMinus = salaryMinus;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }
}
