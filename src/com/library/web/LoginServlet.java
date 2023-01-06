package com.library.web;

import com.library.pojo.User;
import com.library.service.UserService;
import com.library.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 这个功能已经被合并
 */
public class LoginServlet extends HttpServlet {
    public UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User login = userService.login(new User(null, username, password, null));


//         验证检查验证码---先写死，
        if (login != null) {
            //检查用户名是否正确
            System.out.println("获取响应成功--登录成功！");
            req.getRequestDispatcher("login_success.jsp").forward(req, resp);
            req.getRequestDispatcher("index.jsp").forward(req,resp);
        } else {
            //也就是登录失败的情况
            //需要将失败的原因回显给客户端
            req.setAttribute("msg","用户名或密码错误！");
            req.setAttribute("username",username);
            //请求转发，跳转回到login页面，在那个页面进行输出
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }
}
