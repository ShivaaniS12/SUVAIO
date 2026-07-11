<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Delivery Agent Login</title>

<link rel="preconnect"
      href="https://fonts.googleapis.com">

<link rel="preconnect"
      href="https://fonts.gstatic.com"
      crossorigin>

<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
      rel="stylesheet">

<link rel="stylesheet"
      href="css/admin-style.css">

</head>

<body>

<div class="login-page">

    <div class="login-container">

        <h1>Suvaio</h1>

        <h3>
            Delivery Agent Login
        </h3>

        <form action="DeliveryAgentLoginServlet"
              method="post">

            <input type="email"
                   name="email"
                   placeholder="Email"
                   required>

            <input type="password"
                   name="password"
                   placeholder="Password"
                   required>

            <button type="submit">

                Login

            </button>

        </form>

        <br>

        <a href="login.jsp"
           class="btn">

            Back to Main Login

        </a>

    </div>

</div>

</body>

</html>