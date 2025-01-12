package com.github.teofou.emailsenderfx.DAO;

import com.github.teofou.emailsenderfx.service.ecxeptions.PropertiesFileNotFoundException;

public interface IConfigExcelLocationDAO {

    String getExcelFilePath() throws PropertiesFileNotFoundException;
    boolean updateOrCreateConfigExcelLocation(String excelFilePath);
//    ConfigExcelLocation getConfigExcelLocationInstance();
}
