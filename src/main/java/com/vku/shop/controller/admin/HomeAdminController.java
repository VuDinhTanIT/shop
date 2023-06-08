package com.vku.shop.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vku.shop.service.OrderService;
import com.vku.shop.service.ProductService;
import com.vku.shop.service.UserService;

@Controller
@RequestMapping(value = "/admin")
public class HomeAdminController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/home")
	public String home(HttpServletRequest request) {
		request.setAttribute("orders", orderService.findAll(0, 5));
		request.setAttribute("totalProduct", productService.count());
		request.setAttribute("totalOrder", orderService.count());
		request.setAttribute("totalU", userService.count());
//		request.setAttribute("totalProduct", productService.count());
		
		return "admin/home";
	}
	
	
}
