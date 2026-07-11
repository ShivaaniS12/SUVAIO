package com.th.Servelet;

import java.io.IOException;

import com.th.model.User;
import com.th.service.UserService;
import com.th.serviceimpl.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deactivateUser")
public class DeactivateUserServlet extends HttpServlet {

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

        int userId =
                Integer.parseInt(
                        request.getParameter(
                                "userId"));
        
        User loggedInUser =
                (User) request.getSession()
                              .getAttribute("loggedInUser");

        if(loggedInUser != null &&
           loggedInUser.getUserID() == userId) {

            response.sendRedirect("manageUsers");
            return;
        }

        userService.updateUserStatus(
                userId,
                "INACTIVE");

        response.sendRedirect(
                "manageUsers");
    }
}