<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Delivery Agent</title>

<link rel="stylesheet" href="css/admin-style.css">

</head>

<body>

<div class="container">

<a href="manageDeliveryAgents" class="back">
Back
</a>

<h2>Add Delivery Agent</h2>

<form action="addDeliveryAgent" method="post">

<input type="text"
       name="agentName"
       placeholder="Agent Name"
       required>

<input type="email"
       name="email"
       placeholder="Email"
       required>

<input type="text"
       name="phone"
       placeholder="Phone"
       required>

<input type="password"
       name="password"
       placeholder="Password"
       required>

<select name="vehicleType">

<option value="Bike">Bike</option>
<option value="Scooter">Scooter</option>
<option value="Car">Car</option>

</select>

<input type="text"
       name="vehicleNumber"
       placeholder="Vehicle Number"
       required>

<input type="number"
       name="rating"
       step="0.1"
       min="0"
       max="5"
       placeholder="Enter Rating"
       required>

<button type="submit">
Add Agent
</button>

</form>

</div>

</body>
</html>