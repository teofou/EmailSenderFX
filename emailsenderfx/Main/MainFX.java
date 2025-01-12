package com.github.teofou.emailsenderfx.Main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class MainFX extends Application {

    private Stage primaryStage;
    private VBox rootLayout;


    @Override
    public void start(Stage primaryStage) throws Exception {
        Platform.setImplicitExit(true);
//        mainStage = primaryStage;
//        mainStage.setTitle("Email Sender");
//        mainScene = MainWindowFXController.getMainWindow();
//        mainStage.setScene(mainScene);
//        mainStage.show();
//        Instant start = Instant.now();
        this.primaryStage = primaryStage;
        this.primaryStage.setMinHeight(600.0);
        this.primaryStage.setMinWidth(600.0);
        this.primaryStage.setTitle("Email Sender App");



        primaryStage.setOnCloseRequest(new EventHandler<>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Platform.exit();
                System.exit(0);
            }
        });


        initRootLayout();
        this.primaryStage.setMaximized(true);
//        Instant finish = Instant.now();
//        long timeElapsed = Duration.between(start, finish).toMillis();
//        System.out.println(timeElapsed);

//        HBox mainFolder = (HBox) loader("/com/github/teofou/emailsenderfx/main-folder.fxml");
//        HBox subfolder = (HBox) loader("/com/github/teofou/emailsenderfx/subfolder.fxml");
//        VBox emailData = (VBox) loader("/com/github/teofou/emailsenderfx/email-data.fxml");
//        VBox receiversBox = (VBox) loader("/com/github/teofou/emailsenderfx/receivers.fxml");
//        VBox emailSend = (VBox) loader("/com/github/teofou/emailsenderfx/email-send.fxml");
//
//        rootLayout.getChildren().addAll(mainFolder, subfolder, emailData, receiversBox, emailSend);
//        rootLayout.chi
//
//        System.out.println(mainFolder.getChildren());





    }


    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFX.class.getResource("/com/github/teofou/emailsenderfx/main-window.fxml"));
            rootLayout = loader.load();


            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    /**
//     * Shows the person overview inside the root layout.
//     */
//    public void showMainFolderOverview() {
//        try {
//            // Load person overview.
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(MainFX.class.getResource("/com/github/teofou/emailsenderfx/main-folder.fxml"));
//            Pane mainFolderOverview = loader.load();
//
//            // Set person overview into the center of root layout.
//            rootLayout.getChildren().addAll(mainFolderOverview);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void showSubfolder() {
//        try {
//            // Load person overview.
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(MainFX.class.getResource("/com/github/teofou/emailsenderfx/subfolder.fxml"));
//            HBox subfolderOverview = loader.load();
//
//            // Set person overview into the center of root layout.
//            rootLayout.getChildren().addAll(subfolderOverview);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void showEmailData() {
//        try {
//            // Load person overview.
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(MainFX.class.getResource("/com/github/teofou/emailsenderfx/email-data.fxml"));
//            VBox emailDataOverview = loader.load();
//
//            // Set person overview into the center of root layout.
//            rootLayout.getChildren().addAll(emailDataOverview);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public Pane loader(String resourceURL) throws IOException {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFX.class.getResource(resourceURL));
            Pane pane = loader.load();

            // Set person overview into the center of root layout.
            return pane;
//            rootLayout.getChildren().addAll(pane);
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch();
    }


}
