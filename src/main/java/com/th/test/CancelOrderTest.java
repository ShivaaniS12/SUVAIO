package com.th.test;

import com.th.service.OrderService;
import com.th.serviceimpl.OrderServiceImpl;

public class CancelOrderTest {

    public static void main(String[] args) {

        OrderService orderService =
                new OrderServiceImpl();

        try {

            orderService.cancelOrder(3);

            System.out.println(
                    "Order Cancelled Successfully");

        }
        catch(Exception e) {

            System.out.println(
                    "Order Cancellation Failed");

            e.printStackTrace();
        }
    }
}