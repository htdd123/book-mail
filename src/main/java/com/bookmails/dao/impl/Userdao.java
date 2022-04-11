package com.bookmails.dao.impl;

import com.bookmails.pojo.user;

public interface Userdao {

    //根据用户名查询用户信息
    //返回null就是未查询到
    public user queryUserByUsername(String username);
    public int saveUser(user u);
    public user queryUserByUsernameAndPassword(String username,String password);
}
