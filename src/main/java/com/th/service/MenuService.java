package com.th.service;

import java.util.List;
import com.th.model.Menu;

public interface MenuService {

    List<Menu> getAllMenuItems();
    
    List<Menu> getMenusByRestaurant(int restaurantId);

    boolean updateAvailability(
            int menuId,
            boolean available);
    
    void addMenu(Menu menu);
    
    Menu getMenu(int menuId);
    
    void updateMenu(Menu menu);
    
    List<Menu> searchMenuItems(
            int restaurantId,
            String keyword);
}