package com.example.cryptorjfxv2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ControllerDecryptResult {

    @FXML
    private AnchorPane anchorPaneResult;

    @FXML
    private Button backFromDecryptResultButton;

    @FXML
    private TextField decryptOffsetKey;

    @FXML
    private TextArea decryptResultTextArea;

    @FXML
    private Button resultOffsetKeyChooseButton;

    @FXML
    private Button writeInFileButton;

    @FXML
    private Text variantsLabel2, variantsLabel1;;

    private final FileInputOutput fileInputOutput = new FileInputOutput();
    private final AlphabetProvider alphabetProviderObj = new AlphabetProvider();
    private final char[] alphabetCharArray = alphabetProviderObj.getAlphabetCharArray();
    private final DecryptorWithBruteForce decryptorWithBruteForce = new DecryptorWithBruteForce(alphabetCharArray);

    @FXML
    public void initialize (){
        if (!Controller1.outputText.equals("")){
            decryptResultTextArea.setText(Controller1.outputText);
            variantsLabel1.setOpacity(0.0);
            variantsLabel2.setOpacity(0.0);
        }
        else {
            decryptOffsetKey.setDisable(false);
            resultOffsetKeyChooseButton.setDisable(false);
            writeInFileButton.setDisable(true);
            decryptResultTextArea.setText(decryptorWithBruteForce.mapToString(DecryptorWithBruteForce.tempMap));
        }
    }

    public void filterResultByButton (){
        if (decryptOffsetKey.getText()!=null && isInteger(decryptOffsetKey.getText())) {
            String result = decryptorWithBruteForce.chooseCorrectBForceResultJFX(DecryptorWithBruteForce.tempMap, Integer.parseInt(decryptOffsetKey.getText()));
            decryptResultTextArea.setText(result);
            writeInFileButton.setDisable(false);
        }
        else {
            Alert.display("Error", "Error");
        }
    }

    public void switchBackToSceneOfDecrypt (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Scene2Decrypt.fxml"));
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
            fileInputOutput.writeToFileWithChosenDirectory(directory, Controller1.filePath, decryptResultTextArea.getText());
            Alert.display("Done", "Success! Check your folder");
        }
        else {
            Alert.display("Error", "File error");
        }
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException | NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

}
