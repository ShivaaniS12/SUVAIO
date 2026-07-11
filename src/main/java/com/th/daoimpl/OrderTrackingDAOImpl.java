package com.th.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.th.Utility.DBConnection;
import com.th.dao.OrderTrackingDAO;
import com.th.model.OrderTracking;

public class OrderTrackingDAOImpl implements OrderTrackingDAO {

    private Connection connection;

    private static final String INSERT_QUERY =
            "INSERT INTO order_tracking(order_id,status,remarks,updated_by) VALUES(?,?,?,?)";

    private static final String GET_QUERY =
            "SELECT * FROM order_tracking WHERE tracking_id=?";

    private static final String GET_ALL_QUERY =
            "SELECT * FROM order_tracking";

    private static final String GET_BY_ORDER_QUERY =
            "SELECT * FROM order_tracking WHERE order_id=? ORDER BY updated_time";

    private static final String UPDATE_QUERY =
            "UPDATE order_tracking SET order_id=?, status=?, remarks=?, updated_by=? WHERE tracking_id=?";

    private static final String DELETE_QUERY =
            "DELETE FROM order_tracking WHERE tracking_id=?";

    public OrderTrackingDAOImpl() {
        connection = DBConnection.getConnection();
    }

    @Override
    public void addTracking(OrderTracking tracking) {

        try (PreparedStatement pstmt =
                     connection.prepareStatement(INSERT_QUERY)) {

            pstmt.setInt(1, tracking.getOrderId());
            pstmt.setString(2, tracking.getStatus());
            pstmt.setString(3, tracking.getRemarks());
            pstmt.setString(4, tracking.getUpdatedBy());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrderTracking getTracking(int trackingId) {

        OrderTracking tracking = null;

        try (PreparedStatement pstmt =
                     connection.prepareStatement(GET_QUERY)) {

            pstmt.setInt(1, trackingId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                tracking = extractTracking(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tracking;
    }

    @Override
    public List<OrderTracking> getAllTracking() {

        List<OrderTracking> trackingList = new ArrayList<>();

        try (PreparedStatement pstmt =
                     connection.prepareStatement(GET_ALL_QUERY)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                trackingList.add(extractTracking(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return trackingList;
    }

    @Override
    public List<OrderTracking> getTrackingByOrderId(int orderId) {

        List<OrderTracking> trackingList = new ArrayList<>();

        try (PreparedStatement pstmt =
                     connection.prepareStatement(GET_BY_ORDER_QUERY)) {

            pstmt.setInt(1, orderId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                trackingList.add(extractTracking(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return trackingList;
    }

    @Override
    public void updateTracking(OrderTracking tracking) {

        try (PreparedStatement pstmt =
                     connection.prepareStatement(UPDATE_QUERY)) {

            pstmt.setInt(1, tracking.getOrderId());
            pstmt.setString(2, tracking.getStatus());
            pstmt.setString(3, tracking.getRemarks());
            pstmt.setString(4, tracking.getUpdatedBy());
            pstmt.setInt(5, tracking.getTrackingId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTracking(int trackingId) {

        try (PreparedStatement pstmt =
                     connection.prepareStatement(DELETE_QUERY)) {

            pstmt.setInt(1, trackingId);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private OrderTracking extractTracking(ResultSet rs)
            throws SQLException {

        return new OrderTracking(
                rs.getInt("tracking_id"),
                rs.getInt("order_id"),
                rs.getString("status"),
                rs.getString("remarks"),
                rs.getString("updated_by"),
                rs.getTimestamp("updated_time")
        );
    }
}