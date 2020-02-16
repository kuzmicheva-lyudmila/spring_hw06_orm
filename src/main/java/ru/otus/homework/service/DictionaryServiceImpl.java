package ru.otus.homework.service;

import org.springframework.stereotype.Service;
import ru.otus.homework.repository.AuthorRepositoryJpa;
import ru.otus.homework.model.Author;
import ru.otus.homework.model.Genre;
import ru.otus.homework.repository.GenreRepositoryJpa;

import java.util.List;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    private final AuthorRepositoryJpa authorRepositoryJpa;
    private final GenreRepositoryJpa genreRepositoryJpa;

    public DictionaryServiceImpl(
            AuthorRepositoryJpa authorRepositoryJpa,
            GenreRepositoryJpa genreRepositoryJpa
    ) {
        this.authorRepositoryJpa = authorRepositoryJpa;
        this.genreRepositoryJpa = genreRepositoryJpa;
    }

    @Override
    public Author getAuthorsByFullname(String author) {
        return authorRepositoryJpa.getByFullname(author);
    }

    @Override
    public void showAuthors(CommunicationService communicationService) {
        List<Author> authorList = authorRepositoryJpa.getAll();
        authorList.stream().forEach(author -> communicationService.showMessage(author.toString()));
    }

    @Override
    public void showBookGenres(CommunicationService communicationService) {
        List<Genre> genres = genreRepositoryJpa.getAll();
        genres.stream().forEach(bookGenre -> communicationService.showMessage(bookGenre.toString()));
    }
}
