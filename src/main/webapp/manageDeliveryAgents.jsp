<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.th.model.DeliveryAgent" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Delivery Agents</title>

<link rel="stylesheet" href="css/admin-style.css">

</head>

<body>

<div class="container">

<a href="adminDashboard"
   class="back">

   Back To Dashboard

</a>

<h1>Manage Delivery Agents</h1>

<a href="addDeliveryAgent.jsp"
   class="btn activate"
   style="margin-bottom:20px;display:inline-block;">

    Add New Agent

</a>

<%
List<DeliveryAgent> agents =
        (List<DeliveryAgent>)
        request.getAttribute("agents");
%>

<table>

<tr>

<th>Agent ID</th>
<th>Name</th>
<th>Email</th>
<th>Phone</th>
<th>Vehicle</th>
<th>Rating</th>
<th>Employment Status</th>
<th>Action</th>

</tr>

<%

if(agents != null){

for(DeliveryAgent agent : agents){
%>

<tr>

<td>
<%= agent.getAgentId() %>
</td>

<td>
<%= agent.getAgentName() %>
</td>

<td>
<%= agent.getEmail() %>
</td>

<td>
<%= agent.getPhone() %>
</td>

<td>
<%= agent.getVehicleType() %>
<br>
<small>
<%= agent.getVehicleNumber() %>
</small>
</td>

<td>
⭐ <%= agent.getRating() %>
</td>

<td>

<%

if("ACTIVE".equalsIgnoreCase(
        agent.getEmploymentStatus())){
%>

<span class="active">
ACTIVE
</span>

<%
}
else{
%>

<span class="inactive">
INACTIVE
</span>

<%
}
%>

</td>

<td>

<%

if("ACTIVE".equalsIgnoreCase(
        agent.getEmploymentStatus())){
%>

<a href="deactivateAgent?id=<%= agent.getAgentId() %>"
   class="btn deactivate">

   Deactivate

</a>

<%
}
else{
%>

<a href="activateAgent?id=<%= agent.getAgentId() %>"
   class="btn activate">

   Activate

</a>

<%
}
%>

<br><br>

<a href="deleteAgent?id=<%= agent.getAgentId() %>"
   class="btn deactivate"
   onclick="return confirm('Are you sure you want to delete this agent?');">

   Delete

</a>

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