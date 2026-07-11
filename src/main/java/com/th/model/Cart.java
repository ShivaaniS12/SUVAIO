package com.th.model;

public class Cart {

    private int cartId;
    private int userId;

    private double totalAmount;

    private double totalProtein;
    private double totalCarbs;
    private double totalFats;

    private int totalCalories;

    public Cart() {
    }

    public Cart(int cartId, int userId,
                double totalAmount,
                double totalProtein,
                double totalCarbs,
                double totalFats,
                int totalCalories) {

        this.cartId = cartId;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.totalProtein = totalProtein;
        this.totalCarbs = totalCarbs;
        this.totalFats = totalFats;
        this.totalCalories = totalCalories;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "Cart [cartId=" + cartId
                + ", userId=" + userId
                + ", totalAmount=" + totalAmount
                + ", totalProtein=" + totalProtein
                + ", totalCarbs=" + totalCarbs
                + ", totalFats=" + totalFats
                + ", totalCalories=" + totalCalories
                + "]";
    }
}