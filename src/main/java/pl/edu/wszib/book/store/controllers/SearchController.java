package pl.edu.wszib.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.wszib.book.store.model.Book;
import pl.edu.wszib.book.store.services.IBookService;
import pl.edu.wszib.book.store.session.SessionObject;


import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private IBookService bookService;

    @Autowired
    private SessionObject sessionObject;

    @GetMapping("/search")
    public String searchBooks(@RequestParam(name = "searchQuery") String searchQuery, Model model) {
        List<Book> foundBooks = bookService.findBooksByTitleOrAuthorOrISBN(searchQuery);
        model.addAttribute("books", foundBooks);
        model.addAttribute("isLogged", sessionObject.isLogged());
        return "index";
    }


}