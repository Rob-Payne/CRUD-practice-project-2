package com.example.CRUDpracticeproject2.dao;

import com.example.CRUDpracticeproject2.model.Books;
import org.springframework.data.repository.CrudRepository;

public interface BooksRepository extends CrudRepository<Books, Long> {
}
