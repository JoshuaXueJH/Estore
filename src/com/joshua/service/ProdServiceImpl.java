package com.joshua.service;

import java.util.List;
import java.util.UUID;

import com.joshua.dao.ProdDao;
import com.joshua.domain.Prod;
import com.joshua.factory.BasicFactory;

public class ProdServiceImpl implements ProdService {
	ProdDao dao=BasicFactory.getFactory().getDao(ProdDao.class);
	@Override
	public void addProd(Prod prod) {
		prod.setId(UUID.randomUUID().toString());
		dao.addProd(prod);
	}
	@Override
	public List<Prod> findAllProd() {
		return dao.findAllProd();
	}
	@Override
	public Prod findProdById(String id) {
		return dao.findProdById(id);
	}

}
