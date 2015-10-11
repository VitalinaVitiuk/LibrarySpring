package com.vitalina.library.repository;

import com.vitalina.library.domain.Book;
import com.vitalina.library.service.Pagination;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository("bookRepository")
public class JPABookRepository implements BookRepository {

    @PersistenceContext(name = "persistenceUnit")
    private EntityManager em;

    @Override
    public List<Book> getAllBooks() {
        return em.createNamedQuery(Book.FIND_ALL_BOOKS, Book.class)
                .getResultList();
    }

    @Override
    public List<Book> getBookByAuthor(String author, Pagination pagination) {
        return em.createNamedQuery(Book.GET_BOOK_BY_AUTHOR, Book.class)
                .setParameter("author", author)
                .setFirstResult(pagination.getCurrentPage() * pagination.getPageSize())
                .setMaxResults(pagination.getPageSize())
                .getResultList();
    }
    @Override
    public List<Book> getBookByTitle(String title, Pagination pagination)  {
        return em.createNamedQuery(Book.GET_BOOK_BY_TITLE, Book.class)
                .setParameter("title", title)
                .setFirstResult(pagination.getCurrentPage() * pagination.getPageSize())
                .setMaxResults(pagination.getPageSize())
                .getResultList();
    }

    @Override
    public List<Book> getBookByYear(int year, Pagination pagination) {
        return em.createNamedQuery(Book.GET_BOOK_BY_YEAR, Book.class)
                .setParameter("year", year)
                .setFirstResult(pagination.getCurrentPage() * pagination.getPageSize())
                .setMaxResults(pagination.getPageSize())
                .getResultList();
    }

    @Override
    public List<Book> getBookByLanguage(String language,Pagination pagination) {
        return em.createNamedQuery(Book.GET_BOOK_BY_LANGUAGE, Book.class)
                .setParameter("language", language)
                .setFirstResult(pagination.getCurrentPage() * pagination.getPageSize())
                .setMaxResults(pagination.getPageSize())
                .getResultList();
    }

    @Override
    public Long getBookCountByAuthor(String author) {
        return em.createNamedQuery(Book.COUNT_BOOK_BY_AUTHOR, Long.class)
                .setParameter("author", author)
                .getSingleResult();
    }

    @Override
    public Long getBookCountByTitle(String title) {
        return em.createNamedQuery(Book.COUNT_BOOK_BY_TITLE, Long.class)
                .setParameter("title", title)
                .getSingleResult();
    }

    @Override
    public Long getBookCountByYear(int year) {
        return em.createNamedQuery(Book.COUNT_BOOK_BY_YEAR, Long.class)
                .setParameter("year", year)
                .getSingleResult();
    }

    @Override
    public Long getBookCountByLanguage(String language) {
        return em.createNamedQuery(Book.COUNT_BOOK_BY_LANGUAGE, Long.class)
                .setParameter("language", language)
                .getSingleResult();
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == null) {
            em.persist(book);
        } else {
            em.merge(book);
        }
        return book;
    }

    @Override
    public Book getBookById(Long id) {
        Book book = em.find(Book.class, id);
        return book;
    }

}