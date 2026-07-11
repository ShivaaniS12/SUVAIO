<%@page import="java.util.List"%>
<%@page import="com.th.model.Restaurant"%>
<%@page import="com.th.model.User"%>

<%
User user =
(User)request.getAttribute("user");

List<Restaurant> restaurants =
(List<Restaurant>)request.getAttribute("restaurants");
%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>SUVAIO</title>

<link rel="stylesheet"
href="css/home.css">

<link rel="preconnect"
href="https://fonts.googleapis.com">

<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
rel="stylesheet">

<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">

</head>


<script>

window.onload = function () {

    if (!navigator.geolocation) {
        return;
    }

    navigator.geolocation.getCurrentPosition(async function (position) {

        const latitude = position.coords.latitude;
        const longitude = position.coords.longitude;

        let locationName = "Current Location";
        let fullAddress = "";

        try {

            const response = await fetch(
                "https://nominatim.openstreetmap.org/reverse?format=json&lat="
                + latitude +
                "&lon="
                + longitude
            );

            const data = await response.json();

            fullAddress = data.display_name || "";

            if (data.address) {

                locationName =
                    data.address.suburb ||
                    data.address.neighbourhood ||
                    data.address.city ||
                    data.address.town ||
                    data.address.village ||
                    "Current Location";

            }

        } catch (e) {

            console.log(e);

        }

        fetch("LocationServlet", {

            method: "POST",

            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },

            body:
                "latitude=" + latitude +
                "&longitude=" + longitude +
                "&locationName=" + encodeURIComponent(locationName) +
                "&fullAddress=" + encodeURIComponent(fullAddress)

        });

    });

}

</script>

<body>

<header class="navbar">

    <div class="logo">
        SUVAIO
    </div>

    <div class="location">

        <i class="fa-solid fa-location-dot"></i>

        <select>

            <option><%=session.getAttribute("userLocation")==null
?
"Detecting..."
:
session.getAttribute("userLocation")%></option>

            <option>Home</option>

            <option>Office</option>

            <option>Add New Address</option>

        </select>

    </div>

    <div class="search-box">

        <i class="fa-solid fa-magnifying-glass"></i>

        <input
                type="text"
                placeholder="Search restaurants, dishes, cuisines...">

    </div>

    <div class="nav-right">

        <a href="#">
            <i class="fa-solid fa-tags"></i>
            Offers
        </a>

        <a href="MyOrdersServlet">
    <i class="fa-solid fa-box"></i>
    Orders
</a>

        <a href="CartServlet">

<i class="fa-solid fa-cart-shopping"></i>

Cart

(<%=request.getAttribute("cartCount")%>)

</a>

       <div class="profile-dropdown">

    <button
        class="profile-btn"
        onclick="toggleDropdown(event)">

        <i class="fa-solid fa-user"></i>

        <%=user.getUsername()%>

        <i class="fa-solid fa-chevron-down"></i>

    </button>

    <div id="profileMenu"
         class="dropdown-content">

        <a href="ProfileServlet">

            <i class="fa-solid fa-user"></i>

            Profile

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

</div>

</div>

        </div>

    </div>

</header>

<section class="hero">

    <div class="hero-left">

        <span class="offer-tag">

             Flat 50% OFF

        </span>

        <h1>

            Discover Delicious Food

            <span>

                Delivered Fast

            </span>

        </h1>

        <p>

            Order from your favourite restaurants,

            track your delivery live,

            and enjoy delicious meals

            delivered right to your doorstep.

        </p>

        <button>

            Order Now

        </button>

    </div>

    <div class="hero-right">

        <img
        src="images/background.jpeg"
        alt="Food Banner">

    </div>

</section>

<section class="velocity-section">

    <h2>What's on your mind?</h2>

    <div class="velocity-wrapper">

        <div class="velocity-track">

            <!-- First Set -->

            <div class="food-item">
                <img src="images/categories/pizza.png">
                <span>Pizza</span>
            </div>

            <div class="food-item">
                <img src="images/categories/burger.png">
                <span>Burger</span>
            </div>

            <div class="food-item">
                <img src="images/categories/biryani.png">
                <span>Biryani</span>
            </div>

            <div class="food-item">
                <img src="images/categories/chinese.png">
                <span>Chinese</span>
            </div>

            <div class="food-item">
                <img src="images/categories/dosa.png">
                <span>Dosa</span>
            </div>

            <div class="food-item">
                <img src="images/categories/idli.png">
                <span>Idli</span>
            </div>

            <div class="food-item">
                <img src="images/categories/tea.png">
                <span>Tea</span>
            </div>

            <div class="food-item">
                <img src="images/categories/south-indian.png">
                <span>South Indian</span>
            </div>

            <div class="food-item">
                <img src="images/categories/north-indian.png">
                <span>North Indian</span>
            </div>

            <div class="food-item">
                <img src="images/categories/pasta.png">
                <span>Pasta</span>
            </div>

            <div class="food-item">
                <img src="images/categories/rolls.png">
                <span>Rolls</span>
            </div>

            <div class="food-item">
                <img src="images/categories/desserts.png">
                <span>Desserts</span>
            </div>

            <div class="food-item">
                <img src="images/categories/cakes.png">
                <span>Cakes</span>
            </div>

            <div class="food-item">
                <img src="images/categories/sandwich.png">
                <span>Sandwich</span>
            </div>

            <div class="food-item">
                <img src="images/categories/ice-cream.png">
                <span>Ice Cream</span>
            </div>

            <div class="food-item">
                <img src="images/categories/juice.png">
                <span>Juice</span>
            </div>

            <div class="food-item">
                <img src="images/categories/vada-pav.png">
                <span>Vada Pav</span>
            </div>

            <div class="food-item">
                <img src="images/categories/aloo-paratha.png">
                <span>Aloo Paratha</span>
            </div>

            <!-- Duplicate Everything Again -->

            <div class="food-item">
                <img src="images/categories/pizza.png">
                <span>Pizza</span>
            </div>

            <div class="food-item">
                <img src="images/categories/burger.png">
                <span>Burger</span>
            </div>

            <div class="food-item">
                <img src="images/categories/biryani.png">
                <span>Biryani</span>
            </div>

            <div class="food-item">
                <img src="images/categories/chinese.png">
                <span>Chinese</span>
            </div>

            <div class="food-item">
                <img src="images/categories/dosa.png">
                <span>Dosa</span>
            </div>

            <div class="food-item">
                <img src="images/categories/idli.png">
                <span>Idli</span>
            </div>

            <div class="food-item">
                <img src="images/categories/tea.png">
                <span>Tea</span>
            </div>

            <div class="food-item">
                <img src="images/categories/south-indian.png">
                <span>South Indian</span>
            </div>

            <div class="food-item">
                <img src="images/categories/north-indian.png">
                <span>North Indian</span>
            </div>

            <div class="food-item">
                <img src="images/categories/pasta.png">
                <span>Pasta</span>
            </div>

            <div class="food-item">
                <img src="images/categories/rolls.png">
                <span>Rolls</span>
            </div>

            <div class="food-item">
                <img src="images/categories/desserts.png">
                <span>Desserts</span>
            </div>

            <div class="food-item">
                <img src="images/categories/cakes.png">
                <span>Cakes</span>
            </div>

            <div class="food-item">
                <img src="images/categories/sandwich.png">
                <span>Sandwich</span>
            </div>

            <div class="food-item">
                <img src="images/categories/ice-cream.png">
                <span>Ice Cream</span>
            </div>

            <div class="food-item">
                <img src="images/categories/juice.png">
                <span>Juice</span>
            </div>

        </div>

    </div>

</section>

<section class="restaurants">

    <div class="section-header">

        <h2>Popular Restaurants</h2>

        <a href="#">View All</a>

    </div>

    <div class="restaurant-grid">

    <%
    for(Restaurant r : restaurants){

       
    %>

        <div class="restaurant-card">

    <div class="restaurant-image">

      <img src="<%= r.getImagePath() %>"
     alt="<%= r.getName() %>">

        <div class="rating-badge">

            <i class="fa-solid fa-star"></i>

            <%=r.getRating()%>

        </div>

        <div class="wishlist">

            <i class="fa-regular fa-heart"></i>

        </div>

    </div>

    <div class="restaurant-content">

        <h3>

            <%=r.getName()%>

        </h3>

        <p class="cuisine">

            <%=r.getCuisineType()%>

        </p>

       <div class="restaurant-details">

    <div class="delivery-time">

        <i class="fa-regular fa-clock"></i>

        <span><%=r.getDeliveryTime()%></span>

    </div>

    <div class="restaurant-address">

        <i class="fa-solid fa-location-dot"></i>

        <span><%=r.getAddress()%></span>

    </div>

</div>

        <a href="RestaurantMenuServlet?restaurantId=<%=r.getRestaurantId()%>"
           class="menu-btn">

            View Menu

        </a>

    </div>

</div>
        

    <%
    }
    %>

    </div>

</section>

<script>

function toggleDropdown(event){

    event.stopPropagation();

    document.getElementById("profileMenu")
            .classList.toggle("show");

}

window.onclick=function(){

    document.getElementById("profileMenu")
            .classList.remove("show");

}

</script>

</body>

</html>