package com.th.service;

import java.util.List;

import com.th.model.Cart;
import com.th.model.CartItem;
import com.th.model.CartItemView;

public interface CartService {

    Cart getCartByUserId(int userId);

    List<CartItemView> getCartItems(int cartId);

    void removeItem(int cartItemId);

    void increaseQuantity(int cartItemId);

    void decreaseQuantity(int cartItemId);

}