package com.example.controller;


import com.example.model.Book;
import com.example.model.Reader;
import com.example.properties.AmazonProperties;
import com.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
    //private static final String reader = "craig";
    private BookRepository repository;
    private AmazonProperties amazonProperties;
    private String associateId;



    @Autowired
    public void setRepository(BookRepository repository, AmazonProperties amazonProperties){
        this.repository = repository;
        this.amazonProperties = amazonProperties;
    }

    @GetMapping
    public String readerBooks(Reader reader, Model model){
        List<Book> readingList = repository.findByReader(reader);
        if(readingList!=null){
            model.addAttribute("books",readingList);
            model.addAttribute("reader",reader);
            model.addAttribute("amazonID",amazonProperties.getAssociateId());
        }
        return "readingList";
    }

    public void setAssociateId(String associateId) {
        this.associateId = associateId;
    }

    @PostMapping
    public String addToReadingList(Reader reader, Book book){
        book.setReader(reader);
        repository.save(book);
        return "redirect:/";
    }
}
