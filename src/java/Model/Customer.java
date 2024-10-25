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
public class Customer {
    private int customerID;
    private String customerName;
    private String phoneNumber;
    private String email;
    private int point;
    private int accountID;
    private Account account;

    public Customer(int customerID, String customerName, String phoneNumber, String email, int point, int accountID, Account account) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.point = point;
        this.accountID = accountID;
        this.account = account;
    }

    public Customer() {
    }

    public Customer(int customerID, String customerName, String phoneNumber, String email, int point, int accountID) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.point = point;
        this.accountID = accountID;
    }
    
    

    public Account getAccount() {
        return new UserDAO().getAccountByID(accountID);
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
    

    // Getters and Setters
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }
}
