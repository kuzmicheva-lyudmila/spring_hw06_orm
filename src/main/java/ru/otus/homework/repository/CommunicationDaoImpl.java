package ru.otus.homework.repository;

import org.springframework.stereotype.Component;
import ru.otus.homework.model.CommunicationDaoModel;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class CommunicationDaoImpl implements CommunicationDao {
    private static final String CHARSET_NAME = "UTF-8";

    private final PrintStream printStream;
    private final Scanner scanner;

    public CommunicationDaoImpl() throws UnsupportedEncodingException {
        this.printStream = new PrintStream(System.out, true, CHARSET_NAME);
        this.scanner = new Scanner(System.in, CHARSET_NAME);
    }

    @Override
    public <T extends CommunicationDaoModel> T getUserInputString(String message, String errorMessage, List<T>  dictionary) {
        Boolean isFirstInput = true;
        Optional<T> elementDictionary = null;
        do {
            if (isFirstInput) {
                printStream.println(message + ": ");
                isFirstInput = false;
            } else {
                printStream.println(errorMessage + ": ");
            }
            String resultString = scanner.nextLine();
            elementDictionary = dictionary.stream()
                    .filter(e -> e.equalsByString(resultString))
                    .findAny();
        } while (!elementDictionary.isPresent());
        return elementDictionary.get();
    }

    @Override
    public String getUserInputString(String message, String errorMessage, String template) {
        String resultString;
        Boolean isFirstInput = true;
        do {
            if (isFirstInput) {
                printStream.println(message + ": ");
                isFirstInput = false;
            } else {
                printStream.println(errorMessage + ": ");
            }
            resultString = scanner.nextLine();
        } while (!resultString.matches(template));
        return resultString.trim();
    }

    @Override
    public void showMessage(String messageString) {
        printStream.println(messageString);
    }
}
