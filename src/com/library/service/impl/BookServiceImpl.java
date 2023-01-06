package com.library.service.impl;

import com.library.dao.BookDao;
import com.library.dao.impl.BookDaoImpl;
import com.library.pojo.Book;
import com.library.pojo.Page;
import com.library.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(Integer pageNo, Integer pageSize) {
        Page<Book> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        //设置总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);

        //如果页面无法实现整除，那么就将剩余的数据存放到下一页，页面就需要+1
        Integer pageTotal = pageTotalCount/pageSize;
        if (pageTotalCount%pageSize>0){
            pageTotal+=1;
        }

        //设置总页码
        page.setPageTotal(pageTotal);

        //求当前页开始的索引方法
        int begin = (page.getPageNo() - 1)*pageSize;
        //设置当前页数据
        List<Book> items = bookDao.queryForPageItems(begin,pageSize);
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        //设置总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);
        page.setPageTotalCount(pageTotalCount);

        //如果页面无法实现整除，那么就将剩余的数据存放到下一页，页面就需要+1
        Integer pageTotal = pageTotalCount/pageSize;
        if (pageTotalCount%pageSize>0){
            pageTotal+=1;
        }

        //设置总页码
        page.setPageTotal(pageTotal);

        //求当前页开始的索引方法
        int begin = (page.getPageNo() - 1)*pageSize;
        //设置当前页数据
        List<Book> items = bookDao.queryForPageItemsByPrice(begin,pageSize,min,max);
        page.setItems(items);
        return page;
    }
}
