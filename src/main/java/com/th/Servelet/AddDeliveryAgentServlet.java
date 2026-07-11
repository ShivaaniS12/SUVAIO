package com.th.Servelet;

import java.io.IOException;

import com.th.model.DeliveryAgent;
import com.th.service.DeliveryAgentService;
import com.th.serviceimpl.DeliveryAgentServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addDeliveryAgent")
public class AddDeliveryAgentServlet
        extends HttpServlet {

    private DeliveryAgentService deliveryAgentService;

    @Override
    public void init() {

        deliveryAgentService =
                new DeliveryAgentServiceImpl();
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
    	
    	System.out.println("ADD AGENT SERVLET CALLED");

        DeliveryAgent agent =
                new DeliveryAgent();

        agent.setAgentName(                request.getParameter(
                        "agentName"));

        agent.setEmail(
                request.getParameter(
                        "email"));

        agent.setPhone(
                request.getParameter(
                        "phone"));

        agent.setPassword(
                request.getParameter(
                        "password"));

        agent.setVehicleType(
                request.getParameter(
                        "vehicleType"));

        agent.setVehicleNumber(
                request.getParameter(
                        "vehicleNumber"));

        agent.setStatus("Available");
        agent.setEmploymentStatus("ACTIVE");

        double rating = Double.parseDouble(
                request.getParameter("rating"));

        agent.setRating(rating);

        System.out.println(
        	    "Agent Name = "
        	    + request.getParameter("agentName"));

        	System.out.println(
        	    "Email = "
        	    + request.getParameter("email"));

        	try {

        	    deliveryAgentService
        	            .addDeliveryAgent(agent);

        	    response.sendRedirect(
        	            "manageDeliveryAgents");

        	}
        	catch(Exception e) {

        	    request.setAttribute(
        	            "error",
        	            "Email, Phone or Vehicle Number already exists!");

        	    request.getRequestDispatcher(
        	            "addDeliveryAgent.jsp")
        	            .forward(
        	                    request,
        	                    response);
        	}
    }
}