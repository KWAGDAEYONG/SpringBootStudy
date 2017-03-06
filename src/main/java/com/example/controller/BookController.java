package com.example.controller;


import com.example.model.Book;
import com.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by user on 2017-03-06.
 */
@Controller
@RequestMapping("/")
public class BookController {
    private static final String reader = "craig";
    private BookRepository repository;

    @Autowired
    public void setRepository(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String readerBooks(Model model){
        List<Book> readingList = repository.findByReader(reader);
        if(readingList!=null){
            model.addAttribute("books",readingList);
        }
        return "readingList";
    }

    @PostMapping
    public String addToReadingList(Book book){
        book.setReader(reader);
        repository.save(book);
        return "redirect:/";
    }
}
