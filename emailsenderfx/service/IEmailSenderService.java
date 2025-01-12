package com.github.teofou.emailsenderfx.service;


import com.github.teofou.emailsenderfx.DTO.EmailDataDTO;
import com.github.teofou.emailsenderfx.DTO.ReceiverDTO;

import java.io.IOException;

public interface IEmailSenderService {
//    ConfigFile getConfigInstance() throws WrongSettingsFileException, IOException, SettingsFileNotFoundException;
//
//    List<Receiver> getAllReceivers() throws IOException, ExcelFileIsEmptyException,
//            WrongNumberOfCellsException, CellIsNotStringException, WrongHeaderNamesException, EmptyCellException, WrongSettingsFileException, SettingsFileNotFoundException;

    int sendEmailWithOutlookApp(ReceiverDTO receiverDTO, EmailDataDTO emailDataDTO) throws IOException;

}
