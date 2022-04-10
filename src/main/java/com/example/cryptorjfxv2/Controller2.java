package com.example.cryptorjfxv2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Controller2 {

    @FXML
    private Button backFromCryptResultButton;
    @FXML
    private Button writeInFileButton;
    @FXML
    private TextArea resultTextArea;
    @FXML
    private AnchorPane anchorPaneResult;
    @FXML
    void initialize() {
        resultTextArea.setText(Controller1.outputText);
        if (resultTextArea.getText()!=null){
            writeInFileButton.setDisable(false);
        }
    }

    private final FileInputOutput fileInputOutput = new FileInputOutput();

    public void switchBackToSceneOfCrypt (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Scene2Crypt.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void writeInFile (ActionEvent event) throws IOException {
        //fileInputOutput.writeToFile(Controller1.filePath, Controller1.outputText);
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        Stage stage = (Stage) anchorPaneResult.getScene().getWindow();
        File file = directoryChooser.showDialog(stage);
        if (file != null){
            Path directory = Paths.get(file.getAbsolutePath());
            fileInputOutput.writeToFileWithChosenDirectory(directory, Controller1.filePath, Controller1.outputText);
            Alert.display("Done", "Success! Check your folder");
        }
        else {
            Alert.display("Error", "File error");
        }
    }


}

