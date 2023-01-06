package com.library.web;

import com.google.gson.Gson;
import com.library.pojo.User;
import com.library.service.UserService;
import com.library.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
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
            login(req, resp);
        } else if ("register".equals(action)) {
            System.out.println("处理注册的需求");
            register(req, resp);
        }else if ("loginOut".equals(action)) {
            System.out.println("处理注册的需求");
            loginOut(req, resp);
        }else if ("AjaxUsername".equals(action)) {
            System.out.println("处理注册的需求");
            AjaxUsername(req, resp);
        }
    }
    //验证用户名是否可用
    protected void AjaxUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        boolean b = userService.existsUsername(username);
        //false 就是用户名可用
        Map<String ,Object> resultMap = new HashMap<>();
        resultMap.put("b",b);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        resp.getWriter().write(json);
    }


        //注销用户
    protected void loginOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        注销就是销毁用户登录的信息，也就是销毁session
        req.getSession().invalidate();
//                然后就是重定向回首页
        resp.sendRedirect(req.getContextPath());
    }


    /**
     * 作用:专门处理登录的功能模块的Servlet程序的doPost方法’重写‘
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User login = userService.login(new User(null, username, password, null));


//         验证检查验证码---先写死，
        if (login != null) {
            //检查用户名是否正确
            System.out.println("获取响应成功--登录成功！");
            //保存用户登录之后的信息到Session域中
            req.getSession().setAttribute("user",login);
            Cookie cookie = new Cookie("username",username);
            cookie.setMaxAge(60*60);
            resp.addCookie(cookie);

            req.getRequestDispatcher("login_success.jsp").forward(req, resp);
            //req.getRequestDispatcher("index.jsp").forward(req, resp);
        } else {
            //也就是登录失败的情况
            //需要将失败的原因回显给客户端
            req.setAttribute("msg", "用户名或密码错误！");
            req.setAttribute("username", username);
            //请求转发，跳转回到login页面，在那个页面进行输出
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    /**
     * 作用:专门处理注册的功能模块的Servlet程序的doPost方法’重写‘
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取Session中的验证码
        String attribute = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);



        //        获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        //简化获取请求参数

        //User user = (User) webUtils.copyParamToBean(req.getParameterMap(),new User());

//         验证检查验证码---先写死
        if (attribute!=null && attribute.equalsIgnoreCase(code)) {
            //检查用户名是否正确
            if (userService.existsUsername("username")) {
                System.out.println("用户名" + username + "已存在不可用");
                req.setAttribute("msg", "用户名已存在!");
                req.setAttribute("username", username);
                req.setAttribute("email", email);
                req.getRequestDispatcher("/register.jsp").forward(req, resp);
            } else {
                System.out.println("获取响应成功--用户可以添加！");
                userService.registerUser(new User(null, username, password, email));
                req.getRequestDispatcher("register_success.jsp").forward(req, resp);

              //  req.getRequestDispatcher("index.jsp").forward(req, resp);
            }

        } else {
            System.out.println("验证码" + code + " 错误");
            req.setAttribute("msg", "验证码错误!");
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }
    }
}
