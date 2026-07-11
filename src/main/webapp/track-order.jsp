<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@page import="java.util.List"%>
<%@page import="com.th.model.Order"%>
<%@page import="com.th.model.OrderTracking"%>

<%
Order order =
(Order)request.getAttribute("order");

List<OrderTracking> trackingList =
(List<OrderTracking>)request.getAttribute("trackingList");
%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>

Track Order | SUVAIO

</title>

<link rel="stylesheet"
href="css/track-order.css">

<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">

</head>

<body>

<nav class="navbar">

<h2>

SUVAIO

</h2>

<a href="HomeServlet">

Home

</a>

</nav>

<div class="tracking-container">

<h1>

Track Your Order

</h1>

<div class="order-card">

<div>

<h2>

Order #<%=order.getOrderId()%>

</h2>

<p>

Payment :
<%=order.getPaymentMode()%>

</p>

<p>

Amount :

&#8377;

<%=String.format("%.2f",
order.getTotalAmount())%>

</p>

</div>

<div class="status-badge">

<%=order.getOrderStatus()%>

</div>

</div>

<div class="timeline">

<%
for(OrderTracking tracking : trackingList){
%>

<div class="timeline-item">

<div class="circle">

<i class="fa-solid fa-check"></i>

</div>

<div class="content">

<h3>

<%=tracking.getStatus()%>

</h3>

<p>

<%=tracking.getRemarks()%>

</p>

<span>

<%=tracking.getUpdatedTime()%>

</span>

</div>

</div>

<%
}
%>

</div>

<div class="bottom-buttons">

<a
href="HomeServlet">

Continue Shopping

</a>

</div>

</div>

</body>

</html>