package com.github.teofou.emailsenderfx.controllers;

import com.github.teofou.emailsenderfx.controllers.helpers.Folder;
import com.github.teofou.emailsenderfx.controllers.helpers.ReceiverTableData;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.util.Callback;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;



public class ButtonCellFactory<S extends ReceiverTableData, T extends Boolean> implements Callback<TableColumn<ReceiverTableData, Boolean>, TableCell<ReceiverTableData, Boolean>> {


    @Override
    public TableCell<ReceiverTableData, Boolean> call(TableColumn<ReceiverTableData, Boolean> receiverTableDataVoidTableColumn) {
        final TableCell<ReceiverTableData, Boolean> cell = new TableCell<ReceiverTableData, Boolean>() {

            private final Button btn = new Button("Προβολή");

            {
                btn.setOnAction((ActionEvent event) -> {
                    ReceiverTableData receiverTableData = getTableView().getItems().get(getIndex());
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setInitialDirectory(new File(receiverTableData.getFolder().absolutePathProperty().getValue()));
                    if (Folder.areAllFilesInOneFolder()) {
                        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("files", receiverTableData.getName() + "*.*"));
                    }

                    File file = fileChooser.showOpenDialog(btn.getScene().getWindow());
                    if (file != null) {
                        try {
                            Desktop.getDesktop().open(file);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }


                });
            }

            @Override
            public void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btn);
                    TableRow<ReceiverTableData> currentRow = getTableRow();
                    this.setDisable(false); // it is required to fit default state
                    if (currentRow != null && !empty) {
                        if (currentRow.getItem() != null && (!currentRow.getItem().isReadyToSend())) {
                            this.setDisable(true);
                        }
                    } else {
                        this.setDisable(false);
                    }
                }

            }
        };
        return cell;
    }
}
