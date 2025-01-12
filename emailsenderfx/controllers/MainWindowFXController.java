package com.github.teofou.emailsenderfx.controllers;

import com.github.teofou.emailsenderfx.DTO.EmailDataDTO;
import com.github.teofou.emailsenderfx.DTO.ReceiverDTO;
import com.github.teofou.emailsenderfx.controllers.helpers.Folder;
import com.github.teofou.emailsenderfx.controllers.helpers.ReceiverTableData;
import com.github.teofou.emailsenderfx.controllers.helpers.ResultTableData;
import com.github.teofou.emailsenderfx.model.Receiver;
import com.github.teofou.emailsenderfx.service.*;
import com.github.teofou.emailsenderfx.service.ecxeptions.*;
import javafx.beans.binding.StringBinding;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MainWindowFXController {



    IConfigExcelLocationService configExcelLocationService = new ConfigExcelLocationServiceImpl();
    IConfigFoldersService configFoldersService = new ConfigFoldersServiceImpl();
    IReceiverService receiverService = new ReceiverServiceImpl();
    IEmailSenderService emailSenderService = new EmailSenderServiceImpl();


    @FXML
    private TabPane tabPane;
    @FXML
    private Tab mainTab;
    @FXML
    private MenuBarController menuBarController;
    @FXML
    private MainFolderController mainFolderController;
    @FXML
    private SubfolderController subfolderController;
    @FXML
    private EmailDataController emailDataController;
    @FXML
    private ReceiversController receiversController;
    @FXML
    private EmailSendController emailSendController;
    @FXML
    private ResultsController resultsController;



    public void initialize() throws PropertiesFileNotFoundException, WrongNumberOfCellsException, ReceiversNotFoundException, EmptyCellException, WrongPropertiesFileException, WrongHeaderNamesException, ExcelFileNotFoundException, ExcelFileIsEmptyException, HeadersDuplicateException, IOException, CellIsNotStringException, WrongExcelPropertiesException {



        receiversController.isSelectedAtLeastOneProperty().addListener((v, oldVal, newVal) -> {
            emailSendController.getBtnSendEmail().setDisable(!newVal);
        });

        menuBarController.excelPathProperty().addListener((v, oldVal, newVal) -> {

            try {
                receiversController.shutDownAllWatchers();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //receiversController.clearCounters();
            receiversController.removeListeners();

            receiversController.getReceiversTableOl().clear();
//            ReceiversController.setTotalReadyToSendReceivers(0);
//            ReceiversController.setTotalSelectedReceivers(0);
            configExcelLocationService.saveSelectedExcelFile(newVal);

            subfolderController.getSubFolderCheckBox().setSelected(true);
            subfolderController.getCommonNameSubfolderCheckBox().setSelected(true);
            receiversController.getCheckBox().setSelected(true);
            //receiversController.getReceiversTableOl().setAll(FXCollections.observableArrayList());
            try {
                ReceiversController.setTotalReadyToSendReceivers(0);
                ReceiversController.setTotalSelectedReceivers(0);
                putReceiversInTable();

            } catch (WrongPropertiesFileException | ExcelFileNotFoundException | PropertiesFileNotFoundException | NullPointerException e) {
                e.printStackTrace();
                receiversController.getTableView().setPlaceholder(new Label("Δε βρέθηκε αρχείο excel"));
            } catch (WrongHeaderNamesException e) {
                e.printStackTrace();
                receiversController.getTableView().setPlaceholder(new Label("Σφάλμα στο αρχείο excel, η πρώτη σειρά πρέπει να περιέχει\nυποχρεωτικά τα εξής κελιά: NAME, EMAIL, FOLDER"));
            } catch (ReceiversNotFoundException e) {
                e.printStackTrace();
                receiversController.getTableView().setPlaceholder(new Label("Δε βρέθηκαν δεδομένα στο αρχείο"));
            } catch (CellIsNotStringException e) {
                e.printStackTrace();
                receiversController.getTableView().setPlaceholder(new Label("Σφάλμα στο αρχείο excel, η πρώτη σειρά δεν πρέπει να περιέχει κενά κελιά"));
            }
        });


        subfolderController.getSubFolderCheckBox().selectedProperty().addListener((v, oldVal, newVal) -> {
            Folder.allFilesInOneFolderProperty().set(!newVal);
        });

        subfolderController.getTextCommonNameSubfolder().textProperty().addListener((v, oldVal, newVal) -> configFoldersService.saveCommonNameSubfolder(newVal));

        mainFolderController.getTextMainFolderPath().textProperty().addListener((v, oldVal, newVal) -> {
            configFoldersService.saveMainFolderPath(newVal);
        });



        Folder.mainFolderPathProperty().bind(mainFolderController.getTextMainFolderPath().textProperty());
        Folder.commonNameSubfolderProperty().bind(new StringBinding() {
            {
                super.bind(subfolderController.getCommonNameSubfolderCheckBox().selectedProperty(), subfolderController.getTextCommonNameSubfolder().textProperty());
            }
            @Override
            protected String computeValue() {
                if (subfolderController.getCommonNameSubfolderCheckBox().isSelected()) return subfolderController.getTextCommonNameSubfolder().textProperty().getValue();
                return null;
            }
        });





        try {
            menuBarController.excelPathProperty().set((configExcelLocationService.getExcelFilePath() == null || configExcelLocationService.getExcelFilePath().equals("")) ? System.getProperty("user.home") : configExcelLocationService.getExcelFilePath());
        } catch (PropertiesFileNotFoundException e) {
            menuBarController.excelPathProperty().set(System.getProperty("user.home"));
        }

//        mainFolderController.getTextMainFolderPath().textProperty().addListener((v, oldVal, newVal) -> {
//            configFoldersService.saveMainFolderPath(newVal);
//        });

        try {
            mainFolderController.getTextMainFolderPath().setText((configFoldersService.getMainFolderPath() == null || configFoldersService.getMainFolderPath().equals("")) ? System.getProperty("user.home") : configFoldersService.getMainFolderPath());
        } catch (PropertiesFileNotFoundException e) {
            configFoldersService.saveMainFolderPath(System.getProperty("user.home"));
        }



//        subfolderController.getTextCommonNameSubfolder().textProperty().addListener((v, oldVal, newVal) -> configFoldersService.saveCommonNameSubfolder(newVal));

        try {
            subfolderController.getTextCommonNameSubfolder().setText((configFoldersService.getCommonNameSubfolder() == null) ? "" : configFoldersService.getCommonNameSubfolder());
        } catch (PropertiesFileNotFoundException e) {
            configFoldersService.saveCommonNameSubfolder("");
        }




        emailSendController.getBtnSendEmail().setOnMouseClicked(mouseEvent -> {





            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/github/teofou/emailsenderfx/results-tab.fxml"));

            try {
                Tab resultsTab = fxmlLoader.load();
                resultsController = fxmlLoader.getController();
                tabPane.getTabs().add(resultsTab);
                tabPane.getSelectionModel().select(resultsTab);

            } catch (IOException e) {
                e.printStackTrace();
            }


            System.out.println("emailController:");
            System.out.println(emailDataController.getTextAreaEmailBody().getText());
            EmailDataDTO emailDataDTO = new EmailDataDTO();
            emailDataDTO.setSubject(emailDataController.getTextFieldEmailSubject().getText().strip());
            emailDataDTO.setBody(emailDataController.getTextAreaEmailBody().getText().strip());
            System.out.println();
            System.out.println("emailDto body:");
            System.out.println(emailDataDTO.getBody());
            for (ReceiverTableData receiverTableData : receiversController.getReceiversTableOl()) {
                if (receiverTableData.isSelected()) {
                    ReceiverDTO receiverDTO = new ReceiverDTO();
                    receiverDTO.setName(receiverTableData.getName());
                    receiverDTO.setEmail(receiverTableData.getEmail());
                    receiverDTO.setFilesPathList(receiverTableData.getFolder().getFilesPathList());
                    ResultTableData resultTableData = new ResultTableData();
                    resultTableData.setName(receiverTableData.getName());
                    resultTableData.setEmail(receiverTableData.getEmail());
                    resultTableData.setReceiverFolder(receiverTableData.getFolder().getReceiverFolder());
                    resultTableData.setInfo("");
                    resultsController.getResultsTableOl().add(resultTableData);

                    try {
                        int filesSent = emailSenderService.sendEmailWithOutlookApp(receiverDTO, emailDataDTO);
                        switch (filesSent) {
                            case -1:
                                resultTableData.setInfo("Το email δε στάλθηκε");
                                break;
                            case 0:
                                resultTableData.setInfo("Το email στάλθηκε χωρίς αρχεία");
                                break;
                            case 1:
                                resultTableData.setInfo("Στάλθηκε " + filesSent + " αρχείο");
                                break;
                            default:
                                resultTableData.setInfo("Στάλθηκαν " + filesSent + " αρχεία");
                        }
                        //resultTableData.setInfo(emailSenderService.sendEmailWithOutlookApp(receiverDTO, emailDataDTO));
                        //emailSenderService.sendEmailWithOutlookApp(receiverDTO, emailDataDTO);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }


    private void putReceiversInTable() throws WrongPropertiesFileException, PropertiesFileNotFoundException, ExcelFileNotFoundException, WrongHeaderNamesException, ReceiversNotFoundException, CellIsNotStringException {
        List<Receiver> receivers = null;
//        Folder.mainFolderPathProperty().bind(mainFolderController.getTextMainFolderPath().textProperty());
//        Folder.commonNameSubfolderProperty().bind(new StringBinding() {
//            {
//                super.bind(subfolderController.getCheckBox().selectedProperty(), subfolderController.getTextCommonNameSubfolder().textProperty());
//            }
//            @Override
//            protected String computeValue() {
//                if (subfolderController.getCheckBox().isSelected()) return subfolderController.getTextCommonNameSubfolder().textProperty().getValue();
//                return null;
//            }
//        });
        try {
            receivers  = (ArrayList) receiverService.getAllReceivers();

            for (Receiver receiver : receivers) {

//                Folder folder = new Folder();
//                folder.setReceiverFolder(receiver.getFolder());
                //ReceiverTableData receiverTableData = new ReceiverTableData(receiver.getName(), receiver.getEmail(), folder);
                //receiversController.addReceiverToList(receiverTableData);
                receiversController.addReceiverToList(receiver);
                //receiversController.getReceiversTableOl().add(receiverTableData);
            }



        } catch (WrongPropertiesFileException | ExcelFileNotFoundException | PropertiesFileNotFoundException | NullPointerException e) {
            throw e;
            //receiversController.getTableView().setPlaceholder(new Label("Δε βρέθηκε αρχείο excel"));
        } catch (WrongHeaderNamesException e) {
            throw e;
            //receiversController.getTableView().setPlaceholder(new Label("Σφάλμα στο αρχείο excel, η πρώτη σειρά πρέπει να περιέχει\nυποχρεωτικά τα εξής κελιά: NAME, EMAIL, FOLDER"));
        } catch (ReceiversNotFoundException e) {
            throw e;
            //receiversController.getTableView().setPlaceholder(new Label("Δε βρέθηκαν δεδομένα στο αρχείο"));
        } catch (WrongNumberOfCellsException e) {
            e.printStackTrace();
        } catch (EmptyCellException e) {
            e.printStackTrace();
        } catch (ExcelFileIsEmptyException e) {
            e.printStackTrace();
        } catch (HeadersDuplicateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CellIsNotStringException e) {
            throw e;
            //receiversController.getTableView().setPlaceholder(new Label("Σφάλμα στο αρχείο excel, η πρώτη σειρά δεν πρέπει να περιέχει κενά κελιά"));
        } catch (WrongExcelPropertiesException e) {
            e.printStackTrace();
        }
    }
}
