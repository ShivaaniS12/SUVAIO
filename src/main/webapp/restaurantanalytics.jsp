<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Restaurant Analytics</title>

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

    <a href="RestaurantAnalyticsServlet"
       class="active">
        Analytics
    </a>

    <a href="RestaurantLogoutServlet">
        Logout
    </a>

</div>

<div class="main-content">

    <div class="header">

        <h1>
            Restaurant Analytics
        </h1>

    </div>

    <div class="card-container">

        <div class="card">

            <h3>Total Orders</h3>

            <p>
                <%= request.getAttribute("totalOrders") %>
            </p>

        </div>

        <div class="card">

            <h3>Delivered Orders</h3>

            <p>
                <%= request.getAttribute("deliveredOrders") %>
            </p>

        </div>

        <div class="card">

            <h3>Cancelled Orders</h3>

            <p>
                <%= request.getAttribute("cancelledOrders") %>
            </p>

        </div>

        <div class="card">

            <h3>Customers Served</h3>

            <p>
                <%= request.getAttribute("customersServed") %>
            </p>

        </div>

        <div class="card">

            <h3>Most Ordered Item</h3>

            <p style="font-size:32px;">
                <%= request.getAttribute("mostOrderedItem") %>
            </p>

        </div>

        <div class="card">

            <h3>Most Ordered Category</h3>

            <p style="font-size:32px;">
                <%= request.getAttribute("mostOrderedCategory") %>
            </p>

        </div>

        <div class="card">

            <h3>Average Order Value</h3>

            <p>
                ₹<%= request.getAttribute("avgOrderValue") %>
            </p>

        </div>

    </div>

</div>

</body>

</html>