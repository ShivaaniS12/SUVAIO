package com.th.serviceimpl;

import java.util.List;

import com.th.dao.MenuDAO;
import com.th.daoimpl.MenuDAOImpl;
import com.th.model.Menu;
import com.th.service.MenuService;

public class MenuServiceImpl
        implements MenuService {

    private MenuDAO menuDao;

    public MenuServiceImpl() {

        menuDao =
                new MenuDAOImpl();
    }

    @Override
    public List<Menu> getAllMenuItems() {

        return menuDao.getAllMenus();
    }

    @Override
    public boolean updateAvailability(
            int menuId,
            boolean available) {

        return menuDao.updateAvailability(
                menuId,
                available);
    }
    @Override
    public List<Menu> getMenusByRestaurant(
            int restaurantId) {

        return menuDao.getMenusByRestaurant(
                restaurantId);
    }
    
    @Override
    public void addMenu(Menu menu) {

        menuDao.addMenu(menu);
    }
    
    @Override
    public Menu getMenu(int menuId) {

        return menuDao.getMenu(menuId);
    }
    
    @Override
    public void updateMenu(Menu menu) {

        menuDao.updateMenu(menu);
    }
    
    @Override
    public List<Menu> searchMenuItems(
            int restaurantId,
            String keyword) {

        return menuDao.searchMenuItems(
                restaurantId,
                keyword);
    }
}