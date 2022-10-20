package com.app.service;

import java.util.List;

import com.app.pojos.OrderDetails;
import com.app.pojos.Orders;
import com.app.pojos.Products;
import com.app.pojos.Status;

public interface ISupplierService {

	List<Products> getAllProduct(int supplierId);

	List<OrderDetails> getAllOrders(int supplierId);

	List<OrderDetails> getCurrentOrders(int supplierId);

	Orders processCurrOrder(int itemId);

	String setOrderStatus(int itemId, Status status);

}
