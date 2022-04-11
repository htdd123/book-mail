package com.bookmails.service.impl;

import com.bookmails.pojo.user;

public interface UserService {
    //注册用户
    //@param user
    public void registuser(user u);
    //登录
    //@param user
    //return user
    public user login(user u);
    //检查用户名是否可用
    public boolean existUsername(String name);
}
