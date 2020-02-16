package ru.otus.homework.service;

public interface BookInfoService {
    void insertBook(CommunicationService communicationService);
    void updateTitleBookById(CommunicationService communicationService);
    void deleteBookById(CommunicationService communicationService);

    void getAllBooks(CommunicationService communicationService);
}
