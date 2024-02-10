package pl.edu.wszib.book.store.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.edu.wszib.book.store.dao.IBookDAO;
import pl.edu.wszib.book.store.model.Book;
import pl.edu.wszib.book.store.services.IBookService;

import java.util.List;

@Service
public class BookService implements IBookService {

    @Autowired
    IBookDAO bookDAO;


    @Override
    public List<Book> getAll() {

        return this.bookDAO.getAll();
    }

    @Override
    public List<Book> findBooksByTitleOrAuthorOrISBN(String searchQuery) {
        return bookDAO.findBooksByTitleOrAuthorOrISBN(searchQuery);
    }

    @Override
    public void addBook(Book book) {
        Book existingBook = bookDAO.getByISBN(book.getIsbn()).orElse(null);

        if (existingBook == null) {
            bookDAO.persist(book);
        } else {
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setPrice(book.getPrice());
            existingBook.setQuantity(existingBook.getQuantity() + book.getQuantity());
            bookDAO.update(existingBook);
        }
    }

    @Override
    public Book getBookById(int bookId) {
        return null;
    }
}
