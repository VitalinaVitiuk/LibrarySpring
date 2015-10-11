package com.vitalina.library.service;

import com.vitalina.library.domain.*;
import com.vitalina.library.exception.BookLimitException;
import com.vitalina.library.exception.BookAccessExeption;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.vitalina.library.repository.BookRepository;

import java.util.List;

@Service
public class SimpleBookService implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserService userService;

    @Autowired
    IssuanceService issuanceService;

    Logger logger = Logger.getLogger(BookService.class);


    @Override
    @Transactional
    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    @Override
    @Transactional
    public List<Book> getBookByAuthor(String author, Pagination pagination) {
        logger.info("getBookByAuthor" + author +"  " + pagination);

        long bookCount = bookRepository.getBookCountByAuthor(author);
        logger.info("getBookByAuthor" + bookCount);

        int pageCount = (int)Math.ceil((double) bookCount / pagination.getPageSize());
        pagination.setPageCount(pageCount);

        Integer page = pagination.getCurrentPage() > pageCount ? pageCount : pagination.getCurrentPage();
        pagination.setCurrentPage(page);
        logger.info("getBookByAuthor" + author + "  " + pagination);

        return bookRepository.getBookByAuthor(author, pagination);
    }

    @Override
    @Transactional
    public List<Book> getBookByTitle(String title, Pagination pagination) {

        long bookCount = bookRepository.getBookCountByTitle(title);

        int pageCount = (int)Math.ceil((double) bookCount / pagination.getPageSize());
        pagination.setPageCount(pageCount);

        Integer page = pagination.getCurrentPage() > pageCount ? pageCount : pagination.getCurrentPage();
        pagination.setCurrentPage(page);

        return bookRepository.getBookByTitle(title, pagination);
    }

    @Override
    @Transactional
    public List<Book> getBookByYear(int year, Pagination pagination) {
        long bookCount = bookRepository.getBookCountByYear(year);

        int pageCount = (int)Math.ceil((double) bookCount / pagination.getPageSize());
        pagination.setPageCount(pageCount);

        Integer page = pagination.getCurrentPage() > pageCount ? pageCount : pagination.getCurrentPage();
        pagination.setCurrentPage(page);

        return bookRepository.getBookByYear(year, pagination);
    }

    @Override
    public List<Book> getBookByLanguage(String language, Pagination pagination) {
        long bookCount = bookRepository.getBookCountByLanguage(language);

        int pageCount = (int)Math.ceil((double) bookCount / pagination.getPageSize());
        pagination.setPageCount(pageCount);

        Integer page = pagination.getCurrentPage() > pageCount ? pageCount : pagination.getCurrentPage();
        pagination.setCurrentPage(page);

        return bookRepository.getBookByLanguage(language,pagination);
    }


    @Transactional
    @Override
    public Book save(Book book) {
        if (book.getId() == null) {
            book.setStatus(BookStatus.AVAILABLE);
        }
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book getBookById(Long id) {
        return bookRepository.getBookById(id);
    }

    @Override
    @Transactional
    public void orderBook(Long id) throws BookAccessExeption, BookLimitException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userService.getUserByUserName(name);
        List<Issuance> activeIssuances = user.getIssuances();
        int counter = activeIssuances.size();
        for (Issuance issuance : activeIssuances) {
            if (issuance.getStatus() == IssuanceStatus.CLOSED) {
                counter = counter - 1;
            }
        }
        if (counter <= 4) {
            Book book = bookRepository.getBookById(id);
            if (book.getStatus() == BookStatus.AVAILABLE) {
                book.setStatus(BookStatus.ORDERED);
            } else {
                throw new BookAccessExeption();
            }
            Issuance issuance = new Issuance();
            issuance.setUser(user);
            issuance.setBook(getBookById(id));
            issuance.setIssuanceType(getBookById(id).getIssuanceType());
            issuance.setStatus(IssuanceStatus.PROCESSING);

            issuanceService.save(issuance);
        } else {
            throw new BookLimitException();
        }
    }

    @Override
    @Transactional
    public void disableBook(Long id){
        Book book = bookRepository.getBookById(id);
        book.setStatus(BookStatus.NOT_AVAILABLE);
    }

    @Override
    @Transactional
    public void enableBook(Long id){
        Book book = bookRepository.getBookById(id);
        book.setStatus(BookStatus.AVAILABLE);
    }

}
