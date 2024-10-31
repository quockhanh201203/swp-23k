/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Legion
 */
import java.sql.Date;

public class PriceHistory {
    private int priceHistoryId;
    private int productId;
    private double price;
    private Date startDate;
    private Date endDate;
    
        public PriceHistory() {
    }

    // Constructor
    public PriceHistory(int productId, double price, Date startDate, Date endDate) {
        this.productId = productId;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and setters
    public int getPriceHistoryId() {
        return priceHistoryId;
    }

    public void setPriceHistoryId(int priceHistoryId) {
        this.priceHistoryId = priceHistoryId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}

