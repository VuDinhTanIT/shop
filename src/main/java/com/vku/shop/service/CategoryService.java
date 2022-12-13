package com.vku.shop.service;

import java.util.List;

import com.vku.shop.model.CategoryDTO;

public interface CategoryService {
	
	List<CategoryDTO> findAll();
	
}
