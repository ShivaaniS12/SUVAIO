package com.th.Servelet;

import java.io.IOException;

import com.th.model.User;
import com.th.service.UserService;
import com.th.serviceimpl.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserService userService;

    @Override
    public void init() {

        userService = new UserServiceImpl();

    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String name =
                request.getParameter("name");

        String email =
                request.getParameter("email");

        String password =
                request.getParameter("password");

        String confirmPassword =
                request.getParameter("confirmPassword");

        String address =
                request.getParameter("address");

        if(!password.equals(confirmPassword)){

            request.setAttribute(
                    "error",
                    "Passwords do not match.");

            request.getRequestDispatcher(
                    "register.jsp")
                    .forward(request,response);

            return;

        }

        User user = new User();

        user.setUsername(name);

        user.setEmail(email);

        user.setPassword(password);

        user.setAddress(address);

        user.setRole("user");

        boolean success =
                userService.registerUser(user);

        if(success){

            request.setAttribute(
                    "success",
                    "Registration Successful! Please Login.");

            request.getRequestDispatcher(
                    "userlogin.jsp")
                    .forward(request,response);

        }
        else{

            request.setAttribute(
                    "error",
                    "Email already exists.");

            request.getRequestDispatcher(
                    "register.jsp")
                    .forward(request,response);

        }

    }

}