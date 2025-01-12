package com.github.teofou.emailsenderfx.service.ecxeptions;

public class WrongHeaderNamesException extends Exception {
    private static final long serialVersionUID = 1L;

    public WrongHeaderNamesException(String nameHeader, String emailHeader, String folderHeader) {
        super("The first row of excel file must contain: " + nameHeader + ", " + emailHeader + ", " + folderHeader);
    }
}
