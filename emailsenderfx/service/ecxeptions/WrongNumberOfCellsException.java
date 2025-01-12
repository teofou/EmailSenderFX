package com.github.teofou.emailsenderfx.service.ecxeptions;

public class WrongNumberOfCellsException extends Exception {

    private static final long serialVersionUID = 1L;

    public WrongNumberOfCellsException(int cellsNumber) {
        super("Στο αρχείο πρέπει να είναι συμπληρωμένα μόνο τα " + cellsNumber + "πρώτα κελιά");
    }
}
