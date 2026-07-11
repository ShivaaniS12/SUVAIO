package com.th.test;

import java.util.List;

import com.th.dao.RestaurantDAO;
import com.th.daoimpl.RestaurantDAOImpl;
import com.th.model.Restaurant;

public class RestaurantTesting {


    public static void main(String[] args) {

        RestaurantDAO restaurantDAO = new RestaurantDAOImpl();

        // =============================================
        // TEST 1: addRestaurant()
        // =============================================
        System.out.println("========================================");
        System.out.println("TEST 1: Adding a new restaurant...");
        System.out.println("========================================");

        Restaurant newRestaurant = new Restaurant(
                "Pizza Palace",
                "Koramangala, Bengaluru, Karnataka",
                "Italian",
                "Authentic wood-fired pizzas and pastas",
                4.5,
                "ACTIVE",
                null,
                null
        );

        restaurantDAO.addRestaurant(newRestaurant);
        System.out.println("Restaurant added successfully!");
        System.out.println();

        // =============================================
        // TEST 2: getAllRestaurants()
        // =============================================
        System.out.println("========================================");
        System.out.println("TEST 2: Getting all restaurants...");
        System.out.println("========================================");

        List<Restaurant> allRestaurants = restaurantDAO.getAllRestaurants();
        if (allRestaurants.isEmpty()) {
            System.out.println("No restaurants found.");
        } else {
            System.out.println("Total restaurants found: " + allRestaurants.size());
            for (Restaurant r : allRestaurants) {
                System.out.println(r);
            }
        }
        System.out.println();

        // =============================================
        // TEST 3: getRestaurant(int restaurantId)
        // =============================================
        System.out.println("========================================");
        System.out.println("TEST 3: Getting restaurant by ID...");
        System.out.println("========================================");

        int testId = 1; // change this to an ID that exists in your table
        Restaurant fetchedRestaurant = restaurantDAO.getRestaurant(testId);
        if (fetchedRestaurant != null) {
            System.out.println("Restaurant found: ");
            System.out.println(fetchedRestaurant);
        } else {
            System.out.println("No restaurant found with ID: " + testId);
        }
        System.out.println();

        // =============================================
        // TEST 4: updateRestaurant()
        // =============================================
        System.out.println("========================================");
        System.out.println("TEST 4: Updating a restaurant...");
        System.out.println("========================================");

        if (fetchedRestaurant != null) {
            fetchedRestaurant.setName("Pizza Palace - Updated");
            fetchedRestaurant.setRating(4.8);
            fetchedRestaurant.setStatus("ACTIVE");
            restaurantDAO.updateRestaurant(fetchedRestaurant);

            // verify update
            Restaurant updatedRestaurant = restaurantDAO.getRestaurant(testId);
            System.out.println("Updated restaurant: ");
            System.out.println(updatedRestaurant);
        } else {
            System.out.println("Skipping update — no restaurant found with ID: " + testId);
        }
        System.out.println();

        // =============================================
        // TEST 5: getRestaurantsByCuisine()
        // =============================================
        System.out.println("========================================");
        System.out.println("TEST 5: Getting restaurants by cuisine...");
        System.out.println("========================================");

        String cuisineFilter = "Indian";
        List<Restaurant> indianRestaurants = restaurantDAO.getRestaurantsByCuisine(cuisineFilter);
        if (indianRestaurants.isEmpty()) {
            System.out.println("No restaurants found for cuisine: " + cuisineFilter);
        } else {
            System.out.println("Restaurants with cuisine '" + cuisineFilter + "': " + indianRestaurants.size());
            for (Restaurant r : indianRestaurants) {
                System.out.println(r);
            }
        }
        System.out.println();

        // =============================================
        // TEST 6: getRestaurantsByStatus()
        // =============================================
        System.out.println("========================================");
        System.out.println("TEST 6: Getting restaurants by status...");
        System.out.println("========================================");

        String statusFilter = "ACTIVE";
        List<Restaurant> activeRestaurants = restaurantDAO.getRestaurantsByStatus(statusFilter);
        if (activeRestaurants.isEmpty()) {
            System.out.println("No restaurants found with status: " + statusFilter);
        } else {
            System.out.println("Restaurants with status '" + statusFilter + "': " + activeRestaurants.size());
            for (Restaurant r : activeRestaurants) {
                System.out.println(r);
            }
        }
        System.out.println();

        // =============================================
        // TEST 7: deleteRestaurant()
        // =============================================
        System.out.println("========================================");
        System.out.println("TEST 7: Deleting a restaurant...");
        System.out.println("========================================");

        // deleting the restaurant we added in TEST 1
        // get the last inserted restaurant ID from allRestaurants list
        if (!allRestaurants.isEmpty()) {
            int deleteId = allRestaurants.get(allRestaurants.size() - 1).getRestaurantId();
            System.out.println("Deleting restaurant with ID: " + deleteId);
            restaurantDAO.deleteRestaurant(deleteId);

            // verify deletion
            Restaurant deletedRestaurant = restaurantDAO.getRestaurant(deleteId);
            if (deletedRestaurant == null) {
                System.out.println("Restaurant deleted successfully — ID " + deleteId + " no longer exists.");
            } else {
                System.out.println("Delete failed — restaurant still exists: " + deletedRestaurant);
            }
        } else {
            System.out.println("Skipping delete — no restaurants in list.");
        }
        System.out.println();

        // =============================================
        // TEST 8: Final getAllRestaurants() check
        // =============================================
        System.out.println("========================================");
        System.out.println("TEST 8: Final list after all operations...");
        System.out.println("========================================");

        List<Restaurant> finalList = restaurantDAO.getAllRestaurants();
        System.out.println("Total restaurants remaining: " + finalList.size());
        for (Restaurant r : finalList) {
            System.out.println(r);
        }
        System.out.println();
        System.out.println("========================================");
        System.out.println("ALL TESTS COMPLETED SUCCESSFULLY");
        System.out.println("========================================");
    }
}