<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.th.model.Menu"%>
<%@page import="com.th.model.Restaurant"%>
<%@page import="java.util.Map"%>


<%
Restaurant restaurant =
(Restaurant)request.getAttribute("restaurant");

List<Menu> menuList =(List<Menu>)request.getAttribute("menuList");

Map<Integer,Integer> cartMap =(Map<Integer,Integer>)request.getAttribute("cartMap");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title><%=restaurant.getName()%></title>

<link rel="stylesheet"
href="css/restaurant-menu.css">

<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">

<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
rel="stylesheet">

</head>

<body>

<!-- ================= NAVBAR ================= -->

<nav class="navbar">

    <a href="HomeServlet" class="back-btn">

        <i class="fa-solid fa-arrow-left"></i>

        Back

    </a>

    <h2>SUVAIO</h2>

   <a href="<%=request.getContextPath()%>/CartServlet" class="cart-btn">

    <i class="fa-solid fa-cart-shopping"></i>

    Cart (<%=request.getAttribute("cartCount")%>)

</a>

</nav>

<!-- ================= RESTAURANT HEADER ================= -->

<section class="restaurant-header">

    <img src="<%=restaurant.getImagePath()%>">

    <div class="restaurant-info">

        <h1>

            <%=restaurant.getName()%>

        </h1>

        <p>

            <%=restaurant.getCuisineType()%>

        </p>

        <div class="restaurant-meta">

            <span>

                <i class="fa-solid fa-star"></i>

                <%=restaurant.getRating()%>

            </span>

            <span>

                <i class="fa-regular fa-clock"></i>

                <%=restaurant.getDeliveryTime()%>

            </span>

        </div>

        <p>

            <i class="fa-solid fa-location-dot"></i>

            <%=restaurant.getAddress()%>

        </p>

    </div>

</section>

<!-- ================= SEARCH ================= -->

<section class="search-section">

<input
type="text"
placeholder="Search food inside restaurant...">

</section>

<!-- ================= MENU ================= -->

<section class="menu-container" id="menuSection">

<%
for(Menu m : menuList){
%>

<div class="food-card">

<div class="food-image">

<img src="<%=m.getImagePath()%>" alt="<%=m.getItemName()%>">

</div>

<div class="food-details">

<h2>

<%=m.getItemName()%>

</h2>

<p class="category">

<%=m.getCategory()%>

</p>

<div class="rating">

<i class="fa-solid fa-star"></i>

<%=m.getRating()%>

</div>

<p class="description">

<%=m.getDescription()%>

</p>

<div class="nutrition">

<span>

🥩 <%=m.getProtein()%>g Protein

</span>

<span>

🍚 <%=m.getCarbs()%>g Carbs

</span>

<span>

🧈 <%=m.getFats()%>g Fat

</span>

<span>

🔥 <%=m.getCalories()%> Cal

</span>

</div>

<div class="bottom">

<h3>

₹ <%=m.getPrice()%>

</h3>

<%

Integer qty =
cartMap.get(m.getMenuId());

if(qty==null){

%>

<form action="AddToCartServlet"
method="post">

<input type="hidden"
name="menuId"
value="<%=m.getMenuId()%>">

<button
class="add-btn">

Add to Cart

</button>

</form>

<%

}else{

%>

<div class="qty-box">

<form
action="DecreaseQuantityServlet"
method="post">

<input
type="hidden"
name="menuId"
value="<%=m.getMenuId()%>">

<button>

-

</button>

</form>

<span>

<%=qty%>

</span>

<form
action="IncreaseQuantityServlet"
method="post">

<input
type="hidden"
name="menuId"
value="<%=m.getMenuId()%>">

<button>

+

</button>

</form>

</div>

<%

}

%>

</div>

</div>

</div>

<%
}
%>

</section>

</body>

</html>