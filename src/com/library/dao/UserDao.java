package com.library.dao;

import com.library.pojo.User;

public interface UserDao {

     /**
      * 根据用户名查询信息
      * @param username
      * @return
      */
     public User queryByUsername(String username);

     /**
      * 保存用户信息
      * @param user
      * @return
      */
     public int saveUser(User user);

     /**
      * 根据用户名和密码查询用户信息
      * @param username
      * @param password
      * @return
      */
     public User queryByUsernameAndPassword(String username,String password);

}
