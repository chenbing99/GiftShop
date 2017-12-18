<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta charset="UTF-8" />
		<title>叮咚有礼--购物车</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="renderer" content="webkit">
		<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
		<link rel="stylesheet" href="css/demo.css" />
		<link rel="stylesheet" href="css/style.css" />
		<link rel="stylesheet" href="css/fen.css" />
		<script type="text/javascript" src="js/jquery-2.2.0.min.js"></script>
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
					<a href="blog.html"><li>BLOG</li></a>
					<a href="personal.jsp"><li>个人中心</li></a>
					<a href="message.html"><li>留言板</li></a>
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
		<div class="personal">
			<p class="title" style="margin-bottom: 30px;">我的购物车</p>
			<div class="per_box">
			<c:forEach var="b" items="${list2}">
				<ul class="per_navul shop_carul">
					<li style="width: 130px;margin-right: 10px;">
						<input type="checkbox" style="margin-right: 20px;"/>
						全选
					</li>
					<li style="width: 450px;text-align: left;">商品名称</li>
					<li>单价</li>
					<li>数量</li>
					<li>样式</li>
					<li>合计</li>
				</ul>
				<ul class="per_listul shop_carul" style="padding-top: 10px;box-sizing: border-box;">
					<li style="width: 130px;margin-right: 10px;">
						<input type="checkbox" style="margin-right: 10px;margin-left: 18px;"/>
						<img src="img/order_pic5.jpg" style="vertical-align: middle;"/>
					</li>
					<li style="width: 450px;text-align: left;">${b.name}</li>
					<li style="color: #3CA5F1;">${b.price}</li>
					<li>
						<p class="p5">
							<span class="num">-</span>
							<input type="text" value="0"/>
							<span class="num">+</span>
						</p>
					</li>
					<li>M10</li>
					<li style="color: #3CA5F1;">2*${b.price}</li>
					<li>删除</li>
				</ul>
				<ul class="per_listul shop_carul" style="padding-top: 10px;box-sizing: border-box;">
					<li style="width: 130px;margin-right: 10px;">
						<input type="checkbox" style="margin-right: 10px;margin-left: 18px;"/>
						<img src="img/order_pic5.jpg" style="vertical-align: middle;"/>
					</li>
					<li style="width: 450px;text-align: left;">${b.name}</li>
					<li style="color: #3CA5F1;">${b.price}</li>
					<li>
						<p class="p5">
							<span class="num">-</span>
							<input type="text" value="0"/>
							<span class="num">+</span>
						</p>
					</li>
					<li>--</li>
					<li style="color: #3CA5F1;">2*${b.price}</li>
					<li>删除</li>
				</ul>
				</c:forEach>
			</div>
			<p class="shop_car_totle">
				不包含运费共计：<span style="font-size: 19px;color: #d20000;">￥147</span>
				<a class="jie_btn" href="order.jsp">确认结算</a>
			</p>
		</div>
		<div class="login_bg">
			<div class="login">
				<img src="img/close.png" class="close"/>
				<img src="img/login.png" style="margin: 25px 0px;"/>
				<form action="" method="post">
					<p class="list">
						<img src="img/login_pic2.png"/>
						<input type="text" placeholder="请输入注册时的邮箱/手机号"/>
					</p>
					<p class="list">
						<img src="img/login_pic3.png"/>
						<input type="password" placeholder="请输入注册时的邮箱/手机号"/>
					</p>
					<a href="find.html">忘记密码?</a>
					<p>
						<a href="register.jsp">注册</a>
						<a>登录</a>
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