package com.github.teofou.emailsenderfx.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EmailDataController {

    @FXML
    private Label lblEmailSubject;

    @FXML
    private TextField textFieldEmailSubject;

    @FXML
    private Label lblEmailBody;

    @FXML
    private TextArea textAreaEmailBody;

    public Label getLblEmailSubject() {
        return lblEmailSubject;
    }

    public void setLblEmailSubject(Label lblEmailSubject) {
        this.lblEmailSubject = lblEmailSubject;
    }

    public TextField getTextFieldEmailSubject() {
        return textFieldEmailSubject;
    }

    public void setTextFieldEmailSubject(TextField textFieldEmailSubject) {
        this.textFieldEmailSubject = textFieldEmailSubject;
    }

    public Label getLblEmailBody() {
        return lblEmailBody;
    }

    public void setLblEmailBody(Label lblEmailBody) {
        this.lblEmailBody = lblEmailBody;
    }

    public TextArea getTextAreaEmailBody() {
        return textAreaEmailBody;
    }

    public void setTextAreaEmailBody(TextArea textAreaEmailBody) {
        this.textAreaEmailBody = textAreaEmailBody;
    }
}
