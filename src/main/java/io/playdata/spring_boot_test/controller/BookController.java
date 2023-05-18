package io.playdata.spring_boot_test.controller;

import io.playdata.spring_boot_test.model.Book;
import io.playdata.spring_boot_test.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")  // Set the base URL for all BookController endpoints
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());  // Add all books to the model
        return "book/list";  // Return the name of the view template for book list
    }

    @GetMapping("/{id}")
    public String getBookById(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return "redirect:/books";  // Redirect to the book list if the book is not found
        }
        model.addAttribute("book", book);
        return "book/details";  // Return the name of the view template for book details
    }

    @GetMapping("/new")
    public String showBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "book/form";  // Return the name of the view template for the book form
    }

    @PostMapping
    public String saveBook(@ModelAttribute("book") Book book) {
        bookService.saveBook(book);
        return "redirect:/books";  // Redirect to the book list after saving the book
    }

    @GetMapping("/{id}/edit")
    public String showEditBookForm(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return "redirect:/books";  // Redirect to the book list if the book is not found
        }
        model.addAttribute("book", book);
        return "book/form";  // Return the name of the view template for the book form (used for editing)
    }

    @PostMapping("/{id}/edit")
    public String updateBook(@PathVariable Long id, @ModelAttribute("book") Book book) {
        book.setId(id);
        bookService.saveBook(book);
        return "redirect:/books";  // Redirect to the book list after updating the book
    }

    @GetMapping("/{id}/delete")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";  // Redirect to the book list after deleting the book
    }
}