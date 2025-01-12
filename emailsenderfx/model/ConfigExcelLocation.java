package com.github.teofou.emailsenderfx.model;

public class ConfigExcelLocation {


    private String excelFilePath;

    private static final ConfigExcelLocation CONFIG_EXCEL_LOCATION = new ConfigExcelLocation();

    private ConfigExcelLocation() {}

    public static ConfigExcelLocation getInstance() {
        return CONFIG_EXCEL_LOCATION;
    }

    public String getExcelFilePath() {
        return excelFilePath;
    }

    public void setExcelFilePath(String excelFilePath) {
        this.excelFilePath = excelFilePath;
    }


}
