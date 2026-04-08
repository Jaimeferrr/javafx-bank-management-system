package com.example.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class ingresoHandler {
    @FXML
    private TextField ingresoTxt;
    @FXML
    private void ingresoHandler(ActionEvent event) {
        try {
            double cantidad = Double.parseDouble(ingresoTxt.getText());

            if (cantidad <= 0) {
                alertador.alertador("Atención", "Cantidad inválida", "La cantidad debe ser mayor a 0.", Alert.AlertType.WARNING);
            } else {

                controlasaldo.getInstance().depositar(cantidad);

                alertador.alertador(
                        "Ingreso Exitoso",
                        "¡Dinero ingresado!",
                        "Se han ingresado " + cantidad + " €. Su saldo se ha actualizado.",
                        Alert.AlertType.INFORMATION
                );
                ingresoTxt.clear();
            }
        } catch (NumberFormatException e) {
            alertador.alertador("Error", "Formato inválido", "Por favor, introduce un número válido.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void returnHandler(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Volver");
        alert.setHeaderText("¿Estás seguro de que quieres volver a la pantalla principal?");

        if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("segundaescena.fxml"));
                Parent root = loader.load();

                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

                stage.setScene(new Scene(root));
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error al cargar la escena");
            }
        }
    }
}