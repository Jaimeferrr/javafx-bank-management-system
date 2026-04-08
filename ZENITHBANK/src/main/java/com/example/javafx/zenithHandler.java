package com.example.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.print.DocFlavor;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class zenithHandler implements Initializable {
    @FXML
    private Button transBtn;
    @FXML
    private void transHandler (ActionEvent event) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("transferencias.fxml"));
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
    @FXML
    private Label lblSaldo;
    public void initialize(URL location, ResourceBundle resources) {
        lblSaldo.textProperty().bind(
                controlasaldo.getInstance().saldoProperty().asString("%.2f €")
        );
    }
    @FXML
    private Button perfilBtn;
    @FXML
    private void perfilHandler (ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("perfil.fxml"));
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
    @FXML
    private Button ingresoBtn;
    @FXML
    private void ingresoHandler (ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ingreso.fxml"));
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
    @FXML
    private Button retiroBtn;
    @FXML
    private void retiroHandler (ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("retiro.fxml"));
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
    @FXML
    private Button btnLogout;
    @FXML
    private void handleLogout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cerrar Sesión");
        alert.setHeaderText("¿Estás seguro de que quieres salir?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                System.err.println("Error específico: " + e.getMessage());
                System.err.println("Causa: " + e.getCause());
                e.printStackTrace();
            }
        }
    }
}
