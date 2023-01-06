<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
	<script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
	<script type="text/javascript" >
		$(function (){
			$("a.deleteClass").click(function (){

				/**
				 * 返回true就是点击了确认，false就是取消
				 */
				return confirm("你确定要删除"+$(this).parent().parent().find("td:first").text()+"吗?");
			});
		});
	</script>
</head>
<body style="background: url(static/img/bc1.jpg) top center no-repeat;">
	
	<div id="header">
<%--			<img class="logo_img" alt="" src="static/img/logo.gif" >--%>
			<span class="wel_word">图书管理系统</span>
			<div>
<%--				<a href="book_manager.jsp">图书管理</a>--%>
				<a href="order_manager.jsp">订单管理</a>
				<a href="index.jsp">返回商城</a>
			</div>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>		
<%--			<tr>--%>
<%--				<td>时间简史</td>--%>
<%--				<td>20.00</td>--%>
<%--				<td>霍金</td>--%>
<%--				<td>200</td>--%>
<%--				<td>400</td>--%>
<%--				<td><a href="book_edit.jsp">修改</a></td>--%>
<%--				<td><a href="#">删除</a></td>--%>
<%--			</tr>--%>
<%--			el表达式--%>
			<c:forEach items="${requestScope.page.items}" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a href="bookServlet?action=getBook&id=${book.id}&pageNo=${requestScope.page.pageNo}">修改</a></td>
					<td><a class="deleteClass" href="bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
				</tr>
			</c:forEach>
			
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="book_edit.jsp?pageNo=${requestScope.page.pageTotal}">添加图书</a></td>
			</tr>	
		</table>
		<div id="page_nav">
<%--			如果所在页数大于首页，才会显示回到上一页--%>
			<c:if test="${requestScope.page.pageNo > 1}">
				<a href="${requestScope.page.url}&pageNo=1">首页</a>
				<a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
				<a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}"> ${ requestScope.page.pageNo-1 }</a>
			</c:if>

			【${ requestScope.page.pageNo }】
<%--			如果已经是最后一页，那么就不显示下一页和末页--%>
			<c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
				<a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}"> ${ requestScope.page.pageNo+ 1 }</a>
				<a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
				<a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
			</c:if>
			共${requestScope.page.pageTotal }页，${requestScope.page.pageTotalCount }条记录 到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
<%--			<input id="searchPageBtn" type="button" value="确定">--%>
			<input class="bcInput" id="searchPageBtn" type="submit" value="确定">
			<script type="text/javascript">

				//$(document).ready(function(){ //这一行语句的意思是等所有DOM树加载完成后执行绑定事件工作
					//window.onload = function(){
					$(function (){
						$("#searchPageBtn").click(function (){
						//绑定单机事件
						var pageNo = $("#pn_input").val();

						// 页面跳转
						window.location.href ="http://localhost:8080/book_war_exploded/${requestScope.page.url}&pageNo="+pageNo;
					});
				});
			</script>
		</div>
	</div>
	
	<div id="bottom">
		<span>
			Ray &copy;2022.02.14
		</span>
	</div>
</body>
</html>

