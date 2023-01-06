package com.library.web;

import com.library.pojo.Book;
import com.library.pojo.Page;
import com.library.service.BookService;
import com.library.service.impl.BookServiceImpl;
import com.library.utils.webUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;

public class BookServlet extends HttpServlet {

    private BookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        /**
         * 通过反射调用方法
         */
        try {
            //获取当前类的对象,然后通过反射调用他的方法
            Method method =this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
            //打开访问权限
            method.setAccessible(true);
            //通过Method对象调用目标方法
            method.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        if ("add".equals(action)) {
//            System.out.println("处理增加的需求");
//            add(req, resp);
//        } else if ("delete".equals(action)) {
//            System.out.println("处理删除的需求");
//            delete(req, resp);
//        }else if ("update".equals(action)) {
//            System.out.println("处理修改的需求");
//            update(req, resp);
//        }else if ("list".equals(action)) {
//            System.out.println("处理列表展示的需求");
//            list(req, resp);
//        }else if ("getBook".equals(action)) {
//            System.out.println("处理获取修改图书的需求");
//            getBook(req, resp);
//        }else if ("page".equals(action)) {
//            System.out.println("处理列表分页的需求");
//            page(req, resp);
//        }
    }

    /**
     *  先进入index，然后进入后台管理---->图书管理(manager.jsp)---->展示所有图书的信息(需要获取所有图书信息web(BookServlet)->Service->Dao->数据库)
     *
     * 在bookServlet中，list展示所有的信息 【1. 查询所有的信息 2. 保存到Request作用域中 3. 请求转发到manager.jsp中】
     *
     */



    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 获取请求参数，封装成Book对象

        String name = req.getParameter("name");
        String author = req.getParameter("author");
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(req.getParameter("price")));
        Integer sales = Integer.valueOf(req.getParameter("sales"));
        Integer stock = Integer.valueOf(req.getParameter("stock"));

        Book book = new Book(null, name, author, price, sales, stock, null);
        //2. 调用BookService.addBook()保存图书
        bookService.addBook(book);
        //3. 跳转回图书列表页面----又出现表单重复提交的bug
        resp.sendRedirect(req.getContextPath() + "/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            //1. 获取请求参数 id
        Integer id = Integer.valueOf(req.getParameter("id"));
        //2. 调用BookService.deleteBookById()删除数据
        bookService.deleteBookById(id);
        //3. 重定向跳转回原来的页面

        resp.sendRedirect(req.getContextPath() + "/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));

    }
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 获取请求参数，封装成Book对象
        Integer id= Integer.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");
        String author = req.getParameter("author");
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(req.getParameter("price")));
        Integer sales = Integer.valueOf(req.getParameter("sales"));
        Integer stock = Integer.valueOf(req.getParameter("stock"));

        Book book = new Book(id, name, author, price, sales, stock, null);


        bookService.updateBook(book);

        //请求重定向
        resp.sendRedirect(req.getContextPath() + "/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));

    }
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 获取请求的参数信息--图书编号
        Integer id = Integer.valueOf(req.getParameter("id"));

        //2. 调用BookService下的queryBookById()获取该图书的所有信息

        Book book = bookService.queryBookById(id);
        //3. 把数据保存到Request作用域中
        req.setAttribute("book",book);
        //4. 请求转发到book_edit页面
        req.getRequestDispatcher("book_edit.jsp").forward(req,resp);
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1. 通过BookService查询所有的图书
        List<Book> books = bookService.queryBooks();

        //2. 保存所有的图书到request域中

        req.setAttribute("books",books);

        //3. 请求转发到book_manager.jsp
        req.getRequestDispatcher("book_manager.jsp").forward(req,resp);
    }
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 获取请求参数pageNo,pageSize
        //Integer pageNo= Integer.valueOf(req.getParameter("pageNo"));
        int pageNo = webUtils.parseInt(req.getParameter("pageNo"), 1);

        int pageSize = webUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2. 调用BookService.page(pageNo,pageSize)
        Page<Book> page = bookService.page(pageNo, pageSize);
        //如果以后想要修改后台的分页的地址的化,只需要在这里修改即可
        page.setUrl("bookServlet?action=page");
        //3.  保存到request域中
        req.setAttribute("page",page);
        //4. 请求转发book_manager页面
        req.getRequestDispatcher("book_manager.jsp").forward(req,resp);
    }
}
