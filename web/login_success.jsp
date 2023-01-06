<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>墨初会员注册页面</title>
	<!--写base标签，永远固定相对路径跳转的结果-->
<!--	<base href="http://localhost:8080/book/">-->


<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
</style>
</head>
<body style="background: url(static/img/bc1.jpg) top center no-repeat;">
		<div id="header">
<%--				<img class="logo_img" alt="" src="static/img/logo.gif" >--%>
				<div>
					<span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临书城</span>
					<a href="#">我的订单</a>
					<a href="userServlet?action=loginOut">注销</a>&nbsp;&nbsp;
					<a href="index.jsp">返回</a>
				</div>
		</div>
		
		<div id="main">
		
			<h1>欢迎回来 <a href="index.jsp">转到主页</a></h1>
	
		</div>
		
		<div id="bottom">
			<span>
					Ray &copy;2022.02.14
			</span>
		</div>
</body>
</html>