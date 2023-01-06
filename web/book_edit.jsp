<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑图书</title>
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
	
	input {
		text-align: center;
	}
</style>
</head>
<body style="background: url(static/img/bc1.jpg) top center no-repeat;">
		<div id="header">
<%--			<img class="logo_img" alt="" src="static/img/logo.gif" >--%>
			<span class="wel_word">编辑图书</span>
			<div>
				<a href="book_manager.jsp">图书管理</a>
				<a href="order_manager.jsp">订单管理</a>
				<a href="index.jsp">返回商城</a>
			</div>
		</div>
		
		<div id="main">
			<form action="bookServlet" method="get">
				<input type="hidden" name="pageNo" value="${param.pageNo}">
				<input type="hidden" name="action" value="${empty param.id?"add":"update"}" />
				<input type="hidden" name="id" value="${ requestScope.book.id }" />
				<table>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>作者</td>
						<td>销量</td>
						<td>库存</td>
						<td colspan="2">操作</td>
					</tr>		
					<tr>
						<td><input name="name" type="text" value="${requestScope.book.name}"/></td>
						<td><input name="price" type="text" value="${requestScope.book.price}"/></td>
						<td><input name="author" type="text" value="${requestScope.book.author}"/></td>
						<td><input name="sales" type="text" value="${requestScope.book.sales}"/></td>
						<td><input name="stock" type="text" value="${requestScope.book.stock}"/></td>
						<td><input type="submit" value="提交"/></td>
					</tr>	
				</table>
			</form>
			
	
		</div>
		
		<div id="bottom">
			<span>
			Ray &copy;2022.02.14
			</span>
		</div>
</body>
</html>