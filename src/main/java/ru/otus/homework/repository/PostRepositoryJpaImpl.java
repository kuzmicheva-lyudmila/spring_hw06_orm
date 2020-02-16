package ru.otus.homework.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.model.Author;
import ru.otus.homework.model.Book;
import ru.otus.homework.model.Post;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PostRepositoryJpaImpl implements PostRepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Post> findByBookId(long bookId) {
        TypedQuery<Post> query = em.createQuery("select p from posts where book_id = :book_id", Post.class);
        query.setParameter("book_id", bookId);
        return query.getResultList();
    }
}
