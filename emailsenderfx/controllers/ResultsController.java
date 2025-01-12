package com.github.teofou.emailsenderfx.controllers;

import com.github.teofou.emailsenderfx.controllers.helpers.ResultTableData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ResultsController {
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn<ResultTableData, String> nameCol;
    @FXML
    private TableColumn<ResultTableData, String> emailCol;
    @FXML
    private TableColumn<ResultTableData, String> folderCol;
    @FXML
    private TableColumn<ResultTableData, String> infoCol;

    private ObservableList<ResultTableData> resultsTableOl;

    public TableView getTableView() {
        return tableView;
    }

    public void setTableView(TableView tableView) {
        this.tableView = tableView;
    }

    public TableColumn<ResultTableData, String> getNameCol() {
        return nameCol;
    }

    public void setNameCol(TableColumn<ResultTableData, String> nameCol) {
        this.nameCol = nameCol;
    }

    public TableColumn<ResultTableData, String> getEmailCol() {
        return emailCol;
    }

    public void setEmailCol(TableColumn<ResultTableData, String> emailCol) {
        this.emailCol = emailCol;
    }

    public TableColumn<ResultTableData, String> getFolderCol() {
        return folderCol;
    }

    public void setFolderCol(TableColumn<ResultTableData, String> folderCol) {
        this.folderCol = folderCol;
    }

    public TableColumn<ResultTableData, String> getInfoCol() {
        return infoCol;
    }

    public void setInfoCol(TableColumn<ResultTableData, String> infoCol) {
        this.infoCol = infoCol;
    }

    public ObservableList<ResultTableData> getResultsTableOl() {
        return resultsTableOl;
    }

    public void setResultsTableOl(ObservableList<ResultTableData> resultsTableOl) {
        this.resultsTableOl = resultsTableOl;
    }

    public void initialize() {
        this.resultsTableOl = FXCollections.observableArrayList();
//        System.out.println(resultsTableOl);
//        ResultTableData resultTableData = new ResultTableData();
//        resultTableData.setName("he");
//        resultTableData.setEmail("he");
//        resultTableData.setReceiverFolder("he");
//        resultTableData.setInfo("");
//        this.resultsTableOl.add(resultTableData);
        this.tableView.setItems(resultsTableOl);

    }
}
