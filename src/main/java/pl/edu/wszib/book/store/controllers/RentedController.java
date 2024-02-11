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
    private RentalService rentalService; // Serwis obsługujący operacje na wypożyczeniach

    @GetMapping("/rentals")
    public String getAllRentals(Model model) {
        List<Rental> rentals = rentalService.getAllRentals(); // Pobierz wszystkie wypożyczenia z serwisu
        model.addAttribute("rentals", rentals); // Dodaj listę wypożyczeń do modelu, aby przekazać je do widoku
        return "rentals"; // Zwróć nazwę widoku (nazwa pliku HTML bez rozszerzenia)
    }

    @DeleteMapping("/deleteRental/{rentalId}")
    public ResponseEntity<Void> deleteRental(@PathVariable int rentalId) {
        rentalService.deleteRentalById(rentalId);
        return ResponseEntity.ok().build();
    }

}
