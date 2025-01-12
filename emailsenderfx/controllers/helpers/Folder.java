package com.github.teofou.emailsenderfx.controllers.helpers;

import com.github.teofou.emailsenderfx.controllers.MyWatcher;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Folder {


    private static SimpleStringProperty mainFolderPath = new SimpleStringProperty();
    private static SimpleStringProperty commonNameSubfolder = new SimpleStringProperty();
    private static SimpleBooleanProperty allFilesInOneFolder = new SimpleBooleanProperty();
    private static MyWatcher mainFolderWatcher;
    private static ObservableList<Path> allFilesPathList = FXCollections.observableArrayList();

    static {
//        try {
//            mainFolderWatcher = new MyWatcher();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        mainFolderPathProperty().addListener((v, oldVal, newVal) -> {

            if (mainFolderWatcher != null && mainFolderWatcher.getListening()){
                try {
                    mainFolderWatcher.shutDownListener();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                mainFolderWatcher.setListening(false);
            }
            if ((allFilesInOneFolderProperty().getValue()) && newVal != null) {
                try (Stream<Path> stream = Files.walk(Paths.get(newVal), 1)) {
                    allFilesPathList.setAll(stream.map(Path::normalize)
                            .filter(Files::isRegularFile)
                            .collect(Collectors.toList()));

                    //.usingCriteriaProperty().bind(allFilesInOneFolderProperty());
                    //myWatcher.criteriaProperty().bind(ownerProperty());
                    mainFolderWatcher = new MyWatcher();
                    mainFolderWatcher.watchDir(Paths.get(newVal));
                    mainFolderWatcher.startListening();
                    mainFolderWatcher.getChangedFilePath()[1].addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                            if (!t1.equals("")) {
                                if (mainFolderWatcher.getChangedFilePath()[0].getValue().equals("ENTRY_DELETE")) {
                                    allFilesPathList.removeIf(path -> path.getFileName().toString().equals(Path.of(t1).getFileName().toString()));
                                } else if (mainFolderWatcher.getChangedFilePath()[0].getValue().equals("ENTRY_CREATE")) {
                                    allFilesPathList.add(Path.of(t1));
                                }
                            }

                        }
                    });

                    mainFolderWatcher.setListening(true);
                } catch (IOException e) {
                    if (mainFolderWatcher != null && mainFolderWatcher.getListening()) {
                        try {
                            mainFolderWatcher.shutDownListener();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        mainFolderWatcher.setListening(false);
                    }
                }
            }
        });


        allFilesInOneFolderProperty().addListener((v, oldVal, newVal) -> {

            if (mainFolderWatcher != null && mainFolderWatcher.getListening()){
                try {
                    mainFolderWatcher.shutDownListener();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                mainFolderWatcher.setListening(false);
            }
            if ((newVal) && mainFolderPath.getValue() != null) {
                try (Stream<Path> stream = Files.walk(Paths.get(mainFolderPath.getValue()), 1)) {
                    allFilesPathList.setAll(stream.map(Path::normalize)
                            .filter(Files::isRegularFile)
                            .collect(Collectors.toList()));

                    //.usingCriteriaProperty().bind(allFilesInOneFolderProperty());
                    //myWatcher.criteriaProperty().bind(ownerProperty());
                    mainFolderWatcher = new MyWatcher();
                    mainFolderWatcher.watchDir(Paths.get(mainFolderPath.getValue()));
                    mainFolderWatcher.startListening();
                    mainFolderWatcher.getChangedFilePath()[1].addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                            if (!t1.equals("")) {
                                if (mainFolderWatcher.getChangedFilePath()[0].getValue().equals("ENTRY_DELETE")) {
                                    allFilesPathList.removeIf(path -> path.getFileName().toString().equals(Path.of(t1).getFileName().toString()));
                                } else if (mainFolderWatcher.getChangedFilePath()[0].getValue().equals("ENTRY_CREATE")) {
                                    allFilesPathList.add(Path.of(t1));
                                }
                            }

                        }
                    });

                    mainFolderWatcher.setListening(true);
                } catch (IOException e) {
                    if (mainFolderWatcher != null && mainFolderWatcher.getListening()) {
                        try {
                            mainFolderWatcher.shutDownListener();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        mainFolderWatcher.setListening(false);
                    }
                }
            } else if ((!newVal) && mainFolderPath.getValue() != null) {

            }

        });
    }


    private SimpleStringProperty owner;
    private SimpleStringProperty receiverFolder;
    private SimpleStringProperty absolutePath;
    private ObservableList<Path> filesPathList;
    private MyWatcher filesFolderWatcher;



    public Folder(String owner) throws IOException {

        this.owner = new SimpleStringProperty(owner);
        receiverFolder = new SimpleStringProperty();
        absolutePath = new SimpleStringProperty();
        filesPathList = FXCollections.observableArrayList();
        filesFolderWatcher = new MyWatcher();

//        allFilesPathList.addListener(new ListChangeListener<Path>() {
//            @Override
//            public void onChanged(Change<? extends Path> change) {
//                if (change.next() && ownerProperty().getValue() != null && (allFilesInOneFolderProperty().getValue())) {
//                    //filesPathList.setAll(allFilesPathList.filtered(path -> Files.isRegularFile(path) && path.getFileName().toString().startsWith(ownerProperty().getValue())));
//                    if (change.getAddedSize() > 0) {
//                        for (Path path : change.getAddedSubList()) {
//                            if ()
//                        }
//                    }
//
//                }
//
//            }
//        });


//        allFilesInOneFolderProperty().addListener((v, oldVal, newVal) -> {
//            if (mainFolderWatcher != null && mainFolderWatcher.getListening()){
//                mainFolderWatcher.shutDownListener();
//                mainFolderWatcher.setListening(false);
//                System.out.println("mainwatcher shtdowned, main listening: " + mainFolderWatcher.getListening() );
//            }
//            if ((newVal) && mainFolderPath.getValue() != null) {
//                filesPathList.clear();
//                try (Stream<Path> stream = Files.walk(Paths.get(mainFolderPath.getValue()), 1)) {
//                    allFilesPathList.setAll(stream.map(Path::normalize)
//                            .filter(Files::isRegularFile)
//                            .collect(Collectors.toList()));
//
//                    //.usingCriteriaProperty().bind(allFilesInOneFolderProperty());
//                    //myWatcher.criteriaProperty().bind(ownerProperty());
//                    mainFolderWatcher.watchDir(Paths.get(mainFolderPath.getValue()));
//                    mainFolderWatcher.startListening(allFilesPathList);
//                    mainFolderWatcher.setListening(true);
//                    System.out.println("mainwatcher started, main listening: " + mainFolderWatcher.getListening() );
//                } catch (IOException e) {
//                    if (mainFolderWatcher != null && mainFolderWatcher.getListening()) {
//                        mainFolderWatcher.shutDownListener();
//                        mainFolderWatcher.setListening(false);
//                        System.out.println("exception mainwatcher shtdowned, main listening: " + mainFolderWatcher.getListening() );
//                    }
//                }
//            }
//        });




        absolutePathProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

                if (filesFolderWatcher != null && filesFolderWatcher.getListening()) {
                    try {
                        filesFolderWatcher.shutDownListener();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    filesFolderWatcher.setListening(false);
                }
                filesPathList.clear();
                if (!allFilesInOneFolderProperty().getValue()) {
                    if (t1 != null) {
                        try (Stream<Path> stream = Files.walk(Paths.get(t1), 1)) {
                            filesPathList.setAll(stream.map(Path::normalize)
                                    .filter(Files::isRegularFile)
                                    .collect(Collectors.toList()));
                            filesFolderWatcher = new MyWatcher();
                            filesFolderWatcher.watchDir(Paths.get(t1));
                            filesFolderWatcher.startListening();
                            filesFolderWatcher.getChangedFilePath()[1].addListener(new ChangeListener<String>() {
                                @Override
                                public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                                    if (!t1.equals("")) {
                                        if (filesFolderWatcher.getChangedFilePath()[0].getValue().equals("ENTRY_DELETE")) {
                                            filesPathList.removeIf(path -> path.getFileName().toString().equals(Path.of(t1).getFileName().toString()));
                                        } else if (filesFolderWatcher.getChangedFilePath()[0].getValue().equals("ENTRY_CREATE")) {
                                            filesPathList.add(Path.of(t1));
                                        }
                                    }

                                }
                            });
                            filesFolderWatcher.setListening(true);
                        } catch (IOException e) {
                            if (filesFolderWatcher != null && filesFolderWatcher.getListening()) {
                                try {
                                    filesFolderWatcher.shutDownListener();
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                                filesFolderWatcher.setListening(false);
                            }

                        }

                    }
                }

            }
        });
        ownerProperty().addListener((v, oldVal, newVal) -> {
//            if (absolutePathProperty().getValue() != null) {
//                try (Stream<Path> stream = Files.walk(Paths.get(absolutePathProperty().getValue()), 1)) {
//                    filesPathList.setAll(stream.map(Path::normalize)
//                            .filter(!(allFilesInOneFolderProperty().getValue()) ? Files::isRegularFile : path -> Files.isRegularFile(path) && path.getFileName().toString().startsWith(ownerProperty().getValue()))
//                            .collect(Collectors.toList()));
//                } catch (IOException e) {
//                    if (myWatcher != null) myWatcher.shutDownListener();
//                }
//            }

            if (absolutePathProperty().getValue() != null && (allFilesInOneFolderProperty().getValue())) {
                filesPathList.setAll(allFilesPathList.filtered(path -> Files.isRegularFile(path) && path.getFileName().toString().startsWith(newVal.toString())));
            }

        });



        absolutePathProperty().bind(new StringBinding() {
            {
                super.bind(mainFolderPathProperty(), receiverFolderProperty(), commonNameSubfolderProperty(), allFilesInOneFolderProperty());
            }
            @Override
            protected String computeValue() {
                if (mainFolderPathProperty().getValue() == null || mainFolderPathProperty().getValue().equals("")) return null;
                if (allFilesInOneFolderProperty().getValue()) return Paths.get(mainFolderPathProperty().getValue()).toAbsolutePath().toString();
                if (receiverFolderProperty().getValue() == null || receiverFolderProperty().getValue().equals("")) return null;
                if (commonNameSubfolderProperty().getValue() == null) return Paths.get(mainFolderPathProperty().getValue(), receiverFolderProperty().getValue()).toAbsolutePath().toString();
                if (commonNameSubfolderProperty().getValue().equals("")) return null;
                return Paths.get(mainFolderPathProperty().getValue() ,receiverFolderProperty().getValue(), commonNameSubfolderProperty().getValue()).toAbsolutePath().toString();

            }
        });


    }

//    public Folder(String mainFolderPath, String receiverFolder, String commonNameSubfolder) {
//        this.mainFolderPath = new SimpleStringProperty(mainFolderPath);
//        this.commonNameSubfolder = new SimpleStringProperty(commonNameSubfolder);
//        this.receiverFolder = new SimpleStringProperty(receiverFolder);
//        this.absolutePath = new SimpleStringProperty();
//        absolutePathProperty().bind(new StringBinding() {
//            {
//                super.bind(mainFolderPathProperty(), receiverFolderProperty(), commonNameSubfolderProperty());
//            }
//            @Override
//            protected String computeValue() {
//                if (mainFolderPathProperty().getValue() == null) return null;
//                if (receiverFolderProperty().getValue() == null && commonNameSubfolderProperty().getValue() == null) return mainFolderPathProperty().getValue();
//                if (receiverFolderProperty().getValue() == null) return null;
//                if (commonNameSubfolderProperty().getValue() == null) return mainFolderPathProperty().getValue() + "/" + receiverFolderProperty().getValue();
//                return mainFolderPathProperty().getValue() + "/" + receiverFolderProperty().getValue() + "/" + commonNameSubfolderProperty().getValue();
//            }
//        });
//    }


    public static String getMainFolderPath() {
        return mainFolderPath.get();
    }

    public static SimpleStringProperty mainFolderPathProperty() {
        return mainFolderPath;
    }

    public static void setMainFolderPath(String mainFolderPath) {
        Folder.mainFolderPath.set(mainFolderPath);
    }

    public static String getCommonNameSubfolder() {
        return commonNameSubfolder.get();
    }

    public static SimpleStringProperty commonNameSubfolderProperty() {
        return commonNameSubfolder;
    }

    public static void setCommonNameSubfolder(String commonNameSubfolder) {
        Folder.commonNameSubfolder.set(commonNameSubfolder);
    }

    public static boolean isAllFilesInOneFolder() {
        return allFilesInOneFolder.get();
    }

    public static MyWatcher getMainFolderWatcher() {
        return mainFolderWatcher;
    }

    public static void setMainFolderWatcher(MyWatcher mainFolderWatcher) {
        Folder.mainFolderWatcher = mainFolderWatcher;
    }

    public static ObservableList<Path> getAllFilesPathList() {
        return allFilesPathList;
    }

    public static void setAllFilesPathList(ObservableList<Path> allFilesPathList) {
        Folder.allFilesPathList = allFilesPathList;
    }

    public String getOwner() {
        return owner.get();
    }

    public SimpleStringProperty ownerProperty() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner.set(owner);
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

    public String getAbsolutePath() {
        return absolutePath.get();
    }

    public SimpleStringProperty absolutePathProperty() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath.set(absolutePath);
    }

    public ObservableList<Path> getFilesPathList() {
        return filesPathList;
    }

    public void setFilesPathList(ObservableList<Path> filesPathList) {
        this.filesPathList = filesPathList;
    }

    public static boolean areAllFilesInOneFolder() {
        return allFilesInOneFolder.get();
    }

    public static SimpleBooleanProperty allFilesInOneFolderProperty() {
        return allFilesInOneFolder;
    }

    public static void setAllFilesInOneFolder(boolean allFilesInOneFolder) {
        Folder.allFilesInOneFolder.set(allFilesInOneFolder);
    }

    public MyWatcher getFilesFolderWatcher() {
        return filesFolderWatcher;
    }

    public void setFilesFolderWatcher(MyWatcher filesFolderWatcher) {
        this.filesFolderWatcher = filesFolderWatcher;
    }


}
