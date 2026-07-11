<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
String agentName = (String) session.getAttribute("agentName");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Delivery Dashboard</title>

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

   <a href="DeliveryDashboardServlet"
   class="active">
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

<a href="DeliveryAnalyticsServlet">
    Analytics
</a>

<a href="DeliveryAgentLogoutServlet">
    Logout
</a>

</div>

<div class="main-content">

    <div class="header">

        <h1>

            Welcome,
            <%= agentName %>

        </h1>

        <p>

            Manage your assigned deliveries and track performance.

        </p>

    </div>

    <div class="card-container">

        <div class="card">

            <h3>
                Assigned Orders
            </h3>

           <p>
<%= request.getAttribute(
        "assignedOrders") %>
</p>
        </div>

        <div class="card">

            <h3>
                Picked Up Orders
            </h3>

           <p>
<%= request.getAttribute(
        "pickedUpOrders") %>
</p>

        </div>

        <div class="card">

            <h3>
                Delivered Orders
            </h3>

            <p>
<%= request.getAttribute(
        "deliveredOrders") %>
</p>

        </div>

        <div class="card">

            <h3>
                Today's Deliveries
            </h3>

           <p>
<%= request.getAttribute(
        "todayDeliveries") %>
</p>

        </div>

    </div>

    <div class="table-container">

        <h2 style="margin-bottom:20px;">

            Recent Delivery Activity

        </h2>

        <table>

            <thead>

            <tr>

                <th>Order ID</th>

                <th>Status</th>

                <th>Amount</th>

                <th>Date</th>

            </tr>

            </thead>

            <tbody>

            <tr>

                <td>#101</td>

                <td>

                    <span class="status status-assigned">

                        AGENT_ASSIGNED

                    </span>

                </td>

                <td>₹499</td>

                <td>07-Jul-2026</td>

            </tr>

            <tr>

                <td>#102</td>

                <td>

                    <span class="status status-picked">

                        PICKED_UP

                    </span>

                </td>

                <td>₹699</td>

                <td>07-Jul-2026</td>

            </tr>

            <tr>

                <td>#103</td>

                <td>

                    <span class="status status-delivered">

                        DELIVERED

                    </span>

                </td>

                <td>₹899</td>

                <td>06-Jul-2026</td>

            </tr>

            </tbody>

        </table>

    </div>

</div>

</body>

</html>