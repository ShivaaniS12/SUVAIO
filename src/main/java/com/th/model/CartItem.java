package com.th.model;

public class CartItem {

    private int cartItemId;
    private int cartId;
    private int menuId;

    private int quantity;

    private double subtotal;

    // Diet Planner Fields
    private double protein;
    private double carbs;
    private double fats;

    private int calories;

    // Default Constructor
    public CartItem() {
    }

    // Parameterized Constructor
    public CartItem(int cartItemId,
                    int cartId,
                    int menuId,
                    int quantity,
                    double subtotal,
                    double protein,
                    double carbs,
                    double fats,
                    int calories) {

        this.cartItemId = cartItemId;
        this.cartId = cartId;
        this.menuId = menuId;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.protein = protein;
        this.carbs = carbs;
        this.fats = fats;
        this.calories = calories;
    }

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "CartItem [cartItemId=" + cartItemId
                + ", cartId=" + cartId
                + ", menuId=" + menuId
                + ", quantity=" + quantity
                + ", subtotal=" + subtotal
                + ", protein=" + protein
                + ", carbs=" + carbs
                + ", fats=" + fats
                + ", calories=" + calories
                + "]";
    }
}