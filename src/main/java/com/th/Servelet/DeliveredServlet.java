package com.th.Servelet;

import java.io.IOException;

import com.th.service.OrderService;
import com.th.serviceimpl.OrderServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/DeliveredServlet")
public class DeliveredServlet
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int orderId =
                Integer.parseInt(
                        request.getParameter(
                                "orderId"));

        OrderService orderService =
                new OrderServiceImpl();

        orderService.updateOrderStatus(
                orderId,
                "DELIVERED");

        response.sendRedirect(
                "OutForDeliveryOrdersServlet");
    }
}