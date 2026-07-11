package com.th.Servelet;

import java.io.IOException;

import com.th.model.User;
import com.th.service.UserService;
import com.th.serviceimpl.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(){

        userService =
                new UserServiceImpl();

    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if(session == null){

            response.sendRedirect("login.jsp");
            return;

        }

        User user =
        (User)session.getAttribute("loggedInUser");

        if(user == null){

            response.sendRedirect("login.jsp");
            return;

        }

        user.setUsername(
                request.getParameter("username"));

        user.setMobile(
                request.getParameter("mobile"));

        user.setAddress(
                request.getParameter("address"));

        boolean updated =
                userService.updateProfile(user);

        if(updated){

            // Update session with latest values
            session.setAttribute(
                    "loggedInUser",
                    user);

        }

        response.sendRedirect(
                "ProfileServlet");

    }

}