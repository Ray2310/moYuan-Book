package com.library.test;

import com.library.dao.UserDao;
import com.library.dao.impl.UserDaoImpl;
import com.library.pojo.User;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserDaoTest {

    public UserDao userDao = new UserDaoImpl();
    @Test
    public void testQueryByUsername() {

        System.out.println(userDao.queryByUsername("tom"));
    }

    @Test
    public void testSaveUser() {
        System.out.println(userDao.saveUser(new User(null,"allen","123001","1811@163.com")));
    }

    @Test
    public void testQueryByUsernameAndPassword() {
        User user = userDao.queryByUsernameAndPassword("tom", "green");
        if(user==null) {
            System.out.println("用户不存在！");
        }else{
            System.out.println("登录成功！");
            System.out.println(user);
        }
    }
}