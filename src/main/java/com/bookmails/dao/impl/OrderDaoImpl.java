package com.bookmails.dao.impl;

import com.bookmails.dao.Basedao;
import com.bookmails.dao.OrderDao;
import com.bookmails.pojo.Order;

public class OrderDaoImpl extends Basedao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
//        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        String sql = "insert into t_order(order_id,create_time,price,status,user_id) values(?,?,?,?,?)";
//        insert into t_order(order_id,create_time,price,status,user_id) values('124',20220212,100,0,4);
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }
}
