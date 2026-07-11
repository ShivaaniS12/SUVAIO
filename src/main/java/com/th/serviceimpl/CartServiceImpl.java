package com.th.serviceimpl;

import java.util.List;

import com.th.dao.CartDAO;
import com.th.dao.CartItemDAO;
import com.th.daoimpl.CartDAOImpl;
import com.th.daoimpl.CartItemDAOImpl;
import com.th.model.Cart;
import com.th.model.CartItem;
import com.th.model.CartItemView;
import com.th.service.CartService;

public class CartServiceImpl
implements CartService{

    private CartDAO cartDAO =
            new CartDAOImpl();

    private CartItemDAO cartItemDAO =
            new CartItemDAOImpl();

    @Override
    public Cart getCartByUserId(int userId){

        return cartDAO.getCartByUserId(userId);

    }

    @Override
    public List<CartItemView> getCartItems(int cartId){

        return cartItemDAO.getCartItemDetails(cartId);

    }

    @Override
    public void removeItem(int cartItemId){

    }

    @Override
    public void increaseQuantity(int cartItemId){

    }

    @Override
    public void decreaseQuantity(int cartItemId){

    }

}