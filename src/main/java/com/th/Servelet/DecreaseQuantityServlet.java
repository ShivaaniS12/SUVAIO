package com.th.Servelet;

import java.io.IOException;

import com.th.daoimpl.CartDAOImpl;
import com.th.daoimpl.CartItemDAOImpl;
import com.th.daoimpl.MenuDAOImpl;
import com.th.model.Cart;
import com.th.model.CartItem;
import com.th.model.Menu;
import com.th.model.User;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/DecreaseQuantityServlet")
public class DecreaseQuantityServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession();

        User user =
        (User)session.getAttribute("loggedInUser");

        int menuId =
        Integer.parseInt(
        request.getParameter("menuId"));

        CartDAOImpl cartDAO =
        new CartDAOImpl();

        CartItemDAOImpl itemDAO =
        new CartItemDAOImpl();

        MenuDAOImpl menuDAO =
        new MenuDAOImpl();

        Cart cart =
        cartDAO.getCartByUserId(
                user.getUserID());

        CartItem item =
        itemDAO.getCartItemByMenu(
                cart.getCartId(),
                menuId);

        Menu menu =
        menuDAO.getMenu(menuId);

        if(item.getQuantity() == 1){

            itemDAO.deleteCartItem(
                    item.getCartItemId());

        }

        else{

            int qty =
            item.getQuantity()-1;

            itemDAO.updateQuantity(

                    item.getCartItemId(),

                    qty,

                    qty*menu.getPrice(),

                    qty*menu.getProtein(),

                    qty*menu.getCarbs(),

                    qty*menu.getFats(),

                    qty*menu.getCalories());

        }

        //-----------------------------

        cart.setTotalAmount(
                cart.getTotalAmount()
                - menu.getPrice());

        cart.setTotalProtein(
                cart.getTotalProtein()
                - menu.getProtein());

        cart.setTotalCarbs(
                cart.getTotalCarbs()
                - menu.getCarbs());

        cart.setTotalFats(
                cart.getTotalFats()
                - menu.getFats());

        cart.setTotalCalories(
                cart.getTotalCalories()
                - menu.getCalories());

        cartDAO.updateCart(cart);
        
        response.sendRedirect(
        		"RestaurantMenuServlet?restaurantId="
        		+menu.getRestaurantId()
        		+"#menuSection");

    }

}