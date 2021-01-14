package org.example.app.repository;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("BookRepository")
public class BookRepository implements ProjectRepository<Book> {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    private final List<Book> repo = new ArrayList<>();

    @Override
    public List<Book> retreiveAll() {
        return new ArrayList<>(repo);
    }

    @Override
    public void store(Book book) {
        book.setId(book.hashCode());
        logger.info("store new book: " + book);
        repo.add(book);
    }

    @Override
    public boolean removeItemById(Integer bookIdToRemove) {
        for (Book book : retreiveAll()) {
            if (book.getId().equals(bookIdToRemove)) {
                logger.info("remove book by id completed: " + book);
                return repo.remove(book);
            }
        }
        return false;
    }

    public void removeItemByAuthor(String bookAuthorToRemove){
        for (Book book : retreiveAll()) {
            if (book.getAuthor().equals(bookAuthorToRemove)) {
                repo.remove(book);
                logger.info("remove book by author completed: " + book);
            }
        }
    }

    public void removeItemByTitle(String bookTitleToRemove){
        for (Book book : retreiveAll()) {
            if (book.getTitle().equals(bookTitleToRemove)) {
                repo.remove(book);
                logger.info("remove book by title completed: " + book);
            }
        }
    }

    public void removeItemBySize(Integer bookSizeToRemove){
        for (Book book : retreiveAll()) {
            if (book.getSize().equals(bookSizeToRemove)) {
                repo.remove(book);
                logger.info("remove book by title completed: " + book);
            }
        }
    }
}
