package com.itheima.service.impl;

import com.itheima.domain.Book;
import com.itheima.mapper.BookMapper;
import com.itheima.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;



    @Override
    public List<Book> findNewBook(Book book) {
        return bookMapper.selectNewBooks();
    }

    @Override
    public List<Book> find(Book book) {
        String sql = "select * from book where 1 = 1";
        if (!StringUtils.isEmpty(book.getBook_author()))
            sql = sql + " and book_author like concat('%', '" + book.getBook_author() + "','%')";
        if (!StringUtils.isEmpty(book.getBook_borrower()))
            sql = sql + " and book_borrower = " + book.getBook_borrower();
        if (!StringUtils.isEmpty(book.getBook_id()))
            sql = sql + " and book_id  = " + book.getBook_id();


        List<Book> books = jdbcTemplate.query(sql ,new BeanPropertyRowMapper<Book>(Book.class));
        return books;
    }

    @Override
    public Integer updateBook(Book book) {
        Integer i = bookMapper.updateBook(book);
        return i;
    }

    @Override
    public Integer addBook(Book book) {
        Integer i = bookMapper.insertBook(book);
        return i;
    }


}
