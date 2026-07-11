package com.th.Servelet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/DeliveryAgentLogoutServlet")
public class DeliveryAgentLogoutServlet
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if(session != null) {

            session.invalidate();
        }

        response.sendRedirect(
                "delivery-login.jsp");
    }
}