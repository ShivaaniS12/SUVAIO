<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.th.model.Restaurant" %>

<%
List<Restaurant> restaurants = (List<Restaurant>)request.getAttribute("restaurants");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Restaurants</title>

<link rel="stylesheet" href="css/admin-style.css">

</head>
<body>
<div style="width:95%;
            margin:30px auto;">
<a href="adminDashboard"
   class="back">
   Back To Dashboard
</a>

<h2>Manage Restaurants</h2>

<table>

<tr>
    <th>ID</th>
    <th>Name</th>
    <th>Address</th>
    <th>Cuisine</th>
    <th>Rating</th>
    <th>Status</th>
    <th>Action</th>
</tr>

<%
if(restaurants != null){

for(Restaurant restaurant : restaurants){
%>

<tr>

<td>
<%= restaurant.getRestaurantId() %>
</td>

<td>
<%= restaurant.getName() %>
</td>

<td>
<%= restaurant.getAddress() %>
</td>

<td>
<%= restaurant.getCuisineType() %>
</td>

<td>
<%= restaurant.getRating() %>
</td>

<td>

<%
String status =
        restaurant.getStatus();

if("ACTIVE".equalsIgnoreCase(status)){
%>

<span style="color:green;font-weight:bold;">
ACTIVE
</span>

<%
}
else if("PENDING".equalsIgnoreCase(status)){
%>

<span style="color:orange;font-weight:bold;">
PENDING
</span>

<%
}
else if("CLOSED".equalsIgnoreCase(status)){
%>

<span style="color:red;font-weight:bold;">
CLOSED
</span>

<%
}
else{
%>

<span style="color:gray;font-weight:bold;">
INACTIVE
</span>

<%
}
%>

</td>

<td>

<%
if("ACTIVE".equalsIgnoreCase(
        restaurant.getStatus())){
%>

<a href="deactivateRestaurant?restaurantId=<%= restaurant.getRestaurantId() %>">

<button class="btn deactivate">
Deactivate
</button>

</a>

<%
}
else{
%>

<a href="activateRestaurant?restaurantId=<%= restaurant.getRestaurantId() %>">

<button class="btn activate">
Activate
</button>

</a>

<%
}
%>

</td>

</tr>

<%
}
}
%>

</table>
</div>
</body>
</html>