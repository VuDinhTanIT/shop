package com.vku.shop.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vku.shop.entity.Product;
import com.vku.shop.model.RevenueDTO;

import com.vku.shop.service.OrderService;
import com.vku.shop.service.ProductService;

import antlr.collections.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value = "/admin", produces = "application/x-www-form-urlencoded;charset=UTF-8")
public class RevenueController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private ProductService productService;

	@GetMapping(value = "month-stat")
	public String stat(HttpServletRequest request) {
		Map<String, Double> revenueData = orderService.getRevenueData();
		java.util.List<RevenueDTO> revenueDataList = new ArrayList<>();

		for (Map.Entry<String, Double> entry : revenueData.entrySet()) {
			String month = entry.getKey();
			double revenue = entry.getValue();
// DecimalFormat df=new DecimalFormat("#,###");
//
// String formattedNumber= df.format(revenue);
			revenueDataList.add(new RevenueDTO(month, revenue));
		}
		revenueDataList.sort(( o1, o2) -> o1.hashCode());
		System.out.println(revenueDataList);
		
		request.setAttribute("revenueDataList", revenueDataList);
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString;
		try {
			jsonString = objectMapper.writeValueAsString(revenueDataList);
			request.setAttribute("revenueDataListJson", jsonString);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/stat/monthRevenue";
	}

	@GetMapping(value = "statItem")
	public String stat2(HttpServletRequest request) {
		return "admin/stat/new";
	}
	@GetMapping("/best-selling-products-stat")
	public String getBestSellingProducts(HttpServletRequest request) {
	    HashMap<Product, Integer> mapBestSellingProducts = new HashMap<>();
	    HashMap<String, Integer> bestSellingProductsStringInt = new HashMap<String, Integer>();

	    int limit = 5; // Số lượng sản phẩm bán chạy nhất cần lấy
	    java.util.List<Product> listBestSellingProduct = productService.getBestSellingProducts(limit);
	    for (Product product : listBestSellingProduct) {
	        mapBestSellingProducts.put(product, productService.quantityProductSold(product.getProductId()));
	        bestSellingProductsStringInt.put(product.getProductName(), productService.quantityProductSold(product.getProductId()));
	    }

	    String jsonString;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			jsonString = objectMapper.writeValueAsString(bestSellingProductsStringInt);
			System.out.println(jsonString);
			request.setAttribute("bestSellingProductsStringInt", jsonString);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	   
	    request.setAttribute("bestSellingProducts", mapBestSellingProducts);
	
	    request.setAttribute("bestSellingProductsStringInt",  bestSellingProductsStringInt);
	    return "admin/stat/bestSellingProducts";
	}
}