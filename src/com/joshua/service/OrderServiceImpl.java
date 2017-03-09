package com.joshua.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.joshua.dao.OrderDao;
import com.joshua.dao.ProdDao;
import com.joshua.dao.UserDao;
import com.joshua.domain.Order;
import com.joshua.domain.OrderItem;
import com.joshua.domain.OrderListForm;
import com.joshua.domain.Prod;
import com.joshua.domain.SaleListForm;
import com.joshua.factory.BasicFactory;

public class OrderServiceImpl implements OrderService {
	OrderDao orderDao = BasicFactory.getFactory().getDao(OrderDao.class);
	ProdDao prodDao = BasicFactory.getFactory().getDao(ProdDao.class);
	UserDao userDao = BasicFactory.getFactory().getDao(UserDao.class);

	@Override
	public void addOrder(Order order) {
		try {
			// 生成订单
			orderDao.addOrder(order);
			for (OrderItem item : order.getList()) {
				// 生成订单项&扣除产品数量
				orderDao.addOrderItem(item);
				prodDao.delPnum(item.getProduct_id(), item.getBuynum());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<OrderListForm> findOrders(int id) {
		try {
			// 创建List<OrderListForm>，前台页面需要显示的所有订单信息存入
			List<OrderListForm> olfList = new ArrayList<OrderListForm>();
			// 根据user的id查找到此id对应的所有orders
			List<Order> list = orderDao.findOrderByUserId(id);
			// 遍历循环每一个order，将order中的信息以及其他信息放入olfList中
			for (Order order : list) {
				OrderListForm olf = new OrderListForm();
				// 将order信息放入olf
				BeanUtils.copyProperties(olf, order);
				// 将用户名放入olf
				olf.setUsername(userDao.findUserById(id).getUsername());
				// 将购买的产品及数量放入olf
				Map<Prod, Integer> prodMap = new HashMap<Prod, Integer>();
				List<OrderItem> itemList = orderDao.findOrderItemsByOrderId(order.getId());
				for (OrderItem item : itemList) {
					Prod prod = prodDao.findProdById(item.getProduct_id());
					prodMap.put(prod, item.getBuynum());
				}
				olf.setProdMap(prodMap);
				olfList.add(olf);
			}

			return olfList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delOrderById(String orderId) {
		try {
			// 调用prodDao将订单中产品的数量加回去
			List<OrderItem> list = orderDao.findOrderItemsByOrderId(orderId);
			for (OrderItem item : list) {
				prodDao.addPnum(item.getProduct_id(), item.getBuynum());
			}
			// 删除OrderItem中的item
			orderDao.delOrderItemByOrderId(orderId);
			// 删除Order中的order
			orderDao.delOrderByOrderId(orderId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public Order findOrderById(String p2_Order) {
		return orderDao.findOrderById(p2_Order);
	}

	@Override
	public void changePayState(String r6_Order, int i) {
		orderDao.changePayState(r6_Order, i);
	}

	@Override
	public List<SaleListForm> saleList() {
		return orderDao.saleList();
	}

}
