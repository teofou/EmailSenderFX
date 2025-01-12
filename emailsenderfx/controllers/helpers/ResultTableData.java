package com.github.teofou.emailsenderfx.controllers.helpers;

import javafx.beans.property.SimpleStringProperty;

public class ResultTableData {
    private SimpleStringProperty name;
    private SimpleStringProperty email;
    private SimpleStringProperty receiverFolder;
    private SimpleStringProperty info;

    public ResultTableData() {
        this.name = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.receiverFolder = new SimpleStringProperty();
        this.info = new SimpleStringProperty();
    }

    public ResultTableData(ReceiverTableData receiverTableData) {
        this.name = new SimpleStringProperty(receiverTableData.getName());
        this.email = new SimpleStringProperty(receiverTableData.getEmail());
        this.receiverFolder = new SimpleStringProperty(receiverTableData.getFolder().getReceiverFolder());
        this.info = new SimpleStringProperty();
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getReceiverFolder() {
        return receiverFolder.get();
    }

    public SimpleStringProperty receiverFolderProperty() {
        return receiverFolder;
    }

    public void setReceiverFolder(String receiverFolder) {
        this.receiverFolder.set(receiverFolder);
    }

    public String getInfo() {
        return info.get();
    }

    public SimpleStringProperty infoProperty() {
        return info;
    }

    public void setInfo(String info) {
        this.info.set(info);
    }
}
