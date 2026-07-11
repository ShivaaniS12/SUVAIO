<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.List" %>
<%@ page import="com.th.model.Order" %>
<%@ page import="com.th.model.DeliveryAgent" %>

<%
List<Order> orders =
(List<Order>)request.getAttribute("orders");

List<DeliveryAgent> agents =
(List<DeliveryAgent>)request.getAttribute("agents");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Manage Orders</title>

<link rel="stylesheet"
      href="css/admin-style.css">

</head>

<body>



<body>

<div style="width:95%;
            margin:30px auto;">
<div style="margin-bottom:20px;">

    <a href="adminDashboard"
       class="add-btn">

        ← Back To Dashboard

    </a>

</div>

   <h1 style="text-align:center;
           margin-bottom:40px;">

    Assign Delivery Agents

</h1>

    <div class="table-container">

        <table>

            <thead>

            <tr>

                <th>Order ID</th>

                <th>User ID</th>

                <th>Amount</th>

                <th>Status</th>

                <th>Select Agent</th>

                <th>Action</th>

            </tr>

            </thead>

            <tbody>

            <% if(orders != null){ %>

            <% for(Order order : orders){ %>

            <tr>

                <td>
                    #<%= order.getOrderId() %>
                </td>

                <td>
                    <%= order.getUserId() %>
                </td>

                <td>
                    ₹<%= order.getTotalAmount() %>
                </td>

                <td>
                    <%= order.getOrderStatus() %>
                </td>

                <td>

                    <form action="AssignAgentServlet"
                          method="post">

                        <input type="hidden"
                               name="orderId"
                               value="<%= order.getOrderId() %>">

                        <select name="agentId"
                                required>

                            <option value="">
                                Select Agent
                            </option>

                            <% for(DeliveryAgent agent : agents){ %>

                            <% if("ACTIVE".equalsIgnoreCase(
                                    agent.getEmploymentStatus())){ %>

                            <option value="<%= agent.getAgentId() %>">

                                <%= agent.getAgentName() %>
                                (ID:
                                <%= agent.getAgentId() %>)

                            </option>

                            <% } %>

                            <% } %>

                        </select>

                </td>

                <td>

                        <button type="submit">

                            Assign

                        </button>

                    </form>

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