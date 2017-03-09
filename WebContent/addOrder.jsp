<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<h1>Estore-生成订单</h1>
		<hr />
		<font color="red" size="5">订单信息:</font>
		<form action="${pageContext.request.contextPath }/AddOrderServlet" method="post">
			收获地址：<textarea rows="2" cols="50" name="receiverinfo"></textarea>
			支付方式：<input type="radio" name="typex" checked="checked" />在线支付<br /> 
			<input type="submit" value="提交订单" />
		</form>
		<hr />
		<font color="red" size="5">购物清单:</font>
		<table width="100%" style="text-align: center">
			<tr>
				<th>商品名称</th>
				<th>单价</th>
				<th>种类</th>
				<th>购买数量</th>
				<th>库存状态</th>
				<th>合计</th>
			</tr>
			<c:set var="money" value="0" />
			<c:forEach items="${sessionScope.cartmap }" var="entry">
				<tr>
					<td>${entry.key.name}</td>
					<td>${entry.key.price}</td>
					<td>${entry.key.category}</td>
					<td>${entry.value }</td>
					<td><c:if test="${entry.key.pnum>=entry.value }">
							<font color="blue">有货</font>
						</c:if> <c:if test="${entry.key.pnum<entry.value }">
							<font color="red">缺货</font>
						</c:if></td>
					<td>${entry.key.price*entry.value } <c:set var="money"
							value="${money + entry.key.price * entry.value }" />
					</td>
				</tr>
			</c:forEach>
		</table>
		<div align="right">
			<font color="red" size="7">总价：${money }</font>
		</div>
	</div>
</body>
</html>