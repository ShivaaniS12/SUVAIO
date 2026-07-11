package com.th.Servelet;

import java.io.IOException;

import com.th.dao.RestaurantDAO;
import com.th.daoimpl.RestaurantDAOImpl;
import com.th.model.Order;
import com.th.model.Restaurant;
import com.th.service.OrderService;
import com.th.serviceimpl.OrderServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.th.dao.DeliveryAddressDAO;
import com.th.dao.DeliveryAgentDAO;
import com.th.daoimpl.DeliveryAddressDAOImpl;
import com.th.daoimpl.DeliveryAgentDAOImpl;
import com.th.model.DeliveryAddress;
import com.th.model.DeliveryAgent;

@WebServlet("/OrderSuccessServlet")
public class OrderSuccessServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private OrderService orderService;
    private RestaurantDAO restaurantDAO;
    private DeliveryAddressDAO addressDAO;
    private DeliveryAgentDAO agentDAO;

    @Override
    public void init() {

        orderService = new OrderServiceImpl();
        restaurantDAO = new RestaurantDAOImpl();
        addressDAO = new DeliveryAddressDAOImpl();
        agentDAO = new DeliveryAgentDAOImpl();

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

        //----------------------------------------
        // Restaurant
        //----------------------------------------

        Restaurant restaurant =
                restaurantDAO.getRestaurant(
                        order.getRestaurantId());

        //----------------------------------------
        // Delivery Address
        //----------------------------------------

        DeliveryAddress address =
                addressDAO.getAddress(
                        order.getAddressId());

        String deliveryAddress = "Current Location";

        if(address != null){

            deliveryAddress =
                    address.getHouseNo()
                    + ", "
                    + address.getLandmark()
                    + ", "
                    + address.getCity();

        }

        //----------------------------------------
        // Delivery Time
        //----------------------------------------

        String deliveryTime =
                (String)request.getSession()
                .getAttribute("deliveryTime");

        if(deliveryTime == null){

            deliveryTime =
                    restaurant.getDeliveryTime();

        }

        //----------------------------------------
        // Delivery Agent
        //----------------------------------------

        DeliveryAgent agent = null;

        if(order.getAgentId()!=null){

            agent =
                    agentDAO.getDeliveryAgent(
                            order.getAgentId());

        }

        //----------------------------------------
        // Send to JSP
        //----------------------------------------

        request.setAttribute("order",order);

        request.setAttribute(
                "restaurant",
                restaurant);

        request.setAttribute(
                "deliveryAddress",
                deliveryAddress);

        request.setAttribute(
                "deliveryTime",
                deliveryTime);

        request.setAttribute(
                "deliveryAgent",
                agent);

        request.getRequestDispatcher(
                "order-success.jsp")
                .forward(
                        request,
                        response);

    }

}