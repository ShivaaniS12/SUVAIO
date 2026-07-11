package com.th.dao;

import java.util.List;

import com.th.model.Menu;

public interface MenuDAO {
	 void addMenu(Menu menu);

	    Menu getMenu(int menuId);

	    List<Menu> getAllMenus();

	    List<Menu> getMenusByRestaurant(int restaurantId);

	    void updateMenu(Menu menu);

	    void deleteMenu(int menuId);
	    
	    boolean updateAvailability(
	            int menuId,
	            boolean available);
	    int getTotalMenuItems(int restaurantId);

	    int getAvailableMenuItems(int restaurantId);

	    int getUnavailableMenuItems(int restaurantId);
	    
	    List<Menu> searchMenuItems(
	            int restaurantId,
	            String keyword);

}
