<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Register | SUVAIO</title>

<link rel="stylesheet"
href="css/register.css">

</head>

<body>

<div class="register-container">

<div class="magic-card">

<h1>SUVAIO</h1>

<p>Create your account</p>

<%
String error=(String)request.getAttribute("error");

if(error!=null){
%>

<p class="error">

<%=error%>

</p>

<%
}
%>

<form action="RegisterServlet"
method="post">

<input
type="text"
name="name"
placeholder="Full Name"
required>

<input
type="email"
name="email"
placeholder="Email Address"
required>

<input
type="password"
name="password"
placeholder="Password"
required>

<input
type="password"
name="confirmPassword"
placeholder="Confirm Password"
required>

<textarea
name="address"
placeholder="Address"
required></textarea>

<button
type="submit">

Create Account

</button>

</form>

<p>

Already have an account?

<a href="userlogin.jsp">

Login

</a>

</p>

</div>

</div>

</body>

</html>