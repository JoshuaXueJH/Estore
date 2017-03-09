package com.joshua.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.joshua.domain.Order;
import com.joshua.domain.OrderItem;
import com.joshua.domain.SaleListForm;
import com.joshua.util.TransactionManager;

public class OrderDaoImpl implements OrderDao {

	@Override
	public void addOrder(Order order) throws SQLException {
		String sql = "insert into orders values(?,?,?,?,null,?)";
		QueryRunner runner = new QueryRunner(TransactionManager.getSource());
		runner.update(sql, order.getId(), order.getMoney(), order.getReceiverinfo(), order.getPaystate(),
				order.getUser_id());
	}

	@Override
	public void addOrderItem(OrderItem item) throws SQLException {
		String sql = "insert into orderitem values(?,?,?)";
		QueryRunner runner = new QueryRunner(TransactionManager.getSource());
		runner.update(sql, item.getOrder_id(), item.getProduct_id(), item.getBuynum());
	}

	@Override
	public List<Order> findOrderByUserId(int id) {
		String sql = "select * from orders where user_id = ?";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql, new BeanListHandler<Order>(Order.class), id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<OrderItem> findOrderItemsByOrderId(String id) {
		String sql = "select * from orderitem where order_id = ?";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql, new BeanListHandler<OrderItem>(OrderItem.class), id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delOrderItemByOrderId(String orderId) throws SQLException {
		String sql = "delete from orderitem where order_id = ?";
		QueryRunner runner = new QueryRunner(TransactionManager.getSource());
		runner.update(sql, orderId);
	}

	@Override
	public void delOrderByOrderId(String orderId) throws SQLException {
		String sql = "delete from orders where id = ?";
		QueryRunner runner = new QueryRunner(TransactionManager.getSource());
		runner.update(sql, orderId);
	}

	@Override
	public Order findOrderById(String p2_Order) {
		String sql = "select * from orders where id = ?";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql, new BeanHandler<Order>(Order.class), p2_Order);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void changePayState(String r6_Order, int i) {
		String sql = "update orders set paystate=? where id=?";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			runner.update(sql, i, r6_Order);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<SaleListForm> saleList() {
		String sql = "select products.id prod_id, products.name prod_name, sum(orderitem.buynum) sal_num from products, orderitem where products.id = orderitem.product_id group by prod_id order by sal_num desc";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql, new BeanListHandler<SaleListForm>(SaleListForm.class));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
