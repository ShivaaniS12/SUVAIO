package com.th.dao;

import java.util.List;

import com.th.model.OrderTracking;

public interface OrderTrackingDAO {

    void addTracking(OrderTracking tracking);

    OrderTracking getTracking(int trackingId);

    List<OrderTracking> getAllTracking();

    List<OrderTracking> getTrackingByOrderId(int orderId);

    void updateTracking(OrderTracking tracking);

    void deleteTracking(int trackingId);
}