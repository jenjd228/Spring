package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.example.app.services.BookService;
import org.example.web.MyPatterns.OnlyLetters;
import org.example.web.dto.Book;
import org.example.web.dto.RegexForm;
import org.hibernate.validator.constraints.pl.NIP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Controller
@RequestMapping(value = "/books")
public class BookShelfController {

    private Logger logger = Logger.getLogger(BookShelfController.class);
    private BookService bookService;

    @Autowired
    public BookShelfController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/shelf")
    public String books(Model model) {
        logger.info("got book shelf");
        model.addAttribute("book", new Book());
        model.addAttribute("bookList", bookService.getAllBooks());
        return "book_shelf";
    }

    @PostMapping("/save")
    public String saveBook(@Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "book_shelf";
        }
        bookService.saveBook(book);
        logger.info("current repository size: " + bookService.getAllBooks().size());
        return "redirect:/books/shelf";
    }

    @PostMapping("/remove")
    public String removeBook(@RequestParam(value = "bookIdToRemove") @Valid @NotNull(message = "Must be not null") @Size(min = 1,max = 32) Integer bookIdToRemove) {
            bookService.removeBookById(bookIdToRemove);
            return "redirect:/books/shelf";
    }

    @PostMapping("/removeByAuthor")
    public String removeBookByAuthor(@RequestParam(value = "bookAuthorToRemove") String bookAuthorToRemove) {
        bookService.removeBookByAuthor(bookAuthorToRemove);
        return "redirect:/books/shelf";
    }

    @PostMapping("/removeByTitle")
    public String removeBookByTitle(@RequestParam(value = "bookTitleToRemove") String bookTitleToRemove) {
        bookService.removeBookByTitle(bookTitleToRemove);
        return "redirect:/books/shelf";
    }

    @PostMapping("/removeBySize")
    public String removeBookBySize(@RequestParam(value = "bookSizeToRemove") Integer bookSizeToRemove) {
        bookService.removeBookBySize(bookSizeToRemove);
        return "redirect:/books/shelf";
    }

    @GetMapping("/filter")
    public String defaultPage(Model model) {
        logger.info("GET /filter returns filter_page.html");
        //model.addAttribute("bookList",bookService.getAllBooks() );
        return "filter_page";
    }

    @GetMapping("/filter/getBooks")
    public String filter(Model model,@RequestParam(value = "filter") String filter) {
        List<Book> books = bookService.getBooksByRegex(filter);
        logger.info("GET /filter/getBooks returns filter_page.html" + books.toString());
        model.addAttribute("bookList", books);
        return "filter_page";
    }

}
