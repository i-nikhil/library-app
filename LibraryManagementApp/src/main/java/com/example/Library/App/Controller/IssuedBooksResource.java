package com.example.Library.App.Controller;

import com.example.Library.App.DataAccessLayer.IssuedBooks;
import com.example.Library.App.DataAccessLayer.IssuedBooksRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class IssuedBooksResource {

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    @Autowired
    IssuedBooksRepository repository;

    @ApiOperation(value = "To see list of all issued books")
    @GetMapping("/issuedBooks")
    List<IssuedBooks> findAll() {
        return repository.findAll();
    }

    @ApiOperation(value = "To issue a new book")
    @PostMapping(value = "/issueBook")
    @ResponseStatus(HttpStatus.CREATED)
    public IssuedBooks issueBook(@RequestBody IssuedBooks issuedBooks) throws Exception{
        try {
            return repository.save(issuedBooks);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }
    //http://localhost:8080/searchIssuedBooksByUser?q=1234
    @ApiOperation(value = "To search an issued book by User")
    @GetMapping(value = "/searchIssuedBooksByUser")
    public List<IssuedBooks> searchIssuedBooksByUser(@RequestParam(value = "q") String userId){
        LOGGER.info("searchIssuedBooksByUser called with userId "+userId);
        List<IssuedBooks> books = repository.findAll();
        ArrayList<IssuedBooks> list = new ArrayList<IssuedBooks>();
        for(IssuedBooks book : books){
            int id = Integer.parseInt(userId);
            if(book.getUser_id()==id){
                list.add(book);
            }
        }
        return list;
    }
}
