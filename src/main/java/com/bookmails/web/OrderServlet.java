package com.bookmails.web;

import com.bookmails.pojo.Cart;
import com.bookmails.pojo.user;
import com.bookmails.service.impl.OrderService;
import com.bookmails.service.impl.OrderServlceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "OrderServlet", value = "/OrderServlet")
public class OrderServlet extends baseServlet {
    OrderService os =new OrderServlceImpl();

    //生成订单
    protected void createOrder(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        //先获取购物车对象
        Cart cart =(Cart) req.getSession().getAttribute("cart");
        //获取userid
        user  loginuser = (user) req.getSession().getAttribute("user");

        if(loginuser == null)
        {
                req.getRequestDispatcher("pages/user/login.jsp").forward(req,resp);
                return ;
        }


        Integer id = Integer.parseInt(loginuser.getId());
        //调用orderService.creatorder生成订单
        String orderId = os.createOrder(cart,id);

        req.getSession().setAttribute("orderId",orderId);
        //防止重复提交，需要使用重定向到订单页面
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }

}
