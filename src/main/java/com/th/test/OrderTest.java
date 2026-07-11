package com.th.test;

import java.util.List;

import com.th.daoimpl.OrderDAOImpl;
import com.th.model.Order;

public class OrderTest {

    public static void main(String[] args) {

        OrderDAOImpl dao = new OrderDAOImpl();

        // ==========================
        // INSERT TEST
        // ==========================
        System.out.println("===== INSERT TEST =====");

        Order order = new Order(
                0,
                1,          // user_id
                1,          // restaurant_id
                1,          // agent_id
                899.00,
                75.00,
                120.00,
                30.00,
                1450,
                "UPI",
                "PLACED",
                null
        );

        dao.addOrder(order);

        System.out.println("Order Added Successfully");


        // ==========================
        // GET ORDER BY ID
        // ==========================
        System.out.println("\n===== GET ORDER =====");

        Order fetchedOrder = dao.getOrder(1);

        if(fetchedOrder != null) {
            System.out.println(fetchedOrder);
        }
        else {
            System.out.println("Order Not Found");
        }


        // ==========================
        // GET ALL ORDERS
        // ==========================
        System.out.println("\n===== GET ALL ORDERS =====");

        List<Order> orders = dao.getAllOrders();

        for(Order o : orders) {
            System.out.println(o);
        }


        // ==========================
        // GET ORDERS BY USER
        // ==========================
        System.out.println("\n===== USER ORDERS =====");

        List<Order> userOrders =
                dao.getOrdersByUser(1);

        for(Order o : userOrders) {
            System.out.println(o);
        }


        // ==========================
        // GET ORDERS BY RESTAURANT
        // ==========================
        System.out.println("\n===== RESTAURANT ORDERS =====");

        List<Order> restaurantOrders =
                dao.getOrdersByRestaurant(1);

        for(Order o : restaurantOrders) {
            System.out.println(o);
        }


        // ==========================
        // GET ORDERS BY AGENT
        // ==========================
        System.out.println("\n===== AGENT ORDERS =====");

        List<Order> agentOrders =
                dao.getOrdersByAgent(1);

        for(Order o : agentOrders) {
            System.out.println(o);
        }


        // ==========================
        // UPDATE TEST
        // ==========================
        System.out.println("\n===== UPDATE TEST =====");

        Order updateOrder = dao.getOrder(1);

        if(updateOrder != null) {

            updateOrder.setOrderStatus("DELIVERED");

            dao.updateOrder(updateOrder);

            System.out.println("Order Updated Successfully");

            System.out.println(
                    dao.getOrder(1)
            );
        }


        // ==========================
        // DELETE TEST
        // ==========================
        System.out.println("\n===== DELETE TEST =====");

        dao.deleteOrder(4);

        System.out.println("Order Deleted Successfully");


        // ==========================
        // FINAL LIST
        // ==========================
        System.out.println("\n===== FINAL ORDERS =====");

        List<Order> finalOrders =
                dao.getAllOrders();

        for(Order o : finalOrders) {
            System.out.println(o);
        }
    }
}