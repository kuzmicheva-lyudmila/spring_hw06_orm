package ru.otus.homework.service;

public interface PostService {
    void insertPostByBookId(CommunicationService communicationService);
    void deletePostsByBookId(CommunicationService communicationService);
    void getPostsByBookId(CommunicationService communicationService);
}
