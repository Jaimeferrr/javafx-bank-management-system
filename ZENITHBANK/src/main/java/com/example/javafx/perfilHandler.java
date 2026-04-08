package com.example.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

public class perfilHandler {
    @FXML Button perfilBtn;
    @FXML
    private void returnHandler (ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Volver a la pantalla principal");
        alert.setHeaderText("¿Estás seguro de que quieres volver a la pantalla principal?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("segundaescena.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error al cargar la escena");
            }
        }
    }
}

