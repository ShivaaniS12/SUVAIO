package com.th.Servelet;

import java.io.IOException;
import java.util.List;

import com.th.dao.DeliveryAgentDAO;
import com.th.daoimpl.DeliveryAgentDAOImpl;
import com.th.model.DeliveryAgent;
import com.th.model.Order;
import com.th.service.OrderService;
import com.th.serviceimpl.OrderServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/AdminOrdersServlet")
public class AdminOrdersServlet
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        OrderService orderService =
                new OrderServiceImpl();

        DeliveryAgentDAO agentDAO =
                new DeliveryAgentDAOImpl();

        List<Order> orders =
                orderService
                .getUnassignedOrders();

        List<DeliveryAgent> agents =
                agentDAO
                .getAllDeliveryAgents();

        request.setAttribute(
                "orders",
                orders);

        request.setAttribute(
                "agents",
                agents);

        request.getRequestDispatcher(
                "/admin-orders.jsp")
                .forward(request,
                        response);
    }
}