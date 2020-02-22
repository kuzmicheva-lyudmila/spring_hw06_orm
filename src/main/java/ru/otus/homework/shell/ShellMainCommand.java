package ru.otus.homework.shell;

import com.zaxxer.hikari.metrics.PoolStats;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.homework.service.BookInfoService;
import ru.otus.homework.service.CommunicationService;
import ru.otus.homework.service.DictionaryService;
import ru.otus.homework.service.PostService;

@ShellComponent
public class ShellMainCommand {
    private final BookInfoService bookInfoService;
    private final DictionaryService dictionaryService;
    private final PostService postService;
    private final CommunicationService communicationService;

    public ShellMainCommand(
            BookInfoService bookInfoService,
            DictionaryService dictionaryService,
            PostService postService,
            CommunicationService communicationService
    ) {
        this.bookInfoService = bookInfoService;
        this.dictionaryService = dictionaryService;
        this.postService = postService;
        this.communicationService = communicationService;
    }

    @ShellMethod(value = "InsertBook", key = {"insertBook", "ib"})
    public void insertBook() {
        bookInfoService.insertBook(communicationService);
    }

    @ShellMethod(value = "UpdateBook", key = {"updateBook", "ub"})
    public void updateBook() { bookInfoService.updateTitleBookById(communicationService); }

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
    public void getAuthors() { dictionaryService.showAuthors(communicationService); }

    @ShellMethod(value = "SetPostOnBook", key = {"setPost", "sp"})
    public void setPost() { postService.insertPostByBookId(communicationService); }

    @ShellMethod(value = "DeletePostsOnBook", key = {"deletePosts", "dp"})
    public void deletePosts() { postService.deletePostsByBookId(communicationService); }

    @ShellMethod(value = "GetPostsOnBook", key = {"getPosts", "gp"})
    public void getPosts() { postService.getPostsByBookId(communicationService); }
}
