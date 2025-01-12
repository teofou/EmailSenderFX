package com.github.teofou.emailsenderfx.service.ecxeptions;

public class CellIsNotStringException extends Exception {

    private static final long serialVersionUID = 1L;

    public CellIsNotStringException(int row, int column) {
        super("Cell at row: " + row + " column: " + column  + " must be String");
    }
}
