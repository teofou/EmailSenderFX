package com.github.teofou.emailsenderfx.service.ecxeptions;

public class ExcelFileNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public ExcelFileNotFoundException(String fileLocation) {
        super("The " + fileLocation + " is not a file");
    }

}
