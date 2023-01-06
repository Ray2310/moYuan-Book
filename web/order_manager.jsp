<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
</head>
<body style="background: url(static/img/bc1.jpg) top center no-repeat;">
	
	<div id="header">
<%--			<img class="logo_img" alt="" src="static/img/logo.gif" >--%>
			<span class="wel_word">订单管理系统</span>
			<div>
				<a href="bookServlet?action=page">图书管理</a>
<%--				<a href="order_manager.jsp">订单管理</a>--%>
				<a href="index.jsp">返回商城</a>
			</div>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>详情</td>
				<td>发货</td>
				
			</tr>

			<tr>
				<td>2022.10.30</td>
				<td>90.00</td>
				<td><a href="#">查看详情</a></td>
				<td><a href="#">点击发货</a></td>
			</tr>	
			
			<tr>
				<td>2022.10.19</td>
				<td>20.00</td>
				<td><a href="#">查看详情</a></td>
				<td>已发货</td>
			</tr>	
			
			<tr>
				<td>2022.10.6</td>
				<td>190.00</td>
				<td><a href="#">查看详情</a></td>
				<td>等待收货</td>
			</tr>		
		</table>
	</div>
	
	<div id="bottom">
		<span>
			Ray &copy;2022.02.14
		</span>
	</div>
</body>
</html>