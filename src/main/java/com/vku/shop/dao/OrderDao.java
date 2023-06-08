package com.vku.shop.dao;

import java.util.List;

import com.vku.shop.entity.Order;

public interface OrderDao {

	void insert(Order order);
	
	void update(Order order);
	
	void delete(long orderId);
	
	List<Order> findAll(int pageIndex, int pageSize);
	List<Order> findAll();
	List<Order> findByBuyer(long userId);
	
	int count();
	
	Order findById(long orderId);

	List<Order> findOrderByYear(int year);
	
}
