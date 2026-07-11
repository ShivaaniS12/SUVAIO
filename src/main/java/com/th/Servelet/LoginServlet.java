package com.th.Servelet;

import java.io.IOException;

import com.th.model.User;
import com.th.service.UserService;
import com.th.serviceimpl.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.th.dao.RestaurantDAO;
import com.th.daoimpl.RestaurantDAOImpl;
import com.th.model.Restaurant;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserService userService;

    @Override
    public void init() {

        userService = new UserServiceImpl();
    }
    
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String email =
                request.getParameter("email");

        String password =
                request.getParameter("password");

        User user =
                userService.login(
                        email,
                        password);

        if(user != null) {

            HttpSession session =
                    request.getSession();

            session.setAttribute(
                    "loggedInUser",
                    user);

            String role =
                    user.getRole();
            
            session.setAttribute("role", role);

            if(role.equalsIgnoreCase("admin")) {

                response.sendRedirect("adminDashboard");

            }
            else if(role.equalsIgnoreCase("restaurant admin")) {

                RestaurantDAO restaurantDAO =
                        new RestaurantDAOImpl();

                Restaurant restaurant =
                        restaurantDAO.getRestaurantByUserId(
                                user.getUserID());

                if(restaurant != null){

                    session.setAttribute(
                            "restaurantId",
                            restaurant.getRestaurantId());

                    session.setAttribute(
                            "restaurantName",
                            restaurant.getName());

                    response.sendRedirect(
                            "RestaurantDashboardServlet");

                }
                else{

                    session.invalidate();

                    response.sendRedirect(
                            "login.jsp?error=noRestaurant");
                }

            }
            else if(role.equalsIgnoreCase("delivery agent")){

                response.sendRedirect(
                        "DeliveryDashboardServlet");

            }
            else if(role.equalsIgnoreCase("user")){

                response.sendRedirect(
                        "HomeServlet");

            }
            else{

                session.invalidate();

                response.sendRedirect(
                        "login.jsp");

            }
            
        }
    }
}