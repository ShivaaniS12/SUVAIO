package com.th.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.th.Utility.DBConnection;
import com.th.dao.OrderItemDAO;
import com.th.model.OrderItem;

public class OrderItemDAOImpl implements OrderItemDAO {

    private Connection connection;

    private static final String INSERT_QUERY =
            "INSERT INTO order_item(order_id,menu_id,quantity,subtotal,protein,carbs,fats,calories) VALUES(?,?,?,?,?,?,?,?)";

    private static final String GET_QUERY =
            "SELECT * FROM order_item WHERE order_item_id=?";

    private static final String GET_ALL_QUERY =
            "SELECT * FROM order_item";

    private static final String GET_BY_ORDER_QUERY =
            "SELECT * FROM order_item WHERE order_id=?";

    private static final String UPDATE_QUERY =
            "UPDATE order_item SET order_id=?,menu_id=?,quantity=?,subtotal=?,protein=?,carbs=?,fats=?,calories=? WHERE order_item_id=?";

    private static final String DELETE_QUERY =
            "DELETE FROM order_item WHERE order_item_id=?";

    public OrderItemDAOImpl() {
        connection = DBConnection.getConnection();
    }

    @Override
    public void addOrderItem(OrderItem item) {

        try (PreparedStatement pstmt =
                     connection.prepareStatement(INSERT_QUERY)) {

            pstmt.setInt(1, item.getOrderId());
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
    public OrderItem getOrderItem(int orderItemId) {

        OrderItem item = null;

        try (PreparedStatement pstmt =
                     connection.prepareStatement(GET_QUERY)) {

            pstmt.setInt(1, orderItemId);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                item = extractOrderItem(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return item;
    }

    @Override
    public List<OrderItem> getAllOrderItems() {

        List<OrderItem> items = new ArrayList<>();

        try (PreparedStatement pstmt =
                     connection.prepareStatement(GET_ALL_QUERY)) {

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                items.add(extractOrderItem(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {

        List<OrderItem> items = new ArrayList<>();

        try (PreparedStatement pstmt =
                     connection.prepareStatement(GET_BY_ORDER_QUERY)) {

            pstmt.setInt(1, orderId);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                items.add(extractOrderItem(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    @Override
    public void updateOrderItem(OrderItem item) {

        try (PreparedStatement pstmt =
                     connection.prepareStatement(UPDATE_QUERY)) {

            pstmt.setInt(1, item.getOrderId());
            pstmt.setInt(2, item.getMenuId());
            pstmt.setInt(3, item.getQuantity());
            pstmt.setDouble(4, item.getSubtotal());
            pstmt.setDouble(5, item.getProtein());
            pstmt.setDouble(6, item.getCarbs());
            pstmt.setDouble(7, item.getFats());
            pstmt.setInt(8, item.getCalories());

            pstmt.setInt(9, item.getOrderItemId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrderItem(int orderItemId) {

        try (PreparedStatement pstmt =
                     connection.prepareStatement(DELETE_QUERY)) {

            pstmt.setInt(1, orderItemId);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private OrderItem extractOrderItem(ResultSet rs)
            throws SQLException {

        return new OrderItem(
                rs.getInt("order_item_id"),
                rs.getInt("order_id"),
                rs.getInt("menu_id"),
                rs.getInt("quantity"),
                rs.getDouble("subtotal"),
                rs.getDouble("protein"),
                rs.getDouble("carbs"),
                rs.getDouble("fats"),
                rs.getInt("calories")
        );
    }
}