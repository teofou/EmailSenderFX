package com.github.teofou.emailsenderfx.DTO;

public class ConfigDTO {

    private String excelFilePath;
    private String nameHeader;
    private String emailHeader;
    private String folderHeader;
    private String mainFolderPath;
    private String commonNameSubfolder;

    public String getExcelFilePath() {
        return excelFilePath;
    }

    public void setExcelFilePath(String excelFilePath) {
        this.excelFilePath = excelFilePath;
    }

    public String getNameHeader() {
        return nameHeader;
    }

    public void setNameHeader(String nameHeader) {
        this.nameHeader = nameHeader;
    }

    public String getEmailHeader() {
        return emailHeader;
    }

    public void setEmailHeader(String emailHeader) {
        this.emailHeader = emailHeader;
    }

    public String getFolderHeader() {
        return folderHeader;
    }

    public void setFolderHeader(String folderHeader) {
        this.folderHeader = folderHeader;
    }

    public String getMainFolderPath() {
        return mainFolderPath;
    }

    public void setMainFolderPath(String mainFolderPath) {
        this.mainFolderPath = mainFolderPath;
    }

    public String getCommonNameSubfolder() {
        return commonNameSubfolder;
    }

    public void setCommonNameSubfolder(String commonNameSubfolder) {
        this.commonNameSubfolder = commonNameSubfolder;
    }
}
