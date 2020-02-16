package ru.otus.homework.repository;

import org.springframework.stereotype.Component;
import ru.otus.homework.model.CommunicationDaoModel;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class CommunicationDaoImpl implements CommunicationDao {
    @Override
    public <T extends CommunicationDaoModel> T getUserInputString(String message, String errorMessage, List<T>  dictionary) {
        Scanner sc = new Scanner(System.in);
        Boolean isFirstInput = true;
        Optional<T> elementDictionary = null;
        do {
            if (isFirstInput) {
                System.out.println(message + ": ");
                isFirstInput = false;
            } else {
                System.out.println(errorMessage + ": ");
            }
            String resultString = sc.nextLine();
            elementDictionary = dictionary.stream()
                    .filter(e -> e.equalsByString(resultString))
                    .findAny();
        } while (!elementDictionary.isPresent());
        return elementDictionary.get();
    }

    @Override
    public String getUserInputString(String message, String errorMessage, String template) {
        Scanner sc = new Scanner(System.in);
        String resultString = null;
        Boolean isFirstInput = true;
        do {
            if (isFirstInput) {
                System.out.println(message + ": ");
                isFirstInput = false;
            } else {
                System.out.println(errorMessage + ": ");
            }
            resultString = sc.nextLine();
        } while (!resultString.matches(template));
        return resultString.trim();
    }

    @Override
    public void showMessage(String messageString) {
        System.out.println(messageString);
    }
}
