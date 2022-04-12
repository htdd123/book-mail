package com.bookmails.service.impl;

import com.bookmails.dao.OrderDao;
import com.bookmails.dao.OrderItemDao;
import com.bookmails.dao.impl.OrderDaoImpl;
import com.bookmails.dao.impl.OrderItemDaoImpl;
import com.bookmails.pojo.Cart;
import com.bookmails.pojo.CartItem;
import com.bookmails.pojo.Order;
import com.bookmails.pojo.OrderItem;

import java.sql.Date;
import java.util.Map;

public class OrderServlceImpl implements OrderService{
    private OrderDao od =new OrderDaoImpl();
    private OrderItemDao oid =new OrderItemDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        //生成订单号
        String orderId =System.currentTimeMillis()+""+userId;
        Order order =new Order(orderId,new Date(System.currentTimeMillis()),cart.getTotalPrice(),0,userId);
        od.saveOrder(order);

        //遍历购物车中每一个商品项转换成为订单项保存到数据库
        for(Map.Entry<Integer, CartItem>entry : cart.getItems().entrySet())
        {
            //获取每一购物车中的商品项
            CartItem cartItem = entry.getValue();
           //商品项转换为商品项
            OrderItem orderItem =new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            //保存订单项到数据库
            oid.saveOrderItem(orderItem);
        }
        cart.clear();
        return orderId;
    }
}
