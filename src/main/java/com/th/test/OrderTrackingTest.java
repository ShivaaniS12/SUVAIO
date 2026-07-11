package com.th.test;

import java.util.List;

import com.th.daoimpl.OrderTrackingDAOImpl;
import com.th.model.OrderTracking;

public class OrderTrackingTest {

    public static void main(String[] args) {

        OrderTrackingDAOImpl dao = new OrderTrackingDAOImpl();

        // ==========================
        // INSERT TEST
        // ==========================

        System.out.println("===== INSERT TEST =====");

        OrderTracking tracking = new OrderTracking(
                0,
                1,
                "PLACED",
                "Order placed successfully",
                "USER",
                null
        );

        dao.addTracking(tracking);

        System.out.println("Tracking Added Successfully");

        // ==========================
        // GET BY ID
        // ==========================

        System.out.println("\n===== GET TRACKING =====");

        OrderTracking t = dao.getTracking(1);

        if(t != null) {
            System.out.println(t);
        }
        else {
            System.out.println("Tracking Not Found");
        }

        // ==========================
        // GET ALL
        // ==========================

        System.out.println("\n===== GET ALL TRACKING =====");

        List<OrderTracking> trackingList =
                dao.getAllTracking();

        for(OrderTracking ot : trackingList) {
            System.out.println(ot);
        }

        // ==========================
        // GET BY ORDER ID
        // ==========================

        System.out.println("\n===== GET BY ORDER =====");

        List<OrderTracking> orderTracking =
                dao.getTrackingByOrderId(1);

        for(OrderTracking ot : orderTracking) {
            System.out.println(ot);
        }

        // ==========================
        // UPDATE TEST
        // ==========================

        System.out.println("\n===== UPDATE TEST =====");

        OrderTracking updateTracking =
                dao.getTracking(1);

        if(updateTracking != null) {

            updateTracking.setStatus(
                    "RESTAURANT_ACCEPTED");

            updateTracking.setRemarks(
                    "Restaurant accepted order");

            updateTracking.setUpdatedBy(
                    "RESTAURANT");

            dao.updateTracking(updateTracking);

            System.out.println(
                    "Tracking Updated Successfully");
        }

        // ==========================
        // DELETE TEST
        // ==========================

        System.out.println("\n===== DELETE TEST =====");

        dao.deleteTracking(2);

        System.out.println(
                "Tracking Deleted Successfully");

        // ==========================
        // FINAL LIST
        // ==========================

        System.out.println("\n===== FINAL TRACKING =====");

        List<OrderTracking> finalList =
                dao.getAllTracking();

        for(OrderTracking ot : finalList) {
            System.out.println(ot);
        }
    }
}