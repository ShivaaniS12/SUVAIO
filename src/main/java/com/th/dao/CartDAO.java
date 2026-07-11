package com.th.dao;

import java.util.List;
import com.th.model.Cart;

public interface CartDAO {

    void addCart(Cart cart);

    Cart getCart(int cartId);

    Cart getCartByUserId(int userId);

    List<Cart> getAllCarts();

    void updateCart(Cart cart);

    void deleteCart(int cartId);
}