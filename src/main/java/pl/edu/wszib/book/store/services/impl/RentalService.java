package pl.edu.wszib.book.store.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.book.store.dao.IBookDAO;
import pl.edu.wszib.book.store.dao.IRentalDAO;
import pl.edu.wszib.book.store.model.Book;
import pl.edu.wszib.book.store.model.Rental;
import pl.edu.wszib.book.store.services.IRentalService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class RentalService implements IRentalService {


    @Autowired
    private IBookDAO bookDAO;

    @Autowired
    private IRentalDAO rentalDAO;



    @Override
    public Book getBook(int bookId) {
        return bookDAO.getById(bookId).orElse(null);
    }

    @Override
    public void rentBook(Rental rental) {
        Optional<Book> bookOptional = bookDAO.getById(rental.getBookId());

        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();

            LocalDate endDate = LocalDate.now().plusDays(14);

            rental.setBookId(book.getId());
            rental.setName(rental.getName());
            rental.setSurname(rental.getSurname());
            rental.setPhone(rental.getPhone());
            rental.setStartDate(LocalDate.now());
            rental.setEndDate(endDate);

            rentalDAO.persist(rental);
        }
    }
}