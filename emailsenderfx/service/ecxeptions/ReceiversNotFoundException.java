package com.github.teofou.emailsenderfx.service.ecxeptions;

public class ReceiversNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public ReceiversNotFoundException() {
        super("No receivers were found in the excel file");
    }
}
