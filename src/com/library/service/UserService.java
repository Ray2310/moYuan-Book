package com.library.service;

import com.library.pojo.User;

public interface UserService {
    /**
     * 注册用户
     * @param user
     */
    public void registerUser(User user);

    /**
     * 登录
     * @param user
     */
    public User login(User user);

    /**
     * 检查用户名是否可用
     * @param username
     * @return true表示用户名已存在
     */
    public boolean existsUsername(String username);
}
