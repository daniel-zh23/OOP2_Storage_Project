package com.storage.storageui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.IOException;

public class StorageApplication extends Application {
    private static final Logger LOGGER = Logger.getLogger("StorageApp");
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StorageApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Storage App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        FileHandler fileHandler  = new FileHandler("./logger.log");

        LOGGER.addHandler(fileHandler);


        fileHandler.setLevel(Level.ALL);
        LOGGER.setLevel(Level.ALL);

        launch();
    }
}