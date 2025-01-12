package com.github.teofou.emailsenderfx.DAO;//package DAO;
//
//
//import service.ecxeptions.WrongSettingsFileException;
//
//import java.io.*;
//
//public class ConfigDAOImpl implements IConfigDAO {
//
//    private static final ConfigDAOImpl configDAO = new ConfigDAOImpl();
//
//    private ConfigDAOImpl() {}
//
//    public static ConfigDAOImpl getInstance() {
//        return configDAO;
//    }
//
////    @Override
////    public void writeDefaultSettings() throws FileNotFoundException {
////
//////        PrintWriter pwConfig = new PrintWriter(new FileOutputStream(ConfigUtil.getConfigFileNamePath()));
//////        pwConfig.println("nameHeader: " + ConfigDefaultValue.getNameHeader());
//////        pwConfig.println("emailHeader: " + ConfigDefaultValue.getEmailHeader());
//////        pwConfig.println("folderHeader: " + ConfigDefaultValue.getFolderHeader());
//////        pwConfig.println("mainFolderPath: " + ConfigDefaultValue.getMainFolderPath());
//////        pwConfig.println("commonNameSubfolder: " + ConfigDefaultValue.getCommonNameSubfolder());
//////        pwConfig.close();
////
////        PrintWriter pwConfig = null;
////        try {
////             pwConfig = new PrintWriter(new FileOutputStream(ConfigUtil.getConfigFileNamePath()));
////        } catch (FileNotFoundException e) {
////            throw e;
////        }
////
////
////        pwConfig.println("nameHeader: " + ConfigDefaultValue.getNameHeader());
////        pwConfig.println("emailHeader: " + ConfigDefaultValue.getEmailHeader());
////        pwConfig.println("folderHeader: " + ConfigDefaultValue.getFolderHeader());
////        pwConfig.println("mainFolderPath: " + ConfigDefaultValue.getMainFolderPath());
////        pwConfig.println("commonNameSubfolder: " + ConfigDefaultValue.getCommonNameSubfolder());
////        pwConfig.close();
////    }
//
//    @Override
//    public void createOrUpdate(ConfigFile newConfigFile) throws FileNotFoundException {
//        PrintWriter pwConfig = null;
//        try {
//            pwConfig = new PrintWriter(new FileOutputStream(ConfigUtil.getConfigFileNamePath()));
//        } catch (FileNotFoundException e) {
//            throw e;
//        }
//
//        pwConfig.println("excelFilePath: " + newConfigFile.getExcelFilePath());
//        pwConfig.println("nameHeader: " + newConfigFile.getNameHeader());
//        pwConfig.println("emailHeader: " + newConfigFile.getEmailHeader());
//        pwConfig.println("folderHeader: " + newConfigFile.getFolderHeader());
//        pwConfig.println("mainFolderPath: " + newConfigFile.getMainFolderPath());
//        pwConfig.println("commonNameSubfolder: " + newConfigFile.getCommonNameSubfolder());
//        pwConfig.close();
//    }
//
//    @Override
//    public void readInstance() throws IOException, WrongSettingsFileException {
//
////        if (!Files.exists(Path.of(ConfigUtil.getConfigFileNamePath()))) {
//////            ConfigUtil.createConfigFile();
//////            PrintWriter pwConfig = new PrintWriter(new FileOutputStream(ConfigUtil.getConfigFileNamePath()));
//////            setDefaultSettings(pwConfig);
//////            pwConfig.close();
////            throw new SettingsFileNotFoundException();
////        }
//
//        BufferedReader bfConfig = new BufferedReader(new FileReader(ConfigUtil.getConfigFileNamePath()));
//
//        String line = bfConfig.readLine();
//
//        if (line == null) throw new WrongSettingsFileException();
//        while (line != null) {
//            String[] splitted = line.split(" ", 2);
//            if (splitted.length < 2) {
////                setDefaultSettings(new PrintWriter(new FileOutputStream(ConfigUtil.getConfigFileNamePath())));
//                throw new WrongSettingsFileException();
//            }
//            switch (splitted[0]) {
//                case "excelFilePath:":
//                    ConfigFile.getInstance().setExcelFilePath(splitted[1]);
//                    break;
//                case "nameHeader:":
//                    ConfigFile.getInstance().setNameHeader(splitted[1]);
//                    break;
//                case "emailHeader:":
//                    ConfigFile.getInstance().setEmailHeader(splitted[1]);
//                    break;
//                case "folderHeader:":
//                    ConfigFile.getInstance().setFolderHeader(splitted[1]);
//                    break;
//                case "mainFolderPath:":
//                    ConfigFile.getInstance().setMainFolderPath(splitted[1]);
//                    break;
//                case "commonNameSubfolder:":
//                    ConfigFile.getInstance().setCommonNameSubfolder(splitted[1]);
//                    break;
//                default:
//                    throw new WrongSettingsFileException();
//            }
//            line = bfConfig.readLine();
//        }
//    }
//
//
//}
