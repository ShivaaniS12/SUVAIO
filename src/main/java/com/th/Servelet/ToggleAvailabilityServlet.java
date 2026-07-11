package com.th.Servelet;

import java.io.IOException;

import com.th.service.MenuService;
import com.th.serviceimpl.MenuServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ToggleAvailabilityServlet")
public class ToggleAvailabilityServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private MenuService menuService;

    @Override
    public void init() {

        menuService =
                new MenuServiceImpl();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int menuId =
                Integer.parseInt(
                        request.getParameter(
                                "menuId"));

        boolean available =
                Boolean.parseBoolean(
                        request.getParameter(
                                "available"));

        menuService.updateAvailability(
                menuId,
                available);

        response.sendRedirect(
                "ManageMenuServlet");
    }
}