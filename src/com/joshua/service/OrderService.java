package com.joshua.service;

import java.sql.SQLException;
import java.util.List;

import com.joshua.annotation.Tran;
import com.joshua.domain.Order;
import com.joshua.domain.OrderListForm;
import com.joshua.domain.SaleListForm;

public interface OrderService extends Service {

	/**
	 * 添加订单的方法
	 * 
	 * @param order
	 *            其中封装了order的信息
	 */
	@Tran
	void addOrder(Order order);

	/**
	 * 根据user的id查询所有订单的方法
	 * 
	 * @param id
	 *            用户user的id
	 * @return
	 */
	List<OrderListForm> findOrders(int id);

	/**
	 * 根据order的id删除order
	 * 
	 * @param orderId
	 *            要删除的order的id
	 * @throws SQLException
	 */
	@Tran
	void delOrderById(String orderId);

	/**
	 * 根据id查询订单
	 * 
	 * @param p2_Order
	 * @return
	 */
	Order findOrderById(String p2_Order);

	/**
	 * 根据订单id更改支付状态
	 * 
	 * @param r6_Order
	 *            订单id
	 * @param i
	 *            更改后的状态
	 */
	void changePayState(String r6_Order, int i);

	/**
	 * 查询商品的销售排行榜
	 * @return
	 */
	List<SaleListForm> saleList();

}
