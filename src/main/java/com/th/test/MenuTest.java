package com.th.test;

import java.util.List;

import com.th.daoimpl.MenuDAOImpl;
import com.th.model.Menu;

public class MenuTest {

    public static void main(String[] args) {

        MenuDAOImpl dao = new MenuDAOImpl();

        // ==========================
        // INSERT TEST
        // ==========================
        System.out.println("===== INSERT TEST =====");

        Menu menu = new Menu(
                0,
                1,
                "Protein Burger",
                "High protein chicken burger",
                "Non-Veg",
                249.00,
                32.00,
                28.00,
                10.00,
                380,
                4.8,
                true,
                "images/proteinburger.jpg"
        );

        dao.addMenu(menu);

        System.out.println("Menu Item Added Successfully");


        // ==========================
        // GET MENU BY ID
        // ==========================
        System.out.println("\n===== GET MENU BY ID =====");

        Menu fetchedMenu = dao.getMenu(1);

        if (fetchedMenu != null) {
            System.out.println(fetchedMenu);
        } else {
            System.out.println("Menu Item Not Found");
        }


        // ==========================
        // GET ALL MENUS
        // ==========================
        System.out.println("\n===== GET ALL MENUS =====");

        List<Menu> menus = dao.getAllMenus();

        for (Menu m : menus) {
            System.out.println(m);
        }


        // ==========================
        // GET MENUS BY RESTAURANT
        // ==========================
        System.out.println("\n===== MENUS OF RESTAURANT 1 =====");

        List<Menu> restaurantMenus =
                dao.getMenusByRestaurant(1);

        for (Menu m : restaurantMenus) {
            System.out.println(m);
        }


        // ==========================
        // UPDATE TEST
        // ==========================
        System.out.println("\n===== UPDATE TEST =====");

        Menu updateMenu = dao.getMenu(1);

        if (updateMenu != null) {

            updateMenu.setPrice(299.00);
            updateMenu.setProtein(35.00);
            updateMenu.setRating(4.9);

            dao.updateMenu(updateMenu);

            System.out.println("Menu Updated Successfully");

            System.out.println(dao.getMenu(1));
        }


        // ==========================
        // DELETE TEST
        // ==========================
        System.out.println("\n===== DELETE TEST =====");

        dao.deleteMenu(82);

        System.out.println("Menu Deleted Successfully");


        // ==========================
        // FINAL MENU LIST
        // ==========================
        System.out.println("\n===== FINAL MENU LIST =====");

        List<Menu> finalMenus = dao.getAllMenus();

        for (Menu m : finalMenus) {
            System.out.println(m);
        }
    }
}