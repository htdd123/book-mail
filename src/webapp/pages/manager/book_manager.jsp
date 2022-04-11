<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String basepath=request.getScheme()+"://"+
			request.getServerName()+":"+request.getServerPort()+
			request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<base href="<%=basepath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
	<script type="text/javascript" src="static/jquery-1.7.2.js"></script>
	<script type="text/javascript">
		$(function(){
			//给删除的a标签绑定单机事件，用于删除的确认提示操作(防止误删)
			$("a.deleteclass").click(function(){
				//confirm是确认提示框函数，参数是提示内容，他有两个按钮确认和取消
				//记得导入jquery包
				return confirm("你确定要删除【" + $(this).parent().parent().find("td:first").text()+ " 】？");
			})
		})
	</script>
	<script>
         <%--提示修改成功信息--%>
		var xx ='<%=request.getParameter("name")%>';
	if(xx != "null"){
		var aas=xx + "修改成功";
		alert(aas);
	}
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
			<div>
				<a href="pages/manager/book_manager.jsp">图书管理</a>
				<a href="pages/manager/order_manager.jsp">订单管理</a>
				<a href="index.jsp">返回商城</a>
			</div>
	</div>
	<div>
		<a href="manager/servletbookservlet?action=query"/>
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
			<c:forEach items="${requestScope.paged.items}" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a href="manager/servletbookservlet?action=getbook&id=${book.id}&method=update">修改</a></td>
					<td><a class="deleteclass" href="manager/servletbookservlet?action=delete&id=${book.id}">删除</a></td>
				</tr>
			</c:forEach>
			
<%--			<tr>--%>
<%--				<td>时间简史</td>--%>
<%--				<td>20.00</td>--%>
<%--				<td>霍金</td>--%>
<%--				<td>200</td>--%>
<%--				<td>400</td>--%>
<%--				<td><a href="pages/manager/book_edit.jsp">修改</a></td>--%>
<%--				<td><a href="#">删除</a></td>--%>
<%--			</tr>	--%>
<%--			--%>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?method=add">添加图书</a></td>
			</tr>	
		</table>
		<div>--------------------------------------------------------------------------------------------------------------------------------------------------------------------
		</div>
		<div id="page_nav">
			<c:if test="${requestScope.paged.pageNo>1}">
<%--只有页号大于首页才显示上一页--%>
				<a href="manager/servletbookservlet?action=pages&pageNo=1">首页</a>
				<a href="manager/servletbookservlet?action=pages&pageNo=${requestScope.paged.pageNo-1}">上一页</a>
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
						   <a href="manager/servletbookservlet?action=pages&pageNo=${i}">${i}</a>
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
								   <a href="manager/servletbookservlet?action=pages&pageNo=${i}">${i}</a>
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
								   <a href="manager/servletbookservlet?action=pages&pageNo=${i}">${i}</a>
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
								   <a href="manager/servletbookservlet?action=pages&pageNo=${i}">${i}</a>
							   </c:if>
						   </c:forEach>
					   </c:otherwise>
				   </c:choose>
			   </c:when>
		   </c:choose>


			当前页【${requestScope.paged.pageNo}】
			<c:if test="${requestScope.paged.pageNo < requestScope.paged.pageTotal}">
<%--只有页号小于总页码，有下一页和末页--%>
				<a href="manager/servletbookservlet?action=pages&pageNo=${requestScope.paged.pageNo+1}">下一页</a>
				<a href="manager/servletbookservlet?action=pages&pageNo=${requestScope.paged.pageTotal}">末页</a>
			</c:if>
			共${requestScope.paged.pageTotal}页，${requestScope.paged.pageTotalCount}条记录
			到第<input name="pn" id="pn_input"/>页
			<input id="searchPageBtn" type="button" value="确定">
		</div>
   <script tyep="text/javascript">
	   $(function (){
		   $("#searchPageBtn").click(function(){
			   var pageNo = $("#pn_input").val();
			   location.href="${pageScope.basePath}manager/servletbookservlet?action=pages&pageNo="+pageNo;
		   })
	   })

   </script>
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>