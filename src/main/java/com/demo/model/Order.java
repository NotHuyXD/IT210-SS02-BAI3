package com.demo.model;

import java.util.Date;

public class Order {
    private String id;
    private String productName;
    private double totalPrice;
    private Date orderDate;

    public Order(String id, String productName, double totalPrice, Date orderDate) {
        this.id = id;
        this.productName = productName;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }
    // Bạn nhớ Generate các hàm Getters và Setters ở đây nhé!
    public String getId() { return id; }
    public String getProductName() { return productName; }
    public double getTotalPrice() { return totalPrice; }
    public Date getOrderDate() { return orderDate; }
}