<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Delivery Analytics</title>

<link rel="stylesheet"
      href="css/delivery-dashboard.css">

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

    <a href="DeliveryDashboardServlet">
        Dashboard
    </a>

    <a href="AssignedOrdersServlet">
        Assigned Orders
    </a>

    <a href="PickedUpOrdersServlet">
        Picked Up Orders
    </a>

    <a href="OutForDeliveryOrdersServlet">
        Out For Delivery
    </a>

    <a href="DeliveryHistoryServlet">
        Delivery History
    </a>

    <a href="DeliveryAnalyticsServlet"
       class="active">
        Analytics
    </a>

    <a href="DeliveryAgentLogoutServlet">
        Logout
    </a>

</div>

<div class="main-content">

    <div class="header">

        <h1>
            Delivery Analytics
        </h1>

    </div>

    <div class="card-container">

        <div class="card">

            <h3>Total Deliveries</h3>

            <p>
                <%= request.getAttribute(
                        "totalDeliveries") %>
            </p>

        </div>

        <div class="card">

            <h3>Today's Deliveries</h3>

            <p>
                <%= request.getAttribute(
                        "todayDeliveries") %>
            </p>

        </div>

        <div class="card">

            <h3>Completion Rate</h3>

            <p>
                100%
            </p>

        </div>

        <div class="card">

            <h3>Current Status</h3>

            <p style="font-size:24px;">
                Active
            </p>

        </div>

    </div>

</div>

</body>

</html>