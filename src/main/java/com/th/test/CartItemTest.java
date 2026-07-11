package com.th.test;

import java.util.List;

import com.th.daoimpl.CartItemDAOImpl;
import com.th.model.CartItem;

public class CartItemTest {

    public static void main(String[] args) {

        CartItemDAOImpl dao = new CartItemDAOImpl();

        // ==========================
        // INSERT TEST
        // ==========================
        System.out.println("===== INSERT TEST =====");

        CartItem item = new CartItem(
                0,
                1,      // cart_id
                1,      // menu_id
                2,      // quantity
                598.00, // subtotal

                24.00,  // protein
                72.00,  // carbs
                20.00,  // fats
                640      // calories
        );

        dao.addCartItem(item);

        System.out.println("Cart Item Added Successfully");


        // ==========================
        // GET CART ITEM BY ID
        // ==========================
        System.out.println("\n===== GET CART ITEM =====");

        CartItem fetchedItem =
                dao.getCartItem(1);

        if(fetchedItem != null) {
            System.out.println(fetchedItem);
        }
        else {
            System.out.println("Cart Item Not Found");
        }


        // ==========================
        // GET ITEMS BY CART ID
        // ==========================
        System.out.println("\n===== ITEMS OF CART =====");

        List<CartItem> cartItems =
                dao.getCartItemsByCartId(1);

        for(CartItem c : cartItems) {
            System.out.println(c);
        }


        // ==========================
        // GET ALL ITEMS
        // ==========================
        System.out.println("\n===== ALL CART ITEMS =====");

        List<CartItem> allItems =
                dao.getAllCartItems();

        for(CartItem c : allItems) {
            System.out.println(c);
        }


        // ==========================
        // UPDATE TEST
        // ==========================
        System.out.println("\n===== UPDATE TEST =====");

        CartItem updateItem =
                dao.getCartItem(1);

        if(updateItem != null) {

            updateItem.setQuantity(3);

            updateItem.setSubtotal(897.00);

            updateItem.setProtein(36.00);

            updateItem.setCarbs(108.00);

            updateItem.setFats(30.00);

            updateItem.setCalories(960);

            dao.updateCartItem(updateItem);

            System.out.println("Cart Item Updated Successfully");

            System.out.println(
                    dao.getCartItem(1)
            );
        }


        // ==========================
        // DELETE TEST
        // ==========================
        System.out.println("\n===== DELETE TEST =====");

        dao.deleteCartItem(2);

        System.out.println("Cart Item Deleted Successfully");


        // ==========================
        // FINAL LIST
        // ==========================
        System.out.println("\n===== FINAL CART ITEM LIST =====");

        List<CartItem> finalItems =
                dao.getAllCartItems();

        for(CartItem c : finalItems) {
            System.out.println(c);
        }
    }
}