package com.github.teofou.emailsenderfx.service;


import com.github.teofou.emailsenderfx.model.Receiver;
import com.github.teofou.emailsenderfx.service.ecxeptions.*;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;


import java.io.IOException;
import java.util.List;

public interface IReceiverService {

    List<Receiver> getAllReceivers() throws IOException, ExcelFileIsEmptyException,
            WrongNumberOfCellsException, CellIsNotStringException, WrongHeaderNamesException, EmptyCellException, ReceiversNotFoundException, WrongPropertiesFileException, PropertiesFileNotFoundException, ExcelFileNotFoundException, HeadersDuplicateException, WrongExcelPropertiesException, NotOfficeXmlFileException;
}
