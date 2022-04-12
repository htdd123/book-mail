package com.bookmails.service.impl;

import com.bookmails.dao.Userdao;
import com.bookmails.dao.impl.Userdaoimpl;
import com.bookmails.pojo.user;

public class userserviceimpl implements UserService{
    private Userdao ud =new Userdaoimpl();
    @Override
    public void registuser(user u) {
        ud.saveUser(u);
    }

    @Override
    public user login(user u) {
        return ud.queryUserByUsernameAndPassword(u.getUsername(),u.getPassword());
    }

    @Override
    public boolean existUsername(String name) {
       if(ud.queryUserByUsername(name)==null)
       {
           return false;
       }
       else
           return true;
    }
}
