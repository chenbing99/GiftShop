<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta charset="UTF-8" />
		<title>叮咚有礼--注册</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="renderer" content="webkit">
		<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
		<link rel="stylesheet" href="css/demo.css" />
		<link rel="stylesheet" href="css/style.css" />
		<link rel="stylesheet" href="css/fen.css" />
		<script type="text/javascript" src="js/jquery-2.2.0.min.js"></script>
		<style type="text/css">
		.res_form{height:500px;}
		.regis{
		margin: -150px 0 0 500px;}
    	</style>
</head>
<body>
<div class="header">
			<div class="hea_nav">
				<a href="index.jsp"><img src="img/logo.png" class="logo"/></a>
				<ul>
					<a href="index.jsp"><li>首页</li></a>
					<a href="${pageContext.request.contextPath}/gift/list2"><li>叮咚一下</li></a>
					<a href="personal.jsp"><li>个人中心</li></a>
				</ul>
			</div>
			<div class="hea_right">
				<p>
					<a href="register.jsp">注册</a>|<a class="login_btn">登录</a>|<a href="${pageContext.request.contextPath}/adminlogin.jsp">管理员登录</a>
				</p>
				<a href="shop_car.jsp"><p>
						<i class="iconfont">&#xe600;</i>
						<span>0件</span>
					</p>
					</a>
					
			</div>
		</div>
		<div class="header2">
				<img src="img/phone_meau.png" class="meau"/>
				<a href="index.jsp"><img src="img/logo.png" class="logo"/></a>
				<a href="shop_car.jsp"><i class="iconfont">&#xe600;</i><span>1</span></a>
				<ul class="meau_box">
					<a href="index.jsp"><li>首页</li></a>
					<a href="product.jsp"><li>叮咚一下</li></a>
				
					<a href="personal.jsp"><li>个人中心</li></a>
					
					<p style="border-right:1px #fff solid;" class="login_btn">登录</p>
					<a href="register.jsp"><p>注册</p></a>
				</ul>
			</div>
			<script>
				$(function(){
					$(".meau").on("click", function(e) {
					$(".meau_box").slideToggle();
					$(document).one("click", function() {
						$(".meau_box").slideUp();
					});
					e.stopPropagation();
				});
				$(".meau").on("click", function(e) {
					e.stopPropagation();
				});
				});
			</script>
		<div class="register">
			<p class="res_title">用户注册</p>
			<div class="res_box">
				<div class="res_form">
					<form action="user/saveuser" method="post">
						<p class="res_list">
							<span>用户名</span>
							<input type="text" name="username" />
						</p>
						<p class="res_list">
							<span>手机号码</span>
							<input type="tel" name="tel" />
						</p>
						<!-- <p class="res_list" style="margin-top: 0px;">
							<span>手机验证码</span>
							<input type="text" style="width: 70px;"/>
							<span class="yan_code">获取验证码</span>
						</p> -->
						<p class="res_list">
							<span>邮箱地址</span>
							<input type="email" name="email" />
						</p>
					
						<p class="res_list">
						
							<span>设置密码</span>
							<input type="password" name="password"/>
						</p>
						<p class="res_list">
							<span>确认密码</span>
							<input type="password" name="repassword" />
						</p>
						<input type="submit" value="注册"/>
					</form>
					<p class="regis">已有账号？请<a style="color: #AE191B;" class="login_btn">直接登录</a></p>
		</div>
		<div class="login_bg">
			<div class="login">
				<img src="img/close.png" class="close"/>
				<img src="img/login.png" style="margin: 25px 0px;"/>
				<form action="user/login" method="post">
					<p class="list">
						<img src="img/login_pic2.png"/>
						<input type="text" placeholder="请输入注册时名字" name="username"/>
					</p>
					<p class="list">
						<img src="img/login_pic3.png"/>
						<input type="password" placeholder="请输入密码" name="password"/>
					</p>
					<a href="order.jsp">忘记密码?</a>
					<p>
					
						<input type="submit" value="注册"/>
						<input type="submit" value="登录"/>
					</p>
				</form>
			</div>
		</div>
		<script type="text/javascript">
			$(function(){
				$(".close").click(function(){
					$(".login_bg").fadeOut();
				});
				$(".login_btn").click(function(){
					$(".login_bg").slideDown();
					$(".meau_box").slideUp();
				});
			});
		</script>
		<script type="text/javascript">
			$(function(){
				$(".res_nav").children("li").click(function(){
					$(".res_nav").children("li").stop().removeClass("active");
					$(this).stop().addClass("active");
					$(".res_form").stop().slideUp();
					$(".res_form").eq($(".res_nav").children("li").index(this)).stop().slideDown();
				});
			});
		</script>
		<div class="footer">
			<div class="footer_con">
				<p>享有 | enjoy</p>
				<img src="img/footer.png" />
			</div>
			<div class="footer_con2">
				<p>© 2015 dingdongyouli.com All rights reserved.</p>
				<img src="img/footer_p2.jpg" />
			</div>
		</div>
</body>
</html>