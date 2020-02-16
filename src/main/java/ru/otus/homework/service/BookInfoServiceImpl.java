package ru.otus.homework.service;

import org.springframework.stereotype.Service;
import ru.otus.homework.repository.AuthorRepositoryJpa;
import ru.otus.homework.repository.BookInfoRepositoryJpa;
import ru.otus.homework.model.Author;
import ru.otus.homework.model.Book;
import ru.otus.homework.model.Genre;
import ru.otus.homework.repository.GenreRepositoryJpa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookInfoServiceImpl implements BookInfoService{
    private final BookInfoRepositoryJpa bookInfoRepositoryJpa;
    private final AuthorRepositoryJpa authorRepositoryJpa;
    private final GenreRepositoryJpa genreRepositoryJpa;

    public BookInfoServiceImpl(
            BookInfoRepositoryJpa bookInfoRepositoryJpa,
            AuthorRepositoryJpa authorRepositoryJpa,
            GenreRepositoryJpa genreRepositoryJpa
    ) {
        this.bookInfoRepositoryJpa = bookInfoRepositoryJpa;
        this.authorRepositoryJpa = authorRepositoryJpa;
        this.genreRepositoryJpa = genreRepositoryJpa;
    }

    @Override
    public void insertBook(CommunicationService communicationService) {
        String title = communicationService.getUserInputString(
                "Введите наименование книги",
                "Некорректное наименование! Введите наименование еще раз",
                "[^.]+"
        );

        String authors = communicationService.getUserInputString(
                "Введите авторов книги (разделитель ';')",
                "Некорректный автор! Введите авторов еще раз",
                "[^.]+"
        );

        List<Author> authorList = new ArrayList<>();
        Arrays.stream(authors.split(";"))
                .forEach(
                        fullname -> {
                            Author author = authorRepositoryJpa.getByFullname(fullname);
                            if (author == null) {
                                authorList.add(authorRepositoryJpa.save(new Author(fullname)));
                            } else {
                                authorList.add(author);
                            }
                        }
                );

        Genre genre = communicationService.getUserInputString(
                "Введите жанр книги",
                "Некорректный жанр книги! Введите жанр еще раз",
                genreRepositoryJpa.getAll()
        );

        String description = communicationService.getUserInputString(
                "Введите описание книги",
                "Некорректное описание! Введите описание еще раз",
                "[^.]+"
        );

        String message;
        try {
            Book newBook = bookInfoRepositoryJpa.saveBook(
                    new Book(-1, title, genre, authorList, description)
            );
            message = "inserted: " + newBook.toString();
        } catch (Exception e) {
            message = e.getMessage();
        }
        communicationService.showMessage(message);
    }

    @Override
    public void updateTitleBookById(CommunicationService communicationService) {
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
                book.setTitle(title);
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

    @Override
    public void deleteBookById(CommunicationService communicationService) {
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

    @Override
    public void getAllBooks(CommunicationService communicationService) {
        List<Book> bookList = bookInfoRepositoryJpa.findAll();
        bookList.stream().forEach(book -> communicationService.showMessage(book.toString()));
    }
}
