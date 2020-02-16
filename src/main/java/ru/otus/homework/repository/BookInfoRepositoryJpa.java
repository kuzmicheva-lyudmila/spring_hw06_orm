package ru.otus.homework.repository;

import ru.otus.homework.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookInfoRepositoryJpa {
    Book saveBook(Book book);

    Optional<Book> findById(long id);
    List<Book> findAll();
    List<Book> findByTitle(String title);

    int deleteById(long id);
}
