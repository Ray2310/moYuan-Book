package com.library.web;

import com.library.pojo.Cart;
import com.library.pojo.Order;
import com.library.pojo.User;
import com.library.service.OrderService;
import com.library.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//订单项的Servlet
public class OrderServlet extends HttpServlet {
    private OrderService orderService = new OrderServiceImpl();
    private List<Order> list = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if ("createOrder".equals(action)) {
            System.out.println("调用生成订单的功能！");
            createOrder(req, resp);
        }else if("showAllOrders".equals(action)){
            System.out.println("查看生成的所有订单");
            showAllOrders(req,resp);
        }

    }
    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //首先将显示的数据全部保存到list集合中

    }

    /**
     * 生成订单功能
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //先获取购物车对象cart
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //获取userId
        User loginUser = (User) req.getSession().getAttribute("user");
        /**
         * 如果没有登录，那么就会包空指针异常错误
         */
        if (loginUser == null) {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
            return;
        }


        Integer userId = loginUser.getId();
        //调用OrderService.createOrder(cart,userId)生成订单
        String orderId = orderService.createOrder(cart, userId);


        req.getSession().setAttribute("orderId", orderId);

        resp.sendRedirect(req.getContextPath() + "/checkout.jsp");
    }
}
