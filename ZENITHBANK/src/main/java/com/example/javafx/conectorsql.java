package com.example.javafx;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class conectorsql {
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASSWORD = "JAIME";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a MySQL");
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
        return connection;
    }
}

