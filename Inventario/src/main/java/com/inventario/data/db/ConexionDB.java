package com.inventario.data.db;

import javax.imageio.IIOException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionDB {

    private static final String URL = "jdbc:mysql://localhost:3306/inventario_db";
    private static final String USER = "root";
    private static final String PASSWORD = "123456789";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se pudo cargar el driver de MySQL", e);
        }
    }

//    public static Connection getConnection() throws SQLException {
//        Properties properties = new Properties();
//
//        try (FileInputStream input = new FileInputStream("config.properties")) {
//            properties.load(input);
//        } catch (IOException e) {
//            throw new SQLException("Error al leer el archivo de configuración");
//        }
//        String url = properties.getProperty("DB_URL");
//        String user = properties.getProperty("DB_USER");
//        String password = properties.getProperty("DB_PASSWORD");
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            return DriverManager.getConnection(url, user, password);
//        } catch (ClassNotFoundException e) {
//            throw new SQLException("Error al cargar el driver de MySQL");
//        }
//    }
}
