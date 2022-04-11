package com.bookmails.web;

import com.bookmails.pojo.user;
import com.bookmails.service.impl.UserService;
import com.bookmails.service.impl.userserviceimpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

@WebServlet(name = "userServlet", value = "/userservlet")
public class userServlet extends HttpServlet {
    private UserService us=new userserviceimpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
//        //获取session中的验证码
//        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
//        //删除session中的验证码
//        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        String action =request.getParameter("action");

        if(action.equals("login"))
        {
            request.setCharacterEncoding("UTF-8");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
//          System.out.println(username+password);
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
                //登录成功
                //将登陆的用户信息保存在session域中
                request.getSession().setAttribute("user",logined);
                request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request,response);
            }
        }
        else if(action.equals("regist"))
        {
            //获取请求参数
//        response.setContentType("text/html; charset=UTF-8");
//            request.setCharacterEncoding("UTF-8");
//          System.out.println(response.getCharacter
          //获取session中的验证码
        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除session中的验证码
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String code = request.getParameter("yaolei");
//            System.out.println(username);
//            System.out.println(code);
            //检查验证码
            if(token!=null && token.equalsIgnoreCase(code))
            {
                if(us.existUsername((username)))
                {
                    //用户名不可用
//                    System.out.println("用户名-"+username+"-已存在");
                    request.setAttribute("msg","用户名已存在");
                    request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
                }
                else
                {
                    //用户名可用
                    us.registuser(new user(username,password,email));
                    request.getSession().setAttribute("username",username);
                    //跳转到注册成功界面
                    request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request,response);
                }
            }
            else
            {
                //跳回注册页面
//                System.out.println("验证码错误");
                request.setAttribute("msg","验证码错误");
                request.setAttribute("username",username);
                request.setAttribute("email",email);
                //跳转回到注册页面
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
            }
        }
        else if(action.equals("logout"))
        {
            request.getSession().invalidate();
            response.sendRedirect(request.getContextPath());
        }

    }
}
