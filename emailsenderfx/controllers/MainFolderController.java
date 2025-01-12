package com.github.teofou.emailsenderfx.controllers;

import com.github.teofou.emailsenderfx.service.ConfigFoldersServiceImpl;
import com.github.teofou.emailsenderfx.service.IConfigFoldersService;
import com.github.teofou.emailsenderfx.service.ecxeptions.PropertiesFileNotFoundException;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.PublicKey;


public class MainFolderController {



    @FXML
    private Label lblMainFolder;
    @FXML
    private Text textMainFolderPath;
    @FXML
    private Button btnChangeMainFolderPath;




    public Label getLblMainFolder() {
        return lblMainFolder;
    }

    public Text getTextMainFolderPath() {
        return textMainFolderPath;
    }

    public Button getBtnChangeMainFolderPath() {
        return btnChangeMainFolderPath;
    }



    public void initialize() {

    }

    public void changeMainFolderPath() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File(((!textMainFolderPath.getText().equals("")) && (Files.exists(Paths.get(textMainFolderPath.getText())))) ? textMainFolderPath.getText() : System.getProperty("user.home")));
        File file = directoryChooser.showDialog(btnChangeMainFolderPath.getScene().getWindow());
        if (file != null) textMainFolderPath.textProperty().set(file.getAbsolutePath());
    }


}
