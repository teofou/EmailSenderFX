package com.github.teofou.emailsenderfx.service;


import com.github.teofou.emailsenderfx.DAO.ConfigExcelLocationDAOImpl;
import com.github.teofou.emailsenderfx.DAO.IConfigExcelLocationDAO;
import com.github.teofou.emailsenderfx.service.ecxeptions.PropertiesFileNotFoundException;

public class ConfigExcelLocationServiceImpl implements IConfigExcelLocationService {

    private IConfigExcelLocationDAO configExcelLocationDAO = ConfigExcelLocationDAOImpl.getInstance();


    @Override
    public String getExcelFilePath() throws PropertiesFileNotFoundException {
        return configExcelLocationDAO.getExcelFilePath();
    }

    @Override
    public boolean saveSelectedExcelFile(String excelFilePath) {
        return configExcelLocationDAO.updateOrCreateConfigExcelLocation(excelFilePath);
    }
}
