package com.vku.shop.service;

import java.util.List;

import com.vku.shop.model.ProductDTO;

public interface ProductService {

	void insert(ProductDTO productDTO);

	void update(ProductDTO productDTO);

	void delete(long productId);

	ProductDTO findById(long productId);

	List<ProductDTO> findAll(int pageIndex, int pageSize);

	List<ProductDTO> findAllByCategoryId(long categoryId, int pageIndex, int pagesize);
	
	int count();
	
	int countByCategoryId(long categoryId);
	
	List<ProductDTO> hotProducts(int pageIndex, int pageSize);
	
	List<ProductDTO> featuredProducts(int pageIndex, int pageSize);
	
	List<ProductDTO> search(long categoryId, String pricing, double priceFrom, double priceTo, String sort, String text, int pageIndex, int pageSize);

	int countBySearch(long categoryId, String pricing, double priceFrom, double priceTo, String text);
}
