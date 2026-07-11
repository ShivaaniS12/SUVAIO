package com.th.test;

import java.util.List;

import com.th.daoimpl.CartDAOImpl;
import com.th.model.Cart;

public class CartTest {

    public static void main(String[] args) {

        CartDAOImpl dao = new CartDAOImpl();

        // ==========================
        // INSERT TEST
        // ==========================
        System.out.println("===== INSERT TEST =====");

        Cart cart = new Cart(
                0,
                1,
                499.00,
                45.00,
                60.00,
                18.00,
                750
        );

        dao.addCart(cart);

        System.out.println("Cart Added Successfully");


        // ==========================
        // GET CART BY ID
        // ==========================
        System.out.println("\n===== GET CART BY ID =====");

        Cart fetchedCart = dao.getCart(1);

        if (fetchedCart != null) {
            System.out.println(fetchedCart);
        } else {
            System.out.println("Cart Not Found");
        }


        // ==========================
        // GET CART BY USER ID
        // ==========================
        System.out.println("\n===== GET CART BY USER ID =====");

        Cart userCart = dao.getCartByUserId(1);

        if (userCart != null) {
            System.out.println(userCart);
        } else {
            System.out.println("No Cart Found For User");
        }


        // ==========================
        // GET ALL CARTS
        // ==========================
        System.out.println("\n===== GET ALL CARTS =====");

        List<Cart> carts = dao.getAllCarts();

        for (Cart c : carts) {
            System.out.println(c);
        }


        // ==========================
        // UPDATE TEST
        // ==========================
        System.out.println("\n===== UPDATE TEST =====");

        Cart updateCart = dao.getCartByUserId(1);

        if (updateCart != null) {

            updateCart.setTotalAmount(899.00);
            updateCart.setTotalProtein(75.00);
            updateCart.setTotalCarbs(120.00);
            updateCart.setTotalFats(30.00);
            updateCart.setTotalCalories(1450);

            dao.updateCart(updateCart);

            System.out.println("Cart Updated Successfully");

            System.out.println(
                    dao.getCart(updateCart.getCartId())
            );
        }


        // ==========================
        // DELETE TEST
        // ==========================
        System.out.println("\n===== DELETE TEST =====");

        dao.deleteCart(2);

        System.out.println("Cart Deleted Successfully");


        // ==========================
        // FINAL CART LIST
        // ==========================
        System.out.println("\n===== FINAL CART LIST =====");

        List<Cart> finalCarts = dao.getAllCarts();

        for (Cart c : finalCarts) {
            System.out.println(c);
        }
    }
}