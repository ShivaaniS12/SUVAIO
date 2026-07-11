<%@ page import="java.util.List" %>
<%@ page import="com.th.model.Menu" %>
<%@ page contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8"%>

<%
List<Menu> menuList =
(List<Menu>)request.getAttribute("menuList");
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Manage Menu</title>

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

    <h2>Suvaio</h2>

    <a href="RestaurantDashboardServlet">
        Dashboard
    </a>

    <a href="ManageMenuServlet">
        Manage Menu
    </a>

   <a href="RestaurantOrdersServlet">Orders</a>

    <a href="#">
        Revenue
    </a>

    <a href="RestaurantAnalyticsServlet">
    Analytics
</a>

</div>

<div class="main-content">

    <div class="top-bar">

        <h1>Manage Menu</h1>

        <a href="add-food.jsp"
           class="add-btn">

            + Add Food Item

        </a>

    </div>

   <div class="search-box">

    <form action="ManageMenuServlet"
          method="get">

        <input type="text"
               name="keyword"
               placeholder="Search Food Item...">

        <button type="submit"
                class="search-btn">

            Search

        </button>

    </form>

</div>
    <div class="table-container">

        <table>

            <thead>

            <tr>

                <th>Image</th>
                <th>Food Name</th>
                <th>Category</th>
                <th>Price</th>
                <th>Calories</th>
                <th>Protein</th>
                <th>Carbs</th>
                <th>Fats</th>
                <th>Status</th>
                <th>Actions</th>

            </tr>

            </thead>

            <tbody>

            <% if(menuList != null){ %>

            <% for(Menu menu : menuList){ %>

            <tr>

                <td>

                    <img src="<%= request.getContextPath() %>/<%= menu.getImagePath() %>"
     class="food-img">

                </td>

                <td>
                    <%= menu.getItemName() %>
                </td>

                <td>
                    <%= menu.getCategory() %>
                </td>

                <td>
                   &#8377;<%= menu.getPrice() %>
                </td>

                <td>
                    <%= menu.getCalories() %>
                </td>

                <td>
                    <%= menu.getProtein() %> g
                </td>

                <td>
                    <%= menu.getCarbs() %> g
                </td>

                <td>
                    <%= menu.getFats() %> g
                </td>

               <td>

    <span class="<%= menu.isAvailable()
            ? "status-active"
            : "status-inactive" %>">

        <%= menu.isAvailable()
            ? "Available"
            : "Unavailable" %>

    </span>

</td>

          <td>

<div class="action-buttons">

<a href="EditFoodServlet?menuId=<%= menu.getMenuId() %>"
   class="edit-btn">
    Edit
</a>

<a href="ToggleAvailabilityServlet?menuId=<%= menu.getMenuId() %>&available=<%= !menu.isAvailable() %>"
   class="<%= menu.isAvailable()
           ? "deactivate-btn"
           : "activate-btn" %>">

    <%= menu.isAvailable()
        ? "Deactivate"
        : "Activate" %>

</a>

</div>

</td>

            </tr>

            <% } %>

            <% } %>

            </tbody>

        </table>

    </div>

</div>

</body>
</html>