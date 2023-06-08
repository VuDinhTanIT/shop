package com.vku.shop.service.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vku.shop.dao.OrderDao;
import com.vku.shop.entity.Order;
import com.vku.shop.entity.User;
import com.vku.shop.model.OrderDTO;
import com.vku.shop.model.UserDTO;
import com.vku.shop.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderDao orderDao;

	@Override
	public void insert(OrderDTO orderDTO) {
		
		
		User user = new User();
		user.setUserId(orderDTO.getUserDTO().getUserId());
		
		Order order = new Order();
		order.setOrderId(orderDTO.getOrderId());
		order.setBuyDate(orderDTO.getBuyDate());
		order.setStatus(orderDTO.getStatus());
		order.setPriceTotal(orderDTO.getPriceTotal());
		order.setBuyer(user);
		
		orderDao.insert(order);
	}

	@Override
	public void update(OrderDTO orderDTO) {
		

		User user = new User();
		user.setUserId(orderDTO.getUserDTO().getUserId());
		
		Order order = new Order();
		order.setOrderId(orderDTO.getOrderId());
		order.setBuyDate(orderDTO.getBuyDate());
		order.setStatus(orderDTO.getStatus());
		order.setPriceTotal(orderDTO.getPriceTotal());
		order.setBuyer(user);
		
		orderDao.update(order);
	}

	@Override
	public void delete(long orderId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<OrderDTO> findAll(int pageInde, int pageSize) {
		List<Order> orders = orderDao.findAll(pageInde, pageSize);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		List<OrderDTO> orderDTOs = new ArrayList<OrderDTO>();
		for (Order order : orders) {
			UserDTO userDTO = new UserDTO();
			userDTO.setUserId(order.getBuyer().getUserId());
			userDTO.setEmail(order.getBuyer().getEmail());
			userDTO.setAddress(order.getBuyer().getAddress());
			userDTO.setPhone(order.getBuyer().getPhone());
			userDTO.setFullname(order.getBuyer().getFullname());
			
			OrderDTO orderDTO = new OrderDTO();
			orderDTO.setOrderId(order.getOrderId());
			String strBuyDate = sdf.format(order.getBuyDate());
			orderDTO.setBuyDate(order.getBuyDate());
			orderDTO.setStatus(order.getStatus());
			orderDTO.setPriceTotal(order.getPriceTotal());
			orderDTO.setUserDTO(userDTO);
			orderDTOs.add(orderDTO);
//			order.setBuyer(user);
		}
		return orderDTOs;
	}

	@Override
	public List<OrderDTO> findByBuyer(long userId) {
		List<Order> orders = orderDao.findByBuyer(userId);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		List<OrderDTO> orderDTOs = new ArrayList<OrderDTO>();
		for (Order order : orders) {
			OrderDTO orderDTO = new OrderDTO();
			orderDTO.setOrderId(order.getOrderId());
			String strBuyDate = sdf.format(order.getBuyDate());
			orderDTO.setBuyDate(order.getBuyDate());
			orderDTO.setStatus(order.getStatus());
			orderDTO.setPriceTotal(order.getPriceTotal());
			orderDTOs.add(orderDTO);
//			order.setBuyer(user);
		}
		return orderDTOs;
	}

	@Override
	public int count() {
		return orderDao.count();
	}

	@Override
	public OrderDTO findById(long orderId) {
		Order order = orderDao.findById(orderId);
		
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(order.getBuyer().getUserId());
		userDTO.setFullname(order.getBuyer().getFullname());
		userDTO.setAddress(order.getBuyer().getAddress());
		userDTO.setEmail(order.getBuyer().getEmail());
		userDTO.setPhone(order.getBuyer().getPhone());
		
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setOrderId(order.getOrderId());
		orderDTO.setBuyDate(order.getBuyDate());
		orderDTO.setStatus(order.getStatus());
		orderDTO.setPriceTotal(order.getPriceTotal());
		orderDTO.setUserDTO(userDTO);
		return orderDTO;
	}
	 public Map<String, Double> getRevenueData() {
	        List<Order> orders = orderDao.findAll();
	        Map<String, Double> revenueData = new HashMap<>();

	        for (Order order : orders) {
	            Date buyDate = order.getBuyDate();
	            String month = new SimpleDateFormat("MM/yyyy").format(buyDate);
	            double priceTotal = order.getPriceTotal();

	            if (revenueData.containsKey(month)) {
	                double currentRevenue = revenueData.get(month);
	                revenueData.put(month, currentRevenue + priceTotal);
	            } else {
	                revenueData.put(month, priceTotal);
	            }
	        }

	        return revenueData;
	    }
	@Override
	public Map<String, Double> getRevenueData(int year) {
		return null;
//	    List<Order> orders = orderDao.findByBuyDateBetween(
//	        LocalDate.of(year, 1, 1).atStartOfDay(),
//	        LocalDate.of(year, 12, 31).atTime(LocalTime.MAX)
//	    );
//	    Map<String, Double> revenueData = new HashMap<>();
//
//	    for (Order order : orders) {
//	        LocalDate buyDate = order.getBuyDate().toLocalDate();
//	        String month = String.format("%02d", buyDate.getMonthValue());
//	        double priceTotal = order.getPriceTotal();
//
//	        if (revenueData.containsKey(month)) {
//	            double currentRevenue = revenueData.get(month);
//	            revenueData.put(month, currentRevenue + priceTotal);
//	        } else {
//	            revenueData.put(month, priceTotal);
//	        }
//	    }
//
//	    return revenueData;
	}

}
