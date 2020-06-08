package com.example.Library.App.Exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(int id)
    {
        super("User id not found: "+id);
    }
}
