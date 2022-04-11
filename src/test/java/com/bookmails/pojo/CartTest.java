package com.bookmails.pojo;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CartTest {


    @Test
    public void addItem() {
        Cart c =new Cart();
        c.addItem(new CartItem(1,"asdjg",1,new BigDecimal(1000),new BigDecimal(100)));
        c.addItem(new CartItem(1,"asdqwe",1,new BigDecimal(1000),new BigDecimal(100)));
        c.addItem(new CartItem(2,"asd",1,new BigDecimal(1000),new BigDecimal(100)));
        c.addItem(new CartItem(3,"qwe",1,new BigDecimal(1000),new BigDecimal(100)));
        System.out.println(c);
    }

    @Test
    public void deleteItem() {
        Cart c =new Cart();
        c.addItem(new CartItem(1,"asdjg",1,new BigDecimal(1000),new BigDecimal(100)));
        c.addItem(new CartItem(1,"asdqwe",1,new BigDecimal(1000),new BigDecimal(100)));
        c.addItem(new CartItem(2,"asd",1,new BigDecimal(1000),new BigDecimal(100)));
        c.addItem(new CartItem(3,"qwe",1,new BigDecimal(1000),new BigDecimal(100)));
        c.deleteItem(1);
        System.out.println(c);
    }

    @Test
    public void clear() {
        Cart c =new Cart();
        c.addItem(new CartItem(1,"asdjg",1,new BigDecimal(1000),new BigDecimal(100)));
        c.addItem(new CartItem(1,"asdqwe",1,new BigDecimal(1000),new BigDecimal(100)));
        c.addItem(new CartItem(2,"asd",1,new BigDecimal(1000),new BigDecimal(100)));
        c.addItem(new CartItem(3,"qwe",1,new BigDecimal(1000),new BigDecimal(100)));
        c.clear();
        System.out.println(c);
    }

    @Test
    public void update() {
        Cart c =new Cart();
        c.addItem(new CartItem(1,"asdjg",1,new BigDecimal(1000),new BigDecimal(100)));
        c.addItem(new CartItem(1,"asdqwe",1,new BigDecimal(1000),new BigDecimal(100)));
        c.addItem(new CartItem(2,"asd",1,new BigDecimal(1000),new BigDecimal(100)));
        c.addItem(new CartItem(3,"qwe",1,new BigDecimal(1000),new BigDecimal(100)));
        c.update(1,10);
        System.out.println(c);
    }
}