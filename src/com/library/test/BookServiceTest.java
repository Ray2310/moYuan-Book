package com.library.test;

import com.library.pojo.Book;
import com.library.pojo.Page;
import com.library.service.BookService;
import com.library.service.impl.BookServiceImpl;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class BookServiceTest {
    private BookService bookService= new BookServiceImpl();
    @Test
    public void testAddBook() {
        bookService.addBook(new Book(null,"Rayce..","Ray",new BigDecimal(9999),10000,10,null));
    }

    @Test
    public void testDeleteBookById() {
        bookService.deleteBookById(22);
    }

    @Test
    public void testUpdateBook() {
        bookService.updateBook(new Book(22,"Rayce....","Ray",new BigDecimal(999),10000,9,null));

    }

    @Test
    public void testQueryBookById() {
        System.out.println(bookService.queryBookById(22));

    }

    @Test
    public void testQueryBooks() {
        for (Book queryBook:
                bookService.queryBooks()) {
            System.out.println(queryBook);
        }
    }

    @Test
    public void page(){
        System.out.println(bookService.page(1, Page.PAGE_SIZE));
    }
}