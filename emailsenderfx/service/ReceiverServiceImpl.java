package com.github.teofou.emailsenderfx.service;



import com.github.teofou.emailsenderfx.DAO.IReceiverDAO;
import com.github.teofou.emailsenderfx.DAO.ReceiverDAOImpl;
import com.github.teofou.emailsenderfx.model.Receiver;
import com.github.teofou.emailsenderfx.service.ecxeptions.*;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;


import java.io.IOException;
import java.util.List;

public class ReceiverServiceImpl implements IReceiverService {

//    private final IReceiverDAO receiverDAO;

//    public ReceiverServiceImpl(IReceiverDAO receiverDAO) {
//        this.receiverDAO = receiverDAO;
//    }


    private IReceiverDAO receiverDAO = ReceiverDAOImpl.getInstance();

    @Override
    public List<Receiver> getAllReceivers() throws IOException, ExcelFileIsEmptyException, WrongNumberOfCellsException, CellIsNotStringException, WrongHeaderNamesException, EmptyCellException, ReceiversNotFoundException, WrongPropertiesFileException, PropertiesFileNotFoundException, ExcelFileNotFoundException, HeadersDuplicateException, WrongExcelPropertiesException, NotOfficeXmlFileException {
        try {
//            String[] excelFileHeaders = getExcelFileHeaders();
            return receiverDAO.getReceivers();
        } catch (IOException | ExcelFileIsEmptyException | WrongNumberOfCellsException | CellIsNotStringException | WrongHeaderNamesException | EmptyCellException | HeadersDuplicateException | WrongExcelPropertiesException | ReceiversNotFoundException | WrongPropertiesFileException | PropertiesFileNotFoundException | NotOfficeXmlFileException e) {
            throw e;
        }
//        } catch (WrongSettingsFileException | SettingsFileNotFoundException e) {
//            throw e;
//        }
    }

//    private static String[] getExcelFileHeaders() throws WrongSettingsFileException, IOException, SettingsFileNotFoundException {
//        IConfigDAO configDAO = new ConfigDAOImpl();
//        IConfigService configService = new ConfigServiceImpl(configDAO);
//
//        String nameHeader = configService.getConfigInstance().getNameHeader();
//        String emailHeader = configService.getConfigInstance().getEmailHeader();
//        String folderHeader = configService.getConfigInstance().getFolderHeader();
//        String[] excelFileHeaders = {nameHeader, emailHeader, folderHeader};
//        return excelFileHeaders;
//
//    }
}
