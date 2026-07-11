package com.th.dao;

import java.util.List;

import com.th.model.AnalyticsData;
import com.th.model.Order;
import com.th.model.OrderHistoryView;

public interface OrderDAO {

    int addOrder(Order order);

    Order getOrder(int orderId);

    List<Order> getAllOrders();

    List<Order> getOrdersByUser(int userId);

    List<Order> getOrdersByRestaurant(int restaurantId);

    List<Order> getOrdersByAgent(int agentId);

    void updateOrder(Order order);

    void deleteOrder(int orderId);
    
    int getTodayOrderCount();

    double getTodayRevenue();
    
    double getWeeklyRevenue();

    double getMonthlyRevenue();
    
    String getTopRestaurant();
    
    List<AnalyticsData>
    getOrdersPerRestaurant();
    
    List<AnalyticsData> getRestaurantWiseRevenue();
    
    double getPreviousMonthRevenue();

    double getCurrentMonthRevenue();
    
    int getTodayOrderCountByRestaurant(
            int restaurantId);

    double getTodayRevenueByRestaurant(
            int restaurantId);

    double getWeeklyRevenueByRestaurant(
            int restaurantId);

    double getMonthlyRevenueByRestaurant(
            int restaurantId);
    
    List<Order> getRecentOrdersByRestaurant(
            int restaurantId);

    String getMostOrderedItem(
            int restaurantId);
    
    int getDeliveredOrdersCountByRestaurant(
            int restaurantId);

    double getAverageOrderValueByRestaurant(
            int restaurantId);
    
    int getTotalOrdersByRestaurant(
            int restaurantId);

    int getCancelledOrdersByRestaurant(
            int restaurantId);

    int getUniqueCustomersByRestaurant(
            int restaurantId);

    String getMostOrderedCategory(
            int restaurantId);
    
    List<Order> getAssignedOrdersByAgent(
            int agentId);

    int getAssignedOrderCount(
            int agentId);

    int getDeliveredOrderCount(
            int agentId);

    int getPickedUpOrderCount(
            int agentId);
    
    List<Order> getPickedUpOrdersByAgent(
            int agentId);

    List<Order> getOutForDeliveryOrdersByAgent(
            int agentId);
    
    List<Order> getDeliveredOrdersByAgent(
            int agentId);
    
    int getTotalDeliveriesByAgent(
            int agentId);

    int getTodayDeliveriesByAgent(
            int agentId);
    
    List<Order> getUnassignedOrders();
    
    List<OrderHistoryView> getOrderHistoryByUser(int userId);
}