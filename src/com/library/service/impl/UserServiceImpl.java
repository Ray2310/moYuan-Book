package com.library.service.impl;

import com.library.dao.UserDao;
import com.library.dao.impl.UserDaoImpl;
import com.library.pojo.User;
import com.library.service.UserService;

public class UserServiceImpl implements UserService {

    public UserDao userDao = new UserDaoImpl();

    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        User u_1 = userDao.queryByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (u_1!=null){
            return u_1;
        }
        else {
            System.out.println("登录失败");
            return null;
        }
    }

    @Override
    public boolean existsUsername(String username) {
        if(userDao.queryByUsername(username)==null){
            return false;
        }
        return true;
    }
}
