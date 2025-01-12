package com.github.teofou.emailsenderfx.service.ecxeptions;


public class ExcelFileIsEmptyException extends Exception {

    private static final long serialVersionUID = 1L;

    public ExcelFileIsEmptyException() {
        super("The excel file is empty");
    }
}
