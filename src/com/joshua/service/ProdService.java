package com.joshua.service;

import java.util.List;

import com.joshua.domain.Prod;

public interface ProdService extends Service {

	/**
	 * 添加商品信息
	 * 
	 * @param prod
	 *            商品信息
	 */
	void addProd(Prod prod);

	/**
	 * 查找所有存入数据库的prod信息
	 * 
	 * @return 将查找到的信息返回
	 */
	List<Prod> findAllProd();

	/**
	 * 根据id查找prod信息
	 * 
	 * @param id
	 *            prod的id
	 * @return
	 */
	Prod findProdById(String id);

}
