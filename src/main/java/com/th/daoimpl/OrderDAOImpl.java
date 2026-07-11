package com.th.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.th.Utility.DBConnection;
import com.th.dao.OrderDAO;
import com.th.model.AnalyticsData;
import com.th.model.Order;
import com.th.model.OrderHistoryView;

public class OrderDAOImpl implements OrderDAO {

    private static final Connection connection = DBConnection.getConnection();

    private static final String INSERT_QUERY =
    		"INSERT INTO orders(user_id,address_id,restaurant_id,agent_id,total_amount,total_protein,total_carbs,total_fats,total_calories,payment_mode,order_status) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
    private static final String GET_ORDER_QUERY =
            "SELECT * FROM orders WHERE order_id=?";

    private static final String GET_ALL_ORDERS_QUERY =
            "SELECT * FROM orders";

    private static final String GET_BY_USER_QUERY =
            "SELECT * FROM orders WHERE user_id=?";

    private static final String GET_BY_RESTAURANT_QUERY =
            "SELECT * FROM orders WHERE restaurant_id=?";

    private static final String GET_BY_AGENT_QUERY =
            "SELECT * FROM orders WHERE agent_id=?";

    private static final String UPDATE_QUERY =
    		"UPDATE orders SET user_id=?, address_id=?, restaurant_id=?, agent_id=?, total_amount=?, total_protein=?, total_carbs=?, total_fats=?, total_calories=?, payment_mode=?, order_status=? WHERE order_id=?";

    private static final String DELETE_QUERY =
            "DELETE FROM orders WHERE order_id=?";
    
    private static final String GET_ORDER_HISTORY_QUERY =
    	    "SELECT " +
    	    "o.order_id, " +
    	    "o.restaurant_id, " +
    	    "o.total_amount, " +
    	    "o.payment_mode, " +
    	    "o.order_status, " +
    	    "o.order_date, " +
    	    "r.name AS restaurant_name, " +
    	    "r.image_path " +
    	    "FROM orders o " +
    	    "JOIN restaurant r " +
    	    "ON o.restaurant_id = r.restaurant_id " +
    	    "WHERE o.user_id=? " +
    	    "ORDER BY o.order_date DESC";

   
    @Override
    public int addOrder(Order order) {

        try (PreparedStatement pstmt =
                     connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {

        	pstmt.setInt(1, order.getUserId());

        	pstmt.setInt(2, order.getAddressId());

        	pstmt.setInt(3, order.getRestaurantId());

        	if(order.getAgentId() == null){

        	    pstmt.setNull(4, Types.INTEGER);

        	}
        	else{

        	    pstmt.setInt(4, order.getAgentId());

        	}

        	pstmt.setDouble(5, order.getTotalAmount());

        	pstmt.setDouble(6, order.getTotalProtein());

        	pstmt.setDouble(7, order.getTotalCarbs());

        	pstmt.setDouble(8, order.getTotalFats());

        	pstmt.setInt(9, order.getTotalCalories());

        	pstmt.setString(10, order.getPaymentMode());

        	pstmt.setString(11, order.getOrderStatus());
            pstmt.executeUpdate();
            
            ResultSet rs =
                    pstmt.getGeneratedKeys();

            if(rs.next()) {

                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
		return 0;
    }

    @Override
    public Order getOrder(int orderId) {

        Order order = null;

        try (PreparedStatement pstmt =
                     connection.prepareStatement(GET_ORDER_QUERY)) {

            pstmt.setInt(1, orderId);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                order = extractOrder(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

    @Override
    public List<Order> getAllOrders() {

        List<Order> orders = new ArrayList<>();

        try (PreparedStatement pstmt =
                     connection.prepareStatement(GET_ALL_ORDERS_QUERY)) {

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                orders.add(extractOrder(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public List<Order> getOrdersByUser(int userId) {

        List<Order> orders = new ArrayList<>();

        try (PreparedStatement pstmt =
                     connection.prepareStatement(GET_BY_USER_QUERY)) {

            pstmt.setInt(1, userId);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                orders.add(extractOrder(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public List<Order> getOrdersByRestaurant(int restaurantId) {

        List<Order> orders = new ArrayList<>();

        try (PreparedStatement pstmt =
                     connection.prepareStatement(GET_BY_RESTAURANT_QUERY)) {

            pstmt.setInt(1, restaurantId);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                orders.add(extractOrder(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public List<Order> getOrdersByAgent(int agentId) {

        List<Order> orders = new ArrayList<>();

        try (PreparedStatement pstmt =
                     connection.prepareStatement(GET_BY_AGENT_QUERY)) {

            pstmt.setInt(1, agentId);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                orders.add(extractOrder(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public void updateOrder(Order order) {

        try (PreparedStatement pstmt =
                     connection.prepareStatement(UPDATE_QUERY)) {

        	pstmt.setInt(1, order.getUserId());

        	pstmt.setInt(2, order.getAddressId());

        	pstmt.setInt(3, order.getRestaurantId());

        	if(order.getAgentId()==null){

        	    pstmt.setNull(4, Types.INTEGER);

        	}
        	else{

        	    pstmt.setInt(4, order.getAgentId());

        	}

        	pstmt.setDouble(5, order.getTotalAmount());

        	pstmt.setDouble(6, order.getTotalProtein());

        	pstmt.setDouble(7, order.getTotalCarbs());

        	pstmt.setDouble(8, order.getTotalFats());

        	pstmt.setInt(9, order.getTotalCalories());

        	pstmt.setString(10, order.getPaymentMode());

        	pstmt.setString(11, order.getOrderStatus());

        	pstmt.setInt(12, order.getOrderId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(int orderId) {

        try (PreparedStatement pstmt =
                     connection.prepareStatement(DELETE_QUERY)) {

            pstmt.setInt(1, orderId);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Order extractOrder(ResultSet rs)
            throws SQLException {

        Order order = new Order(
                rs.getInt("order_id"),
                rs.getInt("user_id"),
                rs.getInt("restaurant_id"),
                (Integer) rs.getObject("agent_id"),
                rs.getDouble("total_amount"),
                rs.getDouble("total_protein"),
                rs.getDouble("total_carbs"),
                rs.getDouble("total_fats"),
                rs.getInt("total_calories"),
                rs.getString("payment_mode"),
                rs.getString("order_status"),
                rs.getTimestamp("order_date")
        );

        order.setAddressId(
                rs.getInt("address_id"));

        return order;
    }

    @Override
    public int getTodayOrderCount() {

        String query =
            "SELECT COUNT(*) FROM orders WHERE DATE(order_date)=CURDATE()";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(query);

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
    public double getTodayRevenue() {

        String query =
            "SELECT IFNULL(SUM(total_amount),0) FROM orders " +
            "WHERE DATE(order_date)=CURDATE() " +
            "AND order_status='DELIVERED'";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(query);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                return rs.getDouble(1);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public double getWeeklyRevenue() {

        String query =
            "SELECT IFNULL(SUM(total_amount),0) " +
            "FROM orders " +
            "WHERE order_date >= DATE_SUB(NOW(), INTERVAL 7 DAY) " +
            "AND order_status='DELIVERED'";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(query);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                return rs.getDouble(1);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public double getMonthlyRevenue() {

        String query =
            "SELECT IFNULL(SUM(total_amount),0) " +
            "FROM orders " +
            "WHERE MONTH(order_date)=MONTH(CURDATE()) " +
            "AND YEAR(order_date)=YEAR(CURDATE()) " +
            "AND order_status='DELIVERED'";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(query);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                return rs.getDouble(1);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return 0;
    }
    @Override
    public String getTopRestaurant() {

        String query =
            "SELECT r.name " +
            "FROM orders o " +
            "JOIN restaurant r " +
            "ON o.restaurant_id = r.restaurant_id " +
            "WHERE o.order_status='DELIVERED' " +
            "GROUP BY r.restaurant_id, r.name " +
            "ORDER BY SUM(o.total_amount) DESC " +
            "LIMIT 1";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(query);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                return rs.getString(1);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return "No Data Available";
    }
    
    @Override
    public List<AnalyticsData>
    getOrdersPerRestaurant() {

        List<AnalyticsData> list =
                new ArrayList<>();

        String query =
            "SELECT r.name, COUNT(o.order_id) " +
            "FROM orders o " +
            "JOIN restaurant r " +
            "ON o.restaurant_id=r.restaurant_id " +
            "GROUP BY r.restaurant_id, r.name";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(query);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                AnalyticsData data =
                        new AnalyticsData();

                data.setRestaurantName(
                        rs.getString(1));

                data.setOrderCount(
                        rs.getInt(2));

                list.add(data);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return list;
    }
    
    @Override
    public List<AnalyticsData>
    getRestaurantWiseRevenue() {

        List<AnalyticsData> list =
                new ArrayList<>();

        String query =
            "SELECT r.name, " +
            "IFNULL(SUM(o.total_amount),0) " +
            "FROM orders o " +
            "JOIN restaurant r " +
            "ON o.restaurant_id=r.restaurant_id " +
            "WHERE o.order_status='DELIVERED' " +
            "GROUP BY r.restaurant_id,r.name " +
            "ORDER BY SUM(o.total_amount) DESC";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(query);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                AnalyticsData data =
                        new AnalyticsData();

                data.setRestaurantName(
                        rs.getString(1));

                data.setRevenue(
                        rs.getDouble(2));

                list.add(data);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return list;
    }
    
    @Override
    public double getPreviousMonthRevenue() {

        String query =
            "SELECT IFNULL(SUM(total_amount),0) " +
            "FROM orders " +
            "WHERE MONTH(order_date)=MONTH(CURDATE())-1 " +
            "AND YEAR(order_date)=YEAR(CURDATE()) " +
            "AND order_status='DELIVERED'";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(query);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                return rs.getDouble(1);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return 0;
    }
    
    @Override
    public double getCurrentMonthRevenue() {

        String query =
            "SELECT IFNULL(SUM(total_amount),0) " +
            "FROM orders " +
            "WHERE MONTH(order_date)=MONTH(CURDATE()) " +
            "AND YEAR(order_date)=YEAR(CURDATE()) " +
            "AND order_status='DELIVERED'";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(query);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                return rs.getDouble(1);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return 0;
    }
    
    @Override
    public int getTodayOrderCountByRestaurant(
            int restaurantId) {

        String query =
            "SELECT COUNT(*) " +
            "FROM orders " +
            "WHERE restaurant_id=? " +
            "AND DATE(order_date)=CURDATE()";

        try {

            PreparedStatement ps =
                    connection.prepareStatement(
                            query);

            ps.setInt(1, restaurantId);

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
    public double getTodayRevenueByRestaurant(
            int restaurantId) {

        String query =
            "SELECT IFNULL(SUM(total_amount),0) " +
            "FROM orders " +
            "WHERE restaurant_id=? " +
            "AND DATE(order_date)=CURDATE() " +
            "AND order_status='DELIVERED'";

        try {

            PreparedStatement ps =
                    connection.prepareStatement(
                            query);

            ps.setInt(1, restaurantId);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                return rs.getDouble(1);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return 0;
    }
    
    @Override
    public double getWeeklyRevenueByRestaurant(
            int restaurantId) {

        String query =
            "SELECT IFNULL(SUM(total_amount),0) " +
            "FROM orders " +
            "WHERE restaurant_id=? " +
            "AND order_date >= DATE_SUB(NOW(), INTERVAL 7 DAY) " +
            "AND order_status='DELIVERED'";

        try {

            PreparedStatement ps =
                    connection.prepareStatement(
                            query);

            ps.setInt(1, restaurantId);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                return rs.getDouble(1);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public double getMonthlyRevenueByRestaurant(
            int restaurantId) {

        String query =
            "SELECT IFNULL(SUM(total_amount),0) " +
            "FROM orders " +
            "WHERE restaurant_id=? " +
            "AND MONTH(order_date)=MONTH(CURDATE()) " +
            "AND YEAR(order_date)=YEAR(CURDATE()) " +
            "AND order_status='DELIVERED'";

        try {

            PreparedStatement ps =
                    connection.prepareStatement(
                            query);

            ps.setInt(1, restaurantId);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                return rs.getDouble(1);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public List<Order> getRecentOrdersByRestaurant(
            int restaurantId) {

        List<Order> orders =
                new ArrayList<>();

        String query =
            "SELECT * FROM orders " +
            "WHERE restaurant_id=? " +
            "ORDER BY order_date DESC " +
            "LIMIT 5";

        try {

            PreparedStatement ps =
                    connection.prepareStatement(
                            query);

            ps.setInt(1, restaurantId);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                Order order =
                        new Order(
                            rs.getInt("order_id"),
                            rs.getInt("user_id"),
                            rs.getInt("restaurant_id"),
                            (Integer)rs.getObject("agent_id"),
                            rs.getDouble("total_amount"),
                            rs.getDouble("total_protein"),
                            rs.getDouble("total_carbs"),
                            rs.getDouble("total_fats"),
                            rs.getInt("total_calories"),
                            rs.getString("payment_mode"),
                            rs.getString("order_status"),
                            rs.getTimestamp("order_date")
                        );
                
                order.setAddressId(
                        rs.getInt("address_id"));


                orders.add(order);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }
        

        return orders;
    }
    @Override
    public String getMostOrderedItem(
            int restaurantId) {

    	String query =
    		    "SELECT m.item_name " +
    		    "FROM order_item oi " +
    		    "JOIN orders o ON oi.order_id=o.order_id " +
    		    "JOIN menu m ON oi.menu_id=m.menu_id " +
    		    "WHERE o.restaurant_id=? " +
    		    "GROUP BY m.item_name " +
    		    "ORDER BY SUM(oi.quantity) DESC " +
    		    "LIMIT 1";

        try {

            PreparedStatement ps =
                    connection.prepareStatement(
                            query);

            ps.setInt(1, restaurantId);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                return rs.getString(1);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return "No Orders Yet";
    }
    
    @Override
    public int getDeliveredOrdersCountByRestaurant(
            int restaurantId) {

        String query =
            "SELECT COUNT(*) " +
            "FROM orders " +
            "WHERE restaurant_id=? " +
            "AND order_status='DELIVERED'";

        try {

            PreparedStatement ps =
                    connection.prepareStatement(
                            query);

            ps.setInt(1, restaurantId);

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
    public double getAverageOrderValueByRestaurant(
            int restaurantId) {

        String query =
            "SELECT IFNULL(AVG(total_amount),0) " +
            "FROM orders " +
            "WHERE restaurant_id=? " +
            "AND order_status='DELIVERED'";

        try {

            PreparedStatement ps =
                    connection.prepareStatement(
                            query);

            ps.setInt(1, restaurantId);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                return rs.getDouble(1);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return 0;
    }
    
    @Override
    public int getTotalOrdersByRestaurant(
            int restaurantId) {

        String query =
            "SELECT COUNT(*) " +
            "FROM orders " +
            "WHERE restaurant_id=?";

        try {

            PreparedStatement ps =
                    connection.prepareStatement(
                            query);

            ps.setInt(1, restaurantId);

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
    public int getCancelledOrdersByRestaurant(
            int restaurantId) {

        String query =
            "SELECT COUNT(*) " +
            "FROM orders " +
            "WHERE restaurant_id=? " +
            "AND order_status='CANCELLED'";

        try {

            PreparedStatement ps =
                    connection.prepareStatement(
                            query);

            ps.setInt(1, restaurantId);

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
    public int getUniqueCustomersByRestaurant(
            int restaurantId) {

        String query =
            "SELECT COUNT(DISTINCT user_id) " +
            "FROM orders " +
            "WHERE restaurant_id=?";

        try {

            PreparedStatement ps =
                    connection.prepareStatement(
                            query);

            ps.setInt(1, restaurantId);

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
    public String getMostOrderedCategory(
            int restaurantId) {

        String query =
            "SELECT m.category " +
            "FROM order_item oi " +
            "JOIN orders o ON oi.order_id=o.order_id " +
            "JOIN menu m ON oi.menu_id=m.menu_id " +
            "WHERE o.restaurant_id=? " +
            "GROUP BY m.category " +
            "ORDER BY SUM(oi.quantity) DESC " +
            "LIMIT 1";

        try {

            PreparedStatement ps =
                    connection.prepareStatement(
                            query);

            ps.setInt(1, restaurantId);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                return rs.getString(1);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return "No Data";
    }
    
    @Override
    public List<Order> getAssignedOrdersByAgent(
            int agentId) {

        List<Order> orders =
                new ArrayList<>();

        String query =
            "SELECT * FROM orders " +
            "WHERE agent_id=? " +
            "AND order_status='AGENT_ASSIGNED' " +
            "ORDER BY order_date DESC";

        try {

            PreparedStatement ps =
                    connection.prepareStatement(
                            query);

            ps.setInt(1, agentId);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

            	orders.add(
            		    extractOrder(rs));
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return orders;
    }
    
    @Override
    public int getAssignedOrderCount(
            int agentId) {

        String query =
            "SELECT COUNT(*) " +
            "FROM orders " +
            "WHERE agent_id=? " +
            "AND order_status='AGENT_ASSIGNED'";

        try {

            PreparedStatement ps =
                    connection.prepareStatement(
                            query);

            ps.setInt(1, agentId);

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
    public int getDeliveredOrderCount(
            int agentId) {

        String query =
            "SELECT COUNT(*) " +
            "FROM orders " +
            "WHERE agent_id=? " +
            "AND order_status='DELIVERED'";

        try {

            PreparedStatement ps =
                    connection.prepareStatement(
                            query);

            ps.setInt(1, agentId);

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
    public int getPickedUpOrderCount(
            int agentId) {

        String query =
            "SELECT COUNT(*) " +
            "FROM orders " +
            "WHERE agent_id=? " +
            "AND order_status='PICKED_UP'";

        try {

            PreparedStatement ps =
                    connection.prepareStatement(
                            query);

            ps.setInt(1, agentId);

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
    public List<Order> getPickedUpOrdersByAgent(
            int agentId) {

        List<Order> orders =
                new ArrayList<>();

        String query =
            "SELECT * FROM orders " +
            "WHERE agent_id=? " +
            "AND order_status='PICKED_UP'";

        try {

            PreparedStatement ps =
                    connection.prepareStatement(
                            query);

            ps.setInt(1, agentId);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                orders.add(
                        extractOrder(rs));
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return orders;
    }
    
    @Override
    public List<Order> getOutForDeliveryOrdersByAgent(
            int agentId) {

        List<Order> orders =
                new ArrayList<>();

        String query =
            "SELECT * FROM orders " +
            "WHERE agent_id=? " +
            "AND order_status='OUT_FOR_DELIVERY'";

        try {

            PreparedStatement ps =
                    connection.prepareStatement(query);

            ps.setInt(1, agentId);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                orders.add(
                        extractOrder(rs)); // or your helper method name
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return orders;
    }
    
    @Override
    public List<Order> getDeliveredOrdersByAgent(
            int agentId) {

        List<Order> orders =
                new ArrayList<>();

        String query =
            "SELECT * FROM orders " +
            "WHERE agent_id=? " +
            "AND order_status='DELIVERED' " +
            "ORDER BY order_date DESC";

        try {

            PreparedStatement ps =
                    connection.prepareStatement(
                            query);

            ps.setInt(1, agentId);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                orders.add(
                        extractOrder(rs));
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return orders;
    }
    
    @Override
    public int getTotalDeliveriesByAgent(
            int agentId) {

        String query =
            "SELECT COUNT(*) " +
            "FROM orders " +
            "WHERE agent_id=? " +
            "AND order_status='DELIVERED'";

        try {

            PreparedStatement ps =
                    connection.prepareStatement(
                            query);

            ps.setInt(1, agentId);

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
    public int getTodayDeliveriesByAgent(
            int agentId) {

        String query =
            "SELECT COUNT(*) " +
            "FROM orders " +
            "WHERE agent_id=? " +
            "AND order_status='DELIVERED' " +
            "AND DATE(order_date)=CURDATE()";

        try {

            PreparedStatement ps =
                    connection.prepareStatement(
                            query);

            ps.setInt(1, agentId);

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
    public List<Order> getUnassignedOrders() {

        List<Order> orders =
                new ArrayList<>();

        String query =
            "SELECT * FROM orders " +
            "WHERE agent_id IS NULL " +
            "AND order_status='READY_FOR_PICKUP'";

        try {

            PreparedStatement ps =
                    connection.prepareStatement(
                            query);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                orders.add(
                        extractOrder(rs));
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return orders;
    }
    
    @Override
    public List<OrderHistoryView> getOrderHistoryByUser(int userId) {

        List<OrderHistoryView> orders =
                new ArrayList<>();

        try {

            PreparedStatement ps =
                    connection.prepareStatement(
                            GET_ORDER_HISTORY_QUERY);

            ps.setInt(1, userId);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()){

                OrderHistoryView order =
                        new OrderHistoryView();

                order.setOrderId(
                        rs.getInt("order_id"));

                order.setRestaurantId(
                        rs.getInt("restaurant_id"));

                order.setRestaurantName(
                        rs.getString("restaurant_name"));

                order.setRestaurantImage(
                        rs.getString("image_path"));

                order.setTotalAmount(
                        rs.getDouble("total_amount"));

                order.setPaymentMode(
                        rs.getString("payment_mode"));

                order.setOrderStatus(
                        rs.getString("order_status"));

                order.setOrderDate(
                        rs.getTimestamp("order_date"));
                
                

                orders.add(order);

            }

        } catch(Exception e){

            e.printStackTrace();

        }
        

        return orders;

    }
    
}