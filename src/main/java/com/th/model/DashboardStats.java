package com.th.model;

public class DashboardStats {

    private int totalUsers;
    private int totalRestaurants;
    private int ordersToday;
    private double todayRevenue;

    public int getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(int totalUsers) {
        this.totalUsers = totalUsers;
    }

    public int getTotalRestaurants() {
        return totalRestaurants;
    }

    public void setTotalRestaurants(int totalRestaurants) {
        this.totalRestaurants = totalRestaurants;
    }

    public int getOrdersToday() {
        return ordersToday;
    }

    public void setOrdersToday(int ordersToday) {
        this.ordersToday = ordersToday;
    }

    public double getTodayRevenue() {
        return todayRevenue;
    }

    public void setTodayRevenue(double todayRevenue) {
        this.todayRevenue = todayRevenue;
    }
}