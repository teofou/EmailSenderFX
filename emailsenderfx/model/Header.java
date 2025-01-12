package com.github.teofou.emailsenderfx.model;

public class Header {

    private String name;
    private String email;
    private String folder;

    private static final Header HEADER = new Header();

    private Header() {}

    public static Header getInstance() {
        return HEADER;
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
}
