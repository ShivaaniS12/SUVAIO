<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.List" %>
<%@ page import="com.th.model.Order" %>

<%
List<Order> orders =
(List<Order>)request.getAttribute("orders");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Delivery History</title>

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

    <a href="DeliveryHistoryServlet"
       class="active">
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
            Delivery History
        </h1>

    </div>

    <div class="table-container">

        <table>

            <thead>

            <tr>

                <th>Order ID</th>
                <th>Amount</th>
                <th>Payment Mode</th>
                <th>Status</th>
                <th>Date</th>

            </tr>

            </thead>

            <tbody>

            <% if(orders != null){ %>

            <% for(Order order : orders){ %>

            <tr>

                <td>
                    #<%= order.getOrderId() %>
                </td>

                <td>
                    ₹<%= order.getTotalAmount() %>
                </td>

                <td>
                    <%= order.getPaymentMode() %>
                </td>

                <td>

                    <span class="status status-delivered">

                        <%= order.getOrderStatus() %>

                    </span>

                </td>

                <td>
                    <%= order.getOrderDate() %>
                </td>

            </tr>

            <% } %>

            <% } %>

            </tbody>

        </table>

    </div>

</div>

</body>

</html>