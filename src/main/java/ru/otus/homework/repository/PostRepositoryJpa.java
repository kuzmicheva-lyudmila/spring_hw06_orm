package ru.otus.homework.repository;

import ru.otus.homework.model.Book;
import ru.otus.homework.model.Post;

import java.util.List;

public interface PostRepositoryJpa {
    Post save(Post post);
    List<Post> findByBook(Book book);
    int deleteByBook(Book book);
}
