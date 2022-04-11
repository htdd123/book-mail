package com.bookmails.dao.impl;

import com.bookmails.pojo.book;

import java.util.List;

public interface bookDao {
    //添加图书
    public  int addBook(book b);
    //删除图书
    public int  deleteBookById(Integer id);
    //修改图书
    public int updateBook(book b);
    //查询图书
    public book queryBookById(Integer id);
    //查询所有图书
    public List<book> queryBooks();

    Integer queryForPageTotalCount();

    List<book> queryForPageItems(int begin,int pageSize);

    Integer queryForPageTotalCountByPrice(Double min, Double max);

    List<book> queryForPageItemsByPrice(int begin, int pagesize, Double min, Double max);
}
