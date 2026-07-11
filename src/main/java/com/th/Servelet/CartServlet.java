package com.th.Servelet;

import java.io.IOException;
import java.util.List;

import com.th.dao.RestaurantDAO;
import com.th.daoimpl.RestaurantDAOImpl;
import com.th.model.Cart;
import com.th.model.CartItem;
import com.th.model.CartItemView;
import com.th.model.Restaurant;
import com.th.model.User;
import com.th.service.CartService;
import com.th.serviceimpl.CartServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.th.Utility.DistanceUtil;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet{

    private CartService cartService;
    private RestaurantDAO restaurantDAO;

    @Override
    public void init(){

        cartService =
                new CartServiceImpl();
        restaurantDAO = new RestaurantDAOImpl();

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

        if(session == null ||
           session.getAttribute("loggedInUser") == null){

            response.sendRedirect("userlogin.jsp");
            return;
        }

        User user =
        (User) session.getAttribute("loggedInUser");

        Cart cart =
        cartService.getCartByUserId(user.getUserID());

        if(cart == null){

            response.sendRedirect("HomeServlet");
            return;
        }

        List<CartItemView> items =
        cartService.getCartItems(cart.getCartId());

        //-------------------------------------

        double foodTotal = cart.getTotalAmount();

        double distance = 0;

        Double userLatitude =
                (Double) session.getAttribute("userLatitude");

        Double userLongitude =
                (Double) session.getAttribute("userLongitude");

        if(userLatitude != null &&
           userLongitude != null &&
           !items.isEmpty()){

            int restaurantId =
                    items.get(0).getRestaurantId();

            Restaurant restaurant =
                    restaurantDAO.getRestaurant(
                            restaurantId);

            if(restaurant != null){

                distance =
                        DistanceUtil.calculateDistance(
                                userLatitude,
                                userLongitude,
                                restaurant.getLatitude(),
                                restaurant.getLongitude());

                distance =
                        Math.round(distance * 100.0) / 100.0;

            }

        }

        double deliveryFee =
                Math.round(distance * 1.5 * 100.0) / 100.0;

        double platformFee = 0;

        double packingFee = 0;

        double grandTotal =
                foodTotal
                + deliveryFee
                + platformFee
                + packingFee;

        //-------------------------------------

        request.setAttribute("cartItems", items);

        request.setAttribute("foodTotal",
                foodTotal);

        request.setAttribute("deliveryFee",
                deliveryFee);

        request.setAttribute("distance",
                distance);

        request.setAttribute("platformFee",
                platformFee);

        request.setAttribute("packingFee",
                packingFee);

        request.setAttribute("grandTotal",
                grandTotal);

        request.setAttribute("protein",
                cart.getTotalProtein());

        request.setAttribute("carbs",
                cart.getTotalCarbs());

        request.setAttribute("fats",
                cart.getTotalFats());

        request.setAttribute("calories",
                cart.getTotalCalories());

        request.getRequestDispatcher(
                "cart.jsp")
                .forward(request,response);

    }

}