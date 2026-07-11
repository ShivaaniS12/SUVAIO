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

@WebServlet("/RevenueServlet")
public class RevenueServlet extends HttpServlet {

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
                (Integer) session.getAttribute(
                        "restaurantId");

        OrderService orderService =
                new OrderServiceImpl();

        double todayRevenue =
                orderService
                .getTodayRevenueByRestaurant(
                        restaurantId);

        double weeklyRevenue =
                orderService
                .getWeeklyRevenueByRestaurant(
                        restaurantId);

        double monthlyRevenue =
                orderService
                .getMonthlyRevenueByRestaurant(
                        restaurantId);

        int deliveredOrders =
                orderService
                .getDeliveredOrdersCountByRestaurant(
                        restaurantId);

        double averageOrderValue =
                orderService
                .getAverageOrderValueByRestaurant(
                        restaurantId);

        request.setAttribute(
                "todayRevenue",
                todayRevenue);

        request.setAttribute(
                "weeklyRevenue",
                weeklyRevenue);

        request.setAttribute(
                "monthlyRevenue",
                monthlyRevenue);

        request.setAttribute(
                "deliveredOrders",
                deliveredOrders);

        request.setAttribute(
                "averageOrderValue",
                averageOrderValue);

        request.getRequestDispatcher(
                "/revenue.jsp")
                .forward(request, response);
    }
}