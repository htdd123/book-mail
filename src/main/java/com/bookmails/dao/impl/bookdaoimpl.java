package com.bookmails.dao.impl;

import com.bookmails.dao.Basedao;
import com.bookmails.dao.bookDao;
import com.bookmails.pojo.book;

import java.util.List;

public class bookdaoimpl extends Basedao implements bookDao {
    @Override
    public int addBook(book b) {
        String sql="insert into t_book(`name`,`author`,`price`,`sales`,`stock`,`img_path`) values(?,?,?,?,?,?)";
        return update(sql,b.getName(),b.getAuthor(),b.getPrice(),b.getSales(),b.getStock(),b.getImgPath());
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql="delete from t_book where id=?";
        return update(sql,id);
    }

    @Override
    public int updateBook(book b) {
        String sql="update t_book set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=? where id =?";
        return update(sql,b.getName(),b.getAuthor(),b.getPrice(),b.getSales(),b.getStock(),b.getImgPath(),b.getId());
    }

    @Override
    public book queryBookById(Integer id) {
        String sql="select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgpath from t_book where id =?";
        return queryForOne(book.class,sql,id);
    }

    @Override
    public List<book> queryBooks() {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgpath from t_book";
        return queryForList(book.class,sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_book";
        Number o =(Number) queryForSingleValue(sql);
        return o.intValue();
    }

    @Override
    public List<book> queryForPageItems(int begin,int pageSize) {
         String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgpath from t_book limit ?,?";

        return queryForList(book.class,sql,begin,pageSize);
    }

    @Override
    public Integer queryForPageTotalCountByPrice(Double min, Double max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        Number o = (Number) queryForSingleValue(sql,min,max);
        return o.intValue();
    }

    @Override
    public List<book> queryForPageItemsByPrice(int begin, int pagesize, Double min, Double max) {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgpath from t_book where price between ? and ? order by price limit ?,?";
        return queryForList(book.class,sql,min,max,begin,pagesize);
    }
}
