package com.th.Servelet;

import java.io.IOException;

import com.th.service.OrderService;
import com.th.serviceimpl.OrderServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/RestaurantAnalyticsServlet")
public class RestaurantAnalyticsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if(session == null ||
           session.getAttribute("restaurantId") == null) {

            response.sendRedirect("login.jsp");
            return;
        }

        int restaurantId =
                (Integer)session.getAttribute(
                        "restaurantId");

        OrderService orderService =
                new OrderServiceImpl();

        int totalOrders =
                orderService
                .getTotalOrdersByRestaurant(
                        restaurantId);

        int deliveredOrders =
                orderService
                .getDeliveredOrdersCountByRestaurant(
                        restaurantId);

        int cancelledOrders =
                orderService
                .getCancelledOrdersByRestaurant(
                        restaurantId);

        int customersServed =
                orderService
                .getUniqueCustomersByRestaurant(
                        restaurantId);

        String mostOrderedItem =
                orderService
                .getMostOrderedItem(
                        restaurantId);

        String mostOrderedCategory =
                orderService
                .getMostOrderedCategory(
                        restaurantId);

        double avgOrderValue =
                orderService
                .getAverageOrderValueByRestaurant(
                        restaurantId);

        request.setAttribute(
                "totalOrders",
                totalOrders);

        request.setAttribute(
                "deliveredOrders",
                deliveredOrders);

        request.setAttribute(
                "cancelledOrders",
                cancelledOrders);

        request.setAttribute(
                "customersServed",
                customersServed);

        request.setAttribute(
                "mostOrderedItem",
                mostOrderedItem);

        request.setAttribute(
                "mostOrderedCategory",
                mostOrderedCategory);

        request.setAttribute(
                "avgOrderValue",
                Math.round(avgOrderValue * 100.0)
                / 100.0);

        request.getRequestDispatcher(
                "/restaurantanalytics.jsp")
                .forward(request, response);
    }
}