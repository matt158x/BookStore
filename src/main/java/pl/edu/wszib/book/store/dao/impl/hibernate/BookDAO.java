package pl.edu.wszib.book.store.dao.impl.hibernate;

import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.book.store.dao.IBookDAO;
import pl.edu.wszib.book.store.model.Book;

import java.util.List;
import java.util.Optional;

@Repository
public class BookDAO implements IBookDAO {

    private final String GET_BY_ID = "FROM pl.edu.wszib.book.store.model.Book WHERE id = :id";

    private final String GET_ALL = "FROM pl.edu.wszib.book.store.model.Book";

    private final String GET_BY_PATTERN = "FROM pl.edu.wszib.book.store.model.Book WHERE author like :pattern OR title like :pattern OR isbn like :pattern";

    private final String GET_BY_ISBN = "FROM pl.edu.wszib.book.store.model.Book WHERE isbn = :isbn";

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Optional<Book> getById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Book> query = session.createQuery(GET_BY_ID, Book.class);
        query.setParameter("id", id);

        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Book> getAll() {
        Session session = this.sessionFactory.openSession();
        Query<Book> query = session.createQuery(GET_ALL, Book.class);
        List<Book> result = query.getResultList();
        session.close();
        return result;

    }


    @Override
    public void delete(int id) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.remove(new Book(id));
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }


    }

    @Override
    public void update(Book book) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.merge(book);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Book> getByPattern(String pattern) {
        Session session = this.sessionFactory.openSession();
        Query<Book> query = session.createQuery(GET_BY_PATTERN, Book.class);
        query.setParameter("pattern","%" + pattern + "%");
        List<Book> result = query.getResultList();
        session.close();
        return result;
    }


    @Override
    public List<Book> findBooksByTitleOrAuthorOrISBN(String searchQuery) {
        Session session = sessionFactory.openSession();
        Query<Book> query = session.createQuery(GET_BY_PATTERN, Book.class);
        query.setParameter("pattern", "%" + searchQuery + "%");
        List<Book> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public void persist(Book book) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(book);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<Book> getByISBN(String isbn) {
        Session session = this.sessionFactory.openSession();
        Query<Book> query = session.createQuery(GET_BY_ISBN, Book.class);
        query.setParameter("isbn", isbn);

        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        } finally {
            session.close();
        }
    }


}
