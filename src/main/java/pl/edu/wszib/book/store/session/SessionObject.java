package pl.edu.wszib.book.store.session;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.edu.wszib.book.store.model.OrderPosition;
import pl.edu.wszib.book.store.model.User;

import java.util.HashSet;
import java.util.Set;


@Component
@SessionScope
public class SessionObject {

    private User loggedUser;
    private Set<OrderPosition> cart = new HashSet<>();

    public boolean isLogged(){
        return loggedUser != null;
    }
    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public Set<OrderPosition> getCart() {
        return cart;
    }

    public void setCart(Set<OrderPosition> cart) {
        this.cart = cart;
    }
}

