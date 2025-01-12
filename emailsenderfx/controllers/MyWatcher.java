package com.github.teofou.emailsenderfx.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.nio.file.StandardWatchEventKinds.*;

public class MyWatcher {
        private final WatchService watcher;
        private final Map keyPaths;
        private volatile Thread processingThread;


        SimpleStringProperty[]  changedFilePath;


    private Boolean listening;



    public MyWatcher() throws IOException {
        watcher = FileSystems.getDefault().newWatchService();
        keyPaths = new ConcurrentHashMap();
        changedFilePath =  new SimpleStringProperty[2];
        changedFilePath[0] =new SimpleStringProperty();
        changedFilePath[1] = new SimpleStringProperty();
        listening = false;
    }

    public Boolean getListening() {
        return listening;
    }

    public void setListening(Boolean listening) {
        this.listening = listening;
    }

    public SimpleStringProperty[] getChangedFilePath() {
        return changedFilePath;
    }

    public void setChangedFilePath(SimpleStringProperty[] changedFilePath) {
        this.changedFilePath = changedFilePath;
    }

    public void watchDir(Path p) throws IOException {
            //Path p = dir.toPath();
            WatchKey key = p.register(watcher, ENTRY_CREATE, ENTRY_DELETE);
            keyPaths.put(key, p);
    }

    private void processFileNotifications() throws InterruptedException {
        while (true) {
            try {
                WatchKey key = watcher.take();
                Path dir = (Path) keyPaths.get(key);
                for (WatchEvent evt : key.pollEvents()) {
                    WatchEvent.Kind eventType = evt.kind();
                    if (eventType == OVERFLOW)
                        continue;
                    Object o = evt.context();
                    if (o instanceof Path) {
                        Path path = (Path) o;
                        process(dir, path, eventType);
                    } else {
                    }
                }
                key.reset();
            } catch (ClosedWatchServiceException e){
                break;
            }

        }

    }

    public void startListening() {



        processingThread = new Thread() {
            public void run() {
                try {
                    //listening = true;
                    processFileNotifications();
                } catch (InterruptedException ex) {
                    processingThread = null;
                    listening =  false;
                }

            }
        };
        processingThread.start();
    }

    public void shutDownListener() throws IOException {
        Thread thr = processingThread;
        if (thr != null) {
            watcher.close();
            thr.interrupt();

        }
        //listening = false;
    }

    private void process(Path dir, Path file, WatchEvent.Kind evtType) {


        changedFilePath[0].set(evtType.name());
        changedFilePath[1].set(file.toAbsolutePath().toString());

        changedFilePath[0].set("");

        changedFilePath[1].set("");



//        try (Stream<Path> stream = Files.walk(dir, 1)) {
//            filesPathList.setAll(stream.map(Path::normalize)
//                    .filter(!usingCriteriaProperty().getValue() ? Files::isRegularFile : path -> Files.isRegularFile(path) && path.getFileName().toString().startsWith(criteriaProperty().getValue()))
//                    .collect(Collectors.toList()));
//        } catch (IOException | UncheckedIOException e) {
//            e.printStackTrace();
//        }
        //System.out.println("type: " + evtType.name() + ", file: " + file.getFileName().toString());
//        try (Stream<Path> stream = Files.walk(dir, 1)) {
//            filesPathList.setAll(stream.map(Path::normalize)
//                    .filter(Files::isRegularFile)
//                    .collect(Collectors.toList()));
//        } catch (IOException | UncheckedIOException e) {
//            e.printStackTrace();
//        }
//        if (filesPathList != null) {
////            System.out.println();
////            System.out.println(evtType.name());
////            System.out.print("before: ");
////            filesPathList.forEach(path -> System.out.print(path.toString() + ", "));
//            if (evtType.name().equals("ENTRY_DELETE")) {
////                System.out.print("before delete: ");
////                filesPathList.forEach(path -> System.out.print(path.getFileName() + ", "));
//                filesPathList.removeIf(path -> path.getFileName().toString().equals(file.getFileName().toString()));
////                System.out.println();
////                System.out.print("after delete: ");
////                filesPathList.forEach(path -> System.out.print(path.getFileName() + ", "));
//            } else if (evtType.name().equals("ENTRY_CREATE")) {
//
//                filesPathList.add(file.toAbsolutePath());
//            }
//            System.out.println();
//            System.out.print("after: ");
//            filesPathList.forEach(path -> System.out.print(path.toString() + ", "));

//        if (evtType.name().equals("ENTRY_DELETE")) {
//
////                System.out.print("before delete: ");
////                filesPathList.forEach(path -> System.out.print(path.getFileName() + ", "));
//                filesPathList.removeIf(path -> path.getFileName().toString().equals(file.getFileName().toString()));
////                System.out.println();
////                System.out.print("after delete: ");
////                filesPathList.forEach(path -> System.out.print(path.getFileName() + ", "));
//            } else if (evtType.name().equals("ENTRY_CREATE")) {
//
//                filesPathList.add(file.toAbsolutePath());
//
//            }
//        }





    }

}
