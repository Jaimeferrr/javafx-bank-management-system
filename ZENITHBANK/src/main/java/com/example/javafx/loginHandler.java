package com.example.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class loginHandler implements javafx.fxml.Initializable {
    @FXML
    private Circle estado;
    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        System.out.println("Intentando conectar al iniciar la pantalla...");
        try (Connection conn = conectorsql.getConnection()) {
            if (conn != null) {
                estado.setFill(Color.GREEN);
                System.out.println("Conexión OK");
            }
        } catch (SQLException e) {
            estado.setFill(Color.RED);
            System.out.println("Error: " + e.getMessage());
        }
    }
    @FXML
    private Button registroBtn;
    @FXML
    private void registroHandler (ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("registro.fxml"));
            Parent root = loader.load();


            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(true);
            stage.centerOnScreen();
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "Error de sistema", "No se pudo cargar la pantalla principal.");
    }
    }
    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPassword;

    @FXML
    private void loginHandler(ActionEvent event) {
            String user = txtUser.getText();
            String pass = txtPassword.getText();

            if (user.isEmpty() || pass.isEmpty()) {
                alertador.alertador("Error", "Campos vacíos", "Rellena todo.", Alert.AlertType.WARNING);
                return;
            } else if (user.equals("admin")|| pass.equals("admin")) { //POR SI ALGUIEN USA MI PROGRAMA Y NO TIENE ACCESO A LA BBDD
                irAPantallaPrincipal(event);
            }

            String sql = "SELECT * FROM PRUEBA WHERE USUARIO = ? AND CONTRASEÑA = ?";

            try (Connection conn = conectorsql.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, user);
                pstmt.setString(2, pass);

                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {

                    alertador.alertador("Éxito", "Bienvenido", "Login correcto.", Alert.AlertType.INFORMATION);

                    irAPantallaPrincipal(event);
                } else {

                    alertador.alertador("Error", "Login fallido", "Usuario o contraseña incorrectos.", Alert.AlertType.ERROR);
                }

            } catch (SQLException e) {
                e.printStackTrace();
                alertador.alertador("Error", "Error de DB", "No se pudo consultar la base de datos.", Alert.AlertType.ERROR);
            }

    }

    private void mostrarAlerta(String titulo, String encabezado, String contenido) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(encabezado);
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }
    @FXML
    private void irAPantallaPrincipal (ActionEvent Event) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("segundaescena.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) ((Button) Event.getSource()).getScene().getWindow();
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


