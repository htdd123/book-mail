<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String basepath=request.getScheme()+"://"+
			request.getServerName()+":"+request.getServerPort()+
			request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
	<meta charset="UTF-8" />
<title>尚硅谷会员注册页面</title>
	<base href="<%=basepath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css">
	<script type="text/javascript" src="static/jquery-1.7.2.js"></script>
	<script type="text/javascript">
		$(function ()
		{
         $("#sub_btn").click(function()
				 {
					 // 验证用户名：必须由字母，数字下划线组成，并且长度为 5 到 12 位
                     //1 获取用户名输入框里的内容

					 var usernameText = $("#username").val();
					 // var usernamePatt=/^\w{5,12}$/;
					 var usernamePatt=/^[\u4e00-\u9fa5_a-zA-Z0-9]{5,12}$/;
					 if(!usernamePatt.test(usernameText))
					 {
						 $("span.errorMsg").text("用户不合法");
						 return false;
					 }

					 var passwordText = $("#password").val();
					 var passwordPatt=/^\w{5,12}$/;
					 if(!passwordPatt.test(passwordText))
					 {
						 $("span.errorMsg").text("密码不合法");
						 return false;
					 }


					 var repwText=$("#repwd").val();
					 if(repwText != passwordText)
					 {
						 $("span.errorMsg").text("确认密码和密码不一致");
						 return false;
					 }

					 var emailText = $("#email").val();
                           //2 创建正则表达式对象
					 var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
                         //3 使用 test 方法验证是否合法
					 if (!emailPatt.test(emailText)) {
                          //4 提示用户
						 $("span.errorMsg").text("邮箱格式不合法！");
						 return false;
					 }


					 var codeText = $("#code").val();
					   codeText=$.trim(codeText);
					 if(codeText == null || codeText =="")
					 {
						 $("span.errorMsg").text("验证码不能为空");
						 return false;
					 }

					 $("span.errorMsg").text("");
		         }

		 );
		}
		);

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
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">
									<%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%>
								</span>
							</div>
							<div class="form">
								<form action="userservlet" method="post">
									<input type="hidden" name="action" value="regist"/>
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" id="username"
									 value="<%=request.getAttribute("username")==null?"":request.getAttribute("username")%>"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" id="email"
									value="<%=request.getAttribute("email")==null?"":request.getAttribute("email")%>"/>
									<br />
									<br />
									<label>验证码：</label>
<!--									<input class="itxt" type="text" style="width: 150px;" name="code" id="code"/>-->
									<input class="itxt" type="text" name="yaolei" style="width: 130px;" id="code" value=""/>
									<img alt="" src="kaptcha.jpg" style="float: right; margin-right: 40px; width:100px;height: 40px">
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
				尚硅谷书城.Copyright &copy;2015
			</span>
		</div>
</body>
</html>