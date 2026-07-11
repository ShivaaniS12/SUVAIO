<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="com.th.model.User"%>

<%
User user = (User)session.getAttribute("loggedInUser");

if(user == null){

    response.sendRedirect("login.jsp");
    return;
}
%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>My Profile | SUVAIO</title>

<link rel="stylesheet"
href="css/profile.css">

<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">

<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
rel="stylesheet">

</head>

<body>

<nav class="navbar">

<div class="logo">

SUVAIO

</div>

<div class="nav-links">

<a href="HomeServlet">

<i class="fa-solid fa-house"></i>

Home

</a>

<a href="MyOrdersServlet">

<i class="fa-solid fa-box"></i>

Orders

</a>

<a href="UserLogoutServlet">

<i class="fa-solid fa-right-from-bracket"></i>

Logout

</a>

</div>

</nav>

<div class="profile-container">

<div class="profile-card">

<div class="avatar">

<i class="fa-solid fa-user"></i>

</div>

<h1>

My Profile

</h1>

<p class="role">

<%=user.getRole().toUpperCase()%>

</p>

<form
action="UpdateProfileServlet"
method="post"
class="profile-form">

<div class="input-group">

<label>

<i class="fa-solid fa-user"></i>

Full Name

</label>

<input
type="text"
name="username"
value="<%=user.getUsername()%>"
required>

</div>

<div class="input-group">

<label>

<i class="fa-solid fa-envelope"></i>

Email

</label>

<input
type="email"
value="<%=user.getEmail()%>"
readonly
class="readonly">

</div>

<div class="input-group">

<label>

<i class="fa-solid fa-phone"></i>

Mobile Number

</label>

<input
type="text"
name="mobile"
value="<%=user.getMobile()==null?"":user.getMobile()%>"
placeholder="Enter Mobile Number">

</div>

<div class="input-group">

<label>

<i class="fa-solid fa-location-dot"></i>

Address

</label>

<textarea
name="address"
rows="4"><%=user.getAddress()==null?"":user.getAddress()%></textarea>

</div>

<div class="input-group">

<label>

<i class="fa-solid fa-calendar"></i>

Member Since

</label>

<input
type="text"
value="<%=user.getCreatedDate()%>"
readonly
class="readonly">

</div>

<div class="button-group">

<button
type="submit"
class="save-btn">

<i class="fa-solid fa-floppy-disk"></i>

Save Changes

</button>

<a
href="ChangePasswordServlet"
class="password-btn">

<i class="fa-solid fa-lock"></i>

Change Password

</a>

<a
href="UserLogoutServlet"
class="logout-btn">

<i class="fa-solid fa-right-from-bracket"></i>

Logout

</a>

</div>

</form>

</div>

</div>

</body>

</html>