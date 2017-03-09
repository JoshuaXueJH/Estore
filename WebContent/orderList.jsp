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
		<h1>Estore-登录</h1>
		<hr />
		<c:forEach items="${requestScope.list }" var="olf">
			<div align="left">
				<h3>订单号:${olf.id }</h3> <br /> 
				用户名:${olf.username }<br /> 
				订单总额:${olf.money }<br />
				订单提交时间:${olf.ordertime }<br /> 
				支付状态:
				<c:if test="${olf.paystate==0 }">
					<font color="red">未支付</font>
					<a href="${pageContext.request.contextPath }/DelOrderServlet?id=${olf.id }">订单删除</a>
					<a href="${pageContext.request.contextPath }/pay.jsp?id=${olf.id }&money=${olf.money }">在线支付</a>
				</c:if>
				<c:if test="${olf.paystate==1 }">
					<font color="red" >已支付</font>
				</c:if>
				<br />
				<table width="100%">
					<tr>
						<th width="20">商品图片</th>
						<th>商品名称</th>
						<th>种类</th>
						<th>数量</th>
						<th>单价</th>
						<th>总价</th>
					</tr>
					<tr>
						<c:forEach items="${olf.prodMap }" var="entry">
							<tr>
								<td align="center"><img src="${pageContext.request.contextPath }/${entry.key.imgurls  }" /></td>
								<td align="center">${entry.key.name }</td>
								<td align="center">${entry.key.category }</td>
								<td align="center">${entry.value }</td>
								<td align="center">${entry.key.price }</td>
								<td align="center">${entry.key.price*entry.value }</td>
							</tr>
						</c:forEach>
					</tr>
				</table>
			</div>
			<hr />
		</c:forEach>
	</div>
</body>
</html>