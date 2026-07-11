package com.th.Servelet;

import java.io.IOException;

import com.th.service.DeliveryAgentService;
import com.th.serviceimpl.DeliveryAgentServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteAgent")
public class DeleteAgentServlet
        extends HttpServlet {

    private DeliveryAgentService deliveryAgentService;

    @Override
    public void init() {

        deliveryAgentService =
                new DeliveryAgentServiceImpl();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int agentId =
                Integer.parseInt(
                        request.getParameter("id"));

        deliveryAgentService
                .deleteDeliveryAgent(agentId);

        response.sendRedirect(
                "manageDeliveryAgents");
    }
}