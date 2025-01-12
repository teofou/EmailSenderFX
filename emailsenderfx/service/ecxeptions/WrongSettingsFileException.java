package com.github.teofou.emailsenderfx.service.ecxeptions;

public class WrongSettingsFileException extends Exception {
    private static final long serialVersionUID = 1L;

    public WrongSettingsFileException() {
        super("Σφάλμα στα περιεχόμενα του αρχείου settings.txt");
    }
}
