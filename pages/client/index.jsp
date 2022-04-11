<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String basepath=request.getScheme()+"://"+
			request.getServerName()+":"+request.getServerPort()+
			request.getContextPath()+"/";
%>
<%
//用于将加个查询的结果作用与分页查询，要将加个区间带入分页查询，并有分页的servlet处理
//有价格区间就返回jiage没有就没有min值，就返回null
	String jiage;
	if (request.getParameter("min") != null)
	{
		if (!request.getParameter("min").equals(""))
		{
			jiage = "&min="+request.getParameter("min")+"&max="+request.getParameter("max");
		}else
		{
			jiage="";
		}

	}
	else
	{
		jiage="";
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
	<base href="<%=basepath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">网上书城</span>
			<div>
               <%--判断是否用户登录成功--%>
				<c:if test="${empty sessionScope.user}">
					<a href="pages/user/login.jsp">登录</a> |
					<a href="pages/user/regist.jsp">注册</a>
				</c:if>

				<c:if test="${not empty sessionScope.user}">
					<span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
					<a href="pages/order/order.jsp">我的订单</a>
					<a href="userservlet?action=logout">注销</a>&nbsp;&nbsp;
				</c:if>

				<a href="pages/cart/cart.jsp">购物车</a>
				<a href="pages/manager/manager.jsp">后台管理</a>
			</div>
	</div>
	<div id="main">
		<div id="book">
			<div class="book_cond">
				<form action="client/clientservlet" method="get">
					<input type="hidden" name="action" value="pageByPrice">
					价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
						<input id="max" type="text" name="max" value="${param.max}"> 元
						<input type="submit" value="查询" />
				</form>
			</div>
			<div style="text-align: center">
				<span>您的购物车中有3件商品</span>
				<div>
					您刚刚将<span style="color: red">时间简史</span>加入到了购物车中
				</div>
			</div>
			<c:forEach items="${requestScope.paged.items}" var="book">
				<div class="b_list">
					<div class="img_div">
						<img class="book_img" alt="" src="${book.imgPath}" />
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
							<button>加入购物车</button>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>


	<%--			<div class="b_list"></div>--%>
<%--			--%>
<%--			<div class="b_list"></div>--%>
<%--			--%>
<%--			<div class="b_list"></div>--%>
<%--		</div>--%>
		
		<div id="page_nav">
			<c:if test="${requestScope.paged.pageNo>1}">
				<%--只有页号大于首页才显示上一页--%>
				<a href="client/clientservlet?action=pages&pageNo=1<%=jiage%>">首页</a>
				<a href="client/clientservlet?action=pages&pageNo=${requestScope.paged.pageNo-1}<%=jiage%>">上一页</a>
			</c:if>
			<%--页码输出的开始--%>
			<c:choose>
				<%--情况1：总页码小于等于5的情况--%>
				<c:when test="${requestScope.paged.pageTotal <=5 }">
					<c:forEach begin="1" end="${requestScope.paged.pageTotal}" var="i">
						<c:if test="${i == requestScope.paged.pageNo}">
							【${i}】
						</c:if>
						<c:if test="${i != requestScope.paged.pageNo}">
							<a href="client/clientservlet?action=pages&pageNo=${i}<%=jiage%>">${i}</a>
						</c:if>
					</c:forEach>
				</c:when>

				<%--页码数大于5的情况--%>
				<c:when test="${requestScope.paged.pageTotal > 5 }">
					<c:choose>
						<%--当前页码是在前三个的情况--%>
						<c:when test="${requestScope.paged.pageNo <= 3}">
							<c:forEach begin="1" end="5" var="i">
								<c:if test="${i == requestScope.paged.pageNo}">
									【${i}】
								</c:if>
								<c:if test="${i != requestScope.paged.pageNo}">
									<a href="client/clientservlet?action=pages&pageNo=${i}<%=jiage%>">${i}</a>
								</c:if>
							</c:forEach>
						</c:when>

						<%--当前页码在后三个的情况--%>
						<c:when test="${requestScope.paged.pageNo >=requestScope.paged.pageTotal-3}">
							<c:forEach begin="${requestScope.paged.pageTotal-4}" end="${requestScope.paged.pageTotal}" var="i">
								<c:if test="${i == requestScope.paged.pageNo}">
									【${i}】
								</c:if>
								<c:if test="${i != requestScope.paged.pageNo}">
									<a href="client/clientservlet?action=pages&pageNo=${i}<%=jiage%>">${i}</a>
								</c:if>
							</c:forEach>
						</c:when>

						<%--当前页码是在中间的情况--%>
						<c:otherwise>
							<c:forEach begin="${requestScope.paged.pageNo - 2}" end="${requestScope.paged.pageNo + 2}" var="i">
								<c:if test="${i == requestScope.paged.pageNo}">
									【${i}】
								</c:if>
								<c:if test="${i != requestScope.paged.pageNo}">
									<a href="client/clientservlet?action=pages&pageNo=${i}<%=jiage%>">${i}</a>
								</c:if>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</c:when>
			</c:choose>


			当前页【${requestScope.paged.pageNo}】
			<c:if test="${requestScope.paged.pageNo < requestScope.paged.pageTotal}">
				<%--只有页号小于总页码，有下一页和末页--%>
				<a href="client/clientservlet?action=pages&pageNo=${requestScope.paged.pageNo+1}<%=jiage%>">下一页</a>
				<a href="client/clientservlet?action=pages&pageNo=${requestScope.paged.pageTotal}<%=jiage%>">末页</a>
			</c:if>
			共${requestScope.paged.pageTotal}页，${requestScope.paged.pageTotalCount}条记录
			到第<input name="pn" id="pn_input"/>页
			<input id="searchPageBtn" type="button" value="确定">
		</div>
		<script tyep="text/javascript">
			$(function (){
				$("#searchPageBtn").click(function(){
					var pageNo = $("#pn_input").val();
					location.href="${pageScope.basePath}client/clientservlet?action=pages&pageNo="+pageNo+<%=jiage%>;
				})
			})

		</script>
	</div>
<%--		<a href="#">首页</a>--%>
<%--		<a href="#">上一页</a>--%>
<%--		<a href="#">3</a>--%>
<%--		【4】--%>
<%--		<a href="#">5</a>--%>
<%--		<a href="#">下一页</a>--%>
<%--		<a href="#">末页</a>--%>
<%--		共10页，30条记录 到第<input value="4" name="pn" id="pn_input"/>页--%>
<%--		<input type="button" value="确定">--%>
		</div>
	
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>