<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Revenue Overview</title>

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

    <a href="RevenueServlet"
       class="active">
        Revenue
    </a>

    <a href="AnalyticsServlet">
        Analytics
    </a>

    <a href="RestaurantLogoutServlet">
    Logout
</a>
</div>

<div class="main-content">

    <div class="header">

        <h1>
            Revenue Overview
        </h1>

    </div>

    <div class="card-container">

        <div class="card">

            <h3>Today's Revenue</h3>

            <p>
                ₹<%= request.getAttribute("todayRevenue") %>
            </p>

        </div>

        <div class="card">

            <h3>Weekly Revenue</h3>

            <p>
                ₹<%= request.getAttribute("weeklyRevenue") %>
            </p>

        </div>

        <div class="card">

            <h3>Monthly Revenue</h3>

            <p>
                ₹<%= request.getAttribute("monthlyRevenue") %>
            </p>

        </div>

        <div class="card">

            <h3>Delivered Orders</h3>

            <p>
                <%= request.getAttribute("deliveredOrders") %>
            </p>

        </div>

        <div class="card">

            <h3>Average Order Value</h3>

            <p>
                ₹<%= String.format("%.2f",
        ((Double)request.getAttribute("averageOrderValue"))) %>
            </p>

        </div>

    </div>

</div>

</body>
</html>