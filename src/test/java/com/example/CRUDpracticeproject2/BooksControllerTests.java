package com.example.CRUDpracticeproject2;

import com.example.CRUDpracticeproject2.dao.BooksRepository;
import com.example.CRUDpracticeproject2.model.Books;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class BooksControllerTests {

    @Autowired
    MockMvc mvc;

    @Autowired
    BooksRepository booksRepository;

    @Test
    @Transactional
    @Rollback
    public void listAllBooks() throws Exception{
        Books newBooks = new Books();
        newBooks.setName("Book1");
        booksRepository.save(newBooks);
        Books newBooks1 = new Books();
        newBooks1.setName("Book2");
        booksRepository.save(newBooks);

        MockHttpServletRequestBuilder listBooksTest = get("/books")
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(listBooksTest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Book1")));
    }

    @Test
    @Transactional
    @Rollback
    public void getBooksByIdTest() throws Exception{
        Books newBooks = new Books();
        newBooks.setName("Book1");
        Long book1Id = booksRepository.save(newBooks).getId();

        MockHttpServletRequestBuilder getBooksByIdTest = get("/books/"+book1Id)
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(getBooksByIdTest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is("Book1")));
    }

    @Test
    @Transactional
    @Rollback
    public void createBooksTest() throws Exception{
        MockHttpServletRequestBuilder insertNewBookTest = post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Book3\", \"publishDate\":\"2050-01-01\"}");

        this.mvc.perform(insertNewBookTest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is("Book3")));
    }

    @Test
    @Transactional
    @Rollback
    public void deleteBookByIdTest() throws Exception{
        Books newBooks = new Books();
        newBooks.setName("Book1");
        Long book1Id = booksRepository.save(newBooks).getId();

        MockHttpServletRequestBuilder deleteBooksTest = delete("/books/"+book1Id)
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(deleteBooksTest)
                .andExpect(status().isOk());

        MockHttpServletRequestBuilder testThatBookWasDeletedTest = get("/books/"+book1Id)
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(testThatBookWasDeletedTest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    @Transactional
    @Rollback
    public void updateBooksbyIdTest() throws Exception{
        Books newBooks = new Books();
        newBooks.setName("Book1");
        Long book1Id = booksRepository.save(newBooks).getId();

        MockHttpServletRequestBuilder updateBooksTest = patch("/books/"+book1Id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Book12\"}");

        this.mvc.perform(updateBooksTest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Book12")));
    }
}
