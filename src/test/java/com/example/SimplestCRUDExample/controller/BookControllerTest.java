package com.example.SimplestCRUDExample.controller;

import com.example.SimplestCRUDExample.model.Book;
import com.example.SimplestCRUDExample.repo.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doNothing;

class BookControllerTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testDeleteAllBooks2() throws Exception {
        doNothing().when(bookRepository).deleteAll();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/deleteAllBooks");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(bookController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void testDeleteAllBooks3() throws Exception {
        doNothing().when(bookRepository).deleteAll();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/deleteAllBooks");
        requestBuilder.characterEncoding("Encoding");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(bookController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void testDeleteBook2() throws Exception {
        doNothing().when(bookRepository).deleteById(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/deleteBookById/{id}", 1L);
        MockMvcBuilders.standaloneSetup(bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteBook3() throws Exception {
        doNothing().when(bookRepository).deleteById(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/deleteBookById/{id}", 1L);
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetAllBooks() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1L, "Book 1", "Author 1"));
        bookList.add(new Book(2L, "Book 2", "Author 2"));

        when(bookRepository.findAll()).thenReturn(bookList);

        ResponseEntity<List<Book>> responseEntity = bookController.getAllBooks();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size());
    }

    @Test
    void testGetBookById() {
        Book book = new Book(1L, "Book 1", "Author 1");
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        ResponseEntity<Book> responseEntity = bookController.getBookById(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Book 1", responseEntity.getBody().getTitle());
    }

    @Test
    void testAddBook() {
        Book book = new Book(1L, "Book 1", "Author 1");
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        ResponseEntity<Book> responseEntity = bookController.addBook(book);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Book 1", responseEntity.getBody().getTitle());
    }

    @Test
    void testUpdateBook() {
        Book existingBook = new Book(1L, "Book 1", "Author 1");
        Book updatedBook = new Book(1L, "Updated Book 1", "Updated Author 1");

        when(bookRepository.findById(1L)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(any(Book.class))).thenReturn(updatedBook);

        ResponseEntity<Book> responseEntity = bookController.updateBook(1L, updatedBook);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Updated Book 1", responseEntity.getBody().getTitle());
    }

//    @Test
//    void testDeleteBook() {
//        doNothing().when(bookRepository).deleteById(1L);
//
//        ResponseEntity<HttpStatus> responseEntity = bookController.deleteBook(1L);
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertTrue(responseEntity.getBody() == HttpStatus.OK);
//    }
//
//    @Test
//    void testDeleteAllBooks() {
//        doNothing().when(bookRepository).deleteAll();
//
//        ResponseEntity<HttpStatus> responseEntity = bookController.deleteAllBooks();
//
//        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
//        assertTrue(responseEntity.getBody() == HttpStatus.NO_CONTENT);
//    }
}
