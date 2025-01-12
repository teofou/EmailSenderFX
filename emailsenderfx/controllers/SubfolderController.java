package com.github.teofou.emailsenderfx.controllers;


import com.github.teofou.emailsenderfx.controllers.helpers.Folder;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.nio.file.Path;

public class SubfolderController {

    @FXML
    private HBox hBox;
    @FXML
    private CheckBox commonNameSubfolderCheckBox;
    @FXML
    private Label lblCommonNameSubfolderHeader;
    @FXML
    private Text textCommonNameSubfolder;
    @FXML
    private TextField textFieldCommonNameSubfolder;
    @FXML
    private Button btnChangeCommonNameSubfolder;
    @FXML
    private CheckBox subFolderCheckBox;



//    public IConfigFoldersService getConfigFoldersService() {
//        return configFoldersService;
//    }


    public CheckBox getCommonNameSubfolderCheckBox() {
        return commonNameSubfolderCheckBox;
    }

    public HBox gethBox() {
        return hBox;
    }

    public CheckBox getSubFolderCheckBox() {
        return subFolderCheckBox;
    }

    public Label getLblCommonNameSubfolderHeader() {
        return lblCommonNameSubfolderHeader;
    }

    public Text getTextCommonNameSubfolder() {
        return textCommonNameSubfolder;
    }

    public TextField getTextFieldCommonNameSubfolder() {
        return textFieldCommonNameSubfolder;
    }

    public Button getBtnChangeCommonNameSubfolder() {
        return btnChangeCommonNameSubfolder;
    }

    public void setSubFolderCheckBox(CheckBox subFolderCheckBox) {
        this.subFolderCheckBox = subFolderCheckBox;
    }

    public void initialize() {

//        Folder.getAllFilesPathList().addListener(new ListChangeListener<Path>() {
//            @Override
//            public void onChanged(Change<? extends Path> change) {
//                subFolderCheckBox.getScene().setCursor(Cursor.WAIT);
//            }
//
//        });

        commonNameSubfolderCheckBox.selectedProperty().addListener((v, oldVal, newVal) -> {
            textCommonNameSubfolder.setOpacity((newVal) ? 1.0 : 0.5);
            lblCommonNameSubfolderHeader.setOpacity((newVal) ? 1.0 : 0.5);
            textFieldCommonNameSubfolder.setDisable(!newVal);
        });

        subFolderCheckBox.selectedProperty().addListener((v, oldVal, newVal) -> {
            commonNameSubfolderCheckBox.setSelected(newVal);
            hBox.setDisable(!newVal);
        });
//
//        hBox.disableProperty().addListener((v, oldVal, newVal) -> {
//            //textCommonNameSubfolder.setOpacity((newVal) ? 0.3 : (commonNameSubfolderCheckBox.isSelected()) ? 1.0 : 0.5);
//            if (newVal) textCommonNameSubfolder.setOpacity(0.5);
//        });



//        subFolderCheckBox.setSelected(true);
//        commonNameSubfolderCheckBox.setSelected(true);

    }

//    public void commonNameSubfolderCheckBoxHandle(ActionEvent actionEvent) {
//        textCommonNameSubfolder.setOpacity((commonNameSubfolderCheckBox.isSelected()) ? 1.0 : 0.5);
//        lblCommonNameSubfolderHeader.setOpacity((commonNameSubfolderCheckBox.isSelected()) ? 1.0 : 0.5);
//        textFieldCommonNameSubfolder.setDisable(!commonNameSubfolderCheckBox.isSelected());
//    }

    public void changeCommonNameSubfolder() {
        textCommonNameSubfolder.textProperty().set(textFieldCommonNameSubfolder.getText());
        textFieldCommonNameSubfolder.clear();

    }



}
