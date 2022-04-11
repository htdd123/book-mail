package com.bookmails.web;

import com.bookmails.dao.impl.Basedao;
import com.bookmails.pojo.Paged;
import com.bookmails.pojo.book;
import com.bookmails.service.impl.BookService;
import com.bookmails.service.impl.bookserviceimpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "ClientServlet", value = "/client/clientservlet")
public class ClientServlet extends baseServlet {

    private BookService bs = new bookserviceimpl();
    //分页模块-------------------------------------------------------------------------------------------------
    protected void pages(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
        //获取请求的参数pageNo和pageSize
        //调用BOOKservicepage(pageno,pagesize)
        //保存page对象到request域中
        //请求转发到pages/manager/book_manager.jsp
        int pageNo ;
        try{
            pageNo = Integer.parseInt(req.getParameter("pageNo"));
        }catch(NumberFormatException e)
        {
            pageNo =1;
        }
        int pagesize;
        try {
            pagesize = Integer.parseInt(req.getParameter("pageSize"));
        }catch(NumberFormatException e)
        {
            pagesize= Paged.PAGE_SIZE;
        }
        if(req.getParameter("min") != null)//用于判断是否带有价格区间，有的话就重定向到下面的方法
        {
            res.sendRedirect(req.getContextPath()+"/client/clientservlet?action=pageByPrice&pageNo="+pageNo+"&pageSize=" +
                    pagesize+"&min="+req.getParameter("min")+"&max="+req.getParameter("max"));
        }
        else{
            Paged<book> page = bs.page1(pageNo,pagesize);
            req.setAttribute("paged",page);
            req.getRequestDispatcher("/pages/client/index.jsp").forward(req,res);
        }

    }
    protected void pageByPrice(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
        int pageNo ;
        try{
            pageNo = Integer.parseInt(req.getParameter("pageNo"));
        }catch(NumberFormatException e)
        {
            pageNo =1;
        }

        int pagesize;

        try {
            pagesize = Integer.parseInt(req.getParameter("pageSize"));
        }catch(NumberFormatException e)
        {
            pagesize= Paged.PAGE_SIZE;
        }

        Double min ;
        try{
           min = Double.parseDouble(req.getParameter("min"));
        }catch(NumberFormatException e)
        {
            min =0.0;
        }

        Double max;
        try {
            max =Double.parseDouble(req.getParameter("max"));
        }catch(NumberFormatException e)
        {
            max= Double.MAX_VALUE;
        }
        Paged<book> page = bs.pageByPrice(pageNo,pagesize,min,max);
        req.setAttribute("paged",page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,res);
    }
}
