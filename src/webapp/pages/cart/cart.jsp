<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String basepath=request.getScheme()+"://"+
			request.getServerName()+":"+request.getServerPort()+
			request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<base href="<%=basepath%>"/>
<title>购物车</title>
<!--<base href="http://localhost:8080/BookStore02/">-->
	<script type="text/javascript" src="static/jquery-1.7.2.js"></script>
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<div>
				<span>欢迎<span class="um_span">${sessionScope.username}</span>光临尚硅谷书城</span>
				<a href="order/order.jsp">我的订单</a>
				<a href="index.jsp">注销</a>&nbsp;&nbsp;
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
<%--购物车没有商品的显示提示--%>
			<c:if test="${empty sessionScope.cart.items}">
				<tr><td colspan="5">亲，您想在还没添加宝贝呢，点击前往商城挑选</td></tr>
				<tr ><td colspan="5"><a href="index.jsp " style="color: #39987c;font-size: large;">立即前往商城</a></td></tr>
			</c:if>

			<c:forEach items = "${sessionScope.cart.items}" var="entry">
			<tr>
				<td>${entry.value.name}</td>
				<td>${entry.value.count}</td>
				<td>${entry.value.price}</td>
				<td>${entry.value.totalPrice}</td>
				<td><a class="delete" href="cartservlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
			</tr>
			</c:forEach>
		</table>
              <%--购物车非空才显示下面的内容--%>
		<c:if test="${not empty sessionScope.cart.items}">
		<div class="cart_info">
			<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
			<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
			<span class="cart_span"><a class="clear" href="cartservlet?action=clearall">清空购物车</a></span>
			<span class="cart_span"><a href="checkout.jsp">去结账</a></span>
		</div>
		</c:if>
	</div>
	<script type="text/javascript">
		$(function(){
		//	提示用户是否真的要删除商品
		$("a.delete").click(function(){
             return confirm(("你确定要从购物车里删除《"+$(this).parent().find("td:first").text()+"》吗"));
		})
			$("a.clear").click(function () {
				return confirm(("提示，您确定要清空购物车吗"));
			})
		})

	</script>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2022
		</span>
	</div>
</body>
</html>