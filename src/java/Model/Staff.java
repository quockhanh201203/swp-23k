/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import DAO.UserDAO;

/**
 *
 * @author Legion
 */
public class Staff {
    private int staffID;
    private String staffName;
    private String phoneNumber;
    private String email;
    private double salary;
    private boolean newAccount;
    private int accountID;
    private Account account;

    public Account getAccount() {
        return new UserDAO().getAccountByID(accountID);
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    // Constructor
    public Staff(int staffID, String staffName, String phoneNumber, String email, double salary, boolean newAccount, int accountID) {
        this.staffID = staffID;
        this.staffName = staffName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.salary = salary;
        this.newAccount = newAccount;
        this.accountID = accountID;
    }

    // Getters and Setters
    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public boolean isNewAccount() {
        return newAccount;
    }

    public void setNewAccount(boolean newAccount) {
        this.newAccount = newAccount;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }
    
    // Optional toString method for easy display
    @Override
    public String toString() {
        return "Staff [staffID=" + staffID + ", staffName=" + staffName + ", phoneNumber=" + phoneNumber + 
               ", email=" + email + ", salary=" + salary + ", newAccount=" + newAccount + ", accountID=" + accountID + "]";
    }
}

