package com.itheima.service;

import com.itheima.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> findNewBook(Book book);
    List<Book> find(Book book);
    Integer updateBook(Book book);
    Integer addBook(Book book);
}
