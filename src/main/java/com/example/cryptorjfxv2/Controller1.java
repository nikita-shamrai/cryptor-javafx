package com.example.cryptorjfxv2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class Controller1  {
    @FXML
    private Button selectFileButton, nextToStage2Button, backHomeFromSceneOfCryptButton,
            backHomeFromSceneOfDecryptButton, cryptStartButton, reloadButton,
            backFromCryptResultButton, writeInFileButton, decryptStartButton,backFromDecryptResultButton;
    @FXML
    private Slider sliderOffsetKeyChooser;
    @FXML
    private Label offsetKeyValue, offsetKeyValueDecrypt;
    @FXML
    private TextField selectedFileField;
    @FXML
    private RadioButton rbCrypt, rbDecrypt, rbManual, rbRandom, rbManualKey, rbBruteForce;


    private int cryptOrDecryptChooser, manualOrBruteForceChooser;
    private Stage stageOfCrypt, stageOfDecrypt, stageOfHome, stageOfResult;
    private Scene sceneOfCrypt, sceneOfDecrypt, sceneOfHome, sceneOfResult;
    private Parent rootOfCrypt, rootOfDecrypt, rootOfHome, rootOfResult;
    public static Path filePath;
    public static String incomingText, outputText;
    private static int offsetKey;

    private final AlphabetProvider alphabetProviderObj = new AlphabetProvider();
    private final int alphabetLength = alphabetProviderObj.getAlphabetCharArray().length;
    private final char[] alphabetCharArray = alphabetProviderObj.getAlphabetCharArray();
    private final CryptorWithOffsetKey cryptorWithOffsetKey = new CryptorWithOffsetKey(alphabetCharArray);
    private final DecryptorWithOffsetKey decryptorWithOffsetKey = new DecryptorWithOffsetKey(alphabetCharArray);
    private final DecryptorWithBruteForce decryptorWithBruteForce = new DecryptorWithBruteForce(alphabetCharArray);
    private final FileInputOutput fileInputOutput = new FileInputOutput();

    public void selectFile (ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("txt only", "*.txt"));
        if (selectedFile!=null &&
                Files.isRegularFile(Paths.get(selectedFile.getAbsolutePath())) &&
        fileInputOutput.getExtension(selectedFile.getAbsolutePath()).equalsIgnoreCase("txt")){
            nextToStage2Button.setDisable(false);
            selectedFileField.setText(selectedFile.getName());
            filePath = Paths.get(selectedFile.getAbsolutePath());
            incomingText = fileInputOutput.fileTextToString(filePath);
            cryptorWithOffsetKey.setIncomingText(incomingText);
        }
        else{
            Alert.display("File error", "Select TXT file");
        }
    }

    public void startDecryptButtonAction(ActionEvent event) throws IOException {
        if (rbManualKey.isSelected()){
            outputText = decryptorWithOffsetKey.decryptTextWithOffsetKey(incomingText, offsetKey);
        }
        else if (rbBruteForce.isSelected()){
            outputText = decryptorWithBruteForce.bruteForceDecryptorForJFX(incomingText);
        }
        createDecryptResultWindow(event);
    }

    public void createDecryptResultWindow (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SceneDecryptResult.fxml"));
        Stage window = (Stage) decryptStartButton.getScene().getWindow();
        window.setScene(new Scene(root, 720, 480));
//        rootOfResult = FXMLLoader.load(getClass().getResource("SceneResult2.fxml"));
//        stageOfResult = (Stage)((Node)event.getSource()).getScene().getWindow();
//        sceneOfResult = new Scene(rootOfResult);
//        stageOfResult.setScene(sceneOfResult);
//        stageOfResult.show();
        // initialize();
    }

    public void bruteForceMethodChosen (){
        if (rbBruteForce.isSelected()){
            decryptStartButton.setDisable(false);
            manualOrBruteForceChooser = 1;
            sliderOffsetKeyChooser.setDisable(true);
            offsetKeyValue.setText("?");
        }
    }

    public void manualOffsetKeyChooserDecrypt () {
        if (rbManualKey.isSelected()) {
            manualOrBruteForceChooser = 0;
            sliderOffsetKeyChooser.setDisable(false);
            offsetKeyValue.setText(String.valueOf((int)sliderOffsetKeyChooser.getValue()));
        }
    }

    public void startCryptButtonAction (ActionEvent event) throws IOException {
        outputText = cryptorWithOffsetKey.cryptTextWithOffsetKey(incomingText, offsetKey);
        createResultWindow(event);
    }

    public void createResultWindow (ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("SceneResult2.fxml"));
//        Stage window = (Stage) cryptStartButton.getScene().getWindow();
//        window.setScene(new Scene(root, 720, 480));
        rootOfResult = FXMLLoader.load(getClass().getResource("SceneResult2.fxml"));
        stageOfResult = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneOfResult = new Scene(rootOfResult);
        stageOfResult.setScene(sceneOfResult);
        stageOfResult.show();
       // initialize();
    }

    public void reloadButtonAction (){
        randomOffsetValue();
    }

    public void randomOffsetValue (){
        if (rbRandom.isSelected()){
            cryptStartButton.setDisable(false);
            manualOrBruteForceChooser = 1;
            reloadButton.setDisable(false);
            Random r = new Random();
            int low = 1;
            int high = alphabetLength-1;
            offsetKey = r.nextInt(high-low) + low;
            offsetKeyValue.setText(Integer.toString(offsetKey));
            sliderOffsetKeyChooser.setValue((int)offsetKey);
            sliderOffsetKeyChooser.setDisable(true);
        }
    }

    public void manualOffsetKeyChooser () {
        if (rbManual.isSelected()) {
            manualOrBruteForceChooser = 0;
            sliderOffsetKeyChooser.setDisable(false);
            reloadButton.setDisable(true);
        }
    }

    public void sliderValueOnMouseClick (MouseEvent event){
        cryptStartButton.setDisable(false);
        offsetKey = (int)sliderOffsetKeyChooser.getValue();
        offsetKeyValue.setText(Integer.toString(offsetKey));
    }
    public void sliderValueOnMouseClickDecrypt (MouseEvent event){
        decryptStartButton.setDisable(false);
        offsetKey = (int)sliderOffsetKeyChooser.getValue();
        offsetKeyValue.setText(Integer.toString(offsetKey));
    }

    public void chooseCryptOrDecrypt (){
        if (rbCrypt.isSelected()){
            cryptOrDecryptChooser = 0;
        }
        if (rbDecrypt.isSelected()){
            cryptOrDecryptChooser = 1;
        }
    }

    public void switchToSceneOfCrypt (ActionEvent event) throws IOException {
        rootOfCrypt = FXMLLoader.load(getClass().getResource("Scene2Crypt.fxml"));

        stageOfCrypt = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneOfCrypt = new Scene(rootOfCrypt);
        stageOfCrypt.setScene(sceneOfCrypt);
        stageOfCrypt.show();

//        TextHolder holder = TextHolder.getInstance();
//        holder.setCryptorWithOffsetKey(cryptorWithOffsetKey);
//        Node node = (Node) event.getSource();
//        Stage stage = (Stage) node.getScene().getWindow();
//        Scene scene = new Scene(rootOfCrypt);
//        stage.setScene(scene);
//        stage.show();
    }

    public void switchToSceneOfDerypt (ActionEvent event) throws IOException {
        rootOfDecrypt = FXMLLoader.load(getClass().getResource("Scene2Decrypt.fxml"));
        stageOfDecrypt = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneOfDecrypt = new Scene(rootOfDecrypt);
        stageOfDecrypt.setScene(sceneOfDecrypt);
        stageOfDecrypt.show();
    }

    public void switchToSceneOfHome (ActionEvent event) throws IOException {
        rootOfHome = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
        stageOfHome = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneOfHome = new Scene(rootOfHome);
        stageOfHome.setScene(sceneOfHome);
        stageOfHome.show();
    }

    public void switchToNextScene (ActionEvent event) throws IOException {
        if (cryptOrDecryptChooser == 0){
            switchToSceneOfCrypt(event);
        }
        else if (cryptOrDecryptChooser == 1){
            switchToSceneOfDerypt(event);
        }
        else {
            System.out.println("error in switchToNextScene method");
        }
    }

    public void backHomeFromSceneOfCryptButtonAction (ActionEvent event) throws IOException {
        switchToSceneOfHome(event);
    }

    public void backHomeFromSceneOfDecryptButtonAction (ActionEvent event) throws IOException {
        switchToSceneOfHome(event);
    }

}

