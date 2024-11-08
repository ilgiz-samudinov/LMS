package org.example.library.service;

import org.example.library.model.Book;
import org.example.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Получить все книги
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Сохранить или обновить книгу
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    // Получить книгу по id
    public Optional<Book> getBookById(long id) {
        return bookRepository.findById(id);
    }

    // Удалить книгу по id
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> searchBooks(String keyboard){
        return bookRepository.findByTitleContainingOrAuthorContaining(keyboard, keyboard);
    }

}
