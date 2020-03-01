package ru.otus.homework.repository;

import ru.otus.homework.model.Author;

import java.util.List;

public interface AuthorRepositoryJpa {
    List<Author> getAll();
    Author getByFullname(String fullName);
    Author save(Author author);
}
