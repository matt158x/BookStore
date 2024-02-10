package pl.edu.wszib.book.store.services;

public interface ICartService {

    void addBookToCart(int bookId);

    boolean order();

}
