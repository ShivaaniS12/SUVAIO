package com.th.service;

import java.util.List;
import com.th.model.Restaurant;

public interface RestaurantService {

    List<Restaurant> getAllRestaurants();

    boolean updateRestaurantStatus(
            int restaurantId,
            String status);
    
    int getTotalRestaurants();
}