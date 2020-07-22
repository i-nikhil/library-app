package com.example.Library.App.Controller;

import com.example.Library.App.DataAccessLayer.Book;
import com.example.Library.App.DataAccessLayer.BookRepository;
import com.example.Library.App.DataAccessLayer.IssuedBooksRepository;
import com.example.Library.App.DataAccessLayer.UserRepository;
import com.example.Library.App.Exception.BookNotFoundException;
import com.example.Library.App.Exception.BookTitleMissingException;
import com.example.Library.App.Util.BookValidator;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class BookResource {

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Autowired
    BookRepository repository;

    BookValidator validator = new BookValidator();

    // Find All books
    @ApiOperation(value = "To see list of all books")
    @GetMapping("/books")
    List<Book> findAll() {
        LOGGER.info("findAll called");
        List<Book> list = new ArrayList<Book>();
        //list = repository.findAll();
        try {
            list = repository.findAll();
            if (list.size() == 0) {
                LOGGER.severe("No book found");
                throw new BookNotFoundException();
            }
        }
        catch(BookNotFoundException exc)
        {
            LOGGER.severe(exc.toString());
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No Book Found", exc);
        }
        return list;
    }

    @ApiOperation(value = "To add a new book")
    @PostMapping("/books")
    //return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED)
    Book newBook(@RequestBody Book newBook) {

        if (validator.isValid(newBook))
            return repository.save(newBook);
        else {
            LOGGER.severe("newBook is not valid");
            throw new BookTitleMissingException();
        }
    }
    //http://localhost:8080/searchBooksByAuthor?q=Kim
    @ApiOperation(value = "To search a book by its Author")
    @GetMapping(value = "/searchBooksByAuthor")
    public List<Book> searchBooksByAuthor(@RequestParam(value = "q") String author){
        List<Book> books = repository.findAll();
        ArrayList<Book> list = new ArrayList<Book>();
        for(Book book : books){
            if(book.getAuthor().equals(author)) list.add(book);
        }
        return list;
    }
    //http://localhost:8080/searchBooksBySubject?q=Maths
    @ApiOperation(value = "To search a book by its Subject")
    @GetMapping(value = "/searchBooksBySubject")
    public List<Book> searchBooksBySubject(@RequestParam(value = "q") String subject){
        List<Book> books = repository.findAll();
        ArrayList<Book> list = new ArrayList<Book>();
        for(Book book : books){
            if(book.getSubject().equals(subject)) list.add(book);
        }
        return list;
    }
}
