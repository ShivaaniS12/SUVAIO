package com.th.Servelet;

import java.io.IOException;
import java.util.List;

import com.th.model.User;
import com.th.service.UserService;
import com.th.serviceimpl.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/manageUsers")
public class ManageUsersServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() {

        userService =
                new UserServiceImpl();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        List<User> users =
                userService.getAllUsers();

        request.setAttribute(
                "users",
                users);

        request.getRequestDispatcher(
                "manageUsers.jsp")
                .forward(
                        request,
                        response);
    }
}