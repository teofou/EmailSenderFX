package com.github.teofou.emailsenderfx.service;//package service;
//
//import DAO.ConfigDAOImpl;
//import DAO.IConfigDAO;
//import DTO.ConfigDTO;
//import model.ConfigFile;
//import service.ecxeptions.WrongSettingsFileException;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//
//public class ConfigServiceImpl implements IConfigService {
//
////    private final IConfigDAO configDAO;
////
////    public ConfigServiceImpl(IConfigDAO configDAO) {
////        this.configDAO = configDAO;
////    }
//
//    private IConfigDAO configDAO = ConfigDAOImpl.getInstance();
//
//    @Override
//    public ConfigFile getConfigInstance() throws WrongSettingsFileException, IOException {
//        configDAO.readInstance();
//        return ConfigFile.getInstance();
//    }
//
//    @Override
//    public void setDefaultConfigSettings() throws FileNotFoundException {
//        setDefaultConfigInstance();
//        configDAO.createOrUpdate(ConfigFile.getInstance());
//    }
//
//    public void updateConfigSettings(ConfigDTO configDTO) throws FileNotFoundException {
//        ConfigFile.getInstance().setExcelFilePath(configDTO.getExcelFilePath());
//        ConfigFile.getInstance().setNameHeader(configDTO.getNameHeader());
//        ConfigFile.getInstance().setFolderHeader(configDTO.getFolderHeader());
//        ConfigFile.getInstance().setEmailHeader(configDTO.getEmailHeader());
//        ConfigFile.getInstance().setMainFolderPath(configDTO.getMainFolderPath());
//        ConfigFile.getInstance().setCommonNameSubfolder(configDTO.getCommonNameSubfolder());
//
//        configDAO.createOrUpdate(ConfigFile.getInstance());
//    }
//
//    @Override
//    public void getConfigSettings() throws WrongSettingsFileException, IOException {
//        configDAO.readInstance();
//    }
//
//    private static void setDefaultConfigInstance() {
//        ConfigFile.getInstance().setExcelFilePath("G:/My Drive/myprograms/javaprograms/emails.xlsx");
//        ConfigFile.getInstance().setNameHeader("ΕΠΩΝΥΜΙΑ");
//        ConfigFile.getInstance().setEmailHeader("email");
//        ConfigFile.getInstance().setFolderHeader("ΦΑΚΕΛΟΣ");
//        ConfigFile.getInstance().setMainFolderPath(System.getProperty("user.home"));
//        ConfigFile.getInstance().setCommonNameSubfolder("ΤΡΕΧΩΝ ΜΗΝΑΣ");
//    }
//
//}
