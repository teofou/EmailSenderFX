package com.github.teofou.emailsenderfx.controllers;

import com.github.teofou.emailsenderfx.controllers.helpers.Folder;
import com.github.teofou.emailsenderfx.controllers.helpers.ReceiverTableData;
import com.github.teofou.emailsenderfx.model.Receiver;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.nio.file.Path;


public class ReceiversController {



    @FXML
    private TableView tableView;
    @FXML
    private CheckBox checkBox;
    @FXML
    private TableColumn<ReceiverTableData, Boolean> selectedCol;
    @FXML
    private TableColumn<ReceiverTableData, String> nameCol;
    @FXML
    private TableColumn<ReceiverTableData, String> emailCol;
    @FXML
    private TableColumn<ReceiverTableData, String> folderCol;
    @FXML
    private TableColumn<ReceiverTableData, String> infoCol;
    @FXML
    private TableColumn fullPathCol;
    @FXML
    private TableColumn<ReceiverTableData, Boolean> viewFolderBtnCol;

    private static ObservableList<ReceiverTableData> receiversTableOl;

    private static SimpleBooleanProperty isSelectedAtLeastOne;

    private static SimpleIntegerProperty totalSelectedReceivers;

    private static SimpleIntegerProperty totalReadyToSendReceivers;

    @FXML
    private HBox counterHBox;
    @FXML
    private Label lblTotalReceivers;
    @FXML
    private Label lblTotalReadyToSendReceivers;
    @FXML
    private Text textTotalReadyToSendReceivers;
    @FXML
    private Text textTotalReceivers;
    @FXML
    private Label lblSelectedReceivers;
    @FXML
    private Text textSelectedReceivers;




    public TableView<ReceiverTableData> getTableView() {
        return tableView;
    }

    public TableColumn<ReceiverTableData, Boolean> getSelectedCol() {
        return selectedCol;
    }

    public TableColumn<ReceiverTableData, String> getNameCol() {
        return nameCol;
    }

    public TableColumn<ReceiverTableData, String> getEmailCol() {
        return emailCol;
    }

    public TableColumn<ReceiverTableData, String> getFolderCol() {
        return folderCol;
    }

    public TableColumn<ReceiverTableData, String> getInfoCol() {
        return infoCol;
    }

    public TableColumn<ReceiverTableData, Boolean> getViewFolderBtnCol() {
        return viewFolderBtnCol;
    }

    public ObservableList<ReceiverTableData> getReceiversTableOl() {
        return receiversTableOl;
    }

    public void setReceiversTableOl(ObservableList<ReceiverTableData> receiversTableOl) {
        this.receiversTableOl = receiversTableOl;
    }

    public static int getTotalSelectedReceivers() {
        return totalSelectedReceivers.get();
    }

    public static SimpleIntegerProperty totalSelectedReceiversProperty() {
        return totalSelectedReceivers;
    }

    public static void setTotalSelectedReceivers(int totalSelectedReceivers) {
        ReceiversController.totalSelectedReceivers.set(totalSelectedReceivers);
    }

    public static int getTotalReadyToSendReceivers() {
        return totalReadyToSendReceivers.get();
    }

    public static SimpleIntegerProperty totalReadyToSendReceiversProperty() {
        return totalReadyToSendReceivers;
    }

    public static void setTotalReadyToSendReceivers(int totalReadyToSendReceivers) {
        ReceiversController.totalReadyToSendReceivers.set(totalReadyToSendReceivers);
    }

    public boolean isIsSelectedAtLeastOne() {
        return isSelectedAtLeastOne.get();
    }

    public SimpleBooleanProperty isSelectedAtLeastOneProperty() {
        return isSelectedAtLeastOne;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public void initialize() {



        totalSelectedReceivers = new SimpleIntegerProperty();

        totalReadyToSendReceivers = new SimpleIntegerProperty();

        isSelectedAtLeastOne = new SimpleBooleanProperty();

        tableView.setEditable(true);
        tableView.setStyle("-fx-selection-bar: rgba(181, 211, 231, 0.1)");

        selectedCol.setEditable(true);


//        tableView.setRowFactory((row) -> {
//
//            public void updateItem(Boolean item, boolean empty) {
//                super.updateItem(item, empty) ;
//                if (item == null) {
//                    setStyle("");
//                } else if (item.getInstrumentId().equals("1070")) {
//                    setStyle("-fx-background-color: tomato;");
//                } else {
//                    setStyle("");
//                }
//
//        });
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        folderCol.setCellFactory(TextFieldTableCell.forTableColumn());



//        infoCol.setCellFactory(column -> new TableCell<ReceiverTableData, String>() {
//            @Override
//            protected void updateItem(String item, boolean empty) {
//                super.updateItem(item, empty);
//                if (item == null || empty) {
//                    setText(null);
//                } else {
//                    setText(item);
//                    TableRow<ReceiverTableData> currentRow = getTableRow();
//                    if (currentRow != null) {
//                        if (!currentRow.getItem().isReadyToSend()) {
//                            this.setTextFill(Color.RED);
//                        } else {
//                            this.setTextFill(Color.GREEN);
//                        }
//                    }
//                }
//            }
//        });

        totalSelectedReceiversProperty().addListener((v, oldVal, newVal) -> {
            checkBox.setSelected(newVal.intValue() != 0 && newVal.intValue() == totalReadyToSendReceiversProperty().intValue());
        });

        Folder.getAllFilesPathList().addListener(new ListChangeListener<Path>() {
            @Override
            public void onChanged(Change<? extends Path> change) {
                if (change.next() && (Folder.allFilesInOneFolderProperty().getValue()) && receiversTableOl != null) {
                    //filesPathList.setAll(allFilesPathList.filtered(path -> Files.isRegularFile(path) && path.getFileName().toString().startsWith(ownerProperty().getValue())));
                    if (change.getAddedSize() > 0) {
                        for (Path path : change.getAddedSubList()) {
                            for (ReceiverTableData receiverTableData : receiversTableOl) {
                                if (path.toAbsolutePath().getFileName().toString().startsWith(receiverTableData.getName())) {
                                    receiverTableData.getFolder().getFilesPathList().add(path);
                                    break;
                                }
                            }
                        }
                    } else if (change.getRemovedSize() > 0 ){
                        for (Path path : change.getRemoved()) {
                            for (ReceiverTableData receiverTableData : receiversTableOl) {
                                if (path.toAbsolutePath().getFileName().toString().startsWith(receiverTableData.getName())) {
                                    receiverTableData.getFolder().getFilesPathList().removeIf(path1 -> path1.toAbsolutePath().toString().equals(path.toAbsolutePath().toString()));
                                    break;
                                }
                            }
                        }
                    }

                }

            }
        });

        receiversTableOl = FXCollections.observableArrayList();

//        receiversTableOl.addListener(new ListChangeListener<ReceiverTableData>() {
//            @Override
//            public void onChanged(Change<? extends ReceiverTableData> change) {
//
//
//                System.out.println("the list changed");
//
//                if (change.getList().size() > 0) {
//                    System.out.println(change.getList().get(change.getList().size()-1).getName() + " added");
//                    change.getList().get(change.getList().size()-1).selectedProperty().addListener((v, oldVal, newVal) -> {
//                        System.out.println(change.getList().get(change.getList().size()-1).getName() + " selected: " + newVal);
//                        int updateUnit = (newVal) ? +1 : -1;
//                        //textSelectedReceivers.textProperty().set(String.valueOf((Integer.parseInt(textSelectedReceivers.getText()) + updateUnit)));
//                        totalSelectedReceiversProperty().set(totalSelectedReceiversProperty().getValue() + updateUnit);
//
//                    });
//
//                    change.getList().get(change.getList().size()-1).readyToSendProperty().addListener((v, oldVal, newVal) -> {
//                        System.out.println("readychanged: " + newVal);
//                        int updateUnit = (newVal) ? +1 : -1;
//                        //textTotalReadyToSendReceivers.textProperty().set(String.valueOf((Integer.parseInt(textTotalReadyToSendReceivers.getText()) + updateUnit)));
//                        totalReadyToSendReceiversProperty().set(totalReadyToSendReceiversProperty().getValue() + updateUnit);
//                        change.getList().get(change.getList().size()-1).selectedProperty().set(!change.getList().get(change.getList().size()-1).selectedProperty().getValue());
//                        change.getList().get(change.getList().size()-1).selectedProperty().set(newVal);
//                        System.out.println(change.getList().get(change.getList().size()-1).getName() + " button is enabled: " + change.getList().get(change.getList().size()-1).getViewFolderBtnEnabled());
//                        change.getList().get(change.getList().size()-1).viewFolderBtnEnabledProperty().set(!change.getList().get(change.getList().size()-1).viewFolderBtnEnabledProperty().getValue());
//                        change.getList().get(change.getList().size()-1).viewFolderBtnEnabledProperty().set(newVal);
//
//                    });
//
//
//                }
//
//            }
//        });
//
//        for (ReceiverTableData receiverTableData : receiversTableOl) {
//            receiverTableData.selectedProperty().addListener((v, oldVal, newVal) -> {
//                int updateUnit = (newVal) ? +1 : -1;
//                textSelectedReceivers.textProperty().set(String.valueOf((Integer.parseInt(textSelectedReceivers.getText()) + updateUnit)));
//            });
//
//            receiverTableData.readyToSendProperty().addListener((v, oldVal, newVal) -> {
//                System.out.println("readychanged: " + newVal);
//                int updateUnit = (newVal) ? +1 : -1;
//                textTotalReadyToSendReceivers.textProperty().set(String.valueOf((Integer.parseInt(textTotalReadyToSendReceivers.getText()) + updateUnit)));
//                receiverTableData.selectedProperty().set(!receiverTableData.selectedProperty().getValue());
//                receiverTableData.selectedProperty().set(newVal);
//                receiverTableData.viewFolderBtnEnabledProperty().set(!receiverTableData.viewFolderBtnEnabledProperty().getValue());
//                receiverTableData.viewFolderBtnEnabledProperty().set(newVal);
//
//            });
//        }

        tableView.setItems(receiversTableOl);


//        ReceiverTableData.selectedReceiversCounterProperty().addListener((v, oldVal, newVal) -> {
//            if (newVal != null) isSelectedAtLeastOne.set((newVal.intValue() > 0));
//        });


        textTotalReceivers.textProperty().bind(new StringBinding() {
            {
                super.bind(receiversTableOl);
            }
            @Override
            protected String computeValue() {
                return Integer.toString(receiversTableOl.size());
            }
        });

        textTotalReadyToSendReceivers.textProperty().bind(new StringBinding() {
            {
                super.bind(totalReadyToSendReceiversProperty());
            }
            @Override
            protected String computeValue() {
                return Integer.toString(totalReadyToSendReceiversProperty().getValue());
            }
        });

        textSelectedReceivers.textProperty().bind(new StringBinding() {
            {
                super.bind(totalSelectedReceiversProperty());
            }
            @Override
            protected String computeValue() {
                return Integer.toString(totalSelectedReceiversProperty().getValue());
            }
        });

        //textTotalReadyToSendReceivers.textProperty().bind(ReceiverTableData.readyToSendReceiversCounterProperty().asString());



//        textSelectedReceivers.textProperty().addListener((v, oldVal, newVal) -> {
//            checkBox.setSelected(newVal.equals(textTotalReadyToSendReceivers.getText()));
//        });
        //checkBox.setSelected(true);
//        textSelectedReceivers.textProperty().bind(ReceiverTableData.selectedReceiversCounterProperty().asString());



    }

    public void checkBoxHandle() {
        boolean isSelected = checkBox.isSelected();
        for (ReceiverTableData receiverTableData : receiversTableOl) {
            receiverTableData.setSelected(receiverTableData.isReadyToSend() && isSelected);
        }
    }

    public void shutDownAllWatchers() throws IOException {
        if (receiversTableOl != null) {
            for (ReceiverTableData receiverTableData : receiversTableOl) {
                if (receiverTableData.getFolder().getFilesFolderWatcher() != null && receiverTableData.getFolder().getFilesFolderWatcher().getListening()) {
                    receiverTableData.getFolder().getFilesFolderWatcher().shutDownListener();
                }
            }
        }
    }

    public void clearCounters() {
        if (receiversTableOl != null) {
            for (ReceiverTableData receiverTableData : receiversTableOl) {
                receiverTableData.setName("");
                receiverTableData.setEmail("");


            }
        }


    }

    public void addReceiverToList(Receiver receiver) throws IOException {

        Folder folder = new Folder(receiver.getName());
        folder.setReceiverFolder(receiver.getFolder());

        ReceiverTableData receiverTableData = new ReceiverTableData(receiver.getName(), receiver.getEmail(), folder);
        int updateReady = (receiverTableData.isReadyToSend()) ? +1 : 0;
        totalReadyToSendReceiversProperty().set(totalReadyToSendReceiversProperty().getValue() + updateReady);
        receiverTableData.selectedProperty().set(receiverTableData.isReadyToSend());
        receiverTableData.viewFolderBtnEnabledProperty().set(receiverTableData.isReadyToSend());

        int updateSelected = (receiverTableData.isSelected()) ? +1 : 0;
        totalSelectedReceiversProperty().set(totalSelectedReceiversProperty().getValue() + updateSelected);



        ChangeListener<Boolean> selectedChangeListener = new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> v, Boolean oldVal, Boolean newVal) {
                if(newVal != null) {
                    int updateUnit = (newVal) ? +1 : -1;
                    //textSelectedReceivers.textProperty().set(String.valueOf((Integer.parseInt(textSelectedReceivers.getText()) + updateUnit)));
                    totalSelectedReceiversProperty().set(totalSelectedReceiversProperty().getValue() + updateUnit);
                }
            }
        };
        receiverTableData.setSelectedChangeListener(selectedChangeListener);
        receiverTableData.selectedProperty().addListener(receiverTableData.getSelectedChangeListener());


        ChangeListener<Boolean> readyChangeListener = new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> v, Boolean oldVal, Boolean newVal) {
                if (newVal != null) {

                    int updateUnit = (newVal) ? +1 : -1;

                    //textTotalReadyToSendReceivers.textProperty().set(String.valueOf((Integer.parseInt(textTotalReadyToSendReceivers.getText()) + updateUnit)));
                    totalReadyToSendReceiversProperty().set(totalReadyToSendReceiversProperty().getValue() + updateUnit);
                    receiverTableData.selectedProperty().set(!receiverTableData.selectedProperty().getValue());
                    receiverTableData.selectedProperty().set(newVal);
                    receiverTableData.viewFolderBtnEnabledProperty().set(!receiverTableData.viewFolderBtnEnabledProperty().getValue());
                    receiverTableData.viewFolderBtnEnabledProperty().set(newVal);
                }
            }
        };
        receiverTableData.setReadyChangeListener(readyChangeListener);
        receiverTableData.readyToSendProperty().addListener(receiverTableData.getReadyChangeListener());

//        receiverTableData.selectedProperty().addListener((v, oldVal, newVal) -> {
//            if(newVal != null) {
//                int updateUnit = (newVal) ? +1 : -1;
//                //textSelectedReceivers.textProperty().set(String.valueOf((Integer.parseInt(textSelectedReceivers.getText()) + updateUnit)));
//                totalSelectedReceiversProperty().set(totalSelectedReceiversProperty().getValue() + updateUnit);
//            }
//
//
//        });
//
//        receiverTableData.readyToSendProperty().addListener((v, oldVal, newVal) -> {
//
//            if (newVal != null) {
//                System.out.println(receiverTableData.getName() + " changed");
//
//                int updateUnit = (newVal) ? +1 : -1;
//
//                //textTotalReadyToSendReceivers.textProperty().set(String.valueOf((Integer.parseInt(textTotalReadyToSendReceivers.getText()) + updateUnit)));
//                totalReadyToSendReceiversProperty().set(totalReadyToSendReceiversProperty().getValue() + updateUnit);
//                receiverTableData.selectedProperty().set(!receiverTableData.selectedProperty().getValue());
//                receiverTableData.selectedProperty().set(newVal);
//                receiverTableData.viewFolderBtnEnabledProperty().set(!receiverTableData.viewFolderBtnEnabledProperty().getValue());
//                receiverTableData.viewFolderBtnEnabledProperty().set(newVal);
//            }
//
//
//
//        });



        receiversTableOl.add(receiverTableData);

    }

    public void removeListeners() {
        if (receiversTableOl != null) {
            for (ReceiverTableData receiverTableData : receiversTableOl) {
                receiverTableData.selectedProperty().removeListener(receiverTableData.getSelectedChangeListener());
                receiverTableData.readyToSendProperty().removeListener(receiverTableData.getReadyChangeListener());
            }
        }

    }


}
