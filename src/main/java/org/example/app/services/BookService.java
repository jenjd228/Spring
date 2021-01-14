package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.app.repository.BookRepository;
import org.example.web.dto.Book;
import org.example.web.dto.RegexForm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepo;

    private Logger logger = Logger.getLogger(BookService.class);

    public BookService(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllBooks() {
        return bookRepo.retreiveAll();
    }

    public void saveBook(Book book) {
        if (!book.getAuthor().isEmpty()  || !book.getTitle().isEmpty() || book.getSize() != null){
            bookRepo.store(book);
        }
    }

    public List<Book> getBooksByRegex(String regex){
        List<Book> list = new ArrayList<>();
        logger.info("filter " + regex);

        bookRepo.retreiveAll().forEach(book -> {
            if (book.getAuthor().matches(regex) || book.getTitle().matches(regex) || book.getSize().toString().matches(regex)) {
                list.add(book);
            }
        });

        return list;
    }

    public void removeBookById(Integer bookIdToRemove) {
        bookRepo.removeItemById(bookIdToRemove);
    }

    public void removeBookByAuthor(String bookAuthorToRemove){
        bookRepo.removeItemByAuthor(bookAuthorToRemove);
    }

    public void removeBookByTitle(String bookTitleToRemove){
       bookRepo.removeItemByTitle(bookTitleToRemove);
    }

    public void removeBookBySize(Integer bookSizeToRemove){
        bookRepo.removeItemBySize(bookSizeToRemove);
    }


}
