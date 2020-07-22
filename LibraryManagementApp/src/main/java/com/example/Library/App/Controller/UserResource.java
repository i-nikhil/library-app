package com.example.Library.App.Controller;

import com.example.Library.App.DataAccessLayer.User;
import com.example.Library.App.DataAccessLayer.UserRepository;
import com.example.Library.App.Exception.BookNotFoundException;
import com.example.Library.App.Exception.UserNameMissingException;
import com.example.Library.App.Exception.UserNotFoundException;
import com.example.Library.App.Util.UserValidator;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "To see the list of all Users")
    @GetMapping("/users")
    List<User> findAll() {
        return repository.findAll();
    }

    @ApiOperation(value = "To add a new User")
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
    @ApiOperation(value = "To get a User by Id")
    @GetMapping("/users/{id}")
    User findOne(@PathVariable int id) {
        LOGGER.info("/users/{id} called with id "+ id);
        // Optional<User> user = repository.findById(id);
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
