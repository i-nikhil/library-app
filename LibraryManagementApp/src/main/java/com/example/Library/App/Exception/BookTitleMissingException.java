package com.example.Library.App.Exception;

public class BookTitleMissingException extends RuntimeException {

    public BookTitleMissingException()
    {

        super("Enter a valid Book Title");
    }
}
