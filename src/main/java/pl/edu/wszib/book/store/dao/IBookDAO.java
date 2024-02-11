package pl.edu.wszib.book.store.dao;

import pl.edu.wszib.book.store.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookDAO {

    Optional<Book> getById(int id);

    List<Book> getAll();


    void delete(int id);

    void update(Book book);

    Optional<Book> getByISBN(String isbn);

    List<Book> getByPattern(String pattern);

    List<Book> findBooksByTitleOrAuthorOrISBN(String searchQuery);

    void persist(Book book);



}
