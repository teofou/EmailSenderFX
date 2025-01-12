package com.github.teofou.emailsenderfx.service.ecxeptions;

public class WrongPropertiesFileException extends Exception{

    private static final long serialVersionUID = 1L;

    public WrongPropertiesFileException(String propertiesFileName, String property) {
        super("The " + property + " was not found in " + propertiesFileName);
    }
}
