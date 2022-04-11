package com.bookmails.web;

import com.bookmails.pojo.Paged;
import com.bookmails.pojo.book;
import com.bookmails.service.impl.BookService;
import com.bookmails.service.impl.bookserviceimpl;
import com.bookmails.utils.webutiles;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
//这个servlet是使用反射版的处理方式
@WebServlet(name = "Servletbookservlet", value = "/manager/servletbookservlet")
public class Servletbookservlet extends baseServlet {

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
            pagesize=Paged.PAGE_SIZE;
        }
        Paged<book> page = bs.page1(pageNo,pagesize);
        req.setAttribute("paged",page);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,res);
    }

     //添加图书--------------------------------------------------------------------------------------------
    protected void add(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
        //将获取的请求参数转换为book对象
        book b =webutiles.copyParamToBean(req,new book());//因为没有写成map的参数，所以这里也不能写
        //调用bookservice。addbook方法保存图书
        bs.addBook(b);
        //跳到图书列表页面
        //得使用重定向而不是请求转发，是因为请求转发会在用户刷新页面的时候重复提交表单导致多次添加同一本图书
        //重定向的/是定位到端口号，所以需要加工程名称
        res.sendRedirect(req.getContextPath()+"/manager/servletbookservlet?action=pages");
    }


    //删除图书----------------------------------------------------------------------------------------------
    protected void delete(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
        String id=req.getParameter("id");
          //System.out.println(id);
        int i = Integer.parseInt(id);
         //System.out.println(i);
        bs.deleteBookById(i);
        //重定向跳转回图书信book_manager页面
        res.sendRedirect(req.getContextPath()+"/manager/servletbookservlet?action=pages");
    }


    //得到一本书的信息---------------------------------------------------------------------------------------------
    protected void getbook(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
        int id = Integer.parseInt(req.getParameter("id"));
        book book = bs.queryBookById(id);
        req.setAttribute("book",book);
        System.out.println(book);
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,res);

    }
    //更新图书信息------------------------------------------------------------------------------------------------
    protected void update(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
        book b=webutiles.copyParamToBean(req,new book());
        bs.updateBook(b);
//        res.getWriter().write("<script type=\"text/javascript\">\n" +
//                "\t\t\talert(\" ");
//        res.getWriter().write(b.getName()+"被修改了");
//        res.getWriter().write("\" \\\");\\n\" +\n" +
//                  "\"\\t\\t</script>\"");
//        req.setAttribute("name",b.getName());
//        System.out.println(b.getName());

        res.sendRedirect(req.getContextPath()+"/manager/servletbookservlet?action=pages&name="+ URLEncoder.encode(b.getName(),"UTF-8"));
    }


    //查询图书-----------------------------------------------------------------------------------------------------
    protected void query(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
       //1:通过Bookservice查询全部图书
        List<book> books = bs.queryBook();

        //2:把全部图书保存到request域中
        req.setAttribute("books",books);
        //3:请求转发到/pages/manager./book_manager.jsp中
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,res);
    }


}
