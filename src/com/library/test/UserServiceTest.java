package com.library.test;

import com.library.pojo.User;
import com.library.service.UserService;
import com.library.service.impl.UserServiceImpl;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserServiceTest {

    UserService userService = new UserServiceImpl();
    @Test
    public void testRegisterUser() {
        userService.registerUser(new User(null,"ray","21021","15621@qq.com"));
    }

    @Test
    public void testLogin() {
        System.out.println(userService.login(new User(null,"ray","21021","15621@qq.com")));
    }

    @Test
    public void testExistsUsername() {
        boolean b = userService.existsUsername("r1y");
        if(b==true){
            System.out.println("用户已存在！");
        }else{
            System.out.println("用户名可用");
        }
    }
}