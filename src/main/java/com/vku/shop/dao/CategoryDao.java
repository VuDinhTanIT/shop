package com.vku.shop.dao;

import java.util.List;

import com.vku.shop.entity.Category;

public interface CategoryDao {

	List<Category> findAll();
}
