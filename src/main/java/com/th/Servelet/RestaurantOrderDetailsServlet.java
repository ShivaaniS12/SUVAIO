package com.th.Servelet;

import java.io.IOException;
import java.util.List;

import com.th.dao.OrderItemDAO;
import com.th.dao.UserDAO;
import com.th.daoimpl.OrderItemDAOImpl;
import com.th.daoimpl.UserDAOImpl;
import com.th.model.Order;
import com.th.model.OrderItem;
import com.th.model.User;
import com.th.service.OrderService;
import com.th.serviceimpl.OrderServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RestaurantOrderDetailsServlet")
public class RestaurantOrderDetailsServlet
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int orderId =
                Integer.parseInt(
                        request.getParameter(
                                "orderId"));

        OrderService orderService =
                new OrderServiceImpl();

        Order order =
                orderService.getOrder(
                        orderId);

        UserDAO userDAO =
                new UserDAOImpl();

        User user =
                userDAO.getUser(
                        order.getUserId());

        OrderItemDAO orderItemDAO =
                new OrderItemDAOImpl();

        List<OrderItem> orderItems =
                orderItemDAO
                .getOrderItemsByOrderId(
                        orderId);

        request.setAttribute(
                "order",
                order);

        request.setAttribute(
                "user",
                user);

        request.setAttribute(
                "orderItems",
                orderItems);

        request.getRequestDispatcher(
                "/order-details.jsp")
                .forward(request,
                        response);
    }
}