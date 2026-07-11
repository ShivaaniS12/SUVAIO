<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.th.model.Order" %>

<%
List<Order> orders =
(List<Order>) request.getAttribute("orders");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Incoming Orders</title>

<link rel="stylesheet"
href="<%= request.getContextPath() %>/css/restaurant-dashboard.css">

<link rel="preconnect"
href="https://fonts.googleapis.com">

<link rel="preconnect"
href="https://fonts.gstatic.com"
crossorigin>

<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
rel="stylesheet">

</head>

<body>

<div class="sidebar">

    <h2>Suvaio</h2>

    <a href="RestaurantDashboardServlet">
        Dashboard
    </a>

    <a href="ManageMenuServlet">
        Manage Menu
    </a>

    <a href="RestaurantOrdersServlet">
        Orders
    </a>

</div>

<div class="main-content">

    <div class="top-bar">

        <h1>Incoming Orders</h1>

    </div>

    <div class="table-container">

        <table>

            <thead>

                <tr>

                    <th>Order ID</th>
                    <th>User ID</th>
                    <th>Amount</th>
                    <th>Payment</th>
                    <th>Order Date</th>
                    <th>Status</th>
                    <th>Action</th>

                </tr>

            </thead>

            <tbody>

            <% if(orders != null && !orders.isEmpty()) { %>

                <% for(Order order : orders) { %>

                <tr>

                    <td>

<a href="RestaurantOrderDetailsServlet?orderId=<%= order.getOrderId() %>"
   class="view-order-link">

    #<%= order.getOrderId() %>

</a>

</td>

                    <td>
                        <%= order.getUserId() %>
                    </td>

                    <td>
                        &#8377;<%= order.getTotalAmount() %>
                    </td>

                    <td>
                        <%= order.getPaymentMode() %>
                    </td>

                    <td>
                        <%= order.getOrderDate() %>
                    </td>

                    <td>

                        <span class="order-status">

                            <%= order.getOrderStatus() %>

                        </span>

                    </td>

                   <td>

<%
String status =
        order.getOrderStatus();

if("PLACED".equals(status)){
%>

<a href="UpdateOrderStatusServlet?orderId=<%= order.getOrderId() %>&status=RESTAURANT_ACCEPTED"
   class="accept-btn">

    Accept

</a>

<%
}
else if("RESTAURANT_ACCEPTED".equals(status)){
%>

<a href="UpdateOrderStatusServlet?orderId=<%= order.getOrderId() %>&status=PREPARING"
   class="prepare-btn">

    Start Preparing

</a>

<%
}
else if("PREPARING".equals(status)){
%>

<a href="UpdateOrderStatusServlet?orderId=<%= order.getOrderId() %>&status=READY_FOR_PICKUP"
   class="pickup-btn">

    Ready For Pickup

</a>

<%
}
else{
%>

Completed

<%
}
%>

</td>

                </tr>

                <% } %>

            <% } else { %>

                <tr>

                    <td colspan="7"
                        style="text-align:center;padding:30px;">

                        No Orders Found

                    </td>

                </tr>

            <% } %>

            </tbody>

        </table>

    </div>

</div>

</body>
</html>