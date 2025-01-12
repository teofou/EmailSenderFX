package com.github.teofou.emailsenderfx.controllers.helpers;

import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import org.apache.commons.validator.routines.EmailValidator;

import java.nio.file.*;

public class ReceiverTableData {


    private SimpleBooleanProperty selected;
    private SimpleStringProperty name;
    private SimpleStringProperty email;
    private Folder folder;
    private SimpleStringProperty info;
//    private SimpleStringProperty mainFolderPath;
//    private SimpleStringProperty commonNameSubFolder;
    private SimpleBooleanProperty readyToSend;
    private SimpleStringProperty receiverFolderFullPath;
//    private ObservableList<Path> filesPathList;
//    private MyWatcher myWatcher;
//    private static final SimpleIntegerProperty readyToSendReceiversCounter = new SimpleIntegerProperty(0);
//    private static final SimpleIntegerProperty selectedReceiversCounter = new SimpleIntegerProperty(0);

    private SimpleBooleanProperty viewFolderBtnEnabled;

    private ChangeListener<Boolean> selectedChangeListener;
    private ChangeListener<Boolean> readyChangeListener;

    public ReceiverTableData() {}

    public ReceiverTableData(String name, String email, Folder folder) {

        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.folder = folder;
        this.receiverFolderFullPath = new SimpleStringProperty();
        this.info = new SimpleStringProperty();
        this.selected = new SimpleBooleanProperty();
        this.readyToSend = new SimpleBooleanProperty();
        this.viewFolderBtnEnabled = new SimpleBooleanProperty();


        infoProperty().addListener((v, oldVal, newVal) -> {
            if (newVal != null) {
                readyToSendProperty().set(!readyToSendProperty().getValue());
                readyToSendProperty().set(newVal.startsWith("Ετοιμο προς αποστολή"));

            }

//            selectedProperty().set(!selectedProperty().getValue());
//            selectedProperty().set(newVal.equals("Ετοιμο προς αποστολή"));
        });

        folder.ownerProperty().bind(nameProperty());

        infoProperty().bind(new StringBinding() {
            {
                super.bind( emailProperty(), folder.absolutePathProperty(), folder.getFilesPathList(), nameProperty());
            }
            @Override
            protected String computeValue() {
                return getReceiverTableInfo(emailProperty().getValue(), folder);
            }
        });



//        selectedProperty().bind(new BooleanBinding() {
//            {
//                super.bind(infoProperty());
//            }
//            @Override
//            protected boolean computeValue() {
//                return info.getValue().equals("Ετοιμο προς αποστολή");
//            }
//        });

//        readyToSendProperty().addListener((v, oldVal, newVal) -> {
//            int updateUnit = (newVal) ? +1 : -1;
//            //readyToSendReceiversCounterProperty().set(readyToSendReceiversCounterProperty().getValue() + updateUnit);
//            selectedProperty().set(!selectedProperty().getValue());
//            selectedProperty().set(newVal);
//            //receiverFolderFullPathProperty().set((newVal) ? folder.absolutePathProperty().getValue() : null);
//            viewFolderBtnEnabledProperty().set(!viewFolderBtnEnabledProperty().getValue());
//            viewFolderBtnEnabledProperty().set(newVal);
//
//        });

//        selectedProperty().addListener((v, oldVal, newVal) -> {
//            int updateUnit = (newVal) ? +1 : -1;
//            selectedReceiversCounterProperty().set(selectedReceiversCounterProperty().getValue() + updateUnit);
//        });

        receiverFolderFullPathProperty().bind(new StringBinding() {
            {
                super.bind(readyToSendProperty(), folder.absolutePathProperty());
            }
            @Override
            protected String computeValue() {
                if (!readyToSendProperty().getValue()) return null;
                return folder.absolutePathProperty().getValue();
            }
        });



    }

    public String getReceiverFolderFullPath() {
        return receiverFolderFullPath.get();
    }

    public void setReceiverFolderFullPath(String receiverFolderFullPath) {
        this.receiverFolderFullPath.set(receiverFolderFullPath);
    }

    public boolean isSelected() {
        return selected.get();
    }

    public SimpleBooleanProperty selectedProperty() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
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

//    public String getFolder() {
//        return folder.get();
//    }
//
//    public SimpleStringProperty folderProperty() {
//        return folder;
//    }
//
//    public void setFolder(String folder) {
//        this.folder.set(folder);
//    }

    public String getInfo() {
        return info.get();
    }

    public SimpleStringProperty infoProperty() {
        return info;
    }

    public void setInfo(String info) {
        this.info.set(info);
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    public SimpleStringProperty receiverFolderProperty() {
        return folder.receiverFolderProperty();
    }

    public SimpleStringProperty receiverFolderFullPathProperty() {
        return receiverFolderFullPath;
    }
    //    public String getMainFolderPath() {
//        return mainFolderPath.get();
//    }
//
//    public SimpleStringProperty mainFolderPathProperty() {
//        return mainFolderPath;
//    }
//
//    public void setMainFolderPath(String mainFolderPath) {
//        this.mainFolderPath.set(mainFolderPath);
//    }
//
//    public String getCommonNameSubFolder() {
//        return commonNameSubFolder.get();
//    }
//
//    public SimpleStringProperty commonNameSubFolderProperty() {
//        return commonNameSubFolder;
//    }
//
//    public void setCommonNameSubFolder(String commonNameSubFolder) {
//        this.commonNameSubFolder.set(commonNameSubFolder);
//    }

    public boolean isReadyToSend() {
        return readyToSend.get();
    }

    public SimpleBooleanProperty readyToSendProperty() {
        return readyToSend;
    }

    public void setReadyToSend(boolean readyToSend) {
        this.readyToSend.set(readyToSend);
    }

//    public ObservableList<Path> getFilesPathList() {
//        return filesPathList;
//    }
//
//    public void setFilesPathList(ObservableList<Path> filesPathList) {
//        this.filesPathList = filesPathList;
//    }

//    public String getFolderFullAbsolutePath() {
//        return folderFullAbsolutePath.get();
//    }
//
//    public SimpleStringProperty folderFullAbsolutePathProperty() {
//        return folderFullAbsolutePath;
//    }
//
//    public void setFolderFullAbsolutePath(String folderFullAbsolutePath) {
//        this.folderFullAbsolutePath.set(folderFullAbsolutePath);
//    }






    public boolean getViewFolderBtnEnabled() {
        return viewFolderBtnEnabled.get();
    }

    public SimpleBooleanProperty viewFolderBtnEnabledProperty() {
        return viewFolderBtnEnabled;
    }

    public void setViewFolderBtnEnabled(boolean viewFolderBtnEnabled) {
        this.viewFolderBtnEnabled.set(viewFolderBtnEnabled);
    }

//    public static int getReadyToSendReceiversCounter() {
//        return readyToSendReceiversCounter.get();
//    }
//
//    public static SimpleIntegerProperty readyToSendReceiversCounterProperty() {
//        return readyToSendReceiversCounter;
//    }
//
//    public static int getSelectedReceiversCounter() {
//        return selectedReceiversCounter.get();
//    }
//
//    public static SimpleIntegerProperty selectedReceiversCounterProperty() {
//        return selectedReceiversCounter;
//    }


    public ChangeListener<Boolean> getSelectedChangeListener() {
        return selectedChangeListener;
    }

    public void setSelectedChangeListener(ChangeListener<Boolean> selectedChangeListener) {
        this.selectedChangeListener = selectedChangeListener;
    }

    public ChangeListener<Boolean> getReadyChangeListener() {
        return readyChangeListener;
    }

    public void setReadyChangeListener(ChangeListener<Boolean> readyChangeListener) {
        this.readyChangeListener = readyChangeListener;
    }

    private static String getReceiverTableInfo(String email, Folder folder) {

        String mainFolderPath = Folder.getMainFolderPath();
        Boolean allFilesInOneFolder = Folder.areAllFilesInOneFolder();
        String receiverFolder = folder.getReceiverFolder();
        String commonNameSubfolder = Folder.getCommonNameSubfolder();

        boolean mainFolderPathIsValid = true;

        boolean folderIsValid = true;
        boolean commonNameSubfolderIsValid = true;
        boolean mainFolderPathExists = true;
        boolean receiverFolderExists = true;
        boolean commonNameSubfolderExists = true;
        StringBuilder sbInfo = new StringBuilder();
        if (!EmailValidator.getInstance().isValid(email)) buildInfo(sbInfo, "Το email δεν είναι εγκυρο");
        if (mainFolderPath == null || mainFolderPath.equals("")) {
            buildInfo(sbInfo, "Δεν έχει γίνει επιλογή κεντρικού φακέλου");
            mainFolderPathIsValid = false;
        }
        if (!allFilesInOneFolder) {
            if (receiverFolder == null || receiverFolder.equals("")) {
                buildInfo(sbInfo, "To πεδίο ΦΑΚΕΛΟΣ είναι κενό");
                folderIsValid = false;
            }
            if (commonNameSubfolder != null && commonNameSubfolder.equals("")) {
                buildInfo(sbInfo, "Δεν έχει γίνει επιλογή ονόματος υποφακέλου");
                commonNameSubfolderIsValid = false;
            }
            if (mainFolderPathIsValid && !Files.isDirectory(Paths.get(mainFolderPath))) {
                buildInfo(sbInfo, "Ο κεντρικός φάκελος " + mainFolderPath + "δε βρέθηκε");
                mainFolderPathExists = false;
            }

            if (mainFolderPathExists && folderIsValid && !Files.isDirectory(Paths.get(mainFolderPath, receiverFolder))) {
                buildInfo(sbInfo, "Ο φάκελος " + receiverFolder + " δε βρέθηκε");
                receiverFolderExists = false;
            }

            if (folderIsValid && receiverFolderExists && commonNameSubfolder != null && commonNameSubfolderIsValid  && !Files.isDirectory(Paths.get(mainFolderPath ,receiverFolder, commonNameSubfolder))) {
                buildInfo(sbInfo, "Ο φάκελος " + commonNameSubfolder + " δε βρέθηκε");
            }
        }

        if (sbInfo.length() != 0) return sbInfo.toString();

        //return (folder.getFilesPathList().size() == 0) ? sbInfo.append("Ετοιμο προς αποστολή, δε βρέθηκαν αρχεία στο φάκελο").toString() : sbInfo.append("Ετοιμο προς αποστολή, ").append(folder.getFilesPathList().size()).append(" αρχεία βρέθηκαν").toString();

        switch (folder.getFilesPathList().size()) {
            case (0):
                return sbInfo.append("Ετοιμο προς αποστολή, δε βρέθηκαν αρχεία στο φάκελο").toString();
            case (1):
                return sbInfo.append("Ετοιμο προς αποστολή, ").append(folder.getFilesPathList().size()).append(" αρχείο βρέθηκε").toString();
            default:
                return sbInfo.append("Ετοιμο προς αποστολή, ").append(folder.getFilesPathList().size()).append(" αρχεία βρέθηκαν").toString();
        }

    }

    private static void buildInfo(StringBuilder sbInfo, String message) {
        sbInfo = (sbInfo.length() == 0) ? sbInfo.append(message) : sbInfo.append(", ").append(message);
    }
//
//    public static void onClear() {
//        selectedReceiversCounter.set(0);
//        readyToSendReceiversCounter.set(0);
//    }


    @Override
    public String toString() {
        return "name:" + getName() + ", email: " + getEmail() + ", folder: " + getFolder().getReceiverFolder() + ", absolutepath: " + getFolder().getAbsolutePath() + ", ready: "+ isReadyToSend() + ", selected: " +  isSelected() + ", info: " + getInfo();
    }
}
