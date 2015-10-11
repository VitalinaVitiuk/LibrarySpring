package com.vitalina.library.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
        @NamedQuery(name = Book.FIND_ALL_BOOKS, query = "SELECT book FROM Book book"),
        @NamedQuery(name = Book.GET_BOOK_BY_AUTHOR, query = "SELECT book FROM Book book WHERE book.author =:author"),
        @NamedQuery(name = Book.GET_BOOK_BY_TITLE, query = "SELECT book FROM Book book WHERE book.title =:title"),
        @NamedQuery(name = Book.GET_BOOK_BY_YEAR, query = "SELECT book FROM Book book WHERE book.year =:year"),
        @NamedQuery(name = Book.GET_BOOK_BY_LANGUAGE, query = "SELECT book FROM Book book WHERE book.language =:language"),
        @NamedQuery(name = Book.COUNT_BOOK_BY_AUTHOR, query = "SELECT COUNT(book) FROM Book book WHERE book.author =:author"),
        @NamedQuery(name = Book.COUNT_BOOK_BY_TITLE, query = "SELECT COUNT(book) FROM Book book WHERE book.title =:title"),
        @NamedQuery(name = Book.COUNT_BOOK_BY_YEAR, query = "SELECT COUNT(book) FROM Book book WHERE book.year =:year"),
        @NamedQuery(name = Book.COUNT_BOOK_BY_LANGUAGE, query = "SELECT COUNT(book) FROM Book book WHERE book.language =:language")

})

@Table(name = "book")
public class Book {
    public static final String FIND_ALL_BOOKS = "FIND_ALL_BOOKS";
    public static final String GET_BOOK_BY_AUTHOR = "GET_BOOK_BY_AUTHOR";
    public static final String GET_BOOK_BY_TITLE = "GET_BOOK_BY_TITLE";
    public static final String GET_BOOK_BY_YEAR = "GET_BOOK_BY_YEAR";
    public static final String GET_BOOK_BY_LANGUAGE = "GET_BOOK_BY_LANGUAGE";
    public static final String COUNT_BOOK_BY_AUTHOR = "COUNT_BOOK_BY_AUTHOR";
    public static final String COUNT_BOOK_BY_TITLE = "COUNT_BOOK_BY_TITLE";
    public static final String COUNT_BOOK_BY_YEAR = "COUNT_BOOK_BY_YEAR";
    public static final String COUNT_BOOK_BY_LANGUAGE = "COUNT_BOOK_BY_LANGUAGE";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{book.error.null}")
    private String author;

    @NotBlank(message = "{book.error.null}")
    private String title;

    @NotNull(message = "{book.error.null}")
    private Integer year;

    @NotBlank(message = "{book.error.null}")
    private String language;

    @NotNull(message = "{book.error.null}")
    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_issuance")
    private IssuanceType issuanceType;

    @Enumerated(EnumType.STRING)
    private BookStatus status;

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDateOfPublication() {
        return year;
    }

    public void setDateOfPublication(Integer dateOfPublication) {
        this.year = dateOfPublication;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public IssuanceType getIssuanceType() {
        return issuanceType;
    }

    public void setIssuanceType(IssuanceType issuanceType) {
        this.issuanceType = issuanceType;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

}

