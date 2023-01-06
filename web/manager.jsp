<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理</title>
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body style="background: url(static/img/bc1.jpg) top center no-repeat;">
	
	<div id="header">
<%--			<img class="logo_img" alt="" src="static/img/logo.gif" >--%>
			<span class="wel_word">后台管理系统</span>
			<div>
				<a href="bookServlet?action=page">图书管理</a>
				<a href="order_manager.jsp">订单管理</a>
				<a href="index.jsp">返回商城</a>
			</div>
	</div>
	
	<div id="main">
		<h1>欢迎您进入后台管理系统</h1>
		<h1 style="color: red">请未经允许不要轻易删除或修改数据，谢谢！</h1>
	</div>
	
	<div id="bottom">
		<span>
				Ray &copy;2022.02.14
		</span>
	</div>
</body>
</html>