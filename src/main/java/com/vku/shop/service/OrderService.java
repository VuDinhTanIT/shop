package com.vku.shop.service;

import java.util.List;
import java.util.Map;

import com.vku.shop.model.OrderDTO;

public interface OrderService {

	void insert(OrderDTO orderDTO);
	
	void update(OrderDTO orderDTO);
	
	void delete(long orderId);
	
	List<OrderDTO> findAll(int pageInde, int pageSize);
	
	List<OrderDTO> findByBuyer(long userId);
	
	int count();
	
	OrderDTO findById(long orderId);

	Map<String, Double> getRevenueData();

	Map<String, Double> getRevenueData(int year);
	
}
