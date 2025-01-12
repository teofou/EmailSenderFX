package com.github.teofou.emailsenderfx.controllers;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MenuBarController {
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu menu;
    @FXML
    private MenuItem menuItemSelectFile;
    @FXML
    private MenuItem menuItemExit;

    private SimpleStringProperty excelPath;

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public Menu getMenu() {
        return menu;
    }

    public MenuItem getMenuItemSelectFile() {
        return menuItemSelectFile;
    }

    public MenuItem getMenuItemExit() {
        return menuItemExit;
    }

    public String getExcelPath() {
        return excelPath.get();
    }

    public SimpleStringProperty excelPathProperty() {
        return excelPath;
    }

    public void setExcelPath(String excelPath) {
        this.excelPath.set(excelPath);
    }

    public void initialize() {
        excelPath = new SimpleStringProperty();
    }

    public void selectExcelFile() {
        File excelFile = new File(((excelPath.getValue() != null) && (Files.exists(Paths.get(excelPath.getValue())))) ? excelPath.getValue() : System.getProperty("user.home"));
        File dir = new File((Files.isDirectory(Paths.get(excelFile.getAbsolutePath()))) ? excelFile.getAbsolutePath() : excelFile.getAbsoluteFile().getParent());
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
        fileChooser.setInitialDirectory(dir);
        File file = fileChooser.showOpenDialog(menuBar.getScene().getWindow());
        if (file != null) excelPath.set(file.getAbsolutePath());


    }

    public void exitApp() {
        Platform.exit();
        System.exit(0);
    }
}
