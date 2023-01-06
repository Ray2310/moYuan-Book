<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="UTF-8">
<title>结算页面</title>
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
			<span class="wel_word">结算</span>
			<div>
				<c:if test="${not empty sessionScope.user}">
					<span>欢迎<span class="um_span">${sessionScope.user.username}</span>来到书城</span>
					<a href="userServlet?action=loginOut">注销</a>&nbsp;&nbsp;
				</c:if>
				<a href="order_manager.jsp">我的订单</a>
				<a href="index.jsp">返回</a>
			</div>


	</div>
	
	<div id="main">
		
		<h1>你的订单已结算，订单号:${sessionScope.orderId}</h1>
		
	
	</div>
	
	<div id="bottom">
		<span>
				Ray &copy;2022.02.14
		</span>
	</div>
</body>
</html>