package com.example.CRUDpracticeproject2.controller;

import com.example.CRUDpracticeproject2.dao.BooksRepository;
import com.example.CRUDpracticeproject2.model.Books;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class BooksController {
    private final BooksRepository booksRepository;

    public BooksController(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @GetMapping("/books")
    public Iterable<Books> getListOfBooks(){
        return this.booksRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public Optional<Books> getBooksById(@PathVariable Long id){
        return this.booksRepository.findById(id);
    }

    @PostMapping("/books")
    public Books createBooks(@RequestBody Books newBooks){
        return this.booksRepository.save(newBooks);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBooks(@PathVariable Long id){
        this.booksRepository.deleteById(id);
    }

    @PatchMapping("/books/{id}")
    public Books updateBooks(@PathVariable Long id,@RequestBody Books newBooks) {
        //Find the record
        Books oldBooks = this.booksRepository.findById(id).get();

        //Check for updates
        if (newBooks.getName() != null) {
            oldBooks.setName(newBooks.getName());}

            if (newBooks.getPublishDate() != null) {
                oldBooks.setPublishDate(newBooks.getPublishDate());}

                //Return and save to repo
                return this.booksRepository.save(oldBooks);
            }
        }
