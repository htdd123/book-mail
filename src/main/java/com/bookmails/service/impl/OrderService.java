package com.bookmails.service.impl;

import com.bookmails.pojo.Cart;

public interface OrderService {
    //创建订单
    public String createOrder(Cart cart,Integer userId);
}
