<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@page import="java.util.List"%>
<%@page import="com.th.model.OrderHistoryView"%>

<%
List<OrderHistoryView> orders =
(List<OrderHistoryView>)request.getAttribute("orders");
%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>My Orders | SUVAIO</title>

<link rel="stylesheet"
href="css/order-history.css">

<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">

</head>

<body>

<nav class="navbar">

<div class="logo">

SUVAIO

</div>

<div class="nav-links">

<a href="HomeServlet">

<i class="fa-solid fa-house"></i>

Home

</a>

</div>

</nav>

<div class="container">

<h1>

My Orders

</h1>

<%

if(orders==null || orders.isEmpty()){

%>

<div class="empty">

<i class="fa-solid fa-box-open"></i>

<h2>

No Orders Yet

</h2>

<p>

Start exploring restaurants and place your first order.

</p>

<a href="HomeServlet">

Start Ordering

</a>

</div>

<%

}else{

for(OrderHistoryView order : orders){

%>

<div class="order-card">

<div class="restaurant">

<div class="restaurant-image">

<img
src="<%=order.getRestaurantImage()%>">

</div>

<div class="restaurant-info">

<h2>

<%=order.getRestaurantName()%>

</h2>

<p>

Order #

<%=order.getOrderId()%>

</p>

</div>

<span class="status">

<%=order.getOrderStatus()%>

</span>

</div>

<div class="details">

<div class="detail">

<h4>

Amount

</h4>

<p>

₹ <%=String.format("%.2f",
order.getTotalAmount())%>

</p>

</div>

<div class="detail">

<h4>

Payment

</h4>

<p>

<%=order.getPaymentMode()%>

</p>

</div>

<div class="detail">

<h4>

Ordered On

</h4>

<p>

<%=order.getOrderDate()%>

</p>

</div>

</div>

<div class="buttons">

<a
href="TrackOrderServlet?orderId=<%=order.getOrderId()%>"
class="track">

<i class="fa-solid fa-location-dot"></i>

Track Order

</a>

<a
href="RestaurantMenuServlet?restaurantId=<%=order.getRestaurantId()%>"
class="reorder">

<i class="fa-solid fa-rotate-right"></i>

Order Again

</a>

</div>

</div>

<%

}

}

%>

</div>

</body>

</html>