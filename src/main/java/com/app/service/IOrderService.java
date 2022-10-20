package com.app.service;

import java.util.List;

import com.app.pojos.Orders;
import com.app.pojos.Status;

public interface IOrderService {

	String placeOrder(int userId);	
	
	List<Orders> prevOrders(int userId);

	String cancelOrder(int orderId);
	
	String checkOrderStatus(int orderId);

	Orders getSingleOrder(int orderId);

	
}
