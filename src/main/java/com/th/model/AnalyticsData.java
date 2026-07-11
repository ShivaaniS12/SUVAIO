package com.th.model;

public class AnalyticsData {

    private String restaurantName;
    private int orderCount;
    private double revenue;

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(
            String restaurantName) {

        this.restaurantName =
                restaurantName;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(
            int orderCount) {

        this.orderCount =
                orderCount;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(
            double revenue) {

        this.revenue =
                revenue;
    }
}