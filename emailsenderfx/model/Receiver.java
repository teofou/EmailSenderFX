package com.github.teofou.emailsenderfx.model;

public class Receiver {


    private String name;
    private String email;
    private String folder;


    public Receiver() {}

    public Receiver(String name, String email, String folder) {
        this.name = name;
        this.email = email;
        this.folder = folder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }


    @Override
    public String toString() {
        return "Receiver: " +
                "name = " + name + '\t' +
                " email = " + email + '\t' +
                " folder = " + folder;
    }
}
