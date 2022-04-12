package com.bookmails.web;

import com.bookmails.pojo.Cart;
import com.bookmails.pojo.CartItem;
import com.bookmails.pojo.book;
import com.bookmails.service.impl.BookService;
import com.bookmails.service.impl.bookserviceimpl;
import com.bookmails.utils.webutiles;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartServlet extends baseServlet{
    BookService bs = new bookserviceimpl();
    /**
     * 加入购入车
     * @param req
     * @param resp
     */
    protected void addItems(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取请求的参数 商品编号
       int id = Integer.parseInt(req.getParameter("bookid"));
//        System.out.println(id);
       //调用bookservlet.queryBookById得到图书的信息
        book b = bs.queryBookById(id);
        //把图书信息转换为cartItem商品项
        CartItem ci = new CartItem(b.getId(),b.getName(),1,b.getPrice(),b.getPrice());
        //调用cart.additem添加商品项
        Cart c = (Cart)req.getSession().getAttribute("cart");
        if(c == null)
        {
            c=new Cart();
            req.getSession().setAttribute("cart",c);
        }
        c.addItem(ci);
//        System.out.println(c);
        req.getSession().setAttribute("lastnookname",ci.getName());
        //跳转会商品页面
        resp.sendRedirect(req.getHeader("Referer"));

    }
    //删除购物车里面的一键商品
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws IOException{
    int id=Integer.parseInt((req.getParameter("id")));
     Cart cart =(Cart)  req.getSession().getAttribute("cart");
     if(cart!=null)
     {
         cart.deleteItem(id);
         resp.sendRedirect(req.getHeader("Referer"));
     }
    }
    //清空购物车
    protected void clearall(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        Cart cart =(Cart)  req.getSession().getAttribute("cart");
        if(cart!=null)
        {
            cart.clear();
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
}
