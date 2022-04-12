package com.bookmails.dao.impl;

import com.bookmails.dao.Basedao;
import com.bookmails.dao.OrderItemDao;
import com.bookmails.pojo.OrderItem;

public class OrderItemDaoImpl extends Basedao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }
}
