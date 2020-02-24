package ru.otus.homework.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.otus.homework.repository.AuthorRepositoryJpa;
import ru.otus.homework.repository.BookInfoRepositoryJpa;
import ru.otus.homework.model.Author;
import ru.otus.homework.model.Book;
import ru.otus.homework.model.Genre;
import ru.otus.homework.repository.GenreRepositoryJpa;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookInfoServiceImpl implements BookInfoService {
    private final CommunicationService communicationService;
    private final BookInfoRepositoryJpa bookInfoRepositoryJpa;
    private final AuthorRepositoryJpa authorRepositoryJpa;
    private final GenreRepositoryJpa genreRepositoryJpa;

    public BookInfoServiceImpl(
            CommunicationService communicationService,
            BookInfoRepositoryJpa bookInfoRepositoryJpa,
            AuthorRepositoryJpa authorRepositoryJpa,
            GenreRepositoryJpa genreRepositoryJpa
    ) {
        this.communicationService = communicationService;
        this.bookInfoRepositoryJpa = bookInfoRepositoryJpa;
        this.authorRepositoryJpa = authorRepositoryJpa;
        this.genreRepositoryJpa = genreRepositoryJpa;
    }

    @SneakyThrows
    @Override
    public void insertBook() {
        String title = communicationService.getUserInputString(
                "Введите наименование книги",
                "Некорректное наименование! Введите наименование еще раз",
                "[^.]+"
        );

        List<Author> authorList = getAuthors();

        Genre genre = getGenre();

        String description = communicationService.getUserInputString(
                "Введите описание книги",
                "Некорректное описание! Введите описание еще раз",
                "[^.]+"
        );

        String message;
        try {
            Book newBook = bookInfoRepositoryJpa.saveBook(
                    new Book(0, title, genre, authorList, description)
            );
            message = "inserted: " + newBook.toString();
        } catch (Exception e) {
            message = e.getMessage();
        }
        communicationService.showMessage(message);
    }

    private Genre getGenre() throws UnsupportedEncodingException {
        List<Genre> genres = genreRepositoryJpa.getAll();
        if (genres.size() > 0) {
            List<String> genresString = genres.stream()
                    .map(Genre::getGenre)
                    .collect(Collectors.toList());
            String userGenre = communicationService.getUserInputString(
                    "Введите жанр книги",
                    "Некорректный жанр книги! Введите жанр еще раз",
                    genresString
            );

            return genres.stream()
                    .filter(g -> g.getGenre().toLowerCase().equals(userGenre.toLowerCase()))
                    .findAny()
                    .orElse(null);
        }

        return null;
    }

    private List<Author> getAuthors() throws UnsupportedEncodingException {
        String authors = communicationService.getUserInputString(
                "Введите авторов книги (разделитель ';')",
                "Некорректный автор! Введите авторов еще раз",
                "[^.]+"
        );

        List<Author> authorList = new ArrayList<>();
        if (authors != null) {
            Arrays.stream(authors.split(";"))
                    .forEach(
                            authorName -> {
                                Author author = authorRepositoryJpa.getByFullname(authorName);
                                if (author == null) {
                                    authorList.add(
                                            authorRepositoryJpa.save(
                                                    new Author(0, authorName, "")
                                            )
                                    );
                                } else {
                                    authorList.add(author);
                                }
                            }
                    );
        }
        return authorList;
    }

    @SneakyThrows
    @Override
    public void updateTitleBookById() {
        long id = Long.parseLong(
                communicationService.getUserInputString(
                        "Введите идентификатор книги",
                        "Некорректный идентификатор! Введите еще раз",
                        "[^d]+"
                )
        );

        String title = communicationService.getUserInputString(
                "Введите наименование книги",
                "Некорректное наименование! Введите еще раз",
                "[^.]+"
        );

        String message;
        try {
            Optional<Book> updatedBook = bookInfoRepositoryJpa.findById(id);
            if (updatedBook.isPresent()) {
                Book book = updatedBook.get();
                book.setFullName(title);
                bookInfoRepositoryJpa.saveBook(book);
                message = "updated book with id = " + book.getId();
            } else {
                message = "nothing updated";
            }
        } catch (Exception e) {
            message = e.getMessage();
        }

        communicationService.showMessage(message);
    }

    @SneakyThrows
    @Override
    public void deleteBookById() {
        long id = Long.parseLong(
                communicationService.getUserInputString(
                        "Введите идентификатор книги",
                        "Некорректный идентификатор! Введите еще раз",
                        "[^d]+"
                )
        );

        String message;
        try {
            int result = bookInfoRepositoryJpa.deleteById(id);
            message = (result > 0) ? "deleted success" : "nothing deleted";
        } catch (Exception e) {
            message = e.getMessage();
        }
        communicationService.showMessage(message);
    }

    @SneakyThrows
    @Override
    public void getAllBooks() {
        List<Book> bookList = bookInfoRepositoryJpa.findAll();
        for (Book book : bookList) {
            communicationService.showMessage(book.toString());
        }
    }
}
