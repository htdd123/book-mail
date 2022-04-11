package com.bookmails.dao.impl;

public class asd extends Basedao{
    public void asdf(int a,int b)
    {

        String sql="update t_book set `img_path`=?where id=?";

        update(sql,"static/img/"+a+".jpg",b);
    }
}
