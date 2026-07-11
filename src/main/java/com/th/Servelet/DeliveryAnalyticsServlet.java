package com.th.Servelet;

import java.io.IOException;

import com.th.service.OrderService;
import com.th.serviceimpl.OrderServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/DeliveryAnalyticsServlet")
public class DeliveryAnalyticsServlet
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
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

        int totalDeliveries =
                orderService
                .getTotalDeliveriesByAgent(
                        agentId);

        int todayDeliveries =
                orderService
                .getTodayDeliveriesByAgent(
                        agentId);

        request.setAttribute(
                "totalDeliveries",
                totalDeliveries);

        request.setAttribute(
                "todayDeliveries",
                todayDeliveries);

        request.getRequestDispatcher(
                "/delivery-analytics.jsp")
                .forward(request,
                        response);
    }
}