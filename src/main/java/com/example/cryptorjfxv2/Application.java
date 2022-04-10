package com.example.cryptorjfxv2;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Scene1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        stage.setTitle("CRYPTOR");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
//        ConsoleWorkspace consoleWorkspace = new ConsoleWorkspace();
//        consoleWorkspace.printMainMenu();
        launch();
    }
}