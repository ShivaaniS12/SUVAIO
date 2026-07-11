package com.th.Servelet;

import java.io.IOException;

import com.th.dao.DeliveryAgentDAO;
import com.th.daoimpl.DeliveryAgentDAOImpl;
import com.th.model.DeliveryAgent;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/DeliveryAgentLoginServlet")
public class DeliveryAgentLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println(
                "Inside DeliveryAgentLoginServlet");

        String email =
                request.getParameter("email");

        String password =
                request.getParameter("password");

        System.out.println(
                "Email = " + email);

        System.out.println(
                "Password = " + password);

        DeliveryAgentDAO dao =
                new DeliveryAgentDAOImpl();

        DeliveryAgent agent =
                dao.validateAgent(
                        email,
                        password);

        System.out.println(
                "Agent = " + agent);

        if(agent != null) {

            HttpSession session =
                    request.getSession();

            session.setAttribute(
                    "agentId",
                    agent.getAgentId());

            session.setAttribute(
                    "agentName",
                    agent.getAgentName());

            System.out.println(
                    "Login Successful");

            response.sendRedirect(
                    "DeliveryDashboardServlet");
        }
        else {

            System.out.println(
                    "Login Failed");

            response.sendRedirect(
                    "delivery-login.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect(
                "delivery-login.jsp");
    }
}