package com.bookmails.service.impl;

import com.bookmails.pojo.book;
import com.bookmails.pojo.Paged;
import java.util.List;

public interface BookService {
    public void addBook(book b);
    public void deleteBookById(Integer id);
    public void updateBook(book b);
    public book queryBookById(Integer id);
    public List<book> queryBook();
    Paged<book> page1(int pageNo, int pageSize);

    Paged<book> pageByPrice(int pageNo, int pagesize, Double min, Double max);
}
