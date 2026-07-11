<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.th.model.Menu" %>

<%
Menu menu = (Menu) request.getAttribute("menu");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Edit Food Item</title>

<link rel="stylesheet"
href="css/restaurant-dashboard.css">

<link rel="preconnect"
href="https://fonts.googleapis.com">

<link rel="preconnect"
href="https://fonts.gstatic.com"
crossorigin>

<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
rel="stylesheet">

</head>

<body>

<div class="sidebar">

    <h2>TastyHop</h2>

    <a href="RestaurantDashboardServlet">Dashboard</a>

    <a href="ManageMenuServlet">Manage Menu</a>

</div>

<div class="main-content">

    <div class="header">

        <h1>Edit Food Item</h1>

    </div>

    <div class="form-container">

        <form action="UpdateFoodServlet"
              method="post">

            <input type="hidden"
                   name="menuId"
                   value="<%= menu.getMenuId() %>">

            <div class="form-grid">

                <div class="form-group">

                    <label>Food Name</label>

                    <input type="text"
                           name="itemName"
                           value="<%= menu.getItemName() %>"
                           required>

                </div>

                <div class="form-group">

                    <label>Category</label>

                    <select name="category">

                        <option value="Veg"
                        <%= menu.getCategory().equals("Veg") ? "selected" : "" %>>
                        Veg
                        </option>

                        <option value="Non-Veg"
                        <%= menu.getCategory().equals("Non-Veg") ? "selected" : "" %>>
                        Non-Veg
                        </option>

                        <option value="Beverage"
                        <%= menu.getCategory().equals("Beverage") ? "selected" : "" %>>
                        Beverage
                        </option>

                        <option value="Dessert"
                        <%= menu.getCategory().equals("Dessert") ? "selected" : "" %>>
                        Dessert
                        </option>

                    </select>

                </div>

                <div class="form-group">

                    <label>Price</label>

                    <input type="number"
                           step="0.01"
                           name="price"
                           value="<%= menu.getPrice() %>">

                </div>

                <div class="form-group">

                    <label>Calories</label>

                    <input type="number"
                           name="calories"
                           value="<%= menu.getCalories() %>">

                </div>

                <div class="form-group">

                    <label>Protein</label>

                    <input type="number"
                           step="0.01"
                           name="protein"
                           value="<%= menu.getProtein() %>">

                </div>

                <div class="form-group">

                    <label>Carbs</label>

                    <input type="number"
                           step="0.01"
                           name="carbs"
                           value="<%= menu.getCarbs() %>">

                </div>

                <div class="form-group">

                    <label>Fats</label>

                    <input type="number"
                           step="0.01"
                           name="fats"
                           value="<%= menu.getFats() %>">

                </div>

                <div class="form-group">

                    <label>Image Path</label>

                    <input type="text"
                           name="imagePath"
                           value="<%= menu.getImagePath() %>">

                </div>

                <div class="form-group full-width">

                    <label>Description</label>

                    <textarea
                        name="description"
                        rows="4"><%= menu.getDescription() %></textarea>

                </div>

                <div class="form-group">

                    <label>Availability</label>

                    <select name="isAvailable">

                        <option value="true"
                        <%= menu.isAvailable() ? "selected" : "" %>>
                        Available
                        </option>

                        <option value="false"
                        <%= !menu.isAvailable() ? "selected" : "" %>>
                        Unavailable
                        </option>

                    </select>

                </div>

            </div>

            <div class="btn-container">

                <button type="submit"
                        class="save-btn">

                    Update Food

                </button>

                <a href="ManageMenuServlet"
                   class="cancel-btn">

                    Cancel

                </a>

            </div>

        </form>

    </div>

</div>

</body>
</html>