package com.example.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class retiroHandler {
    @FXML
    private Button retiroBtn;
    @FXML
    private TextField retiroTxt;
    @FXML
    private void retiroHandler (ActionEvent event) {
        try {
            double cantidad = Double.parseDouble(retiroTxt.getText());
            if (cantidad <= 0) {
                alertador.alertador("Atención", "Cantidad inválida", "La cantidad debe ser mayor a 0.", Alert.AlertType.WARNING);
            } else {

                controlasaldo.getInstance().retirar(cantidad);

                alertador.alertador(
                        "Retiro Exitoso",
                        "¡Dinero retirado!",
                        "Se han retirado " + cantidad + " €. Su saldo se ha actualizado.",
                        Alert.AlertType.INFORMATION
                );
                retiroTxt.clear();
            }
        } catch (NumberFormatException e) {
            alertador.alertador("Error", "Formato inválido", "Por favor, introduce un número válido.", Alert.AlertType.ERROR);
        }
    }
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

