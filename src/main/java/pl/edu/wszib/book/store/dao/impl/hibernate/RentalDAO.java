package pl.edu.wszib.book.store.dao.impl.hibernate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.edu.wszib.book.store.dao.IRentalDAO;
import pl.edu.wszib.book.store.model.Rental;

import java.util.Optional;

@Repository
@Transactional
public class RentalDAO implements IRentalDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Optional<Rental> getById(int rentalId) {
        Session session = sessionFactory.getCurrentSession();
        Rental rental = session.get(Rental.class, rentalId);
        return Optional.ofNullable(rental);
    }

    @Override
    public void persist(Rental rental) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.persist(rental);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

    @Override
    public void update(Rental rental) {
        Session session = sessionFactory.getCurrentSession();
        session.update(rental);
    }




    @Override
    public void delete(int rentalId) {
        Session session = sessionFactory.getCurrentSession();
        Rental rental = session.get(Rental.class, rentalId);
        if (rental != null) {
            session.delete(rental);
        }
    }
}