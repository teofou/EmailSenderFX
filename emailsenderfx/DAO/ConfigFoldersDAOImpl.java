package com.github.teofou.emailsenderfx.DAO;



import com.github.teofou.emailsenderfx.DAO.propertyfiles.PropertyUtil;
import com.github.teofou.emailsenderfx.service.ecxeptions.PropertiesFileNotFoundException;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.Properties;

public class ConfigFoldersDAOImpl implements IConfigFoldersDAO {

    private static final ConfigFoldersDAOImpl CONFIG_FOLDERS_DAO = new ConfigFoldersDAOImpl();

    private ConfigFoldersDAOImpl() {}

    public static ConfigFoldersDAOImpl getInstance() {
        return CONFIG_FOLDERS_DAO;
    }


    @Override
    public String getMainFolderPath() throws PropertiesFileNotFoundException {
        try (InputStream input = new FileInputStream(PropertyUtil.getUserPropertiesFilesLocation() + PropertyUtil.getFoldersLocationPropertyFilename())) {

            Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty("mainFolderPath");
        } catch (IOException ex) {
            throw new PropertiesFileNotFoundException(PropertyUtil.getUserPropertiesFilesLocation() + PropertyUtil.getFoldersLocationPropertyFilename());
        }
    }

    @Override
    public String getCommonNameSubfolder() throws PropertiesFileNotFoundException {
        try (InputStream input = new FileInputStream(PropertyUtil.getUserPropertiesFilesLocation() + PropertyUtil.getFoldersLocationPropertyFilename())) {

            Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty("commonNameSubfolder");
        } catch (IOException | IllegalArgumentException ex) {
            throw new PropertiesFileNotFoundException(PropertyUtil.getUserPropertiesFilesLocation() + PropertyUtil.getFoldersLocationPropertyFilename());
        }
    }

    @Override
    public boolean updateOrCreateConfigMainFolderPath(String mainFolderPath) {
        try (OutputStream output = FileUtils.openOutputStream(new File(PropertyUtil.getUserPropertiesFilesLocation() + PropertyUtil.getFoldersLocationPropertyFilename()), true)) {
            Properties prop = new Properties();
            prop.setProperty("mainFolderPath", mainFolderPath);
            prop.store(output, null);
            return true;
        } catch (IOException | IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public boolean updateOrCreateConfigCommonNameSubfolder(String commonNameSubfolder) {
        try (OutputStream output = FileUtils.openOutputStream(new File(PropertyUtil.getUserPropertiesFilesLocation() + PropertyUtil.getFoldersLocationPropertyFilename()), true)) {
            Properties prop = new Properties();
            prop.setProperty("commonNameSubfolder", commonNameSubfolder);
            prop.store(output, null);
            return true;
        } catch (IOException io) {
            return false;
        }
    }
}
