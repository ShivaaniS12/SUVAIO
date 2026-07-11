package com.th.Servelet;

import java.io.IOException;

import com.th.daoimpl.CartDAOImpl;
import com.th.daoimpl.CartItemDAOImpl;
import com.th.daoimpl.MenuDAOImpl;
import com.th.model.Cart;
import com.th.model.CartItem;
import com.th.model.Menu;
import com.th.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {

    private CartDAOImpl cartDAO = new CartDAOImpl();
    private CartItemDAOImpl cartItemDAO = new CartItemDAOImpl();
    private MenuDAOImpl menuDAO = new MenuDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        User user =
        		(User) session.getAttribute("loggedInUser");

        if(user == null){

            response.sendRedirect("userlogin.jsp");

            return;
        }

        int userId = user.getUserID();

        int menuId = Integer.parseInt(
                request.getParameter("menuId"));

        //---------------------------------------------------

        Menu menu = menuDAO.getMenu(menuId);

        //---------------------------------------------------

        Cart cart =
                cartDAO.getCartByUserId(userId);

        if(cart == null){

            cart = new Cart();

            cart.setUserId(userId);

            cart.setTotalAmount(0);

            cart.setTotalProtein(0);

            cart.setTotalCarbs(0);

            cart.setTotalFats(0);

            cart.setTotalCalories(0);

            cartDAO.addCart(cart);

            cart = cartDAO.getCartByUserId(userId);
        }

        //---------------------------------------------------

        CartItem existingItem =
                cartItemDAO.getCartItemByMenu(
                        cart.getCartId(),
                        menuId);

        if(existingItem != null){

            int qty =
                    existingItem.getQuantity()+1;

            double subtotal =
                    qty * menu.getPrice();

            double protein =
                    qty * menu.getProtein();

            double carbs =
                    qty * menu.getCarbs();

            double fats =
                    qty * menu.getFats();

            int calories =
                    qty * menu.getCalories();

            cartItemDAO.updateQuantity(

                    existingItem.getCartItemId(),

                    qty,

                    subtotal,

                    protein,

                    carbs,

                    fats,

                    calories);

        }

        else{

            CartItem item = new CartItem();

            item.setCartId(
                    cart.getCartId());

            item.setMenuId(menuId);

            item.setQuantity(1);

            item.setSubtotal(
                    menu.getPrice());

            item.setProtein(
                    menu.getProtein());

            item.setCarbs(
                    menu.getCarbs());

            item.setFats(
                    menu.getFats());

            item.setCalories(
                    menu.getCalories());

            cartItemDAO.addCartItem(item);

        }

        //---------------------------------------------------

        cart.setTotalAmount(
                cart.getTotalAmount()
                        + menu.getPrice());

        cart.setTotalProtein(
                cart.getTotalProtein()
                        + menu.getProtein());

        cart.setTotalCarbs(
                cart.getTotalCarbs()
                        + menu.getCarbs());

        cart.setTotalFats(
                cart.getTotalFats()
                        + menu.getFats());

        cart.setTotalCalories(
                cart.getTotalCalories()
                        + menu.getCalories());

        cartDAO.updateCart(cart);

        //---------------------------------------------------

        response.sendRedirect(
        		"RestaurantMenuServlet?restaurantId="
        		+menu.getRestaurantId()
        		+"#menuSection");

    }

}