package com.th.Servelet;

import java.io.IOException;

import com.th.service.OrderService;
import com.th.serviceimpl.OrderServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/DeliveryDashboardServlet")
public class DeliveryDashboardServlet
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

        int assignedOrders =
                orderService
                .getAssignedOrderCount(
                        agentId);

        int pickedUpOrders =
                orderService
                .getPickedUpOrderCount(
                        agentId);

        int deliveredOrders =
                orderService
                .getDeliveredOrderCount(
                        agentId);

        request.setAttribute(
                "assignedOrders",
                assignedOrders);

        request.setAttribute(
                "pickedUpOrders",
                pickedUpOrders);

        request.setAttribute(
                "deliveredOrders",
                deliveredOrders);

        request.setAttribute(
                "todayDeliveries",
                deliveredOrders);

        request.getRequestDispatcher(
                "/delivery-dashboard.jsp")
                .forward(request,
                        response);
    }
}