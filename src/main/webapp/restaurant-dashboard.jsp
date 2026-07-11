<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.th.model.Order" %>

<%
Integer restaurantId =
(Integer)session.getAttribute("restaurantId");

String restaurantName =
(String)session.getAttribute("restaurantName");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Restaurant Dashboard</title>

<link rel="stylesheet"
      href="css/restaurant-dashboard.css">

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

    <a href="RevenueServlet">
        Revenue
    </a>

    <a href="RestaurantAnalyticsServlet">
    Analytics
</a>
    <a href="RestaurantLogoutServlet">
    Logout
</a>

</div>

<div class="main-content">

    <div class="header">

        <h1>
            Welcome <%= restaurantName %>
        </h1>

    </div>

    <div class="card-container">

        <!-- MENU -->

        <div class="card">

            <h3>Total Menu Items</h3>

            <p>
                <%= request.getAttribute("totalItems") %>
            </p>

        </div>

        <div class="card">

            <h3>Available Items</h3>

            <p>
                <%= request.getAttribute("availableItems") %>
            </p>

        </div>

        <div class="card">

            <h3>Unavailable Items</h3>

            <p>
                <%= request.getAttribute("unavailableItems") %>
            </p>

        </div>

        <!-- ORDERS -->

        <div class="card">

            <h3>Today's Orders</h3>

            <p>
                <%= request.getAttribute("todayOrders") %>
            </p>

        </div>

       
        <%
List<Order> recentOrders =
(List<Order>)request.getAttribute(
        "recentOrders");
%>

<div class="dashboard-section">

    <div class="recent-orders-card">

        <h2>Recent Orders</h2>

        <table class="recent-orders-table">

            <tr>
                <th>Order ID</th>
                <th>Status</th>
            </tr>

            <%
            if(recentOrders != null){

                for(Order order : recentOrders){
            %>

            <tr>

                <td>
                    #<%= order.getOrderId() %>
                </td>

                <td>
                    <%= order.getOrderStatus() %>
                </td>

            </tr>

            <%
                }
            }
            %>

        </table>

    </div>

    <div class="popular-item-card">

        <h2>Most Ordered Item</h2>

        <div class="popular-food">

            <%= request.getAttribute(
                    "mostOrderedItem") %>

        </div>

    </div>

</div>

    </div>

</div>

</body>
</html>