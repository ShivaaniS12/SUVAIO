package com.th.serviceimpl;

import java.util.List;

import com.th.daoimpl.CartDAOImpl;
import com.th.daoimpl.CartItemDAOImpl;
import com.th.daoimpl.OrderDAOImpl;
import com.th.daoimpl.OrderItemDAOImpl;
import com.th.daoimpl.OrderTrackingDAOImpl;
import com.th.model.Cart;
import com.th.model.CartItem;
import com.th.model.Order;
import com.th.model.OrderHistoryView;
import com.th.model.OrderItem;
import com.th.model.OrderTracking;
import com.th.service.OrderService;
import java.util.List;

public class OrderServiceImpl implements OrderService {
	
	private CartDAOImpl cartDAO;
	private CartItemDAOImpl cartItemDAO;
	private OrderDAOImpl orderDAO;
	private OrderItemDAOImpl orderItemDAO;
	private OrderTrackingDAOImpl trackingDAO;
	
	public OrderServiceImpl() {

	    cartDAO = new CartDAOImpl();

	    cartItemDAO = new CartItemDAOImpl();

	    orderDAO = new OrderDAOImpl();

	    orderItemDAO = new OrderItemDAOImpl();

	    trackingDAO = new OrderTrackingDAOImpl();
	}

	@Override
	public int placeOrder(int userId,
	                      int restaurantId,
	                      int addressId,
	                      String paymentMode) {
    	
    	Cart cart = cartDAO.getCartByUserId(userId);
    	
    	if(cart == null) {

    	    throw new RuntimeException(
    	            "Cart not found for User ID : "
    	            + userId);
    	}
    	
    	List<CartItem> cartItems =
    	        cartItemDAO.getCartItemsByCartId(
    	                cart.getCartId()
    	        );
    	
    	
    	if(cartItems == null ||
    			   cartItems.isEmpty()) {

    			    throw new RuntimeException(
    			            "Cart is Empty");
    			}
    	
    	
    	
    	
    	Order order = new Order();
    	order.setUserId(userId);
    	
    	order.setAddressId(addressId);

    	order.setRestaurantId(restaurantId);

    	order.setAgentId(null);

    	order.setTotalAmount(
    	        cart.getTotalAmount());

    	order.setTotalProtein(
    	        cart.getTotalProtein());

    	order.setTotalCarbs(
    	        cart.getTotalCarbs());

    	order.setTotalFats(
    	        cart.getTotalFats());

    	order.setTotalCalories(
    	        cart.getTotalCalories());

    	order.setPaymentMode(paymentMode);

    	order.setOrderStatus("PLACED");
    	
    	
    	int orderId = orderDAO.addOrder(order);
    	if(orderId == 0) {

    	    throw new RuntimeException(
    	            "Order Creation Failed");
    	}
    	
    	
    	for(CartItem cartItem : cartItems) {
    		
    		OrderItem orderItem =
    		        new OrderItem();
    		
    		orderItem.setOrderId(orderId);

    		orderItem.setMenuId(
    		        cartItem.getMenuId());

    		orderItem.setQuantity(
    		        cartItem.getQuantity());

    		orderItem.setSubtotal(
    		        cartItem.getSubtotal());

    		orderItem.setProtein(
    		        cartItem.getProtein());

    		orderItem.setCarbs(
    		        cartItem.getCarbs());

    		orderItem.setFats(
    		        cartItem.getFats());

    		orderItem.setCalories(
    		        cartItem.getCalories());
    		
    		
    		orderItemDAO.addOrderItem(
    		        orderItem);
    		
      	}
    	
    	
    	OrderTracking tracking = new OrderTracking();
    	tracking.setOrderId(orderId);

    	tracking.setStatus("PLACED");

    	tracking.setRemarks(
    	        "Order placed successfully");

    	tracking.setUpdatedBy("USER");
    	
    	trackingDAO.addTracking(tracking);
    	
    	
    	for(CartItem cartItem : cartItems) {

    	    cartItemDAO.deleteCartItem(
    	            cartItem.getCartItemId());
    	}
    	
    	cart.setTotalAmount(0);

    	cart.setTotalProtein(0);

    	cart.setTotalCarbs(0);

    	cart.setTotalFats(0);

    	cart.setTotalCalories(0);
    	
    	cartDAO.updateCart(cart);
    	  	
    	

    	return orderId;
    }

	@Override
	public void assignDeliveryAgent(int orderId, int agentId) {
		// TODO Auto-generated method stub
		Order order =
		        orderDAO.getOrder(orderId);
		if(order == null) {

		    throw new RuntimeException(
		            "Order Not Found");
		}
		
		order.setAgentId(agentId);
		order.setOrderStatus(
		        "AGENT_ASSIGNED");
		orderDAO.updateOrder(order);
		
		OrderTracking tracking =
		        new OrderTracking();
		tracking.setOrderId(orderId);

		tracking.setStatus(
		        "AGENT_ASSIGNED");

		tracking.setRemarks(
		        "Delivery Agent Assigned");

		tracking.setUpdatedBy(
		        "ADMIN");
		trackingDAO.addTracking(
		        tracking);
		
	}

	@Override
	public void updateOrderStatus(int orderId, String status) {
		// TODO Auto-generated method stub
		
		   Order order =
		            orderDAO.getOrder(orderId);

		    if(order == null) {

		        throw new RuntimeException(
		                "Order Not Found");
		    }

		    order.setOrderStatus(status);

		    orderDAO.updateOrder(order);
		    
		    OrderTracking tracking =
		            new OrderTracking();
		    
		    tracking.setOrderId(orderId);
		    tracking.setStatus(status);
		    tracking.setRemarks(
		            "Order status changed to "
		            + status);
		    tracking.setUpdatedBy("SYSTEM");
		    trackingDAO.addTracking(
		            tracking);
		
	}

	@Override
	public void cancelOrder(int orderId) {
		// TODO Auto-generated method stub
		
		Order order =
		        orderDAO.getOrder(orderId);
		if(order == null) {

		    throw new RuntimeException(
		            "Order Not Found");
		}
		order.setOrderStatus(
		        "CANCELLED");
		orderDAO.updateOrder(order);
		
		OrderTracking tracking =
		        new OrderTracking();
		tracking.setOrderId(orderId);

		tracking.setStatus("CANCELLED");

		tracking.setRemarks(
		        "Order Cancelled");

		tracking.setUpdatedBy(
		        "SYSTEM");
		
		trackingDAO.addTracking(
		        tracking);
		
		
		
	}

	@Override
	public List<Order> getOrdersByRestaurant(
	        int restaurantId) {

	    return orderDAO.getOrdersByRestaurant(
	            restaurantId);
	}

	@Override
	public Order getOrder(
	        int orderId) {

	    return orderDAO.getOrder(
	            orderId);
	}
	
	@Override
	public int getTodayOrderCountByRestaurant(
	        int restaurantId) {

	    return orderDAO
	            .getTodayOrderCountByRestaurant(
	                    restaurantId);
	}

	@Override
	public double getTodayRevenueByRestaurant(
	        int restaurantId) {

	    return orderDAO
	            .getTodayRevenueByRestaurant(
	                    restaurantId);
	}

	@Override
	public double getWeeklyRevenueByRestaurant(
	        int restaurantId) {

	    return orderDAO
	            .getWeeklyRevenueByRestaurant(
	                    restaurantId);
	}

	@Override
	public double getMonthlyRevenueByRestaurant(
	        int restaurantId) {

	    return orderDAO
	            .getMonthlyRevenueByRestaurant(
	                    restaurantId);
	}
	
	@Override
	public List<Order> getRecentOrdersByRestaurant(
	        int restaurantId) {

	    return orderDAO
	            .getRecentOrdersByRestaurant(
	                    restaurantId);
	}

	@Override
	public String getMostOrderedItem(
	        int restaurantId) {

	    return orderDAO
	            .getMostOrderedItem(
	                    restaurantId);
	}
	
	@Override
	public int getDeliveredOrdersCountByRestaurant(
	        int restaurantId) {

	    return orderDAO
	            .getDeliveredOrdersCountByRestaurant(
	                    restaurantId);
	}

	@Override
	public double getAverageOrderValueByRestaurant(
	        int restaurantId) {

	    return orderDAO
	            .getAverageOrderValueByRestaurant(
	                    restaurantId);
	}
	
	@Override
	public int getTotalOrdersByRestaurant(
	        int restaurantId) {

	    return orderDAO
	            .getTotalOrdersByRestaurant(
	                    restaurantId);
	}

	@Override
	public int getCancelledOrdersByRestaurant(
	        int restaurantId) {

	    return orderDAO
	            .getCancelledOrdersByRestaurant(
	                    restaurantId);
	}

	@Override
	public int getUniqueCustomersByRestaurant(
	        int restaurantId) {

	    return orderDAO
	            .getUniqueCustomersByRestaurant(
	                    restaurantId);
	}

	@Override
	public String getMostOrderedCategory(
	        int restaurantId) {

	    return orderDAO
	            .getMostOrderedCategory(
	                    restaurantId);
	}
	
	@Override
	public List<Order> getAssignedOrdersByAgent(
	        int agentId) {

	    return orderDAO
	            .getAssignedOrdersByAgent(
	                    agentId);
	}

	@Override
	public int getAssignedOrderCount(
	        int agentId) {

	    return orderDAO
	            .getAssignedOrderCount(
	                    agentId);
	}

	@Override
	public int getDeliveredOrderCount(
	        int agentId) {

	    return orderDAO
	            .getDeliveredOrderCount(
	                    agentId);
	}

	@Override
	public int getPickedUpOrderCount(
	        int agentId) {

	    return orderDAO
	            .getPickedUpOrderCount(
	                    agentId);
	}
	
	@Override
	public List<Order> getPickedUpOrdersByAgent(
	        int agentId) {

	    return orderDAO
	            .getPickedUpOrdersByAgent(
	                    agentId);
	}
	
	@Override
	public List<Order> getOutForDeliveryOrdersByAgent(
	        int agentId) {

	    return orderDAO
	            .getOutForDeliveryOrdersByAgent(
	                    agentId);
	}
	
	@Override
	public List<Order> getDeliveredOrdersByAgent(
	        int agentId) {

	    return orderDAO
	            .getDeliveredOrdersByAgent(
	                    agentId);
	}
	
	@Override
	public int getTotalDeliveriesByAgent(
	        int agentId) {

	    return orderDAO
	            .getTotalDeliveriesByAgent(
	                    agentId);
	}

	@Override
	public int getTodayDeliveriesByAgent(
	        int agentId) {

	    return orderDAO
	            .getTodayDeliveriesByAgent(
	                    agentId);
	}
	
	@Override
	public List<Order> getUnassignedOrders() {

	    return orderDAO
	            .getUnassignedOrders();
	}
	
	@Override
	public List<Order> getOrdersByUser(int userId) {

	    return orderDAO.getOrdersByUser(userId);

	}
	
	@Override
	public List<OrderHistoryView> getOrderHistoryByUser(int userId){

	    return orderDAO.getOrderHistoryByUser(userId);

	}
}