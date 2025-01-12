package com.github.teofou.emailsenderfx.controllers;

import java.io.IOException;
import java.nio.file.*;

public class MyWatchkey implements Runnable{
    private Path path;

    public MyWatchkey(Path path) {
        this.path = path;
    }

    @Override
    public void run() {
        WatchService watchService = null;
        try {
            watchService = FileSystems.getDefault().newWatchService();
        } catch (IOException e) {
            e.printStackTrace();
        }
        WatchKey watchKey = null;
        try {
            watchKey = path.register(
                    watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            for (WatchEvent<?> event : watchKey.pollEvents()) {
                System.out.println(
                        "Event kind:" + event.kind()
                                + ". File affected: " + event.context() + ".");
            }
            boolean valid = watchKey.reset();
            if (!valid) {
                break;
            }
        }
    }
}
