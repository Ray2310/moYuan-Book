<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>
    <%
        String basePath= request.getScheme()
                + "://"
                +request.getServerName()
                +":"
                +request.getServerPort()
                +request.getContextPath()
                +"/";
    %>
<%--    <%=basePath%>--%>
<%--        使用相对路径--%>
    <base href="<%=basePath%>">
    <link type="text/css" rel="stylesheet" href="static/css/style.css" >
    <script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript">
        $(function (){
            //  对加入购物车按钮绑定单机事件
            $("button.addToCart").click(function (){
                location.href="http://localhost:8080/book_war_exploded/cartServlet?action=addItem&id="+$(this).attr("bookId");
                /**
                 * 暂时无法使用ajax，不知原因,待改进
                 */
               // $.getJSON("http://localhost:8080/book_war_exploded/cartServlet","action=ajaxAddItem&id="+bookId,function (data){
                //     $("#cartTotalCount").text("您的购物车中有"+data.totalCount+"件商品");
                //     $("#cartLastName").text(data.cartLastName);
                // })
            });
        });
    </script>

</head>
<body style="background: url(static/img/bc13.jpg) top center no-repeat;">
<div id="header">
<%--    去除logo颜色--%>
<%--    <img class="logo_img" alt="" src="static/img/logo.gif" >--%>
    <span class="wel_word">墨初书城</span>
    <div>
        <!--				<a href="pages/user/login.jsp">登录</a> |-->
        <!--				<a href="web/pages/user/register.jsp">注册</a>-->
<%--        如果用户还没有登录，显示登录注册等信息--%>
        <c:if test="${empty sessionScope.user}">
            <a href="login.jsp">登录</a> |
            <a href="register.jsp">注册</a>
        </c:if>
<%--        如果已经登录，那么就显示用户已经登录的信息--%>
        <c:if test="${not empty sessionScope.user}">
            <span>欢迎<span class="um_span">${sessionScope.user.username}</span>来到书城</span>
            <a href="order_manager.jsp">我的订单</a>
                <%--
                注销就是销毁用户登录的信息，也就是销毁session
                然后就是重定向回首页
              --%>
            <a href="userServlet?action=loginOut">注销</a>&nbsp;&nbsp;
        </c:if>

        <a href="cart.jsp">购物车</a>
        <a href="manager.jsp">后台管理</a>
    </div>
</div>
<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="clientBookServlet" method="get">
                <input type="hidden" name="action" value="pageByPrice">
                价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
                <input id="max" type="text" name="max" value="${param.max}"> 元
                <input type="submit" value="查询" />
            </form>
        </div>
        <div style="text-align: center">
            <c:if test="${empty sessionScope.cart.items}">
                <span id="cartLastName">您的购物车为空！</span>
            </c:if>
            <c:if test="${not empty sessionScope.cart.items}">
                <span id="cartTotalCount">您的购物车中有${sessionScope.cart.totalCount}件商品</span>
                <div>
                    您刚刚将<span id="cartLastName" style="color: red">${sessionScope.lastName}</span>加入到了购物车中
                </div>
            </c:if>
        </div>




        <c:forEach items="${requestScope.page.items}" var="book">

           <div class="b_list">
                <div class="img_div">
<%--                    这里暂时用choose语句来显示，但这不是长久之策，如果数据量过大那么程序将会达到上千甚至上万行代码--%>
                    <c:choose>
                        <c:when test="${book.id==1}">
                            <img class="book_img" alt="" src="static/img/default.jpg" />
                        </c:when>
                        <c:when test="${book.id==2}">
                            <img class="book_img" alt="" src="static/img/suanfa.jpg" />
                        </c:when>
                        <c:when test="${book.id==5}">
                            <img class="book_img" alt="" src="static/img/c++.jpg" />
                        </c:when>
                        <c:when test="${book.id==7}">
                            <img class="book_img" alt="" src="static/img/dushen.jpg" />
                        </c:when>
                        <c:when test="${book.id==8}">
                            <img class="book_img" alt="" src="static/img/8.jpg" />
                        </c:when>
                        <c:when test="${book.id==9}">
                            <img class="book_img" alt="" src="static/img/9.jpg" />
                        </c:when>
                        <c:when test="${book.id==10}">
                            <img class="book_img" alt="" src="static/img/10.jpg" />
                        </c:when>
                        <c:when test="${book.id==11}">
                            <img class="book_img" alt="" src="static/img/11.jpg" />
                        </c:when>
                        <c:when test="${book.id==12}">
                            <img class="book_img" alt="" src="static/img/12.jpg" />
                        </c:when>
                        <c:when test="${book.id==13}">
                            <img class="book_img" alt="" src="static/img/13.jpg" />
                        </c:when>
                        <c:when test="${book.id==14}">
                            <img class="book_img" alt="" src="static/img/14.jpg" />
                        </c:when>
                        <c:when test="${book.id==15}">
                            <img class="book_img" alt="" src="static/img/15.jpg" />
                        </c:when>
                        <c:when test="${book.id==16}">
                            <img class="book_img" alt="" src="static/img/16.jpg" />
                        </c:when>
                        <c:when test="${book.id==17}">
                            <img class="book_img" alt="" src="static/img/17.jpg" />
                        </c:when>
                        <c:when test="${book.id==18}">
                            <img class="book_img" alt="" src="static/img/18.jpg" />
                        </c:when>
                        <c:when test="${book.id==19}">
                            <img class="book_img" alt="" src="static/img/19.jpg" />
                        </c:when>
                        <c:when test="${book.id==29}">
                            <img class="book_img" alt="" src="static/img/29.jpg" />
                        </c:when>
                        <c:otherwise>
                            <img class="book_img" alt="" src="${book.imgPath}" />
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="book_info">
                    <div class="book_name">
                        <span class="sp1">书名:</span>
                        <span class="sp2">${book.name}</span>
                    </div>
                    <div class="book_author">
                        <span class="sp1">作者:</span>
                        <span class="sp2">${book.author}</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">价格:</span>
                        <span class="sp2">￥${book.price}</span>
                    </div>
                    <div class="book_sales">
                        <span class="sp1">销量:</span>
                        <span class="sp2">${book.sales}</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">库存:</span>
                        <span class="sp2">${book.stock}</span>
                    </div>
                    <div class="book_add">
                        <button bookId="${book.id}" class="addToCart">加入购物车</button>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

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
        <input id="searchPageBtn" type="submit" value="确定">
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