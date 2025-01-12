package com.github.teofou.emailsenderfx.service;


import com.github.teofou.emailsenderfx.DAO.ConfigFoldersDAOImpl;
import com.github.teofou.emailsenderfx.DAO.IConfigFoldersDAO;
import com.github.teofou.emailsenderfx.service.ecxeptions.PropertiesFileNotFoundException;

public class ConfigFoldersServiceImpl implements IConfigFoldersService {

    private IConfigFoldersDAO configFoldersDAO = ConfigFoldersDAOImpl.getInstance();


    @Override
    public String getMainFolderPath() throws PropertiesFileNotFoundException {
        return configFoldersDAO.getMainFolderPath();
    }

    @Override
    public String getCommonNameSubfolder() throws PropertiesFileNotFoundException {
        return configFoldersDAO.getCommonNameSubfolder();
    }

    @Override
    public boolean saveMainFolderPath(String mainFolderPath) {
        return configFoldersDAO.updateOrCreateConfigMainFolderPath(mainFolderPath);
    }

    @Override
    public boolean saveCommonNameSubfolder(String commonNameSubfolder) {
        return configFoldersDAO.updateOrCreateConfigCommonNameSubfolder(commonNameSubfolder);
    }
}
