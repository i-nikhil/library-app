package com.example.Library.App.Controller;

import com.example.Library.App.DataAccessLayer.User;
import com.example.Library.App.DataAccessLayer.UserRepository;
import com.example.Library.App.Exception.BookNotFoundException;
import com.example.Library.App.Exception.UserNameMissingException;
import com.example.Library.App.Exception.UserNotFoundException;
import com.example.Library.App.Util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class UserResource {

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    @Autowired
    private UserRepository repository;

    UserValidator validator=new UserValidator();

    // Find
    @GetMapping("/users")
    List<User> findAll() {
        return repository.findAll();
    }

    @PostMapping("/users")
    //return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED)
    User newUser(@RequestBody User newUser) {
        if (validator.isValid(newUser))
            return repository.save(newUser);
        else {
            LOGGER.severe("New User data is not valid");
            throw new UserNameMissingException();
        }
    }

    // Find a given user
    @GetMapping("/users/{id}")
    User findOne(@PathVariable int id) {
        LOGGER.info("/users/{id} called with id "+ id);
        // Optional<User> user = repository.findById(id);
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
