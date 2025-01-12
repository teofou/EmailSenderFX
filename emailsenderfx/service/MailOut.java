package com.github.teofou.emailsenderfx.service;//package service;
//
//import com.ibm.rational.test.lt.kernel.services.ITestExecutionServices;
//import com.jacob.activeX.ActiveXComponent;
//import com.jacob.com.Dispatch;
//import java.util.HashMap;
//import com.
//import java.util.Map;
//
//public class MailOut implements com.ibm.rational.test.lt.kernel.custom.ICustomCode2 {
//
//    Map<String, Object> params = new HashMap<String, Object>();
//    String attachment[] = new String[1];
//    String to[] = new String[1];
//
//    public MailOut() {
//    }
//
//    public String exec(ITestExecutionServices tes, String[] args) {
//        //---------------------------------------------------------------------
//        params.put("subject", "Test subject");
//        params.put("body", "Please see attached.");
//
//        attachment[0] = "C:\\temp\\about_blank.pdf";
//        params.put("attachments", attachment);
//
//        to[0] = "me@here.com";
//        params.put("to", to);
//
//        OutlookJACOB mail = new OutlookJACOB();
//        mail.createEmail(params);
//        //---------------------------------------------------------------------
//
//        return "";
//    }
//
//    public class OutlookJACOB
//    {
//        private ActiveXComponent ol;
//        private Dispatch outlook;
//        private Object mapi[] = new Object[1];
//        private Object email[] = new Object[1];
//
//        public OutlookJACOB()
//        {
//            mapi[0] = "MAPI";
//            email[0] = 0;
//
//            ol = new ActiveXComponent("Outlook.Application");
//            //ol.setProperty("Visible", new Variant(true));
//            outlook = ol.getObject();
//            Dispatch.call(outlook,"GetNamespace",mapi).toDispatch();
//        }
//
//        public void createEmail(Map<String, Object> params)
//        {
//            Dispatch mail = Dispatch.call(outlook,"CreateItem",email).toDispatch();
//            Dispatch.put(mail, "Subject", params.get("subject"));
//            Dispatch.put(mail, "HTMLBody", params.get("body"));
//
//            String to[] = (String[]) params.get("to");
//            String attachments[] = (String[]) params.get("attachments");
//
//            if(to != null)
//            {
//                if(to.length>0)
//                {
//                    String _to = "";
//
//                    for(Object t : to)
//                    {
//                        _to += t + ";";
//                    }
//
//                    Dispatch.put(mail, "To", _to);
//                }
//            }
//
//            if(attachments != null)
//            {
//                if(attachments.length>0)
//                {
//                    Dispatch attachs = Dispatch.get(mail, "Attachments").toDispatch();
//
//                    for(Object attachment : attachments)
//                    {
//                        Dispatch.call(attachs, "Add", attachment);
//                    }
//                }
//            }
//
//            //Dispatch.call(mail, "Send");
//            try {
//                Dispatch.call(mail, "Send");
//            } catch (com.jacob.com.ComFailException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }
//}
