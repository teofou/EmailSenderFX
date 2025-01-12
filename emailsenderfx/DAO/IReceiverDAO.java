package com.github.teofou.emailsenderfx.DAO;



import com.github.teofou.emailsenderfx.model.Receiver;
import com.github.teofou.emailsenderfx.service.ecxeptions.*;

import java.io.IOException;
import java.util.List;

public interface IReceiverDAO {

//    void createOrUpdateConfigExcel(ConfigExcelProperties newConfigExcelProperties) throws FileNotFoundException;
//    void readConfigExcelInstance() throws IOException, WrongSettingsFileException;

    Receiver getOneReceiver();
    List<Receiver> getReceivers() throws IOException, ExcelFileIsEmptyException,
            WrongNumberOfCellsException, CellIsNotStringException, WrongHeaderNamesException, EmptyCellException, HeadersDuplicateException, WrongExcelPropertiesException, PropertiesFileNotFoundException, ExcelFileNotFoundException, WrongPropertiesFileException, ReceiversNotFoundException;
}
