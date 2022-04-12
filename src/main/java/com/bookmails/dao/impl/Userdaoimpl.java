package com.bookmails.dao.impl;

import com.bookmails.dao.Basedao;
import com.bookmails.dao.Userdao;
import com.bookmails.pojo.user;

public class Userdaoimpl extends Basedao implements Userdao {
    @Override
    public user queryUserByUsername(String username) {
        String sql = "select id,username,password,email from t_user where username =? ";
        return queryForOne(user.class,sql,username);
    }

    @Override
    public int saveUser(user u) {
      String sql = "insert into t_user(username,password,email) values(?,?,?)";
      return update(sql,u.getUsername(),u.getPassword(),u.getEmail());
    }

    @Override
    public user queryUserByUsernameAndPassword(String username, String password) {
        String sql ="select id,username,password,email from t_user where username = ? and password = ?";
        return queryForOne(user.class,sql,username,password);
    }
}
