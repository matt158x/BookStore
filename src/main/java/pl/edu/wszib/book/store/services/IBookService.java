package pl.edu.wszib.book.store.services;

import pl.edu.wszib.book.store.model.Book;

import java.util.List;

public interface IBookService {

    List<Book> getAll();


    List<Book> findBooksByTitleOrAuthorOrISBN(String searchQuery);


    void addBook(Book book);

    Book getBookById(int bookId);
}
