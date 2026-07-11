package com.th.Servelet;

import java.io.IOException;

import com.th.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
    	
    	response.setHeader("Cache-Control",
    	        "no-cache, no-store, must-revalidate");

    	response.setHeader("Pragma",
    	        "no-cache");

    	response.setDateHeader("Expires", 0);

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

        request.setAttribute("user", user);

        request.getRequestDispatcher("profile.jsp")
                .forward(request,response);

    }

}