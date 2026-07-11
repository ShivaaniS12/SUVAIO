package com.th.Servelet;

import java.io.IOException;

import com.th.service.OrderService;
import com.th.serviceimpl.OrderServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/AssignAgentServlet")
public class AssignAgentServlet
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int orderId =
                Integer.parseInt(
                        request.getParameter(
                                "orderId"));

        int agentId =
                Integer.parseInt(
                        request.getParameter(
                                "agentId"));

        OrderService orderService =
                new OrderServiceImpl();

        orderService.assignDeliveryAgent(
                orderId,
                agentId);

        response.sendRedirect(
                "AdminOrdersServlet");
    }
}