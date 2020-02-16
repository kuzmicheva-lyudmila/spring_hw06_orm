package ru.otus.homework.repository;

import ru.otus.homework.model.CommunicationDaoModel;

import java.util.List;

public interface CommunicationDao {
    <T extends CommunicationDaoModel> T getUserInputString(String message, String errorMessage, List<T> dictionary);
    String getUserInputString(String message, String errorMessage, String template);

    void showMessage(String messageString);
}
