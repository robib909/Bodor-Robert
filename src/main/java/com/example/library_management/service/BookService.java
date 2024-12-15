package com.example.library_management.service;

import com.example.library_management.Author;
import com.example.library_management.Book;
import com.example.library_management.repository.BookRepository;
import com.example.library_management.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;  // Inject AuthorRepository

    // Get all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Get a book by ID
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    // Save a new book or create a book
    public Book saveBook(Book book) {
        if (book.getAuthor() == null || book.getAuthor().getId() == null) {
            throw new IllegalArgumentException("Author must be provided");
        }
        
        // Use the injected AuthorRepository to find the author by ID
        Author author = authorRepository.findById(book.getAuthor().getId())
                .orElseThrow(() -> new IllegalArgumentException("Author not found"));
        
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    // Update an existing book
    public Book updateBook(Long id, Book book) {
        // Find the existing book
        Book existingBook = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found"));

        // Ensure that the author exists
        if (book.getAuthor() == null || book.getAuthor().getId() == null) {
            throw new IllegalArgumentException("Author must be provided");
        }
        
        Author author = authorRepository.findById(book.getAuthor().getId())
                .orElseThrow(() -> new IllegalArgumentException("Author not found"));
        
        // Update the book fields
        existingBook.setTitle(book.getTitle());
        existingBook.setGenre(book.getGenre());
        existingBook.setAuthor(author);  // Update the author
        
        // Save and return the updated book
        return bookRepository.save(existingBook);
    }

    // Delete a book by ID
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
