package com.github.teofou.emailsenderfx.model;

public class ConfigFolders {

    private String mainFolderPath;
    private String commonNameSubfolder;

    private static final ConfigFolders CONFIG_FOLDERS_LOCATION = new ConfigFolders();

    public static ConfigFolders getInstance() {
        return CONFIG_FOLDERS_LOCATION;
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
