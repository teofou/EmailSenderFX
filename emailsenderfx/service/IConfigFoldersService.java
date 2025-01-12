package com.github.teofou.emailsenderfx.service;


import com.github.teofou.emailsenderfx.service.ecxeptions.PropertiesFileNotFoundException;

public interface IConfigFoldersService {
    String getMainFolderPath() throws PropertiesFileNotFoundException;
    String getCommonNameSubfolder() throws PropertiesFileNotFoundException;
    boolean saveMainFolderPath(String mainFolderPath);
    boolean saveCommonNameSubfolder(String commonNameSubfolder);
}
