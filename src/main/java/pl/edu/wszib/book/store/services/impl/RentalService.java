package pl.edu.wszib.book.store.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.edu.wszib.book.store.dao.IBookDAO;
import pl.edu.wszib.book.store.dao.IRentalDAO;
import pl.edu.wszib.book.store.model.Book;
import pl.edu.wszib.book.store.model.Rental;
import pl.edu.wszib.book.store.services.IRentalService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class RentalService implements IRentalService {


    @Autowired
    private IBookDAO bookDAO;

    @Autowired
    private IRentalDAO rentalDAO;



    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Rental> getAllRentals() {
        String query = "SELECT * FROM trent";
        return jdbcTemplate.query(query, (rs, rowNum) -> mapRowToRental(rs));
    }

    private Rental mapRowToRental(ResultSet rs) throws SQLException {
        Rental rental = new Rental();
        rental.setRentalId(rs.getInt("rentalId"));
        rental.setBookId(rs.getInt("bookId"));
        rental.setName(rs.getString("name"));
        rental.setSurname(rs.getString("surname"));
        rental.setPhone(rs.getString("phone"));
        rental.setStartDate(rs.getDate("startDate").toLocalDate());
        rental.setEndDate(rs.getDate("endDate").toLocalDate());
        return rental;
    }

    @Override
    public void deleteRentalById(int rentalId) {
        rentalDAO.deleteById(rentalId);
    }


    @Override
    public Book getBook(int bookId) {
        return bookDAO.getById(bookId).orElse(null);
    }

    @Override
    public void rentBook(Rental rental) {
        Optional<Book> bookOptional = bookDAO.getById(rental.getBookId());

        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();

            if (book.getQuantity() > 0) {
                book.setQuantity(book.getQuantity() - 1);
                bookDAO.update(book);


                LocalDate endDate = LocalDate.now().plusDays(14);

                rental.setBookId(book.getId());
                rental.setName(rental.getName());
                rental.setSurname(rental.getSurname());
                rental.setPhone(rental.getPhone());
                rental.setStartDate(LocalDate.now());
                rental.setEndDate(endDate);

                rentalDAO.persist(rental);
            } else {
                throw new IllegalStateException("Brak dostępnych egzemplarzy książki.");
            }
        }
    }
}