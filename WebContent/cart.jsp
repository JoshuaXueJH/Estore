<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function changeNum(id,obj,oldNum){
		if(!/^[1-9]\d*$/.test(obj.value)){
			alert("购买数量必须为正整数");
			obj.value=oldNum;
			return;
		}
		window.location.href="ChangeCartNumServlet?id="+id+"&buynum="+obj.value;
	}
</script>
</head>
<body>
<div align="center">
		<h1>Estore-购物车</h1>
		<hr />
		<c:if test="${empty sessionScope.cartmap }">
			<a href="${pageContext.request.contextPath }/ListProdServlet">购物车空空如也，去挑选点东西吧</a>
		</c:if>
		<c:if test="${!empty sessionScope.cartmap }">
			<div align="right">
				<a href="${pageContext.request.contextPath }/ListProdServlet">继续购物</a>
				<a href="${pageContext.request.contextPath }/ClearCartServlet">清空购物车</a>
				<a href="${pageContext.request.contextPath }/addOrder.jsp"><img alt="" src="${pageContext.request.contextPath }/img/gotoorder.bmp"></a>
			</div>
			<hr />
			<table width="100%" style="text-align: center">
				<tr>
					<th>图片</th>
					<th>商品名称</th>
					<th>单价</th>
					<th>种类</th>
					<th>购买数量</th>
					<th>库存状态</th>
					<th>合计</th>
					<th>操作</th>
				</tr>
				<c:set var="money" value="0" />
				<c:forEach items="${sessionScope.cartmap }" var="entry">
					<tr>
						<td><img src="${pageContext.request.contextPath }/${entry.key.imgurls  }" /></td>
						<td>${entry.key.name}</td>
						<td>${entry.key.price}</td>
						<td>${entry.key.category}</td>
						<td><input type="text" id="buynum" value="${entry.value}" style="width:30px" onchange="changeNum('${entry.key.id}',this,'${entry.value}')"/></td>
						<td>
						<c:if test="${entry.key.pnum>=entry.value }"><font color="blue">有货</font></c:if>
						<c:if test="${entry.key.pnum<entry.value }"><font color="red">缺货</font></c:if>
						</td>
						<td>
						${entry.key.price*entry.value }
						<c:set var="money" value="${money + entry.key.price * entry.value }"/>
						</td>
						<td>
						<a href="${pageContext.request.contextPath }/DelCartItemServlet?id=${entry.key.id}">删除</a>
						</td>
					</tr>
				</c:forEach>				
			</table>
			<div align="right">
				<font color="red" size="7">总价：${money }</font>
			</div>
		</c:if>
	</div>
</body>
</html>