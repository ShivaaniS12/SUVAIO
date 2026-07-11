package com.th.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.th.Utility.DBConnection;
import com.th.dao.CartDAO;
import com.th.model.Cart;

public class CartDAOImpl implements CartDAO {

    private Connection connection;

    private static final String INSERT_QUERY =
            "INSERT INTO cart(user_id,total_amount,total_protein,total_carbs,total_fats,total_calories) VALUES(?,?,?,?,?,?)";

    private static final String GET_CART_QUERY =
            "SELECT * FROM cart WHERE cart_id=?";

    private static final String GET_CART_BY_USER_QUERY =
            "SELECT * FROM cart WHERE user_id=?";

    private static final String GET_ALL_QUERY =
            "SELECT * FROM cart";

    private static final String UPDATE_QUERY =
            "UPDATE cart SET user_id=?, total_amount=?, total_protein=?, total_carbs=?, total_fats=?, total_calories=? WHERE cart_id=?";

    private static final String DELETE_QUERY =
            "DELETE FROM cart WHERE cart_id=?";
    
    

    public CartDAOImpl() {
        connection = DBConnection.getConnection();
    }

    @Override
    public void addCart(Cart cart) {

        try (PreparedStatement pstmt =
                     connection.prepareStatement(INSERT_QUERY)) {

            pstmt.setInt(1, cart.getUserId());
            pstmt.setDouble(2, cart.getTotalAmount());
            pstmt.setDouble(3, cart.getTotalProtein());
            pstmt.setDouble(4, cart.getTotalCarbs());
            pstmt.setDouble(5, cart.getTotalFats());
            pstmt.setInt(6, cart.getTotalCalories());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cart getCart(int cartId) {

        Cart cart = null;

        try (PreparedStatement pstmt =
                     connection.prepareStatement(GET_CART_QUERY)) {

            pstmt.setInt(1, cartId);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                cart = extractCart(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cart;
    }

    @Override
    public Cart getCartByUserId(int userId) {

        Cart cart = null;

        try (PreparedStatement pstmt =
                     connection.prepareStatement(GET_CART_BY_USER_QUERY)) {

            pstmt.setInt(1, userId);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                cart = extractCart(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cart;
    }

    @Override
    public List<Cart> getAllCarts() {

        List<Cart> carts = new ArrayList<>();

        try (PreparedStatement pstmt =
                     connection.prepareStatement(GET_ALL_QUERY)) {

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                carts.add(extractCart(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carts;
    }

    @Override
    public void updateCart(Cart cart) {

        try (PreparedStatement pstmt =
                     connection.prepareStatement(UPDATE_QUERY)) {

            pstmt.setInt(1, cart.getUserId());
            pstmt.setDouble(2, cart.getTotalAmount());
            pstmt.setDouble(3, cart.getTotalProtein());
            pstmt.setDouble(4, cart.getTotalCarbs());
            pstmt.setDouble(5, cart.getTotalFats());
            pstmt.setInt(6, cart.getTotalCalories());
            pstmt.setInt(7, cart.getCartId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCart(int cartId) {

        try (PreparedStatement pstmt =
                     connection.prepareStatement(DELETE_QUERY)) {

            pstmt.setInt(1, cartId);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Cart extractCart(ResultSet rs)
            throws SQLException {

        return new Cart(
                rs.getInt("cart_id"),
                rs.getInt("user_id"),
                rs.getDouble("total_amount"),
                rs.getDouble("total_protein"),
                rs.getDouble("total_carbs"),
                rs.getDouble("total_fats"),
                rs.getInt("total_calories")
        );
    }
}