package com.library.test;

import com.library.dao.BookDao;
import com.library.dao.impl.BookDaoImpl;
import com.library.pojo.Book;
import com.library.pojo.Page;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class BookDaoTest {

    private BookDao bookDao = new BookDaoImpl();
    @Test
    public void testAddBook() {
        bookDao.addBook(new Book(null,"假如给我三天光明","Ray",new BigDecimal(9999),10000,10,null));

    }

    @Test
    public void testDeleteBookById() {
        bookDao.deleteBookById(21);
    }

    @Test
    public void testUpdateBook() {
        bookDao.updateBook(new Book(21,"Rayce...","Ray",new BigDecimal(999),10000,9,null));
    }

    @Test
    public void testQueryBookById() {
        System.out.println(bookDao.queryBookById(21));
    }

    @Test
    public void testQueryBooks() {
        for (Book queryBook:
                bookDao.queryBooks()) {
            System.out.println(queryBook);
        }
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount() );
    }
    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(bookDao.queryForPageTotalCountByPrice(10,50) );
    }
   @Test
    public void queryForPageItems() {
        for ( Book book : bookDao.queryForPageItems(8, Page.PAGE_SIZE)){
            System.out.println(book);
       }
    }
    @Test
    public void queryForPageItemsByPrice() {
        for ( Book book : bookDao.queryForPageItemsByPrice(0, Page.PAGE_SIZE,10,50)){
            System.out.println(book);
       }
    }
}