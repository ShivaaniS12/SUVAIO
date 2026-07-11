package com.th.Servelet;

import java.io.IOException;

import com.th.dao.UserDAO;
import com.th.daoimpl.UserDAOImpl;
import com.th.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userDAO.validateUser(email, password);

        if (user != null) {

            HttpSession session = request.getSession();

            session.setAttribute("loggedInUser", user);
            session.setAttribute("userId", user.getUserID());
            session.setAttribute("userName", user.getUsername());
            session.setAttribute("userEmail", user.getEmail());
            session.setAttribute("role", user.getRole());

            response.sendRedirect("HomeServlet");

        } else {

            request.setAttribute("error",
                    "Invalid Email or Password");

            request.getRequestDispatcher("userlogin.jsp")
                    .forward(request, response);

        }
    }
}