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
public class RegisterServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");


//         验证检查验证码---先写死
        if("abcde".equalsIgnoreCase(code)){
            //检查用户名是否正确
            if(userService.existsUsername("username")){
                System.out.println("用户名"+username+"已存在不可用");
                req.setAttribute("msg","用户名已存在!");
                req.setAttribute("username",username);
                req.setAttribute("email",email);
                req.getRequestDispatcher("/register.jsp").forward(req,resp);
            }else{
                System.out.println("获取响应成功--用户可以添加！");
                userService.registerUser(new User(null,username,password,email));
                req.getRequestDispatcher("register_success.jsp").forward(req,resp);

                req.getRequestDispatcher("index.jsp").forward(req,resp);
            }

        }else{
            System.out.println("验证码"+code +" 错误");
            req.setAttribute("msg","验证码错误!");
            req.setAttribute("username",username);
            req.setAttribute("email",email);
            req.getRequestDispatcher("/register.jsp").forward(req,resp);
        }
    }
}
