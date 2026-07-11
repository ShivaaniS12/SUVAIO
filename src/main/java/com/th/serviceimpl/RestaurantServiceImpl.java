package com.th.serviceimpl;

import java.util.List;

import com.th.dao.RestaurantDAO;
import com.th.daoimpl.RestaurantDAOImpl;
import com.th.model.Restaurant;
import com.th.service.RestaurantService;

public class RestaurantServiceImpl
        implements RestaurantService {

    private RestaurantDAO restaurantDao;

    public RestaurantServiceImpl() {

        restaurantDao =
                new RestaurantDAOImpl();
    }

    @Override
    public List<Restaurant> getAllRestaurants() {

        return restaurantDao
                .getAllRestaurants();
    }

    @Override
    public boolean updateRestaurantStatus(
            int restaurantId,
            String status) {

        return restaurantDao
                .updateRestaurantStatus(
                        restaurantId,
                        status);
    }
    
    @Override
    public int getTotalRestaurants() {

        return restaurantDao.getTotalRestaurants();
    }
}