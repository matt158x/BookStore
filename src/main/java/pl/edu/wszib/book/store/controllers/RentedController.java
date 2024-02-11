package pl.edu.wszib.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.edu.wszib.book.store.model.Rental;
import pl.edu.wszib.book.store.services.impl.RentalService;

import java.util.List;

@Controller
public class RentedController {



    @Autowired
    private RentalService rentalService;

    @GetMapping("/rentals")
    public String getAllRentals(Model model) {
        List<Rental> rentals = rentalService.getAllRentals();
        model.addAttribute("rentals", rentals);
        return "rentals";
    }

    @DeleteMapping("/deleteRental/{rentalId}")
    public ResponseEntity<Void> deleteRental(@PathVariable int rentalId) {
        rentalService.deleteRentalById(rentalId);
        return ResponseEntity.ok().build();
    }

}
