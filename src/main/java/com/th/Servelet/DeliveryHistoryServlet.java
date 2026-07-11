package com.th.Servelet;

import java.io.IOException;
import java.util.List;

import com.th.model.Order;
import com.th.service.OrderService;
import com.th.serviceimpl.OrderServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/DeliveryHistoryServlet")
public class DeliveryHistoryServlet
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if(session == null ||
           session.getAttribute("agentId") == null) {

            response.sendRedirect(
                    "delivery-login.jsp");
            return;
        }

        int agentId =
                (Integer)session.getAttribute(
                        "agentId");

        OrderService orderService =
                new OrderServiceImpl();

        List<Order> orders =
                orderService
                .getDeliveredOrdersByAgent(
                        agentId);

        request.setAttribute(
                "orders",
                orders);

        request.getRequestDispatcher(
                "/delivery-history.jsp")
                .forward(request,
                        response);
    }
}