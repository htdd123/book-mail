package com.bookmails.web;

import com.bookmails.pojo.user;
import com.bookmails.service.impl.UserService;
import com.bookmails.service.impl.userserviceimpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RegisServlet", value = "/registservlet")
public class RegisServlet extends HttpServlet {
    private UserService  us= new userserviceimpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           //获取请求参数
//        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        System.out.println(response.getCharacterEncoding());
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("yaolei");
        System.out.println(username);
        System.out.println(code);
        //检查验证码
        if("abcde".equalsIgnoreCase(code))
        {
            if(us.existUsername((username)))
            {
                //用户名不可用
                System.out.println("用户名-"+username+"-已存在");
                request.setAttribute("msg","用户名已存在");
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
            }
            else
            {
                //用户名可用
                us.registuser(new user(username,password,email));
                //跳转到注册成功界面
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request,response);
            }
        }
        else
        {
            //跳回注册页面
            System.out.println("验证码错误");
            request.setAttribute("msg","验证码错误");
            request.setAttribute("username",username);
            request.setAttribute("email",email);
            //跳转回到注册页面
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);

        }

    }
}
