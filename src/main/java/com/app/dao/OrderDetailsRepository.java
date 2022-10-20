package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.pojos.OrderDetails;
import com.app.pojos.Orders;
import com.app.pojos.Products;
import com.app.pojos.Status;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {

	
	List<OrderDetails> findByProductAndStatus(Products product, Status status);

	List<OrderDetails> findByOrder(Orders order);

	List<OrderDetails> findByProduct(Products product);

}
