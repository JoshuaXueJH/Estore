<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	window.onload = function() {
		var str = decodeURI("${cookie.remname.value}");
		document.getElementsByName("username")[0].value = str;
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<h1>Estore-登录</h1>
		<hr />
		<font color="red">${msg }</font>
		<form action="${pageContext.request.contextPath }/LogInServlet"
			method="post">
			<table>
				<tr>
					<td>用户名：</td>
					<td><input type="text" name="username"
						value="${cookie.remname.value }" /></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input type="text" name="password" /></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="remembername" value="true"
						<c:if test="${cookie.remname!=null }">checked="checked"</c:if> />记住用户名</td>
					<td><input type="checkbox" name="autologin" value="true" />30天自动登录</td>
				</tr>
				<tr>
					<td><input type="submit" value="登录" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>