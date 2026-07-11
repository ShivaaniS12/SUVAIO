package com.th.Servelet;

import java.io.IOException;
import java.util.List;

import com.th.model.Restaurant;
import com.th.model.User;
import com.th.service.RestaurantService;
import com.th.serviceimpl.RestaurantServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.th.dao.CartDAO;
import com.th.dao.CartItemDAO;
import com.th.daoimpl.CartDAOImpl;
import com.th.daoimpl.CartItemDAOImpl;
import com.th.model.Cart;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private RestaurantService restaurantService;
    
    
    

    @Override
    public void init() {

        restaurantService = new RestaurantServiceImpl();

    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
    	
    	response.setHeader("Cache-Control",
    	        "no-cache, no-store, must-revalidate");

    	response.setHeader("Pragma",
    	        "no-cache");

    	response.setDateHeader("Expires", 0);

        HttpSession session = request.getSession(false);

        if (session == null ||
            session.getAttribute("loggedInUser") == null) {

            response.sendRedirect("userlogin.jsp");
            return;
        }

        User user =
                (User) session.getAttribute("loggedInUser");

        // Allow only CUSTOMER role
        if (!user.getRole().equalsIgnoreCase("user")) {

            response.sendRedirect("login.jsp");
            return;
        }

        CartDAO cartDAO = new CartDAOImpl();
        CartItemDAO cartItemDAO = new CartItemDAOImpl();

        Cart cart =
                cartDAO.getCartByUserId(user.getUserID());

        int cartCount = 0;

        if (cart != null) {

            cartCount =
                    cartItemDAO.getCartItemCount(
                            cart.getCartId());
        }

        List<Restaurant> restaurants =
                restaurantService.getAllRestaurants();

        request.setAttribute("user", user);
        request.setAttribute("cartCount", cartCount);
        request.setAttribute("restaurants", restaurants);

        request.getRequestDispatcher("home.jsp")
                .forward(request, response);
    }

}