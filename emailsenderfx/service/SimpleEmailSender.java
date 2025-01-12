//package com.github.teofou.emailsenderfx.service;
//
//import org.apache.commons.mail.DefaultAuthenticator;
//import org.apache.commons.mail.Email;
//import org.apache.commons.mail.SimpleEmail;
//
//
//public class SimpleEmailSender {
//
//    private static final String HOST = "smtp.office365.com";
//    private static final int PORT = 587;
//    private static final boolean SSL_FLAG = false;
//
//    public static void main(String[] args) {
//        SimpleEmailSender sender = new SimpleEmailSender();
//        sender.sendSimpleEmail();
//    }
//
//    private void sendSimpleEmail() {
//
//        String userName = "teofou13@outlook.com";
//        String password = "pao28192!";
//
//        String fromAddress="teofou13@outlook.com";
//        String toAddress =  "teofou13@outlook.com";
//        String subject = "Test cc Mail";
//        String message = "Hello from Apache Mail";
//
//        try {
//            Email email = new SimpleEmail();
//            email.setHostName(HOST);
////            email.setPopBeforeSmtp(true, "smtp.office365.com", "teofou13@outlook.com", "pao28192!");
//            email.setSmtpPort(PORT);
//            email.setAuthenticator(new DefaultAuthenticator(userName, password));
//            email.setSSLOnConnect(SSL_FLAG);
//            email.setStartTLSEnabled(true);
//            email.setFrom(fromAddress);
//            email.setSubject(subject);
//            email.setMsg(message);
//            email.addTo(toAddress);
//            email.send();
////            email.addTo("teofou13@outlook.com");
////            email.send();
//
////            email.getMailSession().;
////            URLName urlName = "";
////            email.setPopBeforeSmtp();
////            Folder folder = email.getMailSession().getFolder;
////
////            Session session = email.getMailSession();
////            Store store = session.getStore("imaps");
////            store.connect("imap-mail.outlook.com", "username", "password");
////            Folder folder = store.getFolder("Sent Items");
////            folder.open(Folder.READ_WRITE);
////            message.setFlag(Flag.SEEN, true);
////            folder.appendMessages(new Message[] {message});
////            store.close();
//        }catch(Exception ex){
//            System.out.println("Unable to send email");
//            System.out.println(ex);
//        }
//    }
//}
