package com.th.Servelet;

import java.io.IOException;
import java.util.List;

import com.th.daoimpl.OrderTrackingDAOImpl;
import com.th.model.Order;
import com.th.model.OrderTracking;
import com.th.service.OrderService;
import com.th.serviceimpl.OrderServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/TrackOrderServlet")
public class TrackOrderServlet extends HttpServlet {

    private OrderService orderService;
    private OrderTrackingDAOImpl trackingDAO;

    @Override
    public void init(){

        orderService = new OrderServiceImpl();
        trackingDAO = new OrderTrackingDAOImpl();

    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String orderIdStr =
                request.getParameter("orderId");

        if(orderIdStr == null){

            response.sendRedirect("HomeServlet");
            return;

        }

        int orderId =
                Integer.parseInt(orderIdStr);

        Order order =
                orderService.getOrder(orderId);

        if(order == null){

            response.sendRedirect("HomeServlet");
            return;

        }

        List<OrderTracking> trackingList =
                trackingDAO.getTrackingByOrderId(orderId);

        request.setAttribute("order", order);
        request.setAttribute("trackingList", trackingList);

        request.getRequestDispatcher("track-order.jsp")
                .forward(request,response);

    }

}