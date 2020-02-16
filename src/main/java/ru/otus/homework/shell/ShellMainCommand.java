package ru.otus.homework.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.homework.service.BookInfoService;
import ru.otus.homework.service.CommunicationService;
import ru.otus.homework.service.DictionaryService;

@ShellComponent
public class ShellMainCommand {
    private final BookInfoService bookInfoService;
    private final DictionaryService dictionaryService;
    private final CommunicationService communicationService;

    public ShellMainCommand(BookInfoService bookInfoService, DictionaryService dictionaryService, CommunicationService communicationService) {
        this.bookInfoService = bookInfoService;
        this.dictionaryService = dictionaryService;
        this.communicationService = communicationService;
    }

    @ShellMethod(value = "InsertBook", key = {"insertBook", "ib"})
    public void insertBook() {
        bookInfoService.insertBook(communicationService);
    }

    @ShellMethod(value = "UpdateBook", key = {"updateBook", "ub"})
    public void updateBook() {

        bookInfoService.updateTitleBookById(communicationService);
    }

    @ShellMethod(value = "DeleteBook", key = {"deleteBook", "db"})
    public void deleteBook() {
        bookInfoService.deleteBookById(communicationService);
    }

    @ShellMethod(value = "AllBooks", key = {"showBooks", "sb"})
    public void showBooks() {
        bookInfoService.getAllBooks(communicationService);
    }

    @ShellMethod(value = "AllGenres", key = {"showGenres", "sg"})
    public void getGenres() {
        dictionaryService.showBookGenres(communicationService);
    }

    @ShellMethod(value = "AllAuthors", key = {"showAuthors", "sa"})
    public void getAuthors() {

        dictionaryService.showAuthors(communicationService);
    }
}
