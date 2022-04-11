<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String basepath=request.getScheme()+"://"+
			request.getServerName()+":"+request.getServerPort()+
			request.getContextPath()+"/";
%>
<base href="<%=basepath%>">
<jsp:forward page="client/clientservlet?action=pages"></jsp:forward>