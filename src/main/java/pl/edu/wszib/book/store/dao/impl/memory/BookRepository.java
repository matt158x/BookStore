package pl.edu.wszib.book.store.dao.impl.memory;

import org.springframework.stereotype.Repository;
import pl.edu.wszib.book.store.dao.IBookDAO;
import pl.edu.wszib.book.store.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository implements IBookDAO {

    private final List<Book> books = new ArrayList<>();
    private int nextId = 1;

    public BookRepository() {
        this.books.add(new Book(1, "Pan Tadeusz", "Adam Mickiewicz", "9780781800334", 45, 15));
        this.books.add(new Book(2, "Lalka", "Boleslaw Prus", "9781858660653", 60, 23));
        this.books.add(new Book(3, "Kamienie na szaniec", "Aleksander Kaminski", "9788371320538", 50, 10));
        this.books.add(new Book(4, "Potop", "Henryk Sienkiewicz", "9780870520044", 30, 18));
    }

    @Override
    public Optional<Book> getById(final int id) {
        return this.books.stream().filter(b -> b.getId() == id).findFirst();
    }

    @Override
    public List<Book> getAll() {
        return this.books;
    }

    @Override
    public void delete(final int id) {
        this.books.removeIf(b -> b.getId() == id);

    }

    @Override
    public void update(Book book) {
        //TODO jak bedzie baza
    }

    @Override
    public Optional<Book> getByISBN(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return Optional.of(book);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Book> getByPattern(String pattern) {
        return this.books.stream().filter(b -> b.getAuthor().toLowerCase().contains(pattern.toLowerCase()) || b.getTitle().toLowerCase().contains(pattern.toLowerCase())).toList();

    }

    @Override
    public List<Book> findBooksByTitleOrAuthorOrISBN(String searchQuery) {
        List<Book> foundBooks = new ArrayList<>();

        for (Book book : books) {

            if (book.getTitle().toLowerCase().contains(searchQuery.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(searchQuery.toLowerCase()) ||
                    book.getIsbn().toLowerCase().contains(searchQuery.toLowerCase())) {
                foundBooks.add(book);
            }
        }

        return foundBooks;
    }

    @Override
    public void persist(Book book) {

        book.setId(nextId++);
        books.add(book);
    }


}
