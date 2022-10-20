package com.app.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.OrderDetailsRepository;

@Service
@Transactional
public class OrderDetailsServiceImpl implements IOrderDetailsService {

	// dependency
	@Autowired
	private OrderDetailsRepository orderDetailsRepo;
	
	
}
