package com.th.Servelet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


@WebServlet("/LocationServlet")
public class LocationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        double latitude =
                Double.parseDouble(
                        request.getParameter("latitude"));

        double longitude =
                Double.parseDouble(
                        request.getParameter("longitude"));
        String locationName =
        		request.getParameter("locationName");
        
        String fullAddress =
                request.getParameter("fullAddress");
        

        session.setAttribute("userLatitude", latitude);
        session.setAttribute("userLongitude", longitude);
        session.setAttribute(
        		"userLocation",
        		locationName);
        session.setAttribute("userFullAddress", fullAddress);
      

        response.getWriter().print("success");
    }
}