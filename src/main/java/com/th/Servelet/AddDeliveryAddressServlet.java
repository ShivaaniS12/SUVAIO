package com.th.Servelet;

import java.io.IOException;

import com.th.model.DeliveryAddress;
import com.th.model.User;
import com.th.service.DeliveryAddressService;
import com.th.serviceimpl.DeliveryAddressServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/AddDeliveryAddressServlet")
public class AddDeliveryAddressServlet extends HttpServlet {

    private DeliveryAddressService addressService;

    @Override
    public void init() {

        addressService =
                new DeliveryAddressServiceImpl();

    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if(session == null ||
           session.getAttribute("loggedInUser") == null){

            response.sendRedirect("userlogin.jsp");
            return;
        }

        User user =
                (User) session.getAttribute("loggedInUser");

        DeliveryAddress address =
                new DeliveryAddress();

        address.setUserId(user.getUserID());

        address.setAddressType(
                request.getParameter("addressType"));

        address.setName(
                request.getParameter("receiverName"));

        address.setMobile(
                request.getParameter("mobile"));

        address.setHouseNo(
                request.getParameter("houseNo"));

        address.setLandmark(
                request.getParameter("area"));

        address.setCity(
                request.getParameter("city"));

        address.setState(
                request.getParameter("state"));

        address.setPincode(
                request.getParameter("pincode"));

        address.setLatitude(0);

        address.setLongitude(0);

        address.setDefault(false);

        addressService.addAddress(address);

        response.sendRedirect("CheckoutServlet");

    }

}