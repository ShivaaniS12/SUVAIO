package com.th.dao;

import java.util.List;
import com.th.model.OrderItem;

public interface OrderItemDAO {

    void addOrderItem(OrderItem item);

    OrderItem getOrderItem(int orderItemId);

    List<OrderItem> getAllOrderItems();

    List<OrderItem> getOrderItemsByOrderId(int orderId);

    void updateOrderItem(OrderItem item);

    void deleteOrderItem(int orderItemId);
}