package ru.otus.homework.service;

import org.springframework.stereotype.Service;
import ru.otus.homework.repository.CommunicationDao;
import ru.otus.homework.model.CommunicationDaoModel;

import java.util.List;

@Service
public class CommunicationServiceImpl implements CommunicationService {

    private final CommunicationDao communicationDao;

    public CommunicationServiceImpl(CommunicationDao communicationDao) {
        this.communicationDao = communicationDao;
    }

    @Override
    public <T extends CommunicationDaoModel> T getUserInputString(String message, String errorMessage, List<T> dictionary) {
        return communicationDao.getUserInputString(message, errorMessage, dictionary);
    }

    @Override
    public String getUserInputString(String message, String errorMessage, String template) {
        return communicationDao.getUserInputString(message, errorMessage, template);
    }

    @Override
    public void showMessage(String messageString) {
        communicationDao.showMessage(messageString);
    }
}
