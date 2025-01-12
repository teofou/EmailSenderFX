package com.github.teofou.emailsenderfx.DAO.dbutil;


import com.github.teofou.emailsenderfx.DAO.propertyfiles.PropertyUtil;
import com.github.teofou.emailsenderfx.service.ecxeptions.ExcelFileNotFoundException;
import com.github.teofou.emailsenderfx.service.ecxeptions.PropertiesFileNotFoundException;
import com.github.teofou.emailsenderfx.service.ecxeptions.WrongPropertiesFileException;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DBUtil {
    private static FileInputStream file;

    private DBUtil() {}

    public static Workbook openExcelFile() throws PropertiesFileNotFoundException, WrongPropertiesFileException, ExcelFileNotFoundException, NotOfficeXmlFileException {
//        String fileLocation = "G:/My Drive/myprograms/javaprograms/emails.xlsx";

        Properties excelFilePathProps = null;
        try {
            excelFilePathProps = getProperties(PropertyUtil.getUserPropertiesFilesLocation(), PropertyUtil.getExcelLocationPropertyFilename());
        } catch (PropertiesFileNotFoundException e) {
            throw e;
        }

        String fileLocation = excelFilePathProps.getProperty("excelFilePath");
        if (fileLocation.length() == 0) throw new WrongPropertiesFileException(PropertyUtil.getExcelLocationPropertyFilename(), "excelFilePath");
        try {
            file = new FileInputStream(fileLocation);
            Workbook workbook = new XSSFWorkbook(file);
            return workbook;
        } catch (IOException e) {
            throw new ExcelFileNotFoundException(fileLocation);
        }
        catch (NotOfficeXmlFileException e){
            //throw e;
            throw new ExcelFileNotFoundException(fileLocation);
        }

    }

    public static void closeExcelFile() throws IOException {
       if (file != null) file.close();
    }

    public static Map<String, String> getCorrectExcelHeaders() throws PropertiesFileNotFoundException, WrongPropertiesFileException {
        Map<String, String> correctExcelHeaders =new HashMap<>();
        try {
            Properties excelHeadersProps = getAppProperties(PropertyUtil.getExcelHeadersPropertyFilename());
            excelHeadersProps.forEach((headerKey, headerValue) -> {
                correctExcelHeaders.put(headerKey.toString(), headerValue.toString());
            });
            checkCorrectExcelHeaders(correctExcelHeaders);
            return correctExcelHeaders;
        } catch (PropertiesFileNotFoundException | WrongPropertiesFileException e) {
            throw e;
        }
    }


    private static Properties getProperties(String propertiesFileLocation, String propertiesFileName) throws PropertiesFileNotFoundException {
        Properties excelProperties = new Properties();
        try (InputStream input = new FileInputStream(propertiesFileLocation + propertiesFileName)) {
            excelProperties.load(input);
        } catch (IOException e) {
            throw new PropertiesFileNotFoundException(propertiesFileLocation + propertiesFileName);
        }
        return excelProperties;
    }

    private static Properties getAppProperties(String propertiesFileName) throws PropertiesFileNotFoundException {
        Properties excelProperties = new Properties();

        try (InputStream input = DBUtil.class.getClassLoader().getResourceAsStream(propertiesFileName)) {
            excelProperties.load(input);
        } catch (IOException e) {
            throw new PropertiesFileNotFoundException(propertiesFileName);
        }
        return excelProperties;
    }

    private static void checkCorrectExcelHeaders(Map<String, String> headers) throws WrongPropertiesFileException {
        if ((!headers.containsKey("nameHeader")) || headers.get("nameHeader") == null) throw new WrongPropertiesFileException(PropertyUtil.getExcelHeadersPropertyFilename(), "nameHeader");
        if ((!headers.containsKey("emailHeader")) || headers.get("emailHeader") == null) throw new WrongPropertiesFileException(PropertyUtil.getExcelHeadersPropertyFilename(), "emailHeader");
        if ((!headers.containsKey("folderHeader")) || headers.get("folderHeader") == null) throw new WrongPropertiesFileException(PropertyUtil.getExcelHeadersPropertyFilename(), "folderHeader");

    }

}
