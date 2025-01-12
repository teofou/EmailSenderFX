package com.github.teofou.emailsenderfx.service;

import com.github.teofou.emailsenderfx.service.ecxeptions.PropertiesFileNotFoundException;

public interface IConfigExcelLocationService {
    String getExcelFilePath() throws PropertiesFileNotFoundException;
    boolean saveSelectedExcelFile(String excelFilePath);
}
