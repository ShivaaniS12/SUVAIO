<%@page import="java.util.List"%>
<%@page import="com.th.model.CartItemView"%>
<%@page import="com.th.model.User"%>
<%@page import="com.th.model.DeliveryAddress"%>

<%
User user =
(User)request.getAttribute("user");

List<CartItemView> cartItems =
(List<CartItemView>)request.getAttribute("cartItems");

List<DeliveryAddress> addresses =
(List<DeliveryAddress>)request.getAttribute("addresses");

double foodTotal =
(Double)request.getAttribute("foodTotal");

double deliveryFee =
(Double)request.getAttribute("deliveryFee");

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

<title>

Checkout | SUVAIO

</title>

<link rel="stylesheet"
href="css/checkout.css">

<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">

</head>

<body>

<nav class="navbar">

<h2>

SUVAIO

</h2>

<a href="CartServlet">

<i class="fa-solid fa-arrow-left"></i>

Back To Cart

</a>

</nav>

<div class="checkout-container">

<h1>

Checkout

</h1>

<form
action="PlaceOrderServlet"
method="post">
<div class="card">

<h2>

<i class="fa-solid fa-location-dot"></i>

Delivery Address

</h2>

<!-- Current Location -->

<div class="address-card">

<label>

<input
type="radio"
name="addressId"
value="0"
checked>

<div>

<h3>

<i class="fa-solid fa-location-dot"></i>

Current Location

</h3>

<p>

<%=session.getAttribute("userFullAddress")==null
?
"Location not detected"
:
session.getAttribute("userFullAddress")%>

</p>

</div>

</label>

</div>

<!-- Saved Addresses -->

<%
if(addresses!=null){

for(DeliveryAddress address : addresses){
%>

<div class="address-card">

<label>

<input
type="radio"
name="addressId"
value="<%=address.getAddressId()%>">

<div>

<h3>

<%
String type =
address.getAddressType();

if(type.equalsIgnoreCase("HOME")){
%>

 Home

<%
}
else if(type.equalsIgnoreCase("WORK")){
%>

 Work

<%
}
else{
%>

 Other

<%
}
%>

</h3>

<p>

<%=address.getHouseNo()%>,

<%=address.getLandmark()%>,

<%=address.getCity()%>,

<%=address.getState()%>

-

<%=address.getPincode()%>

</p>

</div>

</label>

</div>

<%
}
}
%>

<button
type="button"
class="add-address-btn"
onclick="openAddressModal()">

<i class="fa-solid fa-plus"></i>

Add New Address

</button>

</div>
<div class="card">

<h2>

Delivery Details

</h2>

<div class="delivery-info">

<div>

<i class="fa-solid fa-route"></i>

<h3>

Distance

</h3>

<p>

<%=request.getAttribute("distance")%> km

</p>

</div>

<div>

<i class="fa-solid fa-motorcycle"></i>

<h3>

Delivery Time

</h3>

<p>

<%=request.getAttribute("deliveryTime")%>

</p>

</div>

</div>

</div>
<div class="card">

<h2>

Your Order

</h2>

<%
for(CartItemView item : cartItems){
%>

<div class="checkout-item">

<img
src="<%=request.getContextPath()%>/<%=item.getImagePath()%>"
alt="<%=item.getItemName()%>">

<div class="item-details">

<h3>

<%=item.getItemName()%>

</h3>

<p>

<%=item.getRestaurantName()%>

</p>

<p>

Quantity :
<b><%=item.getQuantity()%></b>

</p>

</div>

<div class="price">

&#8377;

<%=String.format("%.2f",
item.getSubtotal())%>

</div>

</div>

<%
}
%>

</div>
<div class="card">

<h2>

Nutrition Summary

</h2>

<div class="nutrition-grid">

<div>

Protein

<h3>

<%=protein%> g

</h3>

</div>

<div>

Carbs

<h3>

<%=carbs%> g

</h3>

</div>

<div>

Fats

<h3>

<%=fats%> g

</h3>

</div>

<div>

Calories

<h3>

<%=calories%>

</h3>

</div>

</div>

</div>
<div class="card">

<h2>

Payment Method

</h2>

<label class="payment-option">

<input
type="radio"
name="paymentMode"
value="COD"
checked>

Cash On Delivery

</label>

<label class="payment-option">

<input
type="radio"
name="paymentMode"
value="UPI">

UPI

</label>

<label class="payment-option">

<input
type="radio"
name="paymentMode"
value="CARD">

Credit / Debit Card

</label>

<label class="payment-option">

<input
type="radio"
name="paymentMode"
value="NET_BANKING">

Net Banking

</label>

</div>
<div class="card">

<h2>

Bill Summary

</h2>

<div class="row">

<span>

Food Total

</span>

<span>

&#8377;

<%=String.format("%.2f",
foodTotal)%>

</span>

</div>

<div class="row">

<span>

Delivery Fee

</span>

<span>

&#8377;

<%=String.format("%.2f",
deliveryFee)%>

</span>

</div>

<div class="row">

<span>

Platform Fee

</span>

<span>

FREE

</span>

</div>

<div class="row">

<span>

Packaging

</span>

<span>

FREE

</span>

</div>

<hr>

<div class="grand-total">

<span>

Grand Total

</span>

<span>

&#8377;

<%=String.format("%.2f",
grandTotal)%>

</span>

</div>

<button
type="submit"
class="place-btn">

<i class="fa-solid fa-bag-shopping"></i>

Place Order

</button>

</div>

</form>
<div id="addressModal"
class="modal">

<div class="modal-content">

<h2>

<i class="fa-solid fa-location-dot"></i>

Add Delivery Address

</h2>

<form
action="AddDeliveryAddressServlet"
method="post">

<label>

Address Type

</label>

<select
name="addressType"
required>

<option value="HOME">

🏠 Home

</option>

<option value="WORK">

🏢 Work

</option>

<option value="OTHER">

📌 Other

</option>

</select>

<label>

Receiver Name

</label>

<input
type="text"
name="receiverName"
placeholder="Enter Receiver Name"
required>

<label>

Mobile Number

</label>

<input
type="text"
name="mobile"
placeholder="Enter Mobile Number"
required>

<label>

House / Flat No

</label>

<input
type="text"
name="houseNo"
placeholder="Flat No / House No"
required>

<label>

Area / Landmark

</label>

<input
type="text"
name="area"
placeholder="Area / Landmark"
required>

<label>

City

</label>

<input
type="text"
name="city"
placeholder="City"
required>

<label>

State

</label>

<input
type="text"
name="state"
placeholder="State"
required>

<label>

PIN Code

</label>

<input
type="text"
name="pincode"
placeholder="PIN Code"
required>

<div class="modal-buttons">

<button
type="submit"
class="save-address-btn">

<i class="fa-solid fa-floppy-disk"></i>

Save Address

</button>

<button
type="button"
class="cancel-btn"
onclick="closeAddressModal()">

Cancel

</button>

</div>

</form>

</div>

</div>
<script>

function openAddressModal(){

document
.getElementById("addressModal")
.style.display="flex";

}

function closeAddressModal(){

document
.getElementById("addressModal")
.style.display="none";

}

window.onclick=function(event){

const modal =
document.getElementById(
"addressModal");

if(event.target==modal){

modal.style.display="none";

}

}

</script>
</div>

</body>

</html>