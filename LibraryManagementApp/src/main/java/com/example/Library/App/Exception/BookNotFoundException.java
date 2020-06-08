package com.example.Library.App.Exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException()
    {
        super("Book id not found:");
    }
}
