package com.bookmails.service.impl;

import com.bookmails.dao.bookDao;
import com.bookmails.dao.impl.bookdaoimpl;
import com.bookmails.pojo.Paged;
import com.bookmails.pojo.book;

import java.util.List;

public class bookserviceimpl implements BookService{
    private bookDao bd =new bookdaoimpl();
    @Override
    public void addBook(book b) {
        bd.addBook(b);
    }

    @Override
    public void deleteBookById(Integer id) {
           bd.deleteBookById(id);
    }

    @Override
    public void updateBook(book b) {
          bd.updateBook(b);
    }

    @Override
    public book queryBookById(Integer id) {
        return bd.queryBookById(id);
    }

    @Override
    public List<book> queryBook() {
        return bd.queryBooks();
    }

    @Override
    public Paged<book> page1(int pageNo, int pageSize) {
        Paged<book> page = new Paged<book>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        //求总记录数
        Integer pageTotalCount = bd.queryForPageTotalCount();
        //设置总记录数
        page.setPageTotalCount(pageTotalCount);
        //求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if(pageTotalCount % pageSize >0)//最后一页要是有数据需要加一页
            pageTotal+=1;
        page.setPageTotal(pageTotal);

        if(pageNo< 1)
        {
            page.setPageNo(1);
        }
        if(pageNo > pageTotal)
        {
            page.setPageNo(pageTotal);
        }
        int begin = (page.getPageNo() - 1)*pageSize;
        List<book> items  = bd.queryForPageItems(begin,pageSize);
        //设置当前页数据
        page.setItems(items);

        return page;
    }

    @Override
    public Paged<book> pageByPrice(int pageNo, int pagesize, Double min, Double max) {
        Paged<book> page = new Paged<book>();
        page.setPageNo(pageNo);
        page.setPageSize(pagesize);
        //求总记录数
        Integer pageTotalCount = bd.queryForPageTotalCountByPrice(min,max);
        //设置总记录数
        page.setPageTotalCount(pageTotalCount);
        //求总页码
        Integer pageTotal = pageTotalCount / pagesize;
        if(pageTotalCount % pagesize >0)//最后一页要是有数据需要加一页
            pageTotal+=1;
        page.setPageTotal(pageTotal);

        if(pageNo< 1)
        {
            page.setPageNo(1);
        }
        if(pageNo > pageTotal)
        {
            page.setPageNo(pageTotal);
        }
        int begin = (page.getPageNo() - 1)*pagesize;
        List<book> items  = bd.queryForPageItemsByPrice(begin,pagesize,min,max);
        //设置当前页数据
        page.setItems(items);

        return page;

    }
}
