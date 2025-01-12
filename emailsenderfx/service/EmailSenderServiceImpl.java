package com.github.teofou.emailsenderfx.service;


import com.github.teofou.emailsenderfx.DAO.dbutil.DBUtil;
import com.github.teofou.emailsenderfx.DTO.EmailDataDTO;
import com.github.teofou.emailsenderfx.DTO.ReceiverDTO;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComFailException;
import com.jacob.com.Dispatch;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class EmailSenderServiceImpl implements IEmailSenderService{

//    private final IReceiverDAO receiverDAO;
//    private final IConfigDAO configDAO;

    public EmailSenderServiceImpl() {
//        this.receiverDAO = receiverDAO;
//        this.configDAO = configDAO;
    }
//
//    @Override
//    public ConfigFile getConfigInstance() throws WrongSettingsFileException, IOException, SettingsFileNotFoundException {
//        configDAO.getSettings();
//        return ConfigFile.getInstance();
//    }
//
//    @Override
//    public List<Receiver> getAllReceivers() throws IOException, ExcelFileIsEmptyException, WrongNumberOfCellsException, CellIsNotStringException, WrongHeaderNamesException, EmptyCellException, WrongSettingsFileException, SettingsFileNotFoundException {
//        try {
//            String[] excelFileHeaders = getExcelFileHeaders();
//            return receiverDAO.getReceivers(excelFileHeaders);
//        } catch (IOException | ExcelFileIsEmptyException | WrongNumberOfCellsException |
//                CellIsNotStringException | WrongHeaderNamesException | EmptyCellException e) {
//            throw e;
//        } catch (WrongSettingsFileException | SettingsFileNotFoundException e) {
//            throw e;
//        }
//    }

    @Override
    public int sendEmailWithOutlookApp(ReceiverDTO receiverDTO, EmailDataDTO emailDataDTO) throws IOException {

        int result= 0;

        if (!Files.exists(Paths.get(System.getProperty("user.home"), "EmailSender", "jacob-1.20-x64.dll"))){
            String fileName = "jacob-1.20-x64.dll";
            URL url = EmailSenderServiceImpl.class.getClassLoader().getResource(fileName);
            File destination = new File(System.getProperty("user.home") +"/EmailSender/" + fileName);
            try {
                FileUtils.copyURLToFile(url, destination);
            } catch (IOException e) {
                e.printStackTrace();
                return -1;
            }
        }


        System.setProperty("jacob.dll.path", System.getProperty("user.home") + "/EmailSender/jacob-1.20-x64.dll");
//        System.setProperty("jacob.dll.path", getClass().getClassLoader().getResourceAsStream("jacob-1.20-x64.dll"));
//        InputStream input = DBUtil.class.getClassLoader().getResourceAsStream(propertiesFileName)
        //System.setProperty("jacob.dll.path", System.getProperty("java.home") + "/bin/jacob-1.20-x64.dll");
        ActiveXComponent axOutlook = null;
        try {
            axOutlook = new ActiveXComponent("Outlook.Application");
        } catch (ComFailException e) {
            e.printStackTrace();
            return -1;
        }

        try {
            Dispatch disOutlook = axOutlook.getObject();
            Object email[] = new Object[1];
            Dispatch mail = Dispatch.call(disOutlook, "CreateItem", email).toDispatch();
            Dispatch.put(mail, "Subject", emailDataDTO.getSubject());
            Dispatch.put(mail, "To", receiverDTO.getEmail());
            //Dispatch.put(mail, "HTMLBody", (emailDataDTO.getBody() == null) ? "" : emailDataDTO.getBody());
            Dispatch.put(mail, "Body", (emailDataDTO.getBody() == null) ? "" : emailDataDTO.getBody());

            List<Path> attachmentsPathList = new ArrayList<>();
            for (Path path : receiverDTO.getFilesPathList()) {
                if (Files.exists(path)) attachmentsPathList.add(path);
            }
//        try (Stream<Path> stream = Files.walk(Paths.get(receiverDTO.getFolderPath()))) {
//            attachmentsPathList = stream.map(Path::normalize)
//                    .filter(Files::isRegularFile)
//                    .collect(Collectors.toList());
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw e;
//        }
            if (attachmentsPathList.size() > 0) {
                Dispatch attachs = Dispatch.get(mail, "Attachments").toDispatch();

                for (Path attachment : attachmentsPathList) {
                    Dispatch.call(attachs, "Add", attachment.toString());
                }
            }
            Dispatch.call(mail, "Send");
            result = attachmentsPathList.size();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

        return result;



    }

//    private String[] getExcelFileHeaders() throws WrongSettingsFileException, IOException, SettingsFileNotFoundException {
//        configDAO.getSettings();
//        System.out.println(ConfigFile.getInstance().getNameHeader());
//        String nameHeader = ConfigFile.getInstance().getNameHeader();
//        String emailHeader = ConfigFile.getInstance().getEmailHeader();
//        String folderHeader = ConfigFile.getInstance().getFolderHeader();
//        String[] excelFileHeaders = {nameHeader, emailHeader, folderHeader};
//        return excelFileHeaders;


//
//    }


}
