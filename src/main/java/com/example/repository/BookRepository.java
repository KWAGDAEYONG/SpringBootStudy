package com.example.repository;

import com.example.model.Book;
import com.example.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by user on 2017-03-06.
 */
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByReader(Reader reader);
}
