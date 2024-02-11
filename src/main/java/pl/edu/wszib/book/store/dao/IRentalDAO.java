package pl.edu.wszib.book.store.dao;

import pl.edu.wszib.book.store.model.Rental;

import java.util.Optional;

public interface IRentalDAO {
    Optional<Rental> getById(int rentalId);
    void persist(Rental rental);
    void update(Rental rental);
    void delete(int rentalId);


    void deleteById(int rentalId);
}