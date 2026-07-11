package com.th.Servelet;

import java.io.IOException;

import com.th.service.RestaurantService;
import com.th.serviceimpl.RestaurantServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/activateRestaurant")
public class ActivateRestaurantServlet
        extends HttpServlet {

    private RestaurantService restaurantService;

    @Override
    public void init() {

        restaurantService =
                new RestaurantServiceImpl();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int restaurantId =
                Integer.parseInt(
                        request.getParameter(
                                "restaurantId"));

        restaurantService
                .updateRestaurantStatus(
                        restaurantId,
                        "ACTIVE");

        response.sendRedirect(
                "manageRestaurants");
    }
}