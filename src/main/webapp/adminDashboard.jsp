<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
String role =
(String)session.getAttribute("role");

if(role == null ||
!role.equalsIgnoreCase("ADMIN")){

response.sendRedirect("login.jsp");
return;
}
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Suvaio Admin Dashboard</title>

<link rel="stylesheet"
href="css/admin-style.css">

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

</head>

<body>

<!-- Sidebar -->

<div class="sidebar">

    <div class="logo">

        Suvaio

    </div>

    <a href="adminDashboard">
        Dashboard
    </a>

    <a href="manageUsers">
        Users
    </a>

    <a href="manageRestaurants">
        Restaurants
    </a>

    <a href="manageDeliveryAgents">
        Delivery Agents
    </a>
    
    <a href="AdminOrdersServlet">
    Manage Orders
</a>

</div>

<!-- Main Content -->

<div class="main-content">

    <div class="top-bar">

        <h1>Admin Dashboard</h1>

        <a href="logout"
           class="logout-btn">

           Logout

        </a>

    </div>

    <!-- Stats Cards -->

    <div class="stats-container">

        <div class="stat-card">

            <h3>Total Users</h3>

            <p>
                <%= request.getAttribute("totalUsers") %>
            </p>

        </div>

        <div class="stat-card">

            <h3>Total Restaurants</h3>

            <p>
                <%= request.getAttribute("totalRestaurants") %>
            </p>

        </div>

        <div class="stat-card">

            <h3>Orders Today</h3>

            <p>
                <%= request.getAttribute("ordersToday") %>
            </p>

        </div>

        <div class="stat-card">

            <h3>Revenue Today</h3>

            <p>
                ₹ <%= request.getAttribute("todayRevenue") %>
            </p>

        </div>

    </div>

    <!-- Revenue Graph -->

    <div class="dashboard-section">

        <div class="graph-card">

            <h2>Revenue Trend</h2>

            <canvas id="revenueChart"></canvas>

        </div>

    </div>

   <!-- Business Summary -->

<div class="dashboard-row">

    <div class="dashboard-card">

        <h2>Top Restaurant</h2>

        <h3 style="color:#22c55e;">

            <%= request.getAttribute("topRestaurant") %>

        </h3>

    </div>

    <div class="dashboard-card">

        <h2>Current Month Revenue</h2>

        <h3 style="color:#3b82f6;">

            ₹
            <%= request.getAttribute(
                    "currentMonthRevenue") == null
                    ? 0
                    : request.getAttribute(
                            "currentMonthRevenue") %>

        </h3>

    </div>

    <div class="dashboard-card">

        <h2>Previous Month Revenue</h2>

        <h3 style="color:#f59e0b;">

            ₹
            <%= request.getAttribute(
                    "previousMonthRevenue") == null
                    ? 0
                    : request.getAttribute(
                            "previousMonthRevenue") %>

        </h3>

    </div>

</div>

    <!-- Top Restaurants -->

    <div class="dashboard-section">

        <h2>Top 5 Restaurants By Revenue</h2>

        <table>

            <tr>

                <th>Restaurant</th>

                <th>Revenue</th>

            </tr>

            <tr>

                <td>Pizza Palace</td>

                <td>₹15,500</td>

            </tr>

            <tr>

                <td>Burger Hub</td>

                <td>₹12,000</td>

            </tr>

            <tr>

                <td>Biryani House</td>

                <td>₹8,700</td>

            </tr>

        </table>

    </div>

    <!-- Recent Orders -->

    <div class="dashboard-section">

        <h2>Recent Orders</h2>

        <table>

            <tr>

                <th>Order ID</th>

                <th>Customer</th>

                <th>Status</th>

            </tr>

            <tr>

                <td>101</td>

                <td>Vishnu</td>

                <td>Delivered</td>

            </tr>

            <tr>

                <td>102</td>

                <td>Rahul</td>

                <td>Preparing</td>

            </tr>

        </table>

    </div>

</div>

<script>

const ctx =
document.getElementById(
'revenueChart');

new Chart(ctx, {

type:'line',

data:{

labels:[
'Previous Month',
'Current Month'
],

datasets:[{

label:'Revenue',

data:[

<%= request.getAttribute("previousMonthRevenue") == null ? 0 :
request.getAttribute("previousMonthRevenue") %>,

<%= request.getAttribute("currentMonthRevenue") == null ? 0 :
request.getAttribute("currentMonthRevenue") %>

],

borderWidth:3,

tension:0.4

}]

},

options:{

responsive:true

}

});

</script>

</body>

</html>