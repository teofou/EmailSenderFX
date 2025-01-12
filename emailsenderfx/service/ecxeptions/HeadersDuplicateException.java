package com.github.teofou.emailsenderfx.service.ecxeptions;

public class HeadersDuplicateException extends Exception {

    private static final long serialVersionUID = 1L;

    public HeadersDuplicateException(String headerName) {
        super("There two headers with the name: " + headerName);
    }
}
