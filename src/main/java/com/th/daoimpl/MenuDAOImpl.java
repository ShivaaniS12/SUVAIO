package com.th.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.th.Utility.DBConnection;
import com.th.dao.MenuDAO;
import com.th.model.Menu;

public class MenuDAOImpl implements MenuDAO {

    private Connection connection = DBConnection.getConnection();

    private static final String INSERT_QUERY =
            "INSERT INTO menu(restaurant_id,item_name,description,category,price,protein,carbs,fats,calories,rating,is_available,image_path) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

    private static final String GET_MENU_QUERY =
            "SELECT * FROM menu WHERE menu_id=?";

    private static final String GET_ALL_MENUS_QUERY =
            "SELECT * FROM menu";

    private static final String GET_BY_RESTAURANT_QUERY =
            "SELECT * FROM menu WHERE restaurant_id=?";

    private static final String UPDATE_QUERY =
            "UPDATE menu SET restaurant_id=?, item_name=?, description=?, category=?, price=?, protein=?, carbs=?, fats=?, calories=?, rating=?, is_available=?, image_path=? WHERE menu_id=?";

    private static final String DELETE_QUERY =
            "DELETE FROM menu WHERE menu_id=?";


    

    @Override
    public void addMenu(Menu menu) {

        try (PreparedStatement pstmt =
                     connection.prepareStatement(INSERT_QUERY)) {

            pstmt.setInt(1, menu.getRestaurantId());
            pstmt.setString(2, menu.getItemName());
            pstmt.setString(3, menu.getDescription());
            pstmt.setString(4, menu.getCategory());
            pstmt.setDouble(5, menu.getPrice());
            pstmt.setDouble(6, menu.getProtein());
            pstmt.setDouble(7, menu.getCarbs());
            pstmt.setDouble(8, menu.getFats());
            pstmt.setInt(9, menu.getCalories());
            pstmt.setDouble(10, menu.getRating());
            pstmt.setBoolean(11, menu.isAvailable());
            pstmt.setString(12, menu.getImagePath());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Menu getMenu(int menuId) {

        Menu menu = null;

        try (PreparedStatement pstmt =
                     connection.prepareStatement(GET_MENU_QUERY)) {

            pstmt.setInt(1, menuId);

            ResultSet res = pstmt.executeQuery();

            if (res.next()) {

                menu = extractMenuFromResultSet(res);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return menu;
    }

    @Override
    public List<Menu> getAllMenus() {

    	   List<Menu> menuList =
    	            new ArrayList<>();

    	    String query =
    	            "SELECT * FROM menu";

    	    try {

    	        Connection con =
    	                DBConnection.getConnection();

    	        PreparedStatement ps =
    	                con.prepareStatement(query);

    	        ResultSet rs =
    	                ps.executeQuery();

    	        while(rs.next()) {

    	            Menu menu =
    	                    new Menu();

    	            menu.setMenuId(
    	                    rs.getInt("menu_id"));

    	            menu.setRestaurantId(
    	                    rs.getInt("restaurant_id"));

    	            menu.setItemName(
    	                    rs.getString("item_name"));

    	            menu.setCategory(
    	                    rs.getString("category"));

    	            menu.setPrice(
    	                    rs.getDouble("price"));

    	            menu.setCalories(
    	                    rs.getInt("calories"));

    	            menu.setAvailable(
    	                    rs.getBoolean(
    	                            "is_available"));

    	            menuList.add(menu);
    	        }

    	    } catch(Exception e) {

    	        e.printStackTrace();
    	    }

    	    return menuList;
    }

    @Override
    public List<Menu> getMenusByRestaurant(int restaurantId) {

        List<Menu> menus = new ArrayList<>();

        try (PreparedStatement pstmt =
                     connection.prepareStatement(GET_BY_RESTAURANT_QUERY)) {

            pstmt.setInt(1, restaurantId);

            ResultSet res = pstmt.executeQuery();

            while (res.next()) {

                menus.add(extractMenuFromResultSet(res));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return menus;
    }

    @Override
    public void updateMenu(Menu menu) {

        try (PreparedStatement pstmt =
                     connection.prepareStatement(UPDATE_QUERY)) {

            pstmt.setInt(1, menu.getRestaurantId());
            pstmt.setString(2, menu.getItemName());
            pstmt.setString(3, menu.getDescription());
            pstmt.setString(4, menu.getCategory());
            pstmt.setDouble(5, menu.getPrice());
            pstmt.setDouble(6, menu.getProtein());
            pstmt.setDouble(7, menu.getCarbs());
            pstmt.setDouble(8, menu.getFats());
            pstmt.setInt(9, menu.getCalories());
            pstmt.setDouble(10, menu.getRating());
            pstmt.setBoolean(11, menu.isAvailable());
            pstmt.setString(12, menu.getImagePath());
            pstmt.setInt(13, menu.getMenuId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMenu(int menuId) {

        try (PreparedStatement pstmt =
                     connection.prepareStatement(DELETE_QUERY)) {

            pstmt.setInt(1, menuId);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Helper Method
    private Menu extractMenuFromResultSet(ResultSet res)
            throws SQLException {

        return new Menu(
                res.getInt("menu_id"),
                res.getInt("restaurant_id"),
                res.getString("item_name"),
                res.getString("description"),
                res.getString("category"),
                res.getDouble("price"),
                res.getDouble("protein"),
                res.getDouble("carbs"),
                res.getDouble("fats"),
                res.getInt("calories"),
                res.getDouble("rating"),
                res.getBoolean("is_available"),
                res.getString("image_path")
        );
    }

    @Override
    public boolean updateAvailability(
            int menuId,
            boolean available) {

        String query =
            "UPDATE menu SET is_available=? WHERE menu_id=?";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setBoolean(1, available);
            ps.setInt(2, menuId);

            return ps.executeUpdate() > 0;

        } catch(Exception e) {

            e.printStackTrace();
        }

        return false;
    }
    
    @Override
    public int getTotalMenuItems(int restaurantId) {

        String query =
                "SELECT COUNT(*) FROM menu WHERE restaurant_id=?";

        try {

            PreparedStatement ps =
                    connection.prepareStatement(query);

            ps.setInt(1, restaurantId);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return rs.getInt(1);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
    
    @Override
    public int getAvailableMenuItems(int restaurantId) {

        String query =
                "SELECT COUNT(*) FROM menu WHERE restaurant_id=? AND is_available=1";

        try {

            PreparedStatement ps =
                    connection.prepareStatement(query);

            ps.setInt(1, restaurantId);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return rs.getInt(1);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
    
    @Override
    public int getUnavailableMenuItems(int restaurantId) {

        String query =
                "SELECT COUNT(*) FROM menu WHERE restaurant_id=? AND is_available=0";

        try {

            PreparedStatement ps =
                    connection.prepareStatement(query);

            ps.setInt(1, restaurantId);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return rs.getInt(1);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
    
    @Override
    public List<Menu> searchMenuItems(
            int restaurantId,
            String keyword) {

        List<Menu> menuList =
                new ArrayList<>();

        String query =
                "SELECT * FROM menu " +
                "WHERE restaurant_id=? " +
                "AND item_name LIKE ?";

        try {

            PreparedStatement ps =
                    connection.prepareStatement(query);

            ps.setInt(1, restaurantId);

            ps.setString(2,
                    "%" + keyword + "%");

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                menuList.add(
                        extractMenuFromResultSet(rs));
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return menuList;
    }
}