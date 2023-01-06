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

public class ClientBookServlet extends HttpServlet {
    private BookService bookService = new BookServiceImpl();

    public ClientBookServlet() {
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if ("page".equals(action)) {
            System.out.println("通过调用从而显示前台的数据！");
            this.page(req, resp);
        }else if("pageByPrice".equals(action)){
            System.out.println("调用显示查询价格区间的数据");
            this.pageByPrice(req,resp);
        }

    }

    public void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = webUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = webUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Book> page = this.bookService.page(pageNo, pageSize);
        page.setUrl("clientBookServlet?action=page");
        //3.  保存到request域中
        req.setAttribute("page",page);
        //4. 请求转发book_manager页面
        req.getRequestDispatcher("pre_Index.jsp").forward(req,resp);
    }
    public void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 获取请求参数pageNo,pageSize
        //Integer pageNo= Integer.valueOf(req.getParameter("pageNo"));
        int pageNo = webUtils.parseInt(req.getParameter("pageNo"), 1);

        int pageSize = webUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        int min = webUtils.parseInt(req.getParameter("min"),0);
        int max = webUtils.parseInt(req.getParameter("max"),Integer.MAX_VALUE);
        //2. 调用BookService.page(pageNo,pageSize)
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize,min,max);
        //如果以后想要修改前台的分页的地址的化,只需要在这里修改即可

        StringBuilder sb = new StringBuilder("clientBookServlet?action=pageByPrice");
        //如果有最小参数，那么就追加到分页条地址参数中
        if(req.getParameter("min")!=null){
            sb.append("&min=").append(req.getParameter("min"));
        }
    //如果有最大参数，那么就追加到分页条地址参数中
        if(req.getParameter("max")!=null){
            sb.append("&max=").append(req.getParameter("max"));
        }


        page.setUrl(sb.toString());
        //3.  保存到request域中
        req.setAttribute("page",page);
        //4. 请求转发book_manager页面
        req.getRequestDispatcher("pre_Index.jsp").forward(req,resp);
    }
}
