package com.github.teofou.emailsenderfx.controllers;

import com.github.teofou.emailsenderfx.controllers.helpers.ReceiverTableData;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.paint.Color;
import javafx.util.Callback;

public class CheckBoxTableCellFactory<S extends ReceiverTableData, T extends Boolean> implements Callback<TableColumn<S, T>, TableCell<S, T>> {
    public TableCell<S, T> call(TableColumn<S, T> param) {
        return new CheckBoxTableCell<S,T>() {
            @Override
            public void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                TableRow<S> currentRow = getTableRow();
                this.setDisable(false); // it is required to fit default state
                this.setOpacity(2.0);
                if (currentRow != null && !empty) {
                    if (currentRow.getItem() != null && (!currentRow.getItem().isReadyToSend())) {
                        this.setOpacity(0.3);
                        this.setDisable(true);
                    }
                } else {
                    this.setOpacity(1.0);
                    this.setDisable(false);
                }
            }
        };
    }

}
