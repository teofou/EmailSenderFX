package com.github.teofou.emailsenderfx.service;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

public class OutlookWindowsAppEmailSender {

    private final String jacobDllPath;
    private ActiveXComponent outlookApp;
    private Dispatch oOutlook;
    private Dispatch mail;

    public OutlookWindowsAppEmailSender() {
        jacobDllPath = System.getProperty("java.home") + "/bin/jacob-1.18-x64.dll";
    }
}
