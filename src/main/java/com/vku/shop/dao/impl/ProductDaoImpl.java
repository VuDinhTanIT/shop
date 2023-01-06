package com.vku.shop.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.vku.shop.dao.ProductDao;
import com.vku.shop.entity.Product;
import com.vku.shop.entity.Order;
import com.vku.shop.entity.Item;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void insert(Product product) {
		sessionFactory.getCurrentSession().save(product);
	}

	@Override
	public void update(Product product) {
		sessionFactory.getCurrentSession().merge(product);
	}

	@Override
	public void delete(long productId) {
		Product product = findById(productId);
		sessionFactory.getCurrentSession().delete(product);

	}

	@Override
	public Product findById(long productId) {
		return (Product) sessionFactory.getCurrentSession().get(Product.class, productId);
	}

	@Override
	public List<Product> findAll(int pageIndex, int pageSize) {
		int first = pageIndex * pageSize;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Product.class).setFirstResult(first)
				.setMaxResults(pageSize);
		return criteria.list();
	}

	@Override
	public List<Product> findAllByCategoryId(long catgoryId, int pageIndex, int pageSize) {
		String sql = "SELECT p FROM Product p WHERE p.category.categoryId = " + catgoryId;
		int first = pageIndex * pageSize;
		Query query = sessionFactory.getCurrentSession().createQuery(sql).setFirstResult(first).setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public int count() {
		String sql = "SELECT COUNT(p) FROM Product p";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		long count = (long) query.uniqueResult();
		return (int) count;
	}

	@Override
	public int countByCategoryId(long categoryId) {
		String sql = "SELECT COUNT(p) FROM Product p where p.category.categoryId = " + categoryId;
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		long count = (long) query.uniqueResult();
		return (int) count;
	}

	@Override
	public List<Product> hotProducts(int pageIndex, int pageSize) {
		String sql = "SELECT p FROM Product p ORDER BY p.productId DESC";
//		String sql = "SELECT p,i,o FROM Product p INNER JOIN Item i ON p.productId = i.productId INNER JOIN Order o ON i.order.getOrderId = o.orderId";
		int first = pageIndex * pageSize;
		Query query = sessionFactory.getCurrentSession().createQuery(sql).setFirstResult(first).setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public List<Product> featuredProducts(int pageIndex, int pageSize) {
		String sql = "SELECT p FROM Product p WHERE p.category.categoryId = 1 ORDER BY p.price DESC";
		int first = pageIndex * pageSize;
		Query query = sessionFactory.getCurrentSession().createQuery(sql).setFirstResult(first).setMaxResults(pageSize);
		return query.list();
	}
//	@Override
//	public List<Product> search(String pricing, float priceFrom, float priceTo, String sort, String text, int pageIndex,
//			int pageSize) {
//		String sql = "SELECT p FROM Product p WHERE 1 ";
//		if (pricing != null && !pricing.equals("default") && !pricing.equals("")) {
//			sql += " and ((p.price - (p.price * p.sale.salePercent / 100)) >= " + priceFrom
//					+ " and (p.price - (p.price * p.sale.salePercent / 100)) <= " + priceTo + ")";
//		}
//
//		if (text != null) {
//			sql += " and p.productName like '%" + text + "%'";
//		}
//
//		if (sort != null && !sort.equals("default")) {
//			sql += " ORDER BY (p.price - (p.price * p.sale.salePercent / 100)) " + sort;
//		}
//
//		int first = pageIndex * pageSize;
//		Query query = sessionFactory.getCurrentSession().createQuery(sql).setFirstResult(first).setMaxResults(pageSize);
//		return query.list();
//	}
	@Override
	public List<Product> search(long categoryId, String pricing, float priceFrom, float priceTo, String sort,
			String text, int pageIndex, int pageSize) {
		String sql = "SELECT p FROM Product p WHERE p.category.categoryId = " + categoryId;
//		String sql = "SELECT p FROM Product p WHERE 1 ";
		if (pricing != null && !pricing.equals("default") && !pricing.equals("")) {
			sql += " and ((p.price - (p.price * p.sale.salePercent / 100)) >= " + priceFrom
					+ " and (p.price - (p.price * p.sale.salePercent / 100)) <= " + priceTo + ")";
		}

		if (text != null) {
			sql += " and p.productName like '%" + text + "%'";
		}

		if (sort != null && !sort.equals("default")) {
			sql += " ORDER BY (p.price - (p.price * p.sale.salePercent / 100)) " + sort;
		}

		int first = pageIndex * pageSize;
		Query query = sessionFactory.getCurrentSession().createQuery(sql).setFirstResult(first).setMaxResults(pageSize);
		return query.list();
	}
	
	

	@Override
	public int countBySearch(long categoryId, String pricing, float priceFrom, float priceTo, String text) {
		String sql = "SELECT COUNT(p) FROM Product p where p.category.categoryId = " + categoryId;

		if (pricing != null && !pricing.equals("default") && !pricing.equals("")) {
			sql += " and ((p.price - (p.price * p.sale.salePercent / 100)) >= " + priceFrom
					+ " and (p.price - (p.price * p.sale.salePercent / 100)) <= " + priceTo + ")";
		}

		if (text != null) {
			sql += " and p.productName like '%" + text + "%'";
		}
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		long count = (long) query.uniqueResult();
		return (int) count;
	}



}
