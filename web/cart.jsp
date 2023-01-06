<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<script type="text/javascript" src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
<%--<base href="http://localhost:8080/BookStore02/">--%>
	<script type="text/javascript">
		$(function (){
			$("#clear").click(function (){
				return confirm("你确定要清空【所有的商品】吗?");
			});
			$(".update").change(function (){
				if(confirm("你确定要修改"+$(this).parent().parent().find("td:first").text()+"的数量为"+this.value+"吗?")){
					location.href="http://localhost:8080/book_war_exploded/cartServlet?action=updateCount&count="+this.value+"&id="+$(this).attr("bookid");
				}else {
					this.value= this.defaultValue;
				}
			});
		});
	</script>
	<link type="text/css" rel="stylesheet" href="static/css/style.css" >
</head>
<body style="background: url(static/img/bc1.jpg) top center no-repeat;">
	<div id="header">
<%--			<img class="logo_img" alt="" src="static/img/logo.gif" >--%>
			<span class="wel_word">购物车</span>
			<div>
				<span>欢迎<span class="um_span">${sessionScope.user.username}</span>来到书城</span>
				<a href="order.jsp">我的订单</a>
				<a href="userServlet?action=loginOut">注销</a>&nbsp;&nbsp;
				<a href="index.jsp">返回</a>
			</div>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:if test="${empty sessionScope.cart.items}">
				<tr>
					<td colspan="5">当前购物车数据为空 !</td>
				</tr>
			</c:if>
			<c:if test="${not empty sessionScope.cart.items}">
				<c:forEach items="${sessionScope.cart.items}" var="entry">
					<tr>
						<td>${entry.value.name}</td>
						<td>
							<input class="update" style="width: 80px;"
								   bookid="${entry.value.id}"
								   type="text" value="${entry.value.count}">
						</td>

						<td>${entry.value.price}</td>
						<td>${entry.value.totalPrice}</td>
						<td><a href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>

		</table>
		
		<div class="cart_info">
			<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
			<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
			<span class="cart_span"><a id="clear" href="cartServlet?action=clear">清空购物车</a></span>
			<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
		</div>
	
	</div>
	
	<div id="bottom">
		<span>
			Ray &copy;2022.02.14
		</span>
	</div>
</body>
</html>