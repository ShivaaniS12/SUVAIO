package com.th.Servelet;

import java.io.IOException;

import com.th.model.Menu;
import com.th.service.MenuService;
import com.th.serviceimpl.MenuServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/AddFoodServlet")
public class AddFoodServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private MenuService menuService;

    @Override
    public void init() {

        menuService =
                new MenuServiceImpl();
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        int restaurantId =
                (Integer) session.getAttribute(
                        "restaurantId");

        Menu menu = new Menu();

        menu.setRestaurantId(
                restaurantId);

        menu.setItemName(
                request.getParameter(
                        "itemName"));

        menu.setDescription(
                request.getParameter(
                        "description"));

        menu.setCategory(
                request.getParameter(
                        "category"));

        menu.setPrice(
                Double.parseDouble(
                        request.getParameter(
                                "price")));

        menu.setProtein(
                Double.parseDouble(
                        request.getParameter(
                                "protein")));

        menu.setCarbs(
                Double.parseDouble(
                        request.getParameter(
                                "carbs")));

        menu.setFats(
                Double.parseDouble(
                        request.getParameter(
                                "fats")));

        menu.setCalories(
                Integer.parseInt(
                        request.getParameter(
                                "calories")));

        menu.setImagePath(
                request.getParameter(
                        "imagePath"));

        menu.setAvailable(
                Boolean.parseBoolean(
                        request.getParameter(
                                "isAvailable")));

        menu.setRating(0.0);

        menuService.addMenu(menu);

        response.sendRedirect(
                "ManageMenuServlet");
    }
}