package com.github.teofou.emailsenderfx.DAO;

import com.github.teofou.emailsenderfx.service.ecxeptions.PropertiesFileNotFoundException;


public interface IConfigFoldersDAO {
    String getMainFolderPath() throws PropertiesFileNotFoundException;
    String getCommonNameSubfolder() throws PropertiesFileNotFoundException;
    boolean updateOrCreateConfigMainFolderPath(String mainFolderPath);
    boolean updateOrCreateConfigCommonNameSubfolder(String commonNameSubfolder);
}
