package com.joshua.dao;

import java.sql.SQLException;
import java.util.List;

import com.joshua.domain.Order;
import com.joshua.domain.OrderItem;
import com.joshua.domain.SaleListForm;

public interface OrderDao extends Dao {

	/**
	 * 向orders表中添加订单
	 * 
	 * @param order
	 * @throws SQLException
	 */
	void addOrder(Order order) throws SQLException;

	/**
	 * 向orderitem表中添加订单项
	 * 
	 * @param item
	 * @throws SQLException
	 */
	void addOrderItem(OrderItem item) throws SQLException;

	/**
	 * 根据user的id找出所有订单
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	List<Order> findOrderByUserId(int id);

	/**
	 * 根据order的id查找所有的订单项
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	List<OrderItem> findOrderItemsByOrderId(String id);

	/**
	 * 根据order的id删除所有的orderItem
	 * 
	 * @param orderId
	 * @throws SQLException
	 */
	void delOrderItemByOrderId(String orderId) throws SQLException;

	/**
	 * 根据order的id删除order
	 * 
	 * @param orderId
	 * @throws SQLException
	 */
	void delOrderByOrderId(String orderId) throws SQLException;

	/**
	 * 根据order的id查询order
	 * 
	 * @param p2_Order
	 * @return
	 */
	Order findOrderById(String p2_Order);

	/**
	 * 根据order的id更改支付状态
	 * 
	 * @param r6_Order
	 * @param i
	 */
	void changePayState(String r6_Order, int i);

	/**
	 * 查询销售数量排行榜
	 * @return
	 */
	List<SaleListForm> saleList();

}
