package com.github.teofou.emailsenderfx.service.ecxeptions;

public class WrongExcelPropertiesException extends Exception {

    private static final long serialVersionUID = 1L;

    public WrongExcelPropertiesException() {
        super("Properties for the excel file are not correct");
    }
}
