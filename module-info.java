module com.github.teofou.emailsenderfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires jacob;
    requires commons.validator;
    requires commons.email;
    requires javax.mail;
    requires org.apache.commons.io;
    requires java.desktop;


// defaults
    opens com.github.teofou.emailsenderfx.controllers.helpers to javafx.base;
    exports com.github.teofou.emailsenderfx.Main;
    opens com.github.teofou.emailsenderfx.controllers to javafx.base, javafx.fxml;



//    opens com.github.teofou.emailsenderfx.controllers to javafx.fxml;
//    exports com.github.teofou.emailsenderfx.Main to javafx.graphics;
}

