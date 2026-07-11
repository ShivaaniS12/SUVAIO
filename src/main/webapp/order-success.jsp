<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@page import="com.th.model.Order"%>
<%@page import="com.th.model.Restaurant"%>
<%@page import="com.th.model.DeliveryAgent"%>

<%
Order order=(Order)request.getAttribute("order");
Restaurant restaurant=(Restaurant)request.getAttribute("restaurant");

String deliveryAddress=
(String)request.getAttribute("deliveryAddress");

if(deliveryAddress==null){

    deliveryAddress=
    "Current Location";

}

String deliveryTime=
(String)request.getAttribute("deliveryTime");

if(deliveryTime==null){

    deliveryTime="25-35 mins";

}

String status=
order.getOrderStatus();
%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>

Order Placed | SUVAIO

</title>

<link rel="stylesheet"
href="css/order-success.css">

<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">

</head>

<body>

<!-- ================= NAVBAR ================= -->

<nav class="navbar">

<h2>

SUVAIO

</h2>

</nav>

<!-- ================= SUCCESS CARD ================= -->

<div class="success-container">

<div class="success-card">

<div class="success-icon">

<i class="fa-solid fa-circle-check"></i>

</div>

<h1>

Order Placed Successfully

</h1>

<p class="subtitle">

Thank you for choosing SUVAIO.

<br>

Your order has been confirmed and the restaurant has started preparing your food.

</p>

<!-- ================= RESTAURANT ================= -->

<div class="restaurant-box">

<i class="fa-solid fa-store"></i>

<div>

<h3>

<%=restaurant.getName()%>

</h3>

<p>

<%=restaurant.getCuisineType()%>

</p>

</div>

</div>

<!-- ================= DELIVERY ADDRESS ================= -->

<div class="restaurant-box">

<i class="fa-solid fa-location-dot"></i>

<div>

<h3>

Delivering To

</h3>

<p>

<%=deliveryAddress%>

</p>

</div>

</div>

<!-- ================= ORDER DETAILS ================= -->

<div class="details">

<div class="detail-row">

<span>

Order ID

</span>

<span>

#<%=order.getOrderId()%>

</span>

</div>

<div class="detail-row">

<span>

Payment Method

</span>

<span>

<%=order.getPaymentMode()%>

</span>

</div>

<div class="detail-row">

<span>

Grand Total

</span>

<span>

₹ <%=String.format("%.2f",order.getTotalAmount())%>

</span>

</div>

<div class="detail-row">

<span>

Estimated Delivery

</span>

<span>

<i class="fa-solid fa-clock"></i>

<%=deliveryTime%>

</span>

</div>

<div class="detail-row">

<span>

Current Status

</span>

<span class="status">

<%=status.replace("_"," ")%>

</span>

</div>

</div>


<!-- ================= MESSAGE ================= -->

<!-- ================= DELIVERY PARTNER ================= -->



<%
DeliveryAgent agent =
(DeliveryAgent)request.getAttribute("deliveryAgent");
%>

<div class="restaurant-box">

    <i class="fa-solid fa-motorcycle"></i>

    <div>

        <h3>Delivery Partner</h3>

        <%
        if(agent == null){
        %>

            <p>Searching for nearby delivery partner...</p>

        <%
        }else{
        %>

            <p><strong><%=agent.getAgentName()%></strong></p>

            <p>📞 <%=agent.getPhone()%></p>

            <p>⭐ <%=agent.getRating()%></p>

        <%
        }
        %>

    </div>

</div>

<!-- ================= BUTTONS ================= -->

<div class="buttons">

<a
href="TrackOrderServlet?orderId=<%=order.getOrderId()%>"
class="track-btn">

<i class="fa-solid fa-location-crosshairs"></i>

Track Order

</a>

<a
href="OrderHistoryServlet"
class="orders-btn">

<i class="fa-solid fa-clock-rotate-left"></i>

My Orders

</a>

<a
href="HomeServlet"
class="home-btn">

<i class="fa-solid fa-house"></i>

Continue Shopping

</a>

</div>

</div>

</div>

</body>

</html>