package com.th.model;

import java.sql.Timestamp;

public class Order {

    private int orderId;

    private int userId;

    private int restaurantId;

    private Integer agentId;

    private double totalAmount;

    private double totalProtein;

    private double totalCarbs;

    private double totalFats;

    private int totalCalories;

    private String paymentMode;

    private String orderStatus;

    private Timestamp orderDate;
    
    private int addressId;

    // Default Constructor
    public Order() {
    }

    // Parameterized Constructor
    public Order(int orderId,
                 int userId,
                 int restaurantId,
                 Integer agentId,
                 double totalAmount,
                 double totalProtein,
                 double totalCarbs,
                 double totalFats,
                 int totalCalories,
                 String paymentMode,
                 String orderStatus,
                 Timestamp orderDate) {

        this.orderId = orderId;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.agentId = agentId;
        this.totalAmount = totalAmount;
        this.totalProtein = totalProtein;
        this.totalCarbs = totalCarbs;
        this.totalFats = totalFats;
        this.totalCalories = totalCalories;
        this.paymentMode = paymentMode;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalProtein() {
        return totalProtein;
    }

    public void setTotalProtein(double totalProtein) {
        this.totalProtein = totalProtein;
    }

    public double getTotalCarbs() {
        return totalCarbs;
    }

    public void setTotalCarbs(double totalCarbs) {
        this.totalCarbs = totalCarbs;
    }

    public double getTotalFats() {
        return totalFats;
    }

    public void setTotalFats(double totalFats) {
        this.totalFats = totalFats;
    }

    public int getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(int totalCalories) {
        this.totalCalories = totalCalories;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }
    
    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    @Override
    public String toString() {
        return "Order [orderId=" + orderId
                + ", userId=" + userId
                + ", restaurantId=" + restaurantId
                + ", agentId=" + agentId
                + ", totalAmount=" + totalAmount
                + ", totalProtein=" + totalProtein
                + ", totalCarbs=" + totalCarbs
                + ", totalFats=" + totalFats
                + ", totalCalories=" + totalCalories
                + ", paymentMode=" + paymentMode
                + ", orderStatus=" + orderStatus
                + ", orderDate=" + orderDate
                + "]";
    }
}