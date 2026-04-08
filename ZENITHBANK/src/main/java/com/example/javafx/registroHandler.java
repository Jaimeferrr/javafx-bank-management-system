package com.example.javafx;

import com.example.javafx.alertador;
import com.example.javafx.conectorsql;
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
import javafx.scene.Node;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class registroHandler {
    @FXML
    private Button backBtn;
    @FXML
    private void backHandler (ActionEvent event) {
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
    @FXML
    private TextField nombreTxt;
    @FXML
    private TextField usuarioTxt;
    @FXML
    private TextField contraseñaTxt;
    @FXML
    private Button registrarseBtn;
    @FXML
    private void registroHandler (ActionEvent event) {
        String user = usuarioTxt.getText();
        String pass = contraseñaTxt.getText();
        String name = nombreTxt.getText();
        if (user.isEmpty() || pass.isEmpty()) {
            return;
        }
        String sql = "INSERT INTO prueba (NOMBRE, USUARIO, CONTRASEÑA) VALUES (?, ?, ?)";
        try (Connection conn = conectorsql.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, user);
            pstmt.setString(3, pass);

            pstmt.executeUpdate();
            alertador.alertador("Éxito", "Usuario registrado", "Ya puedes iniciar sesión.", Alert.AlertType.INFORMATION);

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                alertador.alertador("Error", "Usuario existente", "El nombre de usuario ya está pillado.", Alert.AlertType.INFORMATION);
            } else {
                e.printStackTrace();
            }
        }
    }
}





