package com.github.teofou.emailsenderfx.service.ecxeptions;

public class SettingsFileNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public SettingsFileNotFoundException() {
        super("Δε Βρέθηκε το αρχείο settings.txt");
    }
}
