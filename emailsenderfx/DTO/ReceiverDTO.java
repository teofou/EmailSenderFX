package com.github.teofou.emailsenderfx.DTO;

import java.nio.file.Path;
import java.util.List;

public class ReceiverDTO {

    private String name;
    private String email;
    private List<Path> filesPathList;

    public ReceiverDTO() {}

    public ReceiverDTO(String name, String email) {
        this.name = name;
        this.email = email;
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

    public List<Path> getFilesPathList() {
        return filesPathList;
    }

    public void setFilesPathList(List<Path> filesPathList) {
        this.filesPathList = filesPathList;
    }

    @Override
    public String toString() {
        return "Receiver: " +
                "name = " + name + '\t' +
                " email = " + email + '\t';
    }
}
