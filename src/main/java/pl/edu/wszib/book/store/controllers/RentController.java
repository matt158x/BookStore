package pl.edu.wszib.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.book.store.model.Book;
import pl.edu.wszib.book.store.model.Rental;
import pl.edu.wszib.book.store.services.IBookService;
import pl.edu.wszib.book.store.services.IRentalService;
import pl.edu.wszib.book.store.services.impl.RentalService;

@Controller
public class RentController {

    @Autowired
    private IRentalService rentalService;

    @RequestMapping(value = "/book/rent/{bookId}", method = RequestMethod.GET)
    public String showRentPage(@PathVariable int bookId, Model model) {
        Book book = rentalService.getBook(bookId);
        Rental rental = new Rental();
        model.addAttribute("rental", rental);
        model.addAttribute("book", book);
        return "rent";
    }

    @RequestMapping(value = "/rent", method = RequestMethod.POST)
    public String rentBook(@ModelAttribute Rental rental, @ModelAttribute Book book) {
        rental.setBookId(book.getId());
        rentalService.rentBook(rental);
        return "redirect:/main";
    }
}

