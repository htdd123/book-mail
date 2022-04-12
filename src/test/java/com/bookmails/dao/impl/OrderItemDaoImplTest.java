package com.bookmails.dao.impl;

import com.bookmails.dao.OrderItemDao;
import com.bookmails.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderItemDaoImplTest {

    @Test
    public void saveOrderItem() {
        OrderItemDao oid = new OrderItemDaoImpl();
        oid.saveOrderItem(new OrderItem(123,"java从入门到静态",1,new BigDecimal(100),new BigDecimal(100),"123"));
    }
}