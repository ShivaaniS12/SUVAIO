<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.th.model.AnalyticsData" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reports & Analytics</title>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>


<link rel="stylesheet" href="css/admin-style.css">

</head>
<body>

<div class="header">

   <a href="adminDashboard"
   class="back">

       Back To Dashboard

    </a>

</div>

<h1>Reports & Analytics</h1>

<div class="stats-container">

    <div class="card">
        <h3>Today's Revenue</h3>
        <div class="value">
            ₹ 0
        </div>
    </div>

    <div class="card">
        <h3>Weekly Revenue</h3>
        <div class="value">
            ₹ <%= request.getAttribute("weeklyRevenue") == null
        ? 0
        : request.getAttribute("weeklyRevenue") %>
        </div>
    </div>

 <div class="card">
       
        <h3>Monthly Revenue</h3>
        <div class="value">
           ₹ <%= request.getAttribute("monthlyRevenue") == null
        ? 0
        : request.getAttribute("monthlyRevenue") %>
        </div>
    </div>

    <div class="card">
        <h3>Orders Today</h3>
        <div class="value">
            0
        </div>
    </div>

    <div class="card">
        <h3>Weekly Profit</h3>
        <div class="value profit">
           ₹ <%= request.getAttribute("weeklyProfit") == null
        ? 0
        : request.getAttribute("weeklyProfit") %>
        </div>
    </div>

</div>

<br><br>

<div class="card top-restaurant-card">

    <h2>Top Restaurant</h2>

   <div class="top-restaurant-name">
<%= request.getAttribute("topRestaurant") %>
</div>

</div>

<%
List<AnalyticsData> ordersPerRestaurant =
        (List<AnalyticsData>)
        request.getAttribute(
                "ordersPerRestaurant");
%>

<div class="card section-card">

<h2>Orders Per Restaurant</h2>

<table
style="width:100%;
border-collapse:collapse;">

<tr
style="background:#343a40;
color:white;">

<th>Restaurant</th>
<th>Total Orders</th>

</tr>

<%

if(ordersPerRestaurant != null){

for(AnalyticsData data
        : ordersPerRestaurant){
%>

<tr>

<td>
<%= data.getRestaurantName() %>
</td>

<td>
<%= data.getOrderCount() %>
</td>

</tr>

<%
}
}
%>

</table>

</div>

<%
List<AnalyticsData> revenueReport =
        (List<AnalyticsData>)
        request.getAttribute(
                "revenueReport");
%>


    <div class="card section-card">
    
<h2>Restaurant-wise Sales Report</h2>

<table style="
width:100%;
border-collapse:collapse;">

<tr style="
background:#343a40;
color:white;">

<th>Restaurant</th>
<th>Total Revenue</th>

</tr>

<%
if(revenueReport != null){

for(AnalyticsData data
        : revenueReport){
%>

<tr>

<td>
<%= data.getRestaurantName() %>
</td>

<td>
₹ <%= data.getRevenue() %>
</td>

</tr>

<%
}
}
%>

</table>

</div>



<div class="comparison-container">

    <div class="card graph-card">

        <h2>Monthly Revenue Trend</h2>

        <canvas id="revenueChart"></canvas>

    </div>

    <div class="card summary-card">

        <h2>Revenue Comparison Summary</h2>

        <table style="width:100%;">

            <tr>
                <td>Previous Month Revenue</td>
                <td>
                ₹ <%= request.getAttribute(
                        "previousMonthRevenue") %>
                </td>
            </tr>

            <tr>
                <td>Current Month Revenue</td>
                <td>
                ₹ <%= request.getAttribute(
                        "currentMonthRevenue") %>
                </td>
            </tr>

            <tr>
                <td>Growth Percentage</td>
                <td>
                <%= String.format(
                        "%.2f",
                        request.getAttribute(
                                "growthPercentage")) %> %
                </td>
            </tr>

        </table>

    </div>

</div>
<script>

const ctx =
document.getElementById(
        'revenueChart');

new Chart(ctx, {

    type: 'line',

    data: {

        labels: [

            'Previous Month',
            'Current Month'

        ],

        datasets: [{

            label:
            'Revenue',

            data: [

                <%= request.getAttribute(
                        "previousMonthRevenue") %>,

                <%= request.getAttribute(
                        "currentMonthRevenue") %>

            ],

            borderWidth: 3,

            tension: 0.3
        }]
    },

    options: {

        responsive: true,

        plugins: {

            legend: {

                display: true
            }
        }
    }
});

</script>

<div class="footer">
    © 2026 TastyHop. All rights reserved.
</div>

</body>
</html>