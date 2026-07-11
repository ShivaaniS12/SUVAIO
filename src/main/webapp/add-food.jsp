<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Add Food Item</title>

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

    <a href="RestaurantOrdersServlet">Orders </a>

    <a href="#">Revenue</a>

   <a href="RestaurantAnalyticsServlet">
    Analytics
</a>

</div>

<div class="main-content">

    <div class="header">

        <h1>Add Food Item</h1>

    </div>

    <div class="form-container">

        <form action="AddFoodServlet"
              method="post">

            <div class="form-grid">

                <div class="form-group">
                    <label>Food Name</label>
                    <input type="text"
                           name="itemName"
                           required>
                </div>

                <div class="form-group">
                    <label>Category</label>

                    <select name="category">

                        <option>Veg</option>
                        <option>Non-Veg</option>
                        <option>Beverage</option>
                        <option>Dessert</option>

                    </select>
                </div>

                <div class="form-group">
                    <label>Price</label>

                    <input type="number"
                           step="0.01"
                           name="price"
                           required>
                </div>

                <div class="form-group">
                    <label>Calories</label>

                    <input type="number"
                           name="calories">
                </div>

                <div class="form-group">
                    <label>Protein (g)</label>

                    <input type="number"
                           step="0.01"
                           name="protein">
                </div>

                <div class="form-group">
                    <label>Carbs (g)</label>

                    <input type="number"
                           step="0.01"
                           name="carbs">
                </div>

                <div class="form-group">
                    <label>Fats (g)</label>

                    <input type="number"
                           step="0.01"
                           name="fats">
                </div>

                <div class="form-group">
                    <label>Image Path</label>

                    <input type="text"
                           name="imagePath"
                           placeholder="images/menu/food.jpg">
                </div>

                <div class="form-group full-width">

                    <label>Description</label>

                    <textarea
                            name="description"
                            rows="4">
                    </textarea>

                </div>

                <div class="form-group">

                    <label>Availability</label>

                    <select name="isAvailable">

                        <option value="true">
                            Available
                        </option>

                        <option value="false">
                            Unavailable
                        </option>

                    </select>

                </div>

            </div>

            <div class="btn-container">

                <button type="submit"
                        class="save-btn">

                    Add Food

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