package pl.edu.wszib.book.store.dao.impl.hibernate;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.book.store.dao.IOrderDAO;
import pl.edu.wszib.book.store.model.Order;

import java.util.Optional;

@Repository
public class OrderDAO implements IOrderDAO {

    private final String GET_BY_ID = "FROM pl.edu.wszib.book.store.model.Order WHERE id = :id";

    @Autowired
    SessionFactory sessionFactory;



    @Override
    public void persist(Order order) {
        Session session = this.sessionFactory.openSession();
        order.getOrderPositions().forEach(op -> op.setBook(session.merge(op.getBook() )));
        try {
            session.beginTransaction();
            session.persist(order);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

    @Override
    public Optional<Order> getOrderById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Order> query = session.createQuery(GET_BY_ID, Order.class);
        query.setParameter("id", id);
        try{
            return Optional.of(query.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        } finally {
            //session.close();
        }
    }
}
