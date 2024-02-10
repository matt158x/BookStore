package pl.edu.wszib.book.store.services.impl;

import pl.edu.wszib.book.store.model.Book;
import pl.edu.wszib.book.store.services.IBookService;

import java.util.ArrayList;
import java.util.List;

public class BookService2 implements IBookService {
    @Override
    public List<Book> getAll() {
        return new ArrayList<>();
    }



    @Override
    public List<Book> findBooksByTitleOrAuthorOrISBN(String searchQuery) {

        return new ArrayList<>();
    }

    @Override
    public void addBook(Book book) {}

    @Override
    public Book getBookById(int bookId) {
        return null;
    }
}


