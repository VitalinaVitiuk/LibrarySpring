package com.vitalina.library.service;

import com.vitalina.library.domain.Book;
import com.vitalina.library.exception.BookAccessExeption;
import com.vitalina.library.exception.BookLimitException;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    List<Book> getBookByAuthor(String author, Pagination pagination);
    List<Book> getBookByTitle(String title, Pagination pagination);
    List<Book> getBookByYear(int year, Pagination pagination);
    List<Book> getBookByLanguage(String language, Pagination pagination);
    Book save(Book book);
    Book getBookById(Long id);
    void orderBook(Long id) throws BookAccessExeption, BookLimitException;
    void disableBook(Long id);
    void enableBook(Long id);
}
