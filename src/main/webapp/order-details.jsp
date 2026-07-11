<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.List" %>
<%@ page import="com.th.model.Order" %>
<%@ page import="com.th.model.OrderItem" %>
<%@ page import="com.th.model.User" %>

<%
Order order =
(Order)request.getAttribute("order");

User user =
(User)request.getAttribute("user");

List<OrderItem> orderItems =
(List<OrderItem>)request.getAttribute(
        "orderItems");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Order Details</title>

<link rel="stylesheet"
      href="<%= request.getContextPath() %>/css/restaurant-dashboard.css">
</head>

<body>

<div class="sidebar">

    <h2>Suvaio</h2>

    <a href="RestaurantDashboardServlet">
        Dashboard
    </a>

    <a href="RestaurantOrdersServlet">
        Orders
    </a>

    <a href="RestaurantLogoutServlet">
        Logout
    </a>

</div>

<div class="main-content">

   <div class="order-header">

    <a href="RestaurantOrdersServlet"
       class="add-btn">

       ← Back to Orders

    </a>

</div>

<h1 class="order-title">
    Order #<%= order.getOrderId() %>
</h1>

    <!-- Customer Details -->

    <div class="recent-orders-card">

        <h2>Customer Details</h2>

        <p>
            <strong>Name:</strong>
            <%= user.getUsername() %>
        </p>

        <p>
            <strong>Email:</strong>
            <%= user.getEmail() %>
        </p>

        <p>
            <strong>Address:</strong>
            <%= user.getAddress() %>
        </p>

    </div>

    <br>

    <!-- Order Details -->

    <div class="recent-orders-card">

        <h2>Order Information</h2>

        <p>
            <strong>Payment Mode:</strong>
            <%= order.getPaymentMode() %>
        </p>

        <p>
            <strong>Status:</strong>
            <%= order.getOrderStatus() %>
        </p>

        <p>
            <strong>Total Amount:</strong>
            ₹<%= order.getTotalAmount() %>
        </p>

        <p>
            <strong>Order Date:</strong>
            <%= order.getOrderDate() %>
        </p>

    </div>

    <br>

    <!-- Ordered Items -->

    <div class="recent-orders-card">

        <h2>Ordered Items</h2>

        <table class="recent-orders-table">

            <tr>

                <th>Menu ID</th>
                <th>Quantity</th>
                <th>Subtotal</th>

            </tr>

            <% for(OrderItem item : orderItems){ %>

            <tr>

                <td>
                    <%= item.getMenuId() %>
                </td>

                <td>
                    <%= item.getQuantity() %>
                </td>

                <td>
                    ₹<%= item.getSubtotal() %>
                </td>

            </tr>

            <% } %>

        </table>

    </div>

    <br>

    <!-- Nutrition Summary -->

    <div class="recent-orders-card">

        <h2>Nutrition Summary</h2>

        <p>
            Protein :
            <%= order.getTotalProtein() %> g
        </p>

        <p>
            Carbs :
            <%= order.getTotalCarbs() %> g
        </p>

        <p>
            Fats :
            <%= order.getTotalFats() %> g
        </p>

        <p>
            Calories :
            <%= order.getTotalCalories() %>
        </p>

    </div>

</div>

</body>

</html>