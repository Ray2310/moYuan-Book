<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
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
			<span class="wel_word">我的订单</span>
			<div>
				<span>欢迎<span class="um_span">${sessionScope.user.username}</span>来到书城</span>
<%--				<a href="order.jsp">我的订单</a>--%>
				<a href="userServlet?action=loginOut">注销</a>&nbsp;&nbsp;
				<a href="index.jsp">返回</a>
			</div>
	</div>
	
	<div id="main">
		
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
			</tr>		
			<tr>
				<td>2022.10.30</td>
				<td>90.00</td>
				<td>未发货</td>
				<td><a href="#">查看详情</a></td>
			</tr>	
			
			<tr>
				<td>2022.10.19</td>
				<td>20.00</td>
				<td>已发货</td>
				<td><a href="#">查看详情</a></td>
			</tr>	
			
			<tr>
				<td>2022.10.6</td>
				<td>190.00</td>
				<td>已完成</td>
				<td><a href="#">查看详情</a></td>
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