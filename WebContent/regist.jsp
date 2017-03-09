<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/Estore/js/jquery-1.4.2.js"></script>
<script type="text/javascript">
	function changeImg(img) {
		img.src = img.src + "?time=" + new Date().getTime();
	}
	function checkForm() {
		//1.非空校验
		var canSub = true;
		canSub = checkNull("username", "用户名不能为空！") && canSub;
		canSub = checkNull("password", "密码不能为空！") && canSub;
		canSub = checkNull("password2", "密码验证不能为空！") && canSub;
		canSub = checkNull("nickname", "昵称不能为空！") && canSub;
		canSub = checkNull("email", "邮箱不能为空！") && canSub;
		canSub = checkNull("valistr", "验证码不能为空！") && canSub;
		//2.两次密码一致校验
		var psw = document.getElementsByName("password")[0].value;
		var psw2 = document.getElementsByName("password2")[0].value;
		if (psw != psw2) {
			document.getElementById("password2_msg").innerHTML = "<font color='red'>两次密码不一致！</font>";
			canSub = false;
		}
		//3.邮箱格式校验xxxxxx@xx.com
		var email = document.getElementsByName("email")[0].value;
		if (email != null && email != "" && !/^\w+@\w+(\.\w+)+$/.test(email)) {
			document.getElementById("email_msg").innerHTML = "<font color='red'>邮箱格式不正确！</font>";
			canSub = false;
		}

		return canSub;
	}
	function checkNull(name, msg) {
		document.getElementById(name + "_msg").innerHTML = "";
		var username = document.getElementsByName(name)[0].value;
		if (username == null || username == "") {
			document.getElementById(name + "_msg").innerHTML = "<font color='red'>"
					+ msg + "</font>";
			return false;
		}
		return true;
	}
	window.onload=function(){
  		 $("input[type='text'][name='username']").blur(function(){
	  			var username = $(this).val();
	  			$.get("ValiNameServlet",{username:username},function(data){
	  				var json = eval("("+data+")");
	  				if(json.stat==1){
	  					$("#username_msg").html("<font color='red'>"+json.msg+"</font>");
	  				}else if(json.stat==0){
	  					$("#username_msg").html("<font color='green'>"+json.msg+"</font>");
	  				}
	  			});
	  		});  
  		}
</script>
</head>
<body>
	<div align="center">
		<h1>Estore-注册</h1>
		<hr />
		<form action="${pageContext.request.contextPath }/RegistServlet"
			method="post" onsubmit="return checkForm()">
			<table>
				<tr>
					<td>用户名：</td>
					<td><input type="text" name="username" value="${param.username }"/></td>
					<td id="username_msg"></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input type="password" name="password" /></td>
					<td id="password_msg"></td>
				</tr>
				<tr>
					<td>确认密码：</td>
					<td><input type="password" name="password2" /></td>
					<td id="password2_msg"></td>
				</tr>
				<tr>
					<td>昵称：</td>
					<td><input type="text" name="nickname" value="${param.nickname }"/></td>
					<td id="nickname_msg"></td>
				</tr>
				<tr>
					<td>邮箱：</td>
					<td><input type="text" name="email" value="${param.email }"/></td>
					<td id="email_msg"></td>
				</tr>
				<tr>
					<td>验证码：</td>
					<td><input type="text" name="valistr"/></td>
					<td id="valistr_msg">${msg }</td>
				</tr>
				<tr>
					<td><input type="submit" value="注册" /></td>
					<td><img src="${pageContext.request.contextPath }/ValiImage"
						onclick="changeImg(this)" style="cursor: pointer;" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>