package com.th.Servelet;

import java.io.IOException;

import com.th.dao.MenuDAO;
import com.th.daoimpl.MenuDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteFoodServlet")
public class DeleteFoodServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private MenuDAO menuDAO;

    @Override
    public void init() {

        menuDAO = new MenuDAOImpl();
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

        menuDAO.deleteMenu(menuId);

        response.sendRedirect(
                "ManageMenuServlet");
    }
}