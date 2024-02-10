package pl.edu.wszib.book.store.services.impl;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.edu.wszib.book.store.dao.IBookDAO;
import pl.edu.wszib.book.store.dao.IOrderDAO;
import pl.edu.wszib.book.store.model.Book;
import pl.edu.wszib.book.store.model.Order;
import pl.edu.wszib.book.store.model.OrderPosition;
import pl.edu.wszib.book.store.services.ICartService;
import pl.edu.wszib.book.store.session.SessionObject;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

@Service
public class CartService implements ICartService {

    @Autowired
    IBookDAO bookDAO;

    @Autowired
    IOrderDAO orderDAO;

    @Resource
    SessionObject sessionObject;



    @Override
    public void addBookToCart(int bookId) {
        Optional<Book> bookBox = this.bookDAO.getById(bookId);
        bookBox.ifPresent(book -> this.sessionObject.getCart().stream().filter(op -> op.getBook().getId() == bookId).findFirst().ifPresentOrElse(OrderPosition::incrementQuantity, () -> this.sessionObject.getCart().add(new OrderPosition(book, 1))));
    }

    @Override
    public boolean order() {

        Set<OrderPosition> cart = this.sessionObject.getCart();
        Iterator<OrderPosition> cartIterator = cart.iterator();
        while (cartIterator.hasNext()) {
            OrderPosition position = cartIterator.next();
            Optional <Book> bookfromDb = this.bookDAO.getById(position.getBook().getId());
            if(bookfromDb.isEmpty() || bookfromDb.get().getQuantity() < position.getQuantity()) {
                cartIterator.remove();
                return false;
            }
        }

        Order order = new Order();
        order.setUser(this.sessionObject.getLoggedUser());
        order.setDate(LocalDateTime.now());
        order.setStatus(Order.Status.NEW);
        order.setOrderPositions(cart);

        cart.stream().forEach(orderPosition -> {
            Book book = this.bookDAO.getById(orderPosition.getBook().getId()).get();
            book.setQuantity(book.getQuantity() - orderPosition.getQuantity());
            orderPosition.setBook(book);
        });

        this.orderDAO.persist(order);
        this.sessionObject.getCart().clear();
        return true;

    }
}
