<%@ page contentType="text/html;charset=UTF-8" %>

<%
String logout =
request.getParameter("logout");




%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Login - Suvaio</title>

<link rel="stylesheet"
      href="css/userlogin.css">

</head>

<body>


    
    <div class="login-container">

<div class="magic-card">

<%
if("success".equals(logout)){
%>

<div class="success">

<i class="fa-solid fa-circle-check"></i>

Logged out successfully.

</div>

<%
}
%>

        <h1>Suvaio</h1>

        <p>
            Login to continue
        </p>
        
        <%
String error =
(String)request.getAttribute("error");

if(error!=null){
%>

<p style="color:red;
text-align:center;
margin-bottom:20px;">

<%= error %>

</p>

<%
}
%>

<%
String success =
(String)request.getAttribute("success");

if(success!=null){
%>

<p class="success">

<%=success%>

</p>

<%
}
%>
        <form action="UserLoginServlet"
              method="post">

            <input
                type="email"
                name="email"
                placeholder="Email"
                required>

            <input
                type="password"
                name="password"
                placeholder="Password"
                required>

            <button
                type="submit"
                id="loginBtn">

                Login

            </button>

        </form>

        <p>

            Don't have an account?

            <a href="register.jsp">

                Register

            </a>

        </p>

    </div>

</div>

<script src="js/login.js"></script>

</body>

</html>