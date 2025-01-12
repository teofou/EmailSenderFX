package com.github.teofou.emailsenderfx.service.ecxeptions;

import java.nio.file.Paths;

public class PropertiesFileNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public PropertiesFileNotFoundException(String propertiesFilepath) {

        super("The " + Paths.get(propertiesFilepath) + " was not found");

    }

}
