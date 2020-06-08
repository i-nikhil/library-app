package com.example.Library.App.DataAccessLayer;

import com.example.Library.App.DataAccessLayer.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {
    //@Query("SELECT b FROM Book b WHERE b.author = 1")
    //Collection<Book> findBookByAuthor(String author);

}
