package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.OrderDetailsRepository;
import com.app.dao.OrderRepository;
import com.app.dao.ProductRespository;
import com.app.dao.UserRepository;
import com.app.pojos.OrderDetails;
import com.app.pojos.Orders;
import com.app.pojos.Products;
import com.app.pojos.Status;
import com.app.pojos.User;

@Service
@Transactional
public class SupplierServiceImpl implements ISupplierService {
	
	// dependency
	@Autowired
	private ProductRespository productRepo;
	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private OrderDetailsRepository orderDetailsRepo;
	
	@Override
	public List<Products> getAllProduct(int supplierId) {
		User supplier = userRepo.getOne(supplierId);
		return productRepo.findBySupplier(supplier);
	}

	@Override
	public List<OrderDetails> getCurrentOrders(int supplierId) {
		User supplier = userRepo.getOne(supplierId);
		List<Products> products = getAllProduct(supplierId);
		List<OrderDetails> orderList = new ArrayList<>();
		
		for (Products product : products) {
			List<OrderDetails> productOrderList = orderDetailsRepo.findByProductAndStatus(product, Status.PLACED);
			orderList.addAll(productOrderList);
		}
		
		System.out.println("check here");
		
		for (OrderDetails orderDetails : orderList) {
			System.out.println(orderDetails.getOrder().getId());
		}
		
		return orderList;
	}
	
	@Override
	public List<OrderDetails> getAllOrders(int supplierId) {
		User supplier = userRepo.getOne(supplierId);
		List<Products> products = getAllProduct(supplierId);
		List<OrderDetails> orderList = new ArrayList<>();
		
		for (Products product : products) {
			List<OrderDetails> productOrderList = orderDetailsRepo.findByProduct(product);
			orderList.addAll(productOrderList);
		}
		
		System.out.println("check here");
		
		for (OrderDetails orderDetails : orderList) {
			System.out.println(orderDetails.getOrder().getId());
		}
		
		return orderList;
	}
	
	@Override
	public Orders processCurrOrder(int itemId) {
		
		OrderDetails orderItem = orderDetailsRepo.getOne(itemId);
		
		System.out.println(" order ID  "+ orderItem.getOrder().getId() ); 
		
		Optional<Orders> order = orderRepo.findById(orderItem.getOrder().getId());
		
		Orders currOrder = order.get();
	
		return currOrder;
	}
	
	@Override
	public String setOrderStatus( int itemId,Status status) {
				  
		OrderDetails currItem = orderDetailsRepo.getOne(itemId);	
		Orders currOrder = orderRepo.getOne(currItem.getOrder().getId());
		currItem.setStatus(status);
		orderDetailsRepo.flush();

		List<OrderDetails> orderItems = orderDetailsRepo.findByOrder(currOrder);
		for (OrderDetails item : orderItems) {
			
			switch (item.getStatus()) {
			case CANCELLED:
				currOrder.setStatus(Status.CANCELLED);
				return "Status Updated";
			case SHIPPED:
				currOrder.setStatus(Status.SHIPPED);
				return "Status Updated";
			case PLACED:
				currOrder.setStatus(Status.PLACED);
				return "Status Updated";
			case DELIVERED:
				currOrder.setStatus(Status.DELIVERED);
				break;	
			default:
				break;
			}
		}
		return "Status Updated" ;
	}


}
