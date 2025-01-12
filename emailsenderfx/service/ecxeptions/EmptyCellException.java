package com.github.teofou.emailsenderfx.service.ecxeptions;

import org.apache.poi.ss.util.CellAddress;

public class EmptyCellException extends Exception{
    private static final long serialVersionUID = 1L;

    public EmptyCellException(CellAddress cellAddress) {
        super("The " + cellAddress + " cell cannot be empty");
    }
}
