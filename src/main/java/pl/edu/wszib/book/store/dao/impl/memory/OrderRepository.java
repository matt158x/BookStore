package pl.edu.wszib.book.store.dao.impl.memory;

import org.springframework.stereotype.Repository;
import pl.edu.wszib.book.store.dao.IOrderDAO;
import pl.edu.wszib.book.store.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository implements IOrderDAO {

    private final List<Order> orders = new ArrayList<>();

    @Override
    public void persist(Order order) {
        this.orders.add(order);

    }

    @Override
    public Optional<Order> getOrderById(int id) {
        return Optional.empty();
    }
}
