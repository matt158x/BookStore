package pl.edu.wszib.book.store.services;

import pl.edu.wszib.book.store.model.Book;
import pl.edu.wszib.book.store.model.Rental;

import java.util.List;

public interface IRentalService {

    void deleteRentalById(int rentalId);

    Book getBook(int bookId);
    void rentBook(Rental rental);

}