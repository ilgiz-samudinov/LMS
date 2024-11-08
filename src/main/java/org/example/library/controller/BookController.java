package org.example.library.controller;

import org.example.library.model.Book;
import org.example.library.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("book")
public class BookController {

private final BookService bookService;

public BookController(BookService bookService){
    this.bookService = bookService;
}


    @GetMapping("/all")
    public String getAllBooks(Model model) {
        model.addAttribute("books",bookService.getAllBooks());
        return "book-list";
    }

    @GetMapping("/new")
    public String showAddBookForm(Model model){
        model.addAttribute("book", new Book());
        return "book-form";
    }

    @PostMapping("/new")
    public String addBook(@ModelAttribute("book") Book book){
        bookService.saveBook(book);
        return "redirect:/book/all";
    }

    @GetMapping("/edit/{id}")
    public String showEditBookForm(@PathVariable("id") long id, Model model){
        Optional<Book> book = bookService.getBookById(id);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
        }
        return "book-form";
    }

    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable("id") long id, @ModelAttribute("book")  Book book){
     // book.setId(id);
       bookService.saveBook(book);
       return "redirect:/book/all";

    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id")long id){
        bookService.deleteBook(id);
        return "redirect:/book/all";
    }


    @GetMapping("/search")
    public String searchBook(@RequestParam("keybord") String keybord, Model model){
        List<Book> foundBooks = bookService.searchBooks(keybord);
        model.addAttribute("books", foundBooks);
        model.addAttribute("keybord", keybord);
        return "book-list";
    }


}





