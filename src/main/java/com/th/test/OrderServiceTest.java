//package com.th.test;
//
//import com.th.service.OrderService;
//import com.th.serviceimpl.OrderServiceImpl;
//
//public class OrderServiceTest {
//
//    public static void main(String[] args) {
//
//        OrderService orderService =
//                new OrderServiceImpl();
//
//        try {

//            int orderId =
//                    orderService.placeOrder(
//                            1,      // userId
//                            1,      // restaurantId
//                            "UPI"   // payment mode
//                    );
//
//            System.out.println(
//                    "Order Created Successfully");
//
//            System.out.println(
//                    "Generated Order ID : "
//                    + orderId);
//
//        }
//        catch(Exception e) {
//
//            System.out.println(
//                    "Order Creation Failed");
//
//            e.printStackTrace();
//        }
//    }
//}