<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<h1>Estore-${prod.name }</h1>
		<hr />
		<table width="100%">
  		<tr>
  			<td><img src="${pageContext.request.contextPath }/${prod.imgurl  }"/></td>
  			<td>
  				商品名称:${prod.name }<br>
  				商品种类:${prod.category }<br>
  				商品库存:${prod.pnum }<br>
  				商品价格:${prod.price }<br>
  				商品描述:${prod.description }<br>
  				<a href="${pageContext.request.contextPath }/AddCartServlet?id=${prod.id }"><img src="${pageContext.request.contextPath }/img/buy.bmp"/></a><br>
  			</td>
  		</tr>
  	</table>
	</div>
</body>
</html>