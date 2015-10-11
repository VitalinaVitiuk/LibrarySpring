package com.vitalina.library.web;

import com.vitalina.library.domain.*;
import com.vitalina.library.exception.BookAccessExeption;
import com.vitalina.library.exception.BookLimitException;
import com.vitalina.library.service.Pagination;
import com.vitalina.library.service.config.Config;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.vitalina.library.service.BookService;
import com.vitalina.library.service.IssuanceService;
import com.vitalina.library.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    UserService userService;

    @Autowired
    IssuanceService issuanceService;

    Logger logger = Logger.getLogger(BookController.class);

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "add_new_book";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addNewBook(@ModelAttribute @Valid Book book, BindingResult result) {
        logger.info("Adding new book: " + book.toString());
        if (result.hasErrors()) {
            return "add_new_book";
        }

        bookService.save(book);
        return "redirect:/issuances/admin";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit() {
        return "search";
    }


    @RequestMapping(value = "/editbook", method = RequestMethod.GET)
    public String editBook(@RequestParam("bookId") Long id, Model model) {
        Book book = bookService.getBookById(id);
        logger.info("Edited book: " + book);
        model.addAttribute("book", book);
        return "add_new_book";
    }


    @RequestMapping(value = "/showAll", method = RequestMethod.GET)
    public String viewAllBooks(Model model) {
        model.addAttribute("books",
                bookService.getAllBooks());
        System.out.println("books");
        return "books";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String showSearchPage() {
        return "search";
    }

    @RequestMapping(value = "/searchByAuthor", method = RequestMethod.GET)
    public String searchByAuthor(Model model, @RequestParam("author") String author,
                                 @RequestParam(value = "page", required = false) Integer page) {
        logger.info("searchByAuthor " + author + "page " + page);

        Pagination pagination = new Pagination(Config.PAGE_SIZE, page);

        List<Book> books = bookService.getBookByAuthor(author, pagination);

        logger.info("searchByAuthor " + author + " pagination " + pagination + " books " + books);

        model.addAttribute("author", author);
        model.addAttribute("books", books);
        model.addAttribute("pageCount", pagination.getPageCount());
        model.addAttribute("currentPage", pagination.getCurrentPage());

        return "books";
    }

    @RequestMapping(value = "/searchByTitle", method = RequestMethod.GET)
    public String searchByTitle(Model model, @RequestParam("title") String title,
                                @RequestParam(value = "page", required = false) Integer page) {
        logger.info("searchByTitle " + title + "page " + page);

        Pagination pagination = new Pagination(Config.PAGE_SIZE, page);

        List<Book> books = bookService.getBookByTitle(title, pagination);

        logger.info("searchByTitle " + title + " pagination " + pagination + " books " + books);

        model.addAttribute("title", title);
        model.addAttribute("books", books);
        model.addAttribute("pageCount", pagination.getPageCount());
        model.addAttribute("currentPage", pagination.getCurrentPage());

        return "books";
    }

    @RequestMapping(value = "/searchByLanguage", method = RequestMethod.GET)
    public String searchByLanguage(Model model, @RequestParam("language") String language,
                                   @RequestParam(value = "page", required = false) Integer page) {
        logger.info("searchByLanguage " + language + "page " + page);

        Pagination pagination = new Pagination(Config.PAGE_SIZE, page);

        List<Book> books = bookService.getBookByLanguage(language, pagination);

        logger.info("searchByLanguage " + language + " pagination " + pagination + " books " + books);

        model.addAttribute("language", language);
        model.addAttribute("books", books);
        model.addAttribute("pageCount", pagination.getPageCount());
        model.addAttribute("currentPage", pagination.getCurrentPage());

        return "books";
    }


    @RequestMapping(value = "/searchByYear", method = RequestMethod.GET)
    public String searchByYear(Model model, @RequestParam("year") String year,
                               @RequestParam(value = "page", required = false) Integer page) {
        logger.info("searchByYear " + year + "page " + page);

        Pagination pagination = new Pagination(Config.PAGE_SIZE, page);

        List<Book> books = bookService.getBookByYear(Integer.valueOf(year), pagination);

        logger.info("searchByYear" + year + " pagination " + pagination + " books " + books);

        model.addAttribute("year", year);
        model.addAttribute("books", books);
        model.addAttribute("pageCount", pagination.getPageCount());
        model.addAttribute("currentPage", pagination.getCurrentPage());

        return "books";
    }

    @RequestMapping(value = "/orderbook", method = RequestMethod.POST)
    public String orderBook(@RequestParam("bookId") Long id, Model model) {
        logger.info("Order book" + id);
        try {
            bookService.orderBook(id);
        } catch (BookAccessExeption e) {
            logger.warn(e.getMessage());
            model.addAttribute("errorMessageCode", "error.book.ordered");
            return "error/error";
        } catch (BookLimitException e) {
            logger.warn(e.getMessage());
            model.addAttribute("errorMessageCode", "limit.error");
            return "error/error";
        }
        return "order_success";
    }

    @RequestMapping(value = "/disablebook", method = RequestMethod.POST)
    public String disableBook(@RequestParam("id") Long id) {
        logger.info("disableBook with " + id);
        bookService.disableBook(id);
        return "admin";
    }

    @RequestMapping(value = "/enablebook", method = RequestMethod.POST)
    public String enabelBook(@RequestParam("id") Long id) {
        logger.info("enableBook with " + id);
        bookService.enableBook(id);
        return "admin";
    }


}