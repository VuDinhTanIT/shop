package com.vku.shop.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vku.shop.dao.SaleDao;
import com.vku.shop.entity.Sale;

@Repository
@Transactional
public class SaleDaoImpl implements SaleDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Sale> findAll() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Sale.class);
		return criteria.list();
	}

}
