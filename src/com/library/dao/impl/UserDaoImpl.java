package com.library.dao.impl;

import com.library.dao.UserDao;
import com.library.pojo.User;

public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public User queryByUsername(String username) {
        String sql ="select `id`,`username`,`password`,`email` from t_user where `username` = ?";
        return queryForOne(User.class,sql,username);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(`username`,`password`,`email`) VALUES(?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }

    @Override
    public User queryByUsernameAndPassword(String username, String password) {
        String sql ="select `id`,`username`,`password`,`email` from t_user where `username` = ? and `password` = ?";
        return queryForOne(User.class,sql,username,password);
    }

}
