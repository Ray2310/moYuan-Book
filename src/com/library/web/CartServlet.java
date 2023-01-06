package com.library.web;

import com.google.gson.Gson;
import com.library.pojo.Book;
import com.library.pojo.Cart;
import com.library.pojo.CartItem;
import com.library.service.BookService;
import com.library.service.impl.BookServiceImpl;
import com.library.utils.webUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//购物车的Servlet
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if ("addItem".equals(action)) {
            System.out.println("加入购物车功能！");
            addItem(req, resp);
        } else if ("deleteItem".equals(action)) {
            System.out.println("删除购物车数据功能");
            deleteItem(req, resp);
        } else if ("clear".equals(action)) {
            System.out.println("清空购物车数据功能");
            clear(req, resp);
        } else if ("updateCount".equals(action)) {
            System.out.println("修改数据功能");
            updateCount(req, resp);
        }else if ("ajaxAddItem".equals(action)) {
            System.out.println("修改数据功能");
            ajaxAddItem(req, resp);
        }

    }

    /**
     * 加入购物车
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 获取请求参数---商品的编号
//        String id = req.getParameter("bookId");
        int id = Integer.parseInt(req.getParameter("id"));

        //2. 通过调用bookService.queryBookById(): Book得到图书的信息
        BookService bookService = new BookServiceImpl();
        Book book = bookService.queryBookById(id);
        //3. 把图书的信息转换成为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice());

        //4. 调用Cart.addItem() 添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        req.getSession().setAttribute("lastName", cartItem.getName());
        //5. 重定向回到商品列表页面 ， 从哪里来回哪里去
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 删除商品项
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取商品编号
        int id = webUtils.parseInt(req.getParameter("id"), 0);
        //找到购物车中的session对象，然后删除
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.deleteItem(id);
        }
        //从哪里来回哪里去
        resp.sendRedirect(req.getHeader("Referer"));

    }

    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.clear();
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }

    //修改商品数量
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取购物车对象
        int id = webUtils.parseInt(req.getParameter("id"), 0);
        int count = webUtils.parseInt(req.getParameter("count"), 1);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.UpdateCount(id, count);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("使用ajax来添加图书");
        //1. 获取请求参数---商品的编号
//        String id = req.getParameter("bookId");
        int id = Integer.parseInt(req.getParameter("id"));

        //2. 通过调用bookService.queryBookById(): Book得到图书的信息
        BookService bookService = new BookServiceImpl();
        Book book = bookService.queryBookById(id);
        //3. 把图书的信息转换成为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice());

        //4. 调用Cart.addItem() 添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        req.getSession().setAttribute("lastName", cartItem.getName());

        /**
         * 使用ajax来发送添加购物车的请求
         */
        //返回购物车总商品数量和最后一个添加商品名
        Map<String , Object> resultMap = new HashMap<>();
        resultMap.put("totalCount",cart.getTotalCount());
        resultMap.put("lastName",cartItem.getName());

        Gson json = new Gson();
        String s = json.toJson(resultMap);
        resp.getWriter().write(s);
    }

}
