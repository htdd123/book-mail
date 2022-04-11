package com.bookmails.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

//购物车
public class Cart {
//    private Integer totalCount;
//    private BigDecimal totalPrice;
    private Map<Integer,CartItem> items =new HashMap<Integer, CartItem>();


    public Integer getTotalCount() {
       Integer totalCount=0;
        for(Map.Entry<Integer,CartItem> entry:items.entrySet())
        {
         totalCount += entry.getValue().getCount();
        }
    return totalCount;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice =new BigDecimal(0);
        for(Map.Entry<Integer,CartItem> entry:items.entrySet())
        {
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    /*
    * 添加商品项
    * @param cartItem
    * */
    public void addItem(CartItem cartItem)
    {
        //检查购物车中是否已经添加了这个商品，若添加了就增加数量，若没有添加才添加新的商品
        CartItem item = items.get(cartItem.getId());
        if(item == null)
        {
            items.put(cartItem.getId(),cartItem);
        }
        else
        {
            item.setCount( item.getCount() +1);
            item.setTotalPrice( item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }
    /*
    *删除商品项
    *@param id
     */
    public void deleteItem(Integer id)
    {
        items.remove(id);
    }

    /*
    清空购物车
     */
    public void clear()
    {
      items.clear();
    }
    /*
    修改商品数量
     */
    public void update(Integer id,Integer count)
    {
        //先查看购物车中是否有此商品，若有则改
        CartItem cartItem = items.get(id);
        if(cartItem != null)
        {
            cartItem.setCount(count);
            cartItem.setTotalPrice( cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }


    @Override
    public String toString() {
        return "Cart{" +
                "TotalCount=" +this.getTotalCount()+
                "TotalPrice"+this.getTotalPrice()+
                ", items=" + items +
                '}';
    }
}
