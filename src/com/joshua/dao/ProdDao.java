package com.joshua.dao;

import java.sql.SQLException;
import java.util.List;

import com.joshua.domain.Prod;

public interface ProdDao extends Dao {

	/**
	 * 向数据库中假如prod信息
	 * 
	 * @param prod
	 *            封装了prod信息
	 */
	void addProd(Prod prod);

	/**
	 * 从数据库中查找所有prod信息
	 * 
	 * @return 将查找到的prod信息返回
	 */
	List<Prod> findAllProd();

	/**
	 * 根据id从数据库查找prod信息
	 * 
	 * @param id
	 *            prod 的id
	 * @return
	 */
	Prod findProdById(String id);

	/**
	 * 根据产品id删除产品的数量
	 * 
	 * @param product_id
	 * @param buynum
	 * @throws SQLException
	 */
	void delPnum(String product_id, int buynum) throws SQLException;

	/**
	 * 根据产品id增加产品的数量
	 * @param product_id
	 * @param buynum
	 * @throws SQLException 
	 */
	void addPnum(String product_id, int buynum) throws SQLException;

}
