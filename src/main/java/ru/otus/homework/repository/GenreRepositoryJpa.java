package ru.otus.homework.repository;

import ru.otus.homework.model.Genre;

import java.util.List;

public interface GenreRepositoryJpa {
    List<Genre> getAll();
}
