package com.github.teofou.emailsenderfx.controllers;

import com.github.teofou.emailsenderfx.controllers.helpers.ReceiverTableData;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.util.Callback;

public class InfoReceiversCellFactory<S extends ReceiverTableData, T extends String> implements Callback<TableColumn<ReceiverTableData, String>, TableCell<ReceiverTableData, String>> {
    @Override
    public TableCell<ReceiverTableData, String> call(TableColumn<ReceiverTableData, String> receiverTableDataStringTableColumn) {
        return new TableCell<ReceiverTableData, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                TableRow<ReceiverTableData> currentRow = getTableRow();
                this.setDisable(false); // it is required to fit default state
                if (currentRow != null && !empty) {
                    setText(item);
                    if (currentRow.getItem() != null && (!currentRow.getItem().isReadyToSend())) {
                        this.setStyle("-fx-text-fill: #cc3232");
                    } else if (currentRow.getItem() != null && (currentRow.getItem().isReadyToSend()) && currentRow.getItem().getFolder().getFilesPathList().size() == 0){
                        this.setStyle("-fx-text-fill: #db7b2b");
                    } else {
                        //this.setTextFill(Color.GREEN);
                        this.setStyle("-fx-text-fill: #1A7520");
                    }
                } else {
                    this.setText(null);
                }
            }
        };
    }
}
