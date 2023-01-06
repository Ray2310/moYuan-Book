<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>墨初会员注册页面</title>
		<!--写base标签，永远固定相对路径跳转的结果-->
<!--		<base href="http://localhost:8080/book/">-->

		<link type="text/css" rel="stylesheet" href="static/css/style.css" >
		<script src="static/script/jquery-1.7.2.js"></script>
		<script>
			// 页面加载完成之后
			$(function () {
				$("#username").blur(function () {
					//1 获取用户名
					var username = this.value;
					$.getJSON("http://localhost:8080/book_war_exploded/userServlet?action=AjaxUsername&username=" + username,function (data) {
						if (data.existsUsername) {
							$("span.errorMsg").text("用户名已存在！");
						} else {
							$("span.errorMsg").text("用户名可用！");
						}
					});
				});
				//给刷新验证码绑定单机事件
				$("#code_img").click(function (){
					//在事件响应的function函数中有一个this对象，这个对象是当前正在响应的dom对象
					//src属性表示验证码img的标签，
					this.src="${basePath}kaptcha.jpg";
				});


				// 给注册绑定单击事件
				$("#sub_btn").click(function () {
					// 验证用户名：必须由字母，数字下划线组成，并且长度为5到12位
					//1 获取用户名输入框里的内容
					var usernameText = $("#username").val();
					//2 创建正则表达式对象
					var usernamePatt = /^\w{5,12}$/;
					//3 使用test方法验证
					if (!usernamePatt.test(usernameText)) {
						//4 提示用户结果
						$("span.errorMsg").text("用户名不合法！");

						return false;
					}

					// 验证密码：必须由字母，数字下划线组成，并且长度为5到12位
					//1 获取用户名输入框里的内容
					var passwordText = $("#password").val();
					//2 创建正则表达式对象
					var passwordPatt = /^\w{5,12}$/;
					//3 使用test方法验证
					if (!passwordPatt.test(passwordText)) {
						//4 提示用户结果
						$("span.errorMsg").text("密码不合法！");

						return false;
					}

					// 验证确认密码：和密码相同
					//1 获取确认密码内容
					var repwdText = $("#repwd").val();
					//2 和密码相比较
					if (repwdText != passwordText) {
						//3 提示用户
						$("span.errorMsg").text("确认密码和密码不一致！");

						return false;
					}

					// 邮箱验证：xxxxx@xxx.com
					//1 获取邮箱里的内容
					var emailText = $("#email").val();
					//2 创建正则表达式对象
					var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
					//3 使用test方法验证是否合法
					if (!emailPatt.test(emailText)) {
						//4 提示用户
						$("span.errorMsg").text("邮箱格式不合法！");

						return false;
					}

					// 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。
					var codeText = $("#code").val();

					//去掉验证码前后空格
					// alert("去空格前：["+codeText+"]")
					codeText = $.trim(codeText);
					// alert("去空格后：["+codeText+"]")

					if (codeText == null || codeText == "") {
						//4 提示用户
						$("span.errorMsg").text("验证码不能为空！");

						return false;
					}

					// 去掉错误信息
					$("span.errorMsg").text("");

				});

			});

		</script>

	<style type="text/css">
		.login_form{
			height:420px;
			margin-top: 25px;
		}

	</style>
	</head>
	<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>

			<div class="login_banner">

				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>

				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册墨初会员</h1>
								<span class="errorMsg">
									<%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%>
								</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">

<%--									提娜佳隐藏域--%>
									<input type="hidden" name="action" value="register" />
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名(字母和数字结合)"
										   value="<%=request.getAttribute("username")==null?"":request.getAttribute("username")%>"
										   autocomplete="off" tabindex="1" name="username" id="username" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码"
										  autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码"
										   autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址"
										   value="<%=request.getAttribute("email")==null?"":request.getAttribute("email")%>"
										   autocomplete="off" tabindex="1" name="email" id="email" />
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" name="code" style="width: 100px;" id="code"/>
									<img id="code_img" alt="" src="kaptcha.jpg" style="float: right; margin-right: 40px;width: 110px;height:43px; ">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
								</form>
							</div>

						</div>
					</div>
				</div>
			</div>
		<div id="bottom">
			<span>
					Ray &copy;2022.02.14
			</span>
		</div>
	</body>
</html>