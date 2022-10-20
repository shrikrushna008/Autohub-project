package com.app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.app.dao.CartRepository;
import com.app.dao.OrderDetailsRepository;
import com.app.dao.OrderRepository;
import com.app.dao.ProductRespository;
import com.app.dao.UserRepository;
import com.app.pojos.Cart;
import com.app.pojos.OrderDetails;
import com.app.pojos.Orders;
import com.app.pojos.Products;
import com.app.pojos.Status;
import com.app.pojos.User;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {

	// dependency 
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private OrderDetailsRepository orderDetailsRepo;
	
	@Autowired
	private ProductRespository productRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	// place order from cart and remove cart entries
	// make new entries in orders & order details table
	@Override
	public String placeOrder(int userId) {
		Optional<User> currUser = userRepo.findById(userId);
		User user=currUser.get();
		List<Cart> carts = cartRepo.findByUser(currUser) ;
		double amt = 0;
		
		Orders order = new Orders();
		order.setEmail(user.getEmail());
		order.setOrderAddress(user.getAddress());
		order.setShippingAddress(user.getAddress());
		order.setOrderDate(LocalDate.now());
		order.setStatus(Status.PLACED);
		order.setUser(user);
		
		// email order addr orderdate status userid amount
		for (Cart cart : carts) {
			amt += cart.getQuantity() * cart.getProduct().getPrice();
		}
		
		order.setAmount(amt);
		Orders currOrder=orderRepo.save(order); //
		orderRepo.flush();
		
		// price quantity orderid productid status
		for (Cart cart : carts) {
			OrderDetails orderItem = new OrderDetails();
			
			// add cart entry to orderdetails table
			orderItem.setOrder(currOrder);
			orderItem.setProduct(cart.getProduct());
			
			orderItem.setQuantity(cart.getQuantity());
			orderItem.setPrice(cart.getProduct().getPrice());
			orderItem.setStatus(Status.PLACED);
			orderDetailsRepo.save(orderItem);
			orderDetailsRepo.flush();
			
			// update product stock from product table
			Products product = productRepo.getOne(cart.getProduct().getId()); // PERSISTENT PRODUCT 
			product.setStock(product.getStock()-cart.getQuantity());
			productRepo.save(product);
			productRepo.flush();
			
			// delete entry from cart table
			cartRepo.delete(cart);
			cartRepo.flush();
		}
		
		
		return "Success";
	}

	@Override
	public List<Orders> prevOrders(int userId) {
		Optional<User> currUser = userRepo.findById(userId);
		User user=currUser.get();
		
		return orderRepo.findByUser(user);
	}

	@Override
	public String cancelOrder(int orderId) {
		String cancellationstatus = "failed";
		Optional<Orders> currOrder = orderRepo.findById(orderId);
		Orders order =currOrder.get();
		if(order.getStatus()==Status.PLACED) {
			List<OrderDetails> orderItems = orderDetailsRepo.findByOrder(order);
			for (OrderDetails item : orderItems) {
				item.setStatus(Status.CANCELLED);
				orderDetailsRepo.flush();
			}
			order.setStatus(Status.CANCELLED);
			cancellationstatus = "success";
			orderRepo.flush();
		}
		
		return cancellationstatus;
	}

	@Override
	public String checkOrderStatus(int orderId) {
		Optional<Orders> currOrder = orderRepo.findById(orderId);
		Orders order =currOrder.get();
		return order.getStatus().toString();
	}	
	
	@Override
	public Orders getSingleOrder(int orderId) {
		
		Optional<Orders> currOrder = orderRepo.findById(orderId);
		Orders order =currOrder.get();
		return order;
	}
	
	
}
