package com.th.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.th.Utility.DBConnection;
import com.th.dao.CartItemDAO;
import com.th.model.CartItem;
import com.th.model.CartItemView;

public class CartItemDAOImpl implements CartItemDAO {

    private static final Connection connection = DBConnection.getConnection();

    private static final String INSERT_QUERY =
            "INSERT INTO cart_item(cart_id,menu_id,quantity,subtotal,protein,carbs,fats,calories) VALUES(?,?,?,?,?,?,?,?)";

    private static final String GET_QUERY =
            "SELECT * FROM cart_item WHERE cart_item_id=?";

    private static final String GET_BY_CART_QUERY =
            "SELECT * FROM cart_item WHERE cart_id=?";

    private static final String GET_ALL_QUERY =
            "SELECT * FROM cart_item";

    private static final String UPDATE_QUERY =
            "UPDATE cart_item SET cart_id=?,menu_id=?,quantity=?,subtotal=?,protein=?,carbs=?,fats=?,calories=? WHERE cart_item_id=?";

    private static final String DELETE_QUERY =
            "DELETE FROM cart_item WHERE cart_item_id=?";
    
    private static final String GET_BY_MENU =
    		"SELECT * FROM cart_item WHERE cart_id=? AND menu_id=?";

    		private static final String UPDATE_QUANTITY =
    		"UPDATE cart_item SET quantity=?,subtotal=?,protein=?,carbs=?,fats=?,calories=? WHERE cart_item_id=?";
    		
    		private static final String COUNT_ITEMS =
    				"SELECT SUM(quantity) FROM cart_item WHERE cart_id=?";
    		
    		private static final String DELETE_BY_MENU =
    				"DELETE FROM cart_item WHERE cart_id=? AND menu_id=?";
    		private static final String GET_CART_DETAILS =
    		        "SELECT " +
    		        "ci.cart_item_id," +
    		        "ci.menu_id," +
    		        "ci.quantity," +
    		        "ci.subtotal," +
    		        "ci.protein," +
    		        "ci.carbs," +
    		        "ci.fats," +
    		        "ci.calories," +

    		        "m.restaurant_id," +          // <-- ADD THIS
    		        "m.item_name," +
    		        "m.image_path," +
    		        "m.price," +

    		        "r.name AS restaurant_name " +

    		        "FROM cart_item ci " +

    		        "JOIN menu m " +
    		        "ON ci.menu_id=m.menu_id " +

    		        "JOIN restaurant r " +
    		        "ON m.restaurant_id=r.restaurant_id " +

    		        "WHERE ci.cart_id=?";
   
    @Override
    public void addCartItem(CartItem item) {

        try (PreparedStatement pstmt =
                     connection.prepareStatement(INSERT_QUERY)) {

            pstmt.setInt(1, item.getCartId());
            pstmt.setInt(2, item.getMenuId());
            pstmt.setInt(3, item.getQuantity());
            pstmt.setDouble(4, item.getSubtotal());
            pstmt.setDouble(5, item.getProtein());
            pstmt.setDouble(6, item.getCarbs());
            pstmt.setDouble(7, item.getFats());
            pstmt.setInt(8, item.getCalories());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CartItem getCartItem(int cartItemId) {

        CartItem item = null;

        try (PreparedStatement pstmt =
                     connection.prepareStatement(GET_QUERY)) {

            pstmt.setInt(1, cartItemId);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                item = extractCartItem(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return item;
    }

    @Override
    public List<CartItem> getCartItemsByCartId(int cartId) {

        List<CartItem> items = new ArrayList<>();

        try (PreparedStatement pstmt =
                     connection.prepareStatement(GET_BY_CART_QUERY)) {

            pstmt.setInt(1, cartId);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                items.add(extractCartItem(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    @Override
    public List<CartItem> getAllCartItems() {

        List<CartItem> items = new ArrayList<>();

        try (PreparedStatement pstmt =
                     connection.prepareStatement(GET_ALL_QUERY)) {

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                items.add(extractCartItem(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    @Override
    public void updateCartItem(CartItem item) {

        try (PreparedStatement pstmt =
                     connection.prepareStatement(UPDATE_QUERY)) {

            pstmt.setInt(1, item.getCartId());
            pstmt.setInt(2, item.getMenuId());
            pstmt.setInt(3, item.getQuantity());
            pstmt.setDouble(4, item.getSubtotal());
            pstmt.setDouble(5, item.getProtein());
            pstmt.setDouble(6, item.getCarbs());
            pstmt.setDouble(7, item.getFats());
            pstmt.setInt(8, item.getCalories());
            pstmt.setInt(9, item.getCartItemId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCartItem(int cartItemId) {

        try (PreparedStatement pstmt =
                     connection.prepareStatement(DELETE_QUERY)) {

            pstmt.setInt(1, cartItemId);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private CartItem extractCartItem(ResultSet rs)
            throws SQLException {

        return new CartItem(
                rs.getInt("cart_item_id"),
                rs.getInt("cart_id"),
                rs.getInt("menu_id"),
                rs.getInt("quantity"),
                rs.getDouble("subtotal"),
                rs.getDouble("protein"),
                rs.getDouble("carbs"),
                rs.getDouble("fats"),
                rs.getInt("calories")
        );
    }
    
    @Override
    public CartItem getCartItemByMenu(
            int cartId,
            int menuId) {

        try{

            PreparedStatement ps =
            connection.prepareStatement(GET_BY_MENU);

            ps.setInt(1, cartId);
            ps.setInt(2, menuId);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                return extractCartItem(rs);
            }

        }catch(Exception e){

            e.printStackTrace();
        }

        return null;
    }
    
    @Override
    public void updateQuantity(

            int cartItemId,

            int quantity,

            double subtotal,

            double protein,

            double carbs,

            double fats,

            int calories) {

        try{

            PreparedStatement ps =
            connection.prepareStatement(UPDATE_QUANTITY);

            ps.setInt(1, quantity);
            ps.setDouble(2, subtotal);
            ps.setDouble(3, protein);
            ps.setDouble(4, carbs);
            ps.setDouble(5, fats);
            ps.setInt(6, calories);
            ps.setInt(7, cartItemId);

            ps.executeUpdate();

        }catch(Exception e){

            e.printStackTrace();
        }

    }

    @Override
    public int getCartItemCount(int cartId) {

        try {

            PreparedStatement ps =
                    connection.prepareStatement(COUNT_ITEMS);

            ps.setInt(1, cartId);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                return rs.getInt(1);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return 0;
    }
    
    @Override
    public CartItem getCartItemById(int cartItemId){

        return getCartItem(cartItemId);

    }

    @Override
    public void deleteByCartAndMenu(int cartId,int menuId){

        try{

            PreparedStatement ps=
            connection.prepareStatement(DELETE_BY_MENU);

            ps.setInt(1,cartId);
            ps.setInt(2,menuId);

            ps.executeUpdate();

        }catch(Exception e){

            e.printStackTrace();

        }

    }
    
    @Override
    public List<CartItemView> getCartItemDetails(
            int cartId){

        List<CartItemView> list =
                new ArrayList<>();

        try{

            PreparedStatement ps =
            connection.prepareStatement(
                    GET_CART_DETAILS);

            ps.setInt(1,cartId);

            ResultSet rs =
            ps.executeQuery();

            while(rs.next()){

                CartItemView item =
                        new CartItemView();

                item.setCartItemId(
                        rs.getInt("cart_item_id"));

                item.setMenuId(
                        rs.getInt("menu_id"));
                
                item.setRestaurantId(
                        rs.getInt("restaurant_id"));

                item.setItemName(
                        rs.getString("item_name"));

                item.setRestaurantName(
                        rs.getString("restaurant_name"));

                item.setImagePath(
                        rs.getString("image_path"));

                item.setPrice(
                        rs.getDouble("price"));

                item.setQuantity(
                        rs.getInt("quantity"));

                item.setSubtotal(
                        rs.getDouble("subtotal"));

                item.setProtein(
                        rs.getDouble("protein"));

                item.setCarbs(
                        rs.getDouble("carbs"));

                item.setFats(
                        rs.getDouble("fats"));

                item.setCalories(
                        rs.getInt("calories"));

                list.add(item);

            }

        }catch(Exception e){

            e.printStackTrace();

        }

        return list;

    }
}