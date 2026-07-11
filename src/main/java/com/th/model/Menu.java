package com.th.model;

public class Menu {

    private int menuId;
    private int restaurantId;
    private String itemName;
    private String description;
    private String category;

    private double price;
    private double protein;
    private double carbs;
    private double fats;

    private int calories;

    private double rating;
    private boolean isAvailable;
    private String imagePath;

    // Default Constructor
    public Menu() {
    }

    // Parameterized Constructor
    public Menu(int menuId, int restaurantId, String itemName,
                String description, String category,
                double price, double protein,
                double carbs, double fats,
                int calories, double rating,
                boolean isAvailable, String imagePath) {

        this.menuId = menuId;
        this.restaurantId = restaurantId;
        this.itemName = itemName;
        this.description = description;
        this.category = category;
        this.price = price;
        this.protein = protein;
        this.carbs = carbs;
        this.fats = fats;
        this.calories = calories;
        this.rating = rating;
        this.isAvailable = isAvailable;
        this.imagePath = imagePath;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Menu [menuId=" + menuId
                + ", restaurantId=" + restaurantId
                + ", itemName=" + itemName
                + ", description=" + description
                + ", category=" + category
                + ", price=" + price
                + ", protein=" + protein
                + ", carbs=" + carbs
                + ", fats=" + fats
                + ", calories=" + calories
                + ", rating=" + rating
                + ", isAvailable=" + isAvailable
                + ", imagePath=" + imagePath + "]";
    }
}