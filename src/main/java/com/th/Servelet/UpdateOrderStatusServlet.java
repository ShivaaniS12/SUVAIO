package com.th.Servelet;

import java.io.IOException;

import com.th.service.OrderService;
import com.th.serviceimpl.OrderServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UpdateOrderStatusServlet")
public class UpdateOrderStatusServlet
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private OrderService orderService;

    @Override
    public void init() {

        orderService =
                new OrderServiceImpl();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int orderId =
                Integer.parseInt(
                        request.getParameter(
                                "orderId"));

        String status =
                request.getParameter(
                        "status");

        orderService.updateOrderStatus(
                orderId,
                status);

        response.sendRedirect(
                "RestaurantOrdersServlet");
    }
}