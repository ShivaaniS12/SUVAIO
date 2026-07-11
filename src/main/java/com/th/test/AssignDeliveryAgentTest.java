package com.th.test;

import com.th.service.OrderService;
import com.th.serviceimpl.OrderServiceImpl;

public class AssignDeliveryAgentTest {

    public static void main(String[] args) {

        OrderService orderService =
                new OrderServiceImpl();

        try {

            orderService.assignDeliveryAgent(
                    3,   // orderId
                    1    // agentId
            );

            System.out.println(
                    "Delivery Agent Assigned Successfully");

        }
        catch(Exception e) {

            System.out.println(
                    "Assignment Failed");

            e.printStackTrace();
        }
    }
}