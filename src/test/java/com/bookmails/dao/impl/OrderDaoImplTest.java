package com.bookmails.dao.impl;

import com.bookmails.dao.OrderDao;
import com.bookmails.pojo.Order;
import org.junit.Test;
import java.math.BigDecimal;
import java.sql.Date;

import static org.junit.Assert.*;

public class OrderDaoImplTest {

    @Test
    public void saveOrder() {
        OrderDao od = new OrderDaoImpl();
        od.saveOrder(new Order("123121",new Date(System.currentTimeMillis()),new BigDecimal(100),0,4));

    }
}