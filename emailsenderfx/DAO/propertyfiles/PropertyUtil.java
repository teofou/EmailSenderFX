package com.github.teofou.emailsenderfx.DAO.propertyfiles;



public class PropertyUtil {

    private static final String APP_PROPERTIES_FILES_LOCATION = System.getProperty("user.dir") + "/src/main/resources/properties/";
    private static final String USER_PROPERTIES_FILES_LOCATION = System.getProperty("user.home") + "/EmailSender/";
    //private static final String USER_PROPERTIES_FILES_LOCATION = Paths.get(System.getProperty("user.home"),"EmailSender/").toAbsolutePath().toString();
    private static final String EXCEL_LOCATION_PROPERTY_FILENAME = "excel-location.properties";
    private static final String EXCEL_HEADERS_PROPERTY_FILENAME = "excel-headers.properties";
    private static final String FOLDERS_LOCATION_PROPERTY_FILENAME = "folders-location.properties";

    private static final PropertyUtil PROPERTY_UTIL = new PropertyUtil();

    private PropertyUtil() {}


    public static String getAppPropertiesFilesLocation() {
        return APP_PROPERTIES_FILES_LOCATION;
    }

    public static String getUserPropertiesFilesLocation() {
        return USER_PROPERTIES_FILES_LOCATION;
    }


    public static String getExcelLocationPropertyFilename() {
        return EXCEL_LOCATION_PROPERTY_FILENAME;
    }

    public static String getExcelHeadersPropertyFilename() {
        return EXCEL_HEADERS_PROPERTY_FILENAME;
    }

    public static String getFoldersLocationPropertyFilename() {
        return FOLDERS_LOCATION_PROPERTY_FILENAME;
    }
}
