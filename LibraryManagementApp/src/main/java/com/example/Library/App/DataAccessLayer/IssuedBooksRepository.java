package com.example.Library.App.DataAccessLayer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IssuedBooksRepository extends JpaRepository<IssuedBooks,Integer> {
}
