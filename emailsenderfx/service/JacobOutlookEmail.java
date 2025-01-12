package com.github.teofou.emailsenderfx.service;//package service;
//
//import com.jacob.activeX.ActiveXComponent;
//import com.jacob.com.Dispatch;
//import org.apache.commons.io.FileUtils;
//
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.net.URL;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipInputStream;
//
//
//public class JacobOutlookEmail {
//
//    private ActiveXComponent ol;
//    private Dispatch oOutlook;
//    private Object email[] = new Object[1];
//
//
//    /**
//     * standard run loop
//     *
//     * @param asArgs command line arguments
//     * @throws Exception
//     */
//    public static void main(String asArgs[]) throws Exception {
////
////        System.setProperty("jacob.dll.path", "C:/Νέος φάκελος/jacob-1.18-x64.dll");
////        System.out.println(System.getProperty("java.home"));
//
//        System.setProperty("jacob.dll.path", System.getProperty("java.home") + "/bin/jacob-1.18-x64.dll");
//        System.out.println(System.getProperty("java.home"));
//
//        System.out.println("Outlook: IN");
//
//        JacobOutlookEmail jacobOutlook = new JacobOutlookEmail();
//
//        if (Files.exists(Path.of(System.getProperty("java.home") + "/bin/jacob-1.18-x64.dll"))) {
//            FileUtils.copyURLToFile(new URL("https://github.com/freemansoft/jacob-project/releases/download/Root_B-1_18/jacob-1.18.zip"), new File("C:/Νέος φάκελος/dsfds.zip"));
//            String fileZip = "C:/Νέος φάκελος/dsfds.zip";
//            File destDir = new File(System.getProperty("java.home") + "/bin/");
//            byte[] buffer = new byte[1024];
//            ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
//            ZipEntry zipEntry = zis.getNextEntry();
//            while (zipEntry != null) {
//                System.out.println(zipEntry.getName());
//                if (zipEntry.getName().equals("jacob-1.18/jacob-1.18-x64.dll")) {
//
//                }
//                File newFile = newFile(destDir, zipEntry);
////                if (zipEntry.isDirectory()) {
////                    if (!newFile.isDirectory() && !newFile.mkdirs()) {
////                        throw new IOException("Failed to create directory " + newFile);
////                    }
////                } else {
////                    // fix for Windows-created archives
////                    File parent = newFile.getParentFile();
////                    if (!parent.isDirectory() && !parent.mkdirs()) {
////                        throw new IOException("Failed to create directory " + parent);
////                    }
////
////                    // write file content
////                    FileOutputStream fos = new FileOutputStream(newFile);
////                    int len;
////                    while ((len = zis.read(buffer)) > 0) {
////                        fos.write(buffer, 0, len);
////                    }
////                    fos.close();
////                }
//                zipEntry = zis.getNextEntry();
//            }
//            zis.closeEntry();
//            zis.close();
//        }
//
//        ActiveXComponent axOutlook = null;
//        try {
//            axOutlook = new ActiveXComponent("Outlook.Application");
//        } catch (UnsatisfiedLinkError e) {
////            FileUtils.copyURLToFile(
////                    new URL(FILE_URL),
////                    new File(FILE_NAME),
////                    CONNECT_TIMEOUT,
////                    READ_TIMEOUT);
////            URL home = new URL("https://github.com");
////            URL url = new URL(home, "freemansoft/jacob-project/releases/download/Root_B-1_18/jacob-1.18.zip");
//            //FileUtils.copyURLToFile(new URL("https://github.com/freemansoft/jacob-project/releases/download/Root_B-1_18/jacob-1.18.zip"), new File("C:/Νέος φάκελος/dsfds.zip"));
//            e.printStackTrace();
//        }
//        try {
//            System.out.println("version=" + axOutlook.getProperty("Version"));
//
//            jacobOutlook.oOutlook = axOutlook.getObject();
//            System.out.println("version=" + Dispatch.get(jacobOutlook.oOutlook, "Version"));
//
//            Dispatch oNameSpace = axOutlook.getProperty("Session").toDispatch();
//            System.out.println("oNameSpace=" + oNameSpace);
//
//            String emailBody = "<HTML><BODY><p><b>Bold text</b></p>" +
//                    "<p><i>Italic text</i></p>" +
//                    "<p>Normal text</p>" +
//                    "</BODY></HTML>";
//            String emailSubject = "Email demo using Jacob";
//
//            String recipientTo = "teofousony@gmail.com";
////            String recipientCC = "Alias2@domain.com";
////            String recipientBCC = "Alias3@domain.com";
//
////            String[] attachments = new String[]{"D:\\temp.txt"};
//
//            jacobOutlook.createEmail(emailSubject, recipientTo, emailBody);
//
//        } finally {
////            Uncomment if you want close outlook after job is done
////            axOutlook.invoke("Quit", new Variant[]{});
//        }
//    }
//
//
//    public void createEmail(String subject, String recipientTo,  String body) {
//        Dispatch mail = Dispatch.call(oOutlook, "CreateItem", email).toDispatch();
//        Dispatch.put(mail, "Subject", subject);
//        Dispatch.put(mail, "To", recipientTo);
////        Dispatch.put(mail, "CC", recipientCC);
////        Dispatch.put(mail, "BCC", recipientBCC);
//
////        Use if sample text is used
////        Dispatch.put(mail, "Body", body);
//
//
//        Dispatch.put(mail, "HTMLBody", body);
//
//
//
////        if (attachments.length > 0) {
////            Dispatch attachs = Dispatch.get(mail, "Attachments").toDispatch();
////
////            for (Object attachment : attachments) {
////                Dispatch.call(attachs, "Add", attachment);
////            }
////        }
////        Save on D drive
////        Dispatch.call(mail, "SaveAs","D:\\JacobEmail.msg");
////        Display in outlook
//        Dispatch.call(mail, "Send");
//    }
//
//    public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
//        File destFile = new File(destinationDir, zipEntry.getName());
//
//        String destDirPath = destinationDir.getCanonicalPath();
//        String destFilePath = destFile.getCanonicalPath();
//
//        if (!destFilePath.startsWith(destDirPath + File.separator)) {
//            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
//        }
//
//        return destFile;
//    }
//
//}
