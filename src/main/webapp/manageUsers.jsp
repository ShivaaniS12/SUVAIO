<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.th.model.User" %>

<%
    List<User> users =
            (List<User>) request.getAttribute("users");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Users</title>

<link rel="stylesheet" href="css/admin-style.css">

</head>
<body>
<div style="width:95%;
            margin:30px auto;">

<a href="adminDashboard" class="back">
Back To Dashboard
</a>

<h2>Manage Users</h2>

<table>

<tr>
    <th>User ID</th>
    <th>Name</th>
    <th>Email</th>
    <th>Role</th>
    <th>Status</th>
    <th>Action</th>
</tr>

<%

if(users != null){
	

    for(User user : users){
%>

<tr>

<td><%= user.getUserID() %></td>

<td><%= user.getUsername() %></td>

<td><%= user.getEmail() %></td>

<td><%= user.getRole() %></td>

<td>

<%
if("ACTIVE".equalsIgnoreCase(user.getStatus())){
%>

<span class="active">
ACTIVE
</span>

<%
}
else{
%>

<span class="inactive">
<%= user.getStatus() %>
</span>

<%
}
%>

</td>

<td>

<%
User loggedInUser =
        (User) session.getAttribute(
                "loggedInUser");

if(loggedInUser != null &&
   loggedInUser.getUserID() ==
   user.getUserID()){
%>

<span style="color:blue;font-weight:bold;">
Protected
</span>

<%
}
else if("ACTIVE".equalsIgnoreCase(
        user.getStatus())){
%>

<a href="deactivateUser?userId=<%= user.getUserID() %>">
<button class="btn deactivate">
Deactivate
</button>
</a>

<%
}
else{
%>

<a href="activateUser?userId=<%= user.getUserID() %>">
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