package com.th.test;

import com.th.service.OrderService;
import com.th.serviceimpl.OrderServiceImpl;

public class UpdateOrderTest {

    public static void main(String[] args) {

        OrderService orderService =
                new OrderServiceImpl();

        try {

            orderService.updateOrderStatus(
                    3,
                    "PREPARING"
            );

            System.out.println(
                    "Status Updated Successfully");

        }
        catch(Exception e) {

            System.out.println(
                    "Status Update Failed");

            e.printStackTrace();
        }
    }
}