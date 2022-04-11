package com.bookmails.web;

import com.bookmails.pojo.user;
import com.bookmails.service.impl.UserService;
import com.bookmails.service.impl.userserviceimpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "loginServlet", value = "/loginservlet")
public class loginServlet extends HttpServlet {
    private UserService us=new userserviceimpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username+password);
        //调用userservice.login处理登录业务
        user logined = us.login(new user(username,password,null));
        //如果等于null，就是登录失败
        if(logined==null)
        {
            //跳回登陆界面
            //把错误信息，和回显的表单项信息，保存到request域中
            request.setAttribute("msg","用户名或密码错误");
            request.setAttribute("username",username);

            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);

        }else
        {
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request,response);
        }
    }
}
