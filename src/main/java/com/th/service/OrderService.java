package com.th.service;
import java.util.List;
import com.th.model.Order;
import com.th.model.OrderHistoryView;

public interface OrderService {

	int placeOrder(int userId,
            int restaurantId,
            int addressId,
            String paymentMode);

    void assignDeliveryAgent(int orderId,
                             int agentId);

    void updateOrderStatus(int orderId,
                           String status);

    void cancelOrder(int orderId);
    
    List<Order> getOrdersByRestaurant(
            int restaurantId);

    Order getOrder(
            int orderId);
    
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
    
    List<Order> getOrdersByUser(int userId);
    List<OrderHistoryView> getOrderHistoryByUser(int userId);
    
}