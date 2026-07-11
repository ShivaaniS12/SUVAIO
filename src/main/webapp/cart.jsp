
   <%@page import="java.util.List"%>
<%@page import="com.th.model.CartItemView"%>

<%
List<CartItemView> cartItems =
(List<CartItemView>)request.getAttribute("cartItems");

double foodTotal =
(Double)request.getAttribute("foodTotal");

double deliveryFee =
(Double)request.getAttribute("deliveryFee");

double distance =
(Double)request.getAttribute("distance");

double platformFee =
(Double)request.getAttribute("platformFee");

double packingFee =
(Double)request.getAttribute("packingFee");

double grandTotal =
(Double)request.getAttribute("grandTotal");

double protein =
(Double)request.getAttribute("protein");

double carbs =
(Double)request.getAttribute("carbs");

double fats =
(Double)request.getAttribute("fats");

int calories =
(Integer)request.getAttribute("calories");
%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>My Cart | SUVAIO</title>

<link rel="stylesheet"
href="css/cart.css">

<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">

</head>

<body>

<header class="navbar">

<div class="logo">

<a href="HomeServlet">

SUVAIO

</a>

</div>

<div class="nav-links">

<a href="HomeServlet">

<i class="fa-solid fa-house"></i>

Home

</a>

<a href="CartServlet">

<i class="fa-solid fa-cart-shopping"></i>

Cart

</a>

</div>

</header>

<div class="cart-container">

<div class="left-panel">

<h1>

My Cart

</h1>

<%
if(cartItems == null || cartItems.isEmpty()){
%>

<div class="empty-cart">

<i class="fa-solid fa-cart-shopping"></i>

<h2>

Your cart is empty

</h2>
<p>
Looks like you haven't added any delicious food yet.
</p>
<a href="HomeServlet">

Browse Restaurants

</a>

</div>

<%
}else{

for(CartItemView item : cartItems){
%>

<div class="cart-card">

<div class="food-image">

<img src="<%=request.getContextPath()%>/<%=item.getImagePath()%>"
     alt="<%=item.getItemName()%>">

</div>

<div class="food-details">

<h2>

<%=item.getItemName()%>

</h2>

<p class="restaurant">

<%=item.getRestaurantName()%>

</p>

<p class="price">

&#8377; <%=item.getPrice()%>

</p>

<div class="nutrition">

<span>

Protein

<b>

<%=item.getProtein()%> g

</b>

</span>

<span>

Carbs

<b>

<%=item.getCarbs()%> g

</b>

</span>

<span>

Fats

<b>

<%=item.getFats()%> g

</b>

</span>

<span>

Calories

<b>

<%=item.getCalories()%>

</b>

</span>

</div>

<div class="cart-bottom">

<div class="qty-box">

<form
action="DecreaseQuantityServlet"
method="post">

<input
type="hidden"
name="menuId"
value="<%=item.getMenuId()%>">

<button type="submit">

-

</button>

</form>

<span>

<%=item.getQuantity()%>

</span>

<form
action="IncreaseQuantityServlet"
method="post">

<input
type="hidden"
name="menuId"
value="<%=item.getMenuId()%>">

<button type="submit">

+

</button>

</form>

</div>

<div class="subtotal">

&#8377; <%=item.getSubtotal()%>

</div>

</div>

<div class="remove">

<form
action="RemoveCartItemServlet"
method="post">

<input
type="hidden"
name="menuId"
value="<%=item.getMenuId()%>">

<button type="submit">

<i class="fa-solid fa-trash"></i>

Remove

</button>

</form>

</div>

</div>

</div>

<%

}

}

%>
</div>

<!-- RIGHT PANEL -->

<div class="right-panel">

<div class="bill-card">

<h2>

Bill Summary

</h2>

<div class="bill-row">

<span>

Food Total

</span>

<span>

&#8377; <%=String.format("%.2f",foodTotal)%>

</span>

</div>

<div class="bill-row">

<span>

Delivery Distance

</span>

<span>

<%=distance%> km

</span>

</div>

<div class="bill-row">

<span>

Delivery Charge

</span>

<span>

&#8377; <%=String.format("%.2f",deliveryFee)%>

</span>

</div>

<div class="bill-row">

<span>

Platform Fee

</span>

<span class="free">

<%= platformFee == 0 ? "FREE"
        : "₹ " + String.format("%.2f", platformFee) %>

</span>

</div>

<div class="bill-row">

<span>

Packaging

</span>

<span class="free">

<%= packingFee == 0 ? "FREE"
        : "₹ " + String.format("%.2f", packingFee) %>

</span>

</div>

<hr>

<h2>

Nutrition Summary

</h2>

<div class="nutrition-summary">

<div>

<h3>

Protein

</h3>

<p>

<%=String.format("%.1f",protein)%> g

</p>

</div>

<div>

<h3>

Carbs

</h3>

<p>

<%=String.format("%.1f",carbs)%> g

</p>

</div>

<div>

<h3>

Fats

</h3>

<p>

<%=String.format("%.1f",fats)%> g

</p>

</div>

<div>

<h3>

Calories

</h3>

<p>

<%=calories%>

</p>

</div>

</div>

<hr>

<div class="grand-total">

<span>

Grand Total

</span>

<span>

&#8377; <%=String.format("%.2f",grandTotal)%>

</span>

</div>

<a

href="CheckoutServlet"

class="checkout-btn">

Proceed To Checkout

</a>

<a href="HomeServlet"
   class="continue-btn">

Continue Shopping

</a>

</div>

</div>

</div>

</body>

</html>