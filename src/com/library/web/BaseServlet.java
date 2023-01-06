package com.library.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 此Base标签暂时用不了，无法合并，报错
 *
 */
public abstract class BaseServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
//        try {
//            Method method =this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
//            method.invoke(this,req,resp);
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        if ("login".equals(action)) {
            System.out.println("处理登录的需求");
            //UserServlet.login(req, resp);
        } else if ("register".equals(action)) {
            System.out.println("处理注册的需求");
            //UserServlet.register(req, resp);
        }
    }
}
