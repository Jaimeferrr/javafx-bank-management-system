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

public class transcontroller {
    @FXML
    private TextField cuentaTxt;
    @FXML
    private TextField nombreTxt;
@FXML
private TextField cantidadTxt;
@FXML
private void transHandler (ActionEvent event) {
    try{
    double cantidad = Double.parseDouble(cantidadTxt.getText());
    double saldoActual = controlasaldo.getInstance().getSaldo();
        if (cantidad <= 0) {
            alertador.alertador("Atención", "Cantidad inválido", "La cantidad debe ser mayor a 0.", Alert.AlertType.WARNING);
        }
        else if (cantidad <= saldoActual) {
            controlasaldo.getInstance().retirar(cantidad);
            alertador.alertador(
                    "Transferencia Exitosa",
                    "¡Dinero enviado!",
                    "Se han transferido " + cantidad + " € correctamente.",
                    Alert.AlertType.INFORMATION
            );
            cantidadTxt.clear();
            cuentaTxt.clear();
            nombreTxt.clear();
        }
        else {

            alertador.alertador(
                    "Operación Denegada",
                    "Saldo insuficiente",
                    "No tienes fondos suficientes. Tu saldo actual es: " + saldoActual + " €",
                    Alert.AlertType.ERROR
            );
        }
} catch (NumberFormatException e) {
        alertador.alertador("Error", "Formato inválido", "Por favor, introduce un número válido.", Alert.AlertType.INFORMATION);
    }
}

@FXML
private Button transbtn;
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
