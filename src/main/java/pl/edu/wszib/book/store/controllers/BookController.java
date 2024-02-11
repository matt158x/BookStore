package pl.edu.wszib.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.book.store.model.Book;
import pl.edu.wszib.book.store.services.IBookService;

@Controller
public class BookController {

    @Autowired
    private IBookService bookService;

    @RequestMapping(value = "/addbook", method = RequestMethod.GET)
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";
    }

    @RequestMapping(value = "/addbook", method = RequestMethod.POST)
    public String addBook(@ModelAttribute Book book) {
        bookService.addBook(book);
        return "redirect:/main";
    }

    @PutMapping("/increaseBookQuantity/{bookId}")
    public ResponseEntity<Void> increaseBookQuantity(@PathVariable int bookId) {
        bookService.increaseBookQuantityById(bookId);
        return ResponseEntity.ok().build();
    }

}
