package com.bookmails.dao.impl;

import com.bookmails.pojo.Paged;
import com.bookmails.pojo.book;
import org.junit.Test;

import static org.junit.Assert.*;

public class bookdaoimplTest {
      bookDao bd = new bookdaoimpl();
    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(bd.queryForPageTotalCountByPrice(10.1,1000.2));
    }

    @Test
    public void queryForPageItemsByPrice() {
       for(book b : bd.queryForPageItemsByPrice(1, Paged.PAGE_SIZE,30.0,100.0))
           System.out.println(b);;
    }
}