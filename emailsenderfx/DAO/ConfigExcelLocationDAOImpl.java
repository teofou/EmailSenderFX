package com.github.teofou.emailsenderfx.DAO;

import com.github.teofou.emailsenderfx.DAO.propertyfiles.PropertyUtil;
import com.github.teofou.emailsenderfx.service.ecxeptions.PropertiesFileNotFoundException;

import java.io.*;
import java.util.Properties;
import org.apache.commons.io.FileUtils;

public class ConfigExcelLocationDAOImpl implements IConfigExcelLocationDAO {

    private static final ConfigExcelLocationDAOImpl CONFIG_EXCEL_LOCATION_DAO = new ConfigExcelLocationDAOImpl();

    private ConfigExcelLocationDAOImpl() {}

    public static ConfigExcelLocationDAOImpl getInstance() {
        return CONFIG_EXCEL_LOCATION_DAO;
    }


    @Override
    public String getExcelFilePath() throws PropertiesFileNotFoundException {
        try (InputStream input = new FileInputStream(PropertyUtil.getUserPropertiesFilesLocation() + PropertyUtil.getExcelLocationPropertyFilename())) {

            Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty("excelFilePath");
        } catch (IOException | IllegalArgumentException ex) {
            throw new PropertiesFileNotFoundException(PropertyUtil.getUserPropertiesFilesLocation() + PropertyUtil.getExcelLocationPropertyFilename());
        }
    }

    @Override
    public boolean updateOrCreateConfigExcelLocation(String excelFilePath) {
        try (OutputStream output = FileUtils.openOutputStream(new File(PropertyUtil.getUserPropertiesFilesLocation() + PropertyUtil.getExcelLocationPropertyFilename()))) {
            Properties prop = new Properties();
            prop.setProperty("excelFilePath", excelFilePath);
            prop.store(output, null);
            return true;
        } catch (IOException | IllegalArgumentException e) {
            return false;
        }
    }

//    @Override
//    public String getConfigExcelLocationInstance() {
//        try (InputStream input = new FileInputStream(PropertyUtil.getUserPropertiesFilesLocation() + PropertyUtil.getExcelLocationPropertyFilename())) {
//
//            Properties prop = new Properties();
//            prop.load(input);
//
//            // get the property value and print it out
//            System.out.println(prop.getProperty("db.url"));
//            System.out.println(prop.getProperty("db.user"));
//            System.out.println(prop.getProperty("db.password"));
//            ConfigExcelLocation.getInstance().setExcelFilePath(prop.getProperty("excelFilePath"));
//            return ConfigExcelLocation.getInstance().getExcelFilePath();
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }

//    public static void main(String[] args) throws IOException {
////        try (OutputStream output = new FileOutputStream(PropertyUtil.getUserPropertiesFilesLocation() + PropertyUtil.getExcelLocationPropertyFilename())) {
////            Properties prop = new Properties();
////            prop.setProperty("excelFilePath", System.getProperty("user.home"));
////            prop.store(output, null);
////        } catch (IOException | IllegalArgumentException e) {
////            e.printStackTrace();
////        }
//        OutputStream out = FileUtils.openOutputStream(new File(PropertyUtil.getUserPropertiesFilesLocation() + PropertyUtil.getExcelLocationPropertyFilename()));
//        FileUtils.openOutputStream(new File(PropertyUtil.getUserPropertiesFilesLocation() + PropertyUtil.getExcelLocationPropertyFilename()));
//        File f = new File("sadas");
//        OutputStream output = new FileOutputStream(FileUtils.openOutputStream(new File(PropertyUtil.getUserPropertiesFilesLocation() + PropertyUtil.getExcelLocationPropertyFilename())))
//    }
}
