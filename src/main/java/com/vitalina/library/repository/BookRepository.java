package com.vitalina.library.repository;

import com.vitalina.library.domain.Book;
import com.vitalina.library.service.Pagination;

import java.util.List;

public interface BookRepository {
    List<Book> getAllBooks();
    List<Book> getBookByAuthor(String author, Pagination pagination);
    List<Book> getBookByTitle(String title, Pagination pagination);
    List<Book> getBookByYear(int year, Pagination pagination);
    List<Book> getBookByLanguage(String language, Pagination pagination);

    Long getBookCountByAuthor(String author);
    Long getBookCountByTitle(String title);
    Long getBookCountByYear(int year);
    Long getBookCountByLanguage(String language);

    Book save(Book book);
    Book getBookById(Long id);
}
