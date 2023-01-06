package com.library.service;

import com.library.pojo.Book;
import com.library.pojo.Page;

import java.util.List;

public interface BookService {
    public void addBook(Book book);

    public void deleteBookById(Integer id);

    public void updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    Page<Book> page(Integer pageNo, Integer pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);

}
