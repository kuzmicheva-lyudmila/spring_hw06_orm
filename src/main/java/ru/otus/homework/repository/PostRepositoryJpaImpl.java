package ru.otus.homework.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.model.Book;
import ru.otus.homework.model.Post;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PostRepositoryJpaImpl implements PostRepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Post save(Post post) {
        if (post.getId() <= 0) {
            em.persist(post);
            return post;
        } else {
            return em.merge(post);
        }
    }

    @Override
    public List<Post> findByBook(Book book) {
        TypedQuery<Post> query = em.createQuery("select p from Post p where book = :book", Post.class);
        query.setParameter("book", book);
        return query.getResultList();
    }

    @Override
    public int deleteByBook(Book book) {
        Query query = em.createQuery(
                "delete from Post p where book = :book"
        );
        query.setParameter("book", book);
        return query.executeUpdate();
    }
}
