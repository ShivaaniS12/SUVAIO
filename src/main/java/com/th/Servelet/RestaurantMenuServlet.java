package com.th.Servelet;

import java.io.IOException;
import java.util.List;

import com.th.dao.RestaurantDAO;
import com.th.daoimpl.RestaurantDAOImpl;
import com.th.model.Menu;
import com.th.model.Restaurant;
import com.th.service.MenuService;
import com.th.serviceimpl.MenuServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.util.HashMap;
import java.util.Map;

import com.th.dao.CartDAO;
import com.th.dao.CartItemDAO;
import com.th.daoimpl.CartDAOImpl;
import com.th.daoimpl.CartItemDAOImpl;
import com.th.model.Cart;
import com.th.model.CartItem;
import com.th.model.User;

@WebServlet("/RestaurantMenuServlet")
public class RestaurantMenuServlet extends HttpServlet {

    private MenuService menuService;
    private RestaurantDAO restaurantDAO;

    @Override
    public void init() {

        menuService = new MenuServiceImpl();
        restaurantDAO = new RestaurantDAOImpl();

    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int restaurantId =
                Integer.parseInt(
                        request.getParameter("restaurantId"));

        Restaurant restaurant =
                restaurantDAO.getRestaurant(restaurantId);

        List<Menu> menuList =
                menuService.getMenusByRestaurant(restaurantId);
        
        HttpSession session =
        		request.getSession(false);

        		Map<Integer,Integer> cartMap =
        		new HashMap<>();
        		
        		int cartCount = 0;

        		if(session != null &&
        		   session.getAttribute("loggedInUser") != null){

        		    User user =
        		    (User)session.getAttribute("loggedInUser");

        		    CartDAO cartDAO =
        		    new CartDAOImpl();

        		    Cart cart =
        		    cartDAO.getCartByUserId(
        		            user.getUserID());

        		    if(cart != null){

        		        CartItemDAO itemDAO =
        		        new CartItemDAOImpl();

        		        List<CartItem> items =
        		        itemDAO.getCartItemsByCartId(
        		                cart.getCartId());

        		        for(CartItem item : items){

        		            cartMap.put(
        		                    item.getMenuId(),
        		                    item.getQuantity());

        		            cartCount += item.getQuantity();

        		        }

        		    }

        		}
        		
        		

        request.setAttribute("restaurant", restaurant);
        request.setAttribute("menuList", menuList);
        request.setAttribute("cartMap",cartMap);
        request.setAttribute("cartCount", cartCount);

        request.getRequestDispatcher("restaurant-menu.jsp")
               .forward(request, response);

    }
}