package ru.otus.homework.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.otus.homework.model.Author;
import ru.otus.homework.model.Genre;
import ru.otus.homework.repository.GenreRepositoryJpa;
import ru.otus.homework.repository.AuthorRepositoryJpa;

import java.util.List;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    private final AuthorRepositoryJpa authorRepositoryJpa;
    private final GenreRepositoryJpa genreRepositoryJpa;
    private final CommunicationService communicationService;

    public DictionaryServiceImpl(
            AuthorRepositoryJpa authorRepositoryJpa,
            GenreRepositoryJpa genreRepositoryJpa,
            CommunicationService communicationService
    ) {
        this.authorRepositoryJpa = authorRepositoryJpa;
        this.genreRepositoryJpa = genreRepositoryJpa;
        this.communicationService = communicationService;
    }

    @SneakyThrows
    @Override
    public void showAuthors() {
        List<Author> authorList = authorRepositoryJpa.getAll();
        for (Author author : authorList) {
            communicationService.showMessage(author.toString());
        }
    }

    @SneakyThrows
    @Override
    public void showBookGenres() {
        List<Genre> genres = genreRepositoryJpa.getAll();
        for (Genre genre : genres) {
            communicationService.showMessage(genre.toString());
        }
    }
}
