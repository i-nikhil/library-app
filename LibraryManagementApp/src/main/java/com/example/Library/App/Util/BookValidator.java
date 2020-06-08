package com.example.Library.App.Util;

import com.example.Library.App.DataAccessLayer.Book;

public class BookValidator {

    public boolean isValid(Book book){

        if(book.getTitle()==null||book.getTitle().equals(""))
            return false;
        return true;
    }
}
