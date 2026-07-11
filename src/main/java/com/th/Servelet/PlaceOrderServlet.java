package com.th.Servelet;

import java.io.IOException;
import java.util.List;

import com.th.model.Cart;
import com.th.model.CartItemView;
import com.th.model.User;
import com.th.service.CartService;
import com.th.service.OrderService;
import com.th.serviceimpl.CartServiceImpl;
import com.th.serviceimpl.OrderServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.th.model.DeliveryAddress;
import com.th.service.DeliveryAddressService;
import com.th.serviceimpl.DeliveryAddressServiceImpl;

@WebServlet("/PlaceOrderServlet")
public class PlaceOrderServlet extends HttpServlet {

    private CartService cartService;
    private OrderService orderService;
    private DeliveryAddressService addressService;

    @Override
    public void init() {

        cartService = new CartServiceImpl();
        orderService = new OrderServiceImpl();
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
           session.getAttribute("loggedInUser")==null){

            response.sendRedirect("userlogin.jsp");
            return;

        }

        User user =
                (User)session.getAttribute(
                        "loggedInUser");

        Cart cart =
                cartService.getCartByUserId(
                        user.getUserID());

        if(cart == null){

            response.sendRedirect(
                    "CartServlet");

            return;

        }

        List<CartItemView> items =
                cartService.getCartItems(
                        cart.getCartId());

        if(items == null ||
           items.isEmpty()){

            response.sendRedirect(
                    "CartServlet");

            return;

        }

        int restaurantId =
                items.get(0)
                     .getRestaurantId();

        String paymentMode =
                request.getParameter(
                        "paymentMode");
        
        String addressIdStr =
                request.getParameter("addressId");

        int addressId = 0;

        if(addressIdStr != null &&
           !addressIdStr.isBlank()){

            addressId =
            Integer.parseInt(addressIdStr);

        }

        if(addressId == 0){

            DeliveryAddress address =
                    new DeliveryAddress();

            address.setUserId(user.getUserID());

            address.setAddressType("CURRENT");

            address.setName(user.getUsername());

            address.setMobile(user.getMobile());

            address.setHouseNo("Current Location");

            address.setLandmark(
                    (String) session.getAttribute("userLocation"));

            address.setCity("");

            address.setState("");

            address.setPincode("");

            Double lat =
                    (Double) session.getAttribute("userLatitude");

            Double lon =
                    (Double) session.getAttribute("userLongitude");

            address.setLatitude(lat == null ? 0 : lat);

            address.setLongitude(lon == null ? 0 : lon);

            address.setDefault(false);

            addressId =
                    addressService.addAddress(address);

        }
        if(paymentMode == null ||
                paymentMode.isBlank()){

            paymentMode = "COD";

        }
        
       
        int orderId =
                orderService.placeOrder(
                        user.getUserID(),
                        restaurantId,
                        addressId,
                        paymentMode);
        
        session.removeAttribute(
                "deliveryTime");
        
        session.setAttribute(
                "lastRestaurantId",
                restaurantId);

        response.sendRedirect(
                "OrderSuccessServlet?orderId="
                        + orderId);

    }

}