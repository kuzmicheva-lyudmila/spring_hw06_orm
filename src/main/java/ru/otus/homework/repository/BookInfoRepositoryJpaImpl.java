package ru.otus.homework.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.model.Book;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
public class BookInfoRepositoryJpaImpl implements BookInfoRepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Book saveBook(Book book) {
        if (book.getId() <= 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery(
                "select b from books b", Book.class
        );

        return query.getResultList();
    }

    @Override
    public List<Book> findByTitle(String title) {
        TypedQuery<Book> query = em.createQuery(
                "select b from books b where b.title = :title",
                Book.class
        );
        query.setParameter("name", title);
        return query.getResultList();
    }

    @Override
    public int deleteById(long id) {
        Query query = em.createQuery(
                "delete from books b where b.id = :id"
        );
        query.setParameter("id", id);
        return query.executeUpdate();
    }
}