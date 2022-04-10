package com.example.cryptorjfxv2;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Alert {

    public static void display (String title, String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(300);
        window.setHeight(150);
        window.setResizable(false);
        Label label = new Label();
        label.setText(message);
        label.setFont(Font.font("Courier",18.0));

        Button closeButton = new Button("Close");
        closeButton.setFont(Font.font("Courier", 16.0));
        closeButton.setOnAction(e->window.close());
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

}
