package com.vku.shop.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vku.shop.dao.SaleDao;
import com.vku.shop.entity.Sale;
import com.vku.shop.model.SaleDTO;
import com.vku.shop.service.SaleService;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {

	@Autowired
	private SaleDao saleDao;
	
	@Override
	public List<SaleDTO> findAll() {
		List<Sale> sales = saleDao.findAll();
		List<SaleDTO> saleDTOs = new ArrayList<>();
		for (Sale sale : sales) {
			SaleDTO saleDTO = new SaleDTO();
			saleDTO.setSaleId(sale.getSaleId());
			saleDTO.setSalePercent(sale.getSalePercent());
			saleDTOs.add(saleDTO);
		}
		return saleDTOs;
	}

}
