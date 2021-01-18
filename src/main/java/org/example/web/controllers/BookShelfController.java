package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.example.app.services.BookService;
import org.example.web.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/books")
public class BookShelfController {

    private Logger logger = Logger.getLogger(BookShelfController.class);

    private final BookService bookService;

    private final HashMap<String,Object> allModelFieldsAttributeBookShelf;

    private final HashMap<String,Object> allModelFieldsAttributeFilter;

    public BookShelfController(HashMap<String,Object> allModelFieldsAttributeFilter,HashMap<String,Object> allModelFieldsAttributeBookShelf,BookService bookService) {
        this.allModelFieldsAttributeFilter = allModelFieldsAttributeFilter;
        this.allModelFieldsAttributeBookShelf = allModelFieldsAttributeBookShelf;
        this.bookService = bookService;
    }

    @GetMapping("/shelf")
    public String books(Model model) {
        logger.info("got book shelf");
        model.addAllAttributes(allModelFieldsAttributeBookShelf);
        model.addAttribute("bookList", bookService.getAllBooks());
        return "book_shelf";
    }

    @PostMapping("/save")
    public String saveBook(@Valid Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAllAttributes(getModelAttributesWithoutThis("book"));
            model.addAttribute("bookList", bookService.getAllBooks());
            return "book_shelf";
        }
        bookService.saveBook(book);
        logger.info("current repository size: " + bookService.getAllBooks().size());
        return "redirect:/books/shelf";
    }

    @PostMapping("/remove")
    public String removeBook(@Valid BookIdToRemoveDTO bookIdToRemoveDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAllAttributes(getModelAttributesWithoutThis("bookIdToRemoveDTO"));
            model.addAttribute("bookList", bookService.getAllBooks());
            return "book_shelf";
        }
        bookService.removeBookById(bookIdToRemoveDTO.getBookIdToRemove());
        return "redirect:/books/shelf";
    }

    @PostMapping("/removeByAuthor")
    public String removeBookByAuthor(@Valid BookAuthorToRemoveDTO bookAuthorToRemoveDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAllAttributes(getModelAttributesWithoutThis("bookAuthorToRemoveDTO"));
            model.addAttribute("bookList", bookService.getAllBooks());
            return "book_shelf";
        }
        bookService.removeBookByAuthor(bookAuthorToRemoveDTO.getBookAuthorToRemove());
        return "redirect:/books/shelf";
    }

    @PostMapping("/removeByTitle")
    public String removeBookByTitle(@Valid BookTitleToRemoveDTO bookTitleToRemoveDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAllAttributes(getModelAttributesWithoutThis("bookTitleToRemoveDTO"));
            model.addAttribute("bookList", bookService.getAllBooks());
            return "book_shelf";
        }
        bookService.removeBookByTitle(bookTitleToRemoveDTO.getBookTitleToRemove());
        return "redirect:/books/shelf";
    }

    @PostMapping("/removeBySize")
    public String removeBookBySize(@Valid BookSizeToRemoveDTO bookSizeToRemoveDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAllAttributes(getModelAttributesWithoutThis("bookSizeToRemoveDTO"));
            model.addAttribute("bookList", bookService.getAllBooks());
            return "book_shelf";
        }
        bookService.removeBookBySize(bookSizeToRemoveDTO.getBookSizeToRemove());
        return "redirect:/books/shelf";
    }

    @PostMapping(value = "/upload",consumes = "multipart/form-data")
    public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        logger.info("/upload "+file);
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("fileMessage","Must be not null");
        }
        return "redirect:/books/shelf";
    }

    //System.getProperty("catalina.home");
    private HashMap<String,Object> getModelAttributesWithoutThis(String nameAttribute){
        HashMap<String,Object> map = new HashMap<>(allModelFieldsAttributeBookShelf);
        map.remove(nameAttribute);
        return map;
    }

    @GetMapping("/filter")
    public String defaultPage(Model model) {
        logger.info("GET /filter returns filter_page.html");
        model.addAllAttributes(allModelFieldsAttributeFilter);
        return "filter_page";
    }

    @GetMapping("/filter/getBooks")
    public String filter(Model model, @Valid FilterDTO filterDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "filter_page";
        }
        List<Book> books = bookService.getBooksByRegex(filterDTO.getRegex());
        logger.info("GET /filter/getBooks returns filter_page.html" + books.toString());
        model.addAllAttributes(allModelFieldsAttributeFilter);
        model.addAttribute("bookList", books);
        return "filter_page";
    }

}
