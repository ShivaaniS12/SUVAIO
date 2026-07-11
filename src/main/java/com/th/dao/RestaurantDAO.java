package com.th.dao;

import java.util.List;

import com.th.model.Restaurant;

public interface RestaurantDAO {
	
	 void addRestaurant(Restaurant restaurant);
	    Restaurant getRestaurant(int restaurantId);
	    void updateRestaurant(Restaurant restaurant);
	    void deleteRestaurant(int restaurantId);
	    List<Restaurant> getAllRestaurants();
	    boolean updateRestaurantStatus(int restaurantId, String status);
	    List<Restaurant> getRestaurantsByCuisine(String cuisineType);
	    List<Restaurant> getRestaurantsByStatus(String status);
	    int getTotalRestaurants();
	    
	    Restaurant getRestaurantByUserId(int userId);
	    
	    
	    

}
